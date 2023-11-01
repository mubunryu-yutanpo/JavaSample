package com.diary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

// DIで利用するためにアノテーションをつける。
// @Componentだけでなく、@Service、@Repository、@Controllerといったものをつけても同様の宣言が可能。
// そのクラスの種類（役割）によって使い分けたりする。DAOのクラスなら@Repositoryを使うことも多い。
@Component
public class DiaryDAO {
	
	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:8889/javasample";
	private final String DB_USER = "root";
	private final String DB_PW = "root";

	// DBに登録してある日記を全件取得する
	public List<Diary> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Diary> diaryList = new ArrayList<>();
		try {
			// jdbcを読み込んで使える状態にする
			Class.forName(DRIVER_NAME);
			// DriverManagerクラスのgetConnectionメソッドを使ってデータベースに接続する。
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PW);
			
			// まずSQL自体をString型で定義
			// 全カラム取得して、DTOであるDiaryクラスにあとでセットしていく。
            String sql = "SELECT * FROM diary WHERE delete_flg != 1 ORDER BY created_at DESC";

            // SQL実行の準備
            stmt = conn.prepareStatement(sql);
            // SQL実行
            rs = stmt.executeQuery();
            
        	// SQLを実行した結果として得られた各レコードから1つ1つデータを取得する
            while(rs.next()) {
            	Diary diary = new Diary();
            	
            	diary.setId(rs.getInt("id"));
            	diary.setContent(rs.getString("content"));
            	diary.setDelete_flg(rs.getInt("delete_flg"));
            	diary.setCreated_at(rs.getTimestamp("created_at"));
            	diary.setUpdated_at(rs.getTimestamp("updated_at"));
            	
            	// 各レコードの情報がセットされているdiaryを、
            	// さらに、全レコードのリストであるdiaryListに詰め込んでいく。
            	diaryList.add(diary);
            }
		} catch (SQLException e) {
			// DBへの接続や、SQL処理で失敗が起きた時
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// JDBCドライバが見つけられない時
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null) {
					rs.close();
				}
				// DBとの接続を切断する
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return diaryList;

	}
	
	// DBに日記の内容を登録する
	public boolean create(Diary diary) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// jdbcを読み込んで使える状態にする
			Class.forName(DRIVER_NAME);
			// DriverManagerクラスのgetConnectionメソッドを使ってデータベースに接続する。
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PW);
			
			// INSERT文を書いておく
            String sql = "INSERT INTO diary (content, delete_flg, created_at, updated_at) VALUES (?, ?, ?, ?)";
            // SQLを実行する準備
            stmt = conn.prepareStatement(sql);
            // SQLで?をいれていたところに値を左から順番にセットしていく
            stmt.setString(1, diary.getContent());
            stmt.setInt(2, diary.getDelete_flg());
            stmt.setTimestamp(3, diary.getCreated_at());
            stmt.setTimestamp(4, diary.getUpdated_at());
            // executeUpdate()は、成功したら、変更した行の数を返してくる。
            // この場合、insertに成功すれば1、失敗すれば0が返ってくる。
            int result = stmt.executeUpdate();
            if (result != 1) {
            	return false;
            }
		} catch (SQLException e) {
			// DBへの接続や、SQL処理で失敗が起きた時
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// JDBCドライバが見つけられない時
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				// DBとの接続を切断する
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}


}
