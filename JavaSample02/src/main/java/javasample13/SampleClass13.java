package javasample13;

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
@WebServlet("/SampleClass13")
public class SampleClass13 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleClass13() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DiaryDAO diaryDAO = new DiaryDAO();
		
		List<Diary> diaryList = diaryDAO.findAll();
		request.setAttribute("diaryList", diaryList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/sample13.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Diary diary = new Diary();
		diary.setContent(request.getParameter("content"));
		diary.setDelete_flg(0);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		diary.setCreated_at(now);
		diary.setUpdated_at(now);


		DiaryDAO diaryDAO = new DiaryDAO();
		boolean result = diaryDAO.create(diary);
		String url = "SampleClass13";
		response.sendRedirect(url);
		
	}

}
