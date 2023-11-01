package com.diary;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
// このクラスをURLで呼び出す時のパスを指定できる。
// localhost:8080/api/diary

@RequestMapping("api")
public class DiaryController {
	
	// @Autowiredを使うことで、DiaryDAOクラスを利用していくことができるようになる。
	// このように、newせずに他のクラスを利用する仕組みをDIと呼ぶ
	// 利用したい先のクラスの方で、「DIで利用するよ」という宣言のために「@Component」などのアノテーションをつけてあげる。
	@Autowired
    private DiaryDAO diaryDAO;
	
	// リクエストの時のパスをここでメソッド単位で指定できる。
	@GetMapping("diary")
	public List<Diary> getList() {
		List<Diary> diaryList = diaryDAO.findAll();
	    return diaryList;
	}
	
	@PostMapping("diary")
	// レスポンスのHTTPステータスを201にしてくれる。POSTの場合は201を返してあげることが多い。
    @ResponseStatus(HttpStatus.CREATED)
	// @RequestBodyとつけると、ブラウザから送られてきたパラメータを受け取ることができる。
	// データ型をStringにしたら文字列で受け取ることができるが、送られてくるデータがJsonの場合であれば、クラスを使ってリクエストの内容を受け取ることも可能。
	public Diary create(@RequestBody Diary diary) {
		diary.setDelete_flg(0);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		diary.setCreated_at(now);
		diary.setUpdated_at(now);
		diaryDAO.create(diary);
	    return diary;
	}
}
