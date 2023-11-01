package javasample12;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SampleClass10
 */
@WebServlet("/SampleClass12")
public class SampleClass12 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleClass12() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// サーブレットだけを使ってアプリを実装すると、HTMLを一行一行出力する必要があったため
		// 画面表示処理の書き方が面倒だったり、
		// DB接続を行うたびに接続処理を書いておく必要があり、かなり実装が大変になってしまう。
		
		// 今回はJSPや
		// DAO（Data Access Objectという、Javaでのデータベースへの接続を記載しておくもの）
		// DTO (Data Transfer Objectという、DBデータなどの様々な値を転送するためのもの）
		// といった方法を用いて、
		// MVCモデルを使った実装方法を見ていく。
		// MVCモデルについては、PHPフレームワーク部で扱うので詳しくはそちらを参照。
		
		// DAOをインスタンス化してこのクラスで使える状態にする
		DiaryDAO diaryDAO = new DiaryDAO();
		
		// 今回であれば、DAOがModelに相当し、このサーブレットクラスがControllerに相当する。
		// また画面表示を行うJSPがViewに相当する。
		// 実際の現場では、DAOを介してDBに接続し、いろんな計算処理などを行うことが多いが、
		// その場合は、それらの計算処理を行う部分もModelに当たる。
		
		// DAOを使ってデータベースに接続し、その結果をDTOにセットして取得する。
		List<Diary> diaryList = diaryDAO.findAll();
		// JSP側で、今回取得したデータを表示できるように、リクエストの内容に変数名と値をセットしておく。
		// request.setAttributeというメソッドを使う。
		request.setAttribute("diaryList", diaryList);
		
		// 他にもセッションに変数名や値をセットしたり、
		// セッションから値を取得する場合は下記のような書き方もできる。
		// HttpSession session = request.getSession();
		// session.setAttribute("セッションにセットする変数名", value);
		// session.getAttribute("セッションから値を取得したい変数名");
		
		// JSPファイルに対してブラウザから直接アクセスさせたくない場合は、
		// ブラウザ等の外部から内部のファイルにアクセスできないWEB-INFというフォルダの下におくことが多い。
		// RequestDispatcherとは、ブラウザから受け取ったリクエストをサーバー上の他のファイルに渡す時に使う。
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/sample12.jsp");
		// RequestDispatcherで定義されたforwardという方法を使って、サーバー上の別のファイルに処理を受け渡すことができる。
		// このサーブレットクラスでレスポンスを作るのではなく、処理を渡した先のファイルでレスポンスを作っていくことになる。
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// DAOには、DiaryというDTOを使ってデータを受け渡しているので、
		// Diaryクラスから生成したインスタンスに値をセットしていく。
		Diary diary = new Diary();
		diary.setContent(request.getParameter("content"));
		diary.setDelete_flg(0);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		diary.setCreated_at(now);
		diary.setUpdated_at(now);

		// DAOをインスタンス化してこのクラスで使える状態にする
		DiaryDAO diaryDAO = new DiaryDAO();
		boolean result = diaryDAO.create(diary);
		// resultの内容によってDB登録が成功したかどうかを判定し、処理を分ける場合もあるが、今回はそのまま画面表示させる
		// 日記表示画面にリダイレクトする
		String url = "SampleClass12";
		response.sendRedirect(url);
		
	}

}
