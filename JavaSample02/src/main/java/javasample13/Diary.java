package javasample13;

import java.sql.Timestamp;

// DTOと呼ばれる、データを転送する為に、データをいれておくためのクラス
public class Diary {
	private int id;
	private String content;
	private int delete_flg;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	
	// Getter、Setterと呼ばれるメソッドを定義しておく。
	// PHPオブジェクト指向部で説明しているので、詳しくはそちらを参照。
	// 他のクラスから上記の変数に直接アクセスはさせずに、
	// Getter、Setterメソッドを介してアクセスさせるようにするためのもの。
	// Eclipseの機能で自動生成することができる。
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getDelete_flg() {
		return delete_flg;
	}
	public void setDelete_flg(int delete_flg) {
		this.delete_flg = delete_flg;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	



}
