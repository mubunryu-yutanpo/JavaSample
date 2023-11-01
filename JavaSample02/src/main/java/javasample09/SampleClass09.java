package javasample09;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleClass09
 */
@WebServlet("/SampleClass09")
// HttpServletというクラスを継承してサーブレットクラスを作成していく

public class SampleClass09 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleClass09() {
    	// 親クラス（HttpServlet）のコンストラクタを実行する
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// このメソッドでは日記アプリの画面を表示する。
		
		// まず、HttpServletRequestとHttpServletResponseというのは、リクエストやレスポンスの情報を格納するもの
		// 引数で受けると、リクエストパラメータを取り出したり、レスポンスとして返す値をリセットしたりできる。
		// throwsについては、このメソッドでエラーが起きたら、このメソッドの呼び出し元にエラーを投げるという意味だが、
		// サーブレットを使うときの書き方は基本的にどの場合でも同じなので、おさまりの書き方として把握してもらえればOK
		
		// サーブレットのクラスの書き方やメソッドの書き方は少し難しいが、
		// 基本的にEclipseで自動生成できるので、ここまで説明した内容をざっくり理解できていれば良い。
		
		// 画面表示処理を書くまえに、DBから値をとってくる処理を書いていく
		
		// DB接続する際には、jdbcというものが必要となる。
		// jdbcとは、Javaからデータベースにアクセスするためのツールのこと。
		// データベースの種類ごとに用意されている。（mysqlならmysqlのjdbcを使う必要がある）
		
		// mysqlのjdbcは下記のサイトからダウンロードできる
		// https://dev.mysql.com/downloads/connector/j/
		
		// 動的webプロジェクトの場合は、WebContent/WEB-INF/libの下にjarファイルを置くと、
		// 自動的にビルドパスを設定してくれるので今回はそこに配置する
		
		// データベースに接続し、テーブルから値を取得する
		Connection conn = null;
		// テーブルから取得したデータを格納する変数を事前に用意しておく
		// Listのなかに、Listを入れている。
		// String型のListを、複数集めてList形式で保持している。
		// Listの定義は、
		List<List<String>> diaryList = new ArrayList<>();
		
		try {
			// jdbcを読み込んで使える状態にする
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DriverManagerクラスのgetConnectionメソッドを使ってデータベースに接続する。
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/javasample", "root", "root");
			
			// まずSQL事態をString型で定義
			// DATE_FORMATを使って、テーブルのcreated_atを○月○日の形式で取得しておく。
			// さらに、取得した値の後ろに「AS　post_date」と書いておくことで、データを取り出すときにpost_dateという名前で扱っていける。
			String sql = "SELECT * , DATE_FORMAT(created_at, '%Y-%m-%d %H:%i:%s') AS post_date FROM diary WHERE delete_flg != 1 ORDER BY created_at DESC ";
			// JavaでSQLを実行していくための、PreparedStatementというものを用いる。
			PreparedStatement stmt = conn.prepareStatement(sql);
			// PreparedStatementの、executeQuery()というメソッドを使い、SQLを実行できる。
			ResultSet rs = stmt.executeQuery();
			
			// SQLを実行した結果として得られた各レコードから1つ1つデータを取得する
			// rs.next()は、レコードを1つ1つ取り出していき、次のレコードがあればtrueが返ってくる。レコードの数だけwhile文がループする。
			// while文の中では、そのレコードの情報を「rs.get....」とすることで取得できる
			
			while(rs.next() ) {
				// まずSQLで得られた各レコードの「post_date」「content」を取得して変数に入れる。
				String postDate = rs.getString("post_date");
				String content = rs.getString("content");
				
				// 次にList型のdiaryという変数に、post_dateやcontentを追加していく
				// SQLで取得した全レコードの中の、一つ一つのレコードの情報をここでリストにセットするイメージ。
				List<String> diary = new ArrayList<>();
				diary.add(postDate);
				diary.add(content);
				
				// 各レコードの情報がセットされているdiaryという変数を、さらに全レコードのリストであるdiaryListに詰め込んでいく。
				diaryList.add(diary);
				
			}
		}catch (SQLException e) {
			// 失敗時
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// JDBCドライバが見つけられない場合
			e.printStackTrace();
		} finally {
			// DBとの接続を切断する
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e){
					// DBとの接続切断に失敗した場合
					e.printStackTrace();
				}
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		// レスポンスとして文字列を返す場合は、PrintWriterや、getWriter()というメソッドを使って、下記のように書いていく。
		PrintWriter out = response.getWriter();
		
		// htmlの内容を1行1行出力していく
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Java日記アプ</title>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" />");
		  // WebContentフォルダの下に置いたファイルは、
		  // http://<サーバ名>/<プロジェクト名>/　の後ろにそのファイル名をつけるだけでアクセスできるようになる。
		  // 実際の現場ではWebContentフォルダ下にcssフォルダやjsフォルダなどを作ってそこに配置していく場合が多い。
		
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/sample09.css\" >");
		out.println("</head>");
		out.println("<body>");
		// header
		out.println("<header>");
		out.println("<div class=\"logo\"> Diary </div>");
		out.println("</header>");
		// main
		out.println("<div class=\"main\" >");
		  
		  out.println("<div class=\"index_type_virualcard_circle\" >");
		  out.println("</div>");
		  
		  // 日記投稿フォーム
		  out.println("<form method=\"post\" >");
		    
		    out.println("<div class=\"mt30\"> ");
		      out.println("<label for=\"content\" >投稿内容：</labal>");
		      out.println("<textarea id=\"content\" class=\"content\" name=\"content\" ></textarea>");
		    out.println("</div>");
		    
		    out.println("<div class=\"button-area\">");
		      out.println("<button type=\"submit\" class=\"btn btn-success mt30 relative float-right\">日記を投稿</button>");
		    out.println("</div>");
		  out.println("</form>");
		  
		  // 日記一覧
		  out.println("<h1 class=\"diary-list-title\" >日記一覧</h1>");
		  out.println("<div");
		    for (List<String> diary : diaryList) {
		    	// DBから取得した日記の内容のリストをループして、１つずつ値を取り出して画面表示する
		    	out.println("<div class=\"diary-content\" >");
		    	  
		    	  out.println("<div class=\"post-date\"> ");
		    	    // ここで、投稿日（post_date）の情報を取り出す。
		    	    out.println(diary.get(0));
		    	  out.println("</div>");
		    	
		    	// ここで、日記の内容（content）の情報を取り出す。
		    	  out.println("<p>");
		    	  
		    	out.println("</div>");
		    } // end for
		    
		  out.println("</div>");
		out.println("</div>");
      out.println("</body>");
    out.println("</html>");
		  
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POSTされてきたフォームデータを読み込むときに文字化けしないように文字コードをセットしておく
		request.setCharacterEncoding("UTF-8");
		
		// データベースに接続し、ブラウザからのPOSTリクエストで送られてきた日記の内容をテーブルに登録する
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// 現在時刻を取得する
			// System.currentTimeMillis()というメソッドは現在時刻をミリ秒で取得してくれるメソッド。
			Timestamp now = new Timestamp(System.currentTimeMillis() );
			// jdbcを読み込んで使える状態にする
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DriverManagerクラスのgetConnectionメソッドを使ってデータベースに接続する。
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/javasample" , "root" , "root");
			// ここでは、SQLに後でセットしたい値は「？」で書いておく。
			// idは自動採番なのでSQLには書かない。
			String sql = "INSERT INTO diary (content, delete_flg, created_at, updated_at) VALUES (?, 0, ?, ?)";
			stmt = conn.prepareStatement(sql);
			// SQLで？を入れていたところに値を左から順番にセットしていく
			stmt.setString(1, request.getParameter("content") );
			stmt.setTimestamp(2, now);
			stmt.setTimestamp(3, now);
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			// DB接続やSQL処理で失敗時
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			// JDBCドライバが見つからないとき
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		//日記の内容を投稿した後は、また日記の一覧が出てきて欲しいので、日記表示画面にリダイレクトする
		// http://localhost:8080/JavaSample2/SampleClass09
		String url = "SampleClass09";
		response.sendRedirect(url);
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
