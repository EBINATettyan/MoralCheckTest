package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.InitialQuestion;
import dao.AccessLogDAO;
import dao.InitialQuestionDAO;

public class IdentifyUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//****このservletは一番始めに読み込まれる****

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);

		//アクセスログにアクセスし、テストを使い始めたこと、何番目の使用者かを保持する
		AccessLogDAO accessLogDAO = new AccessLogDAO();
		int userId = accessLogDAO.identifyUser();
		System.out.println("現在のUSERID：" + (userId+1) );

		//アクセスログの新規登録
		accessLogDAO.addAccessLog(userId + 1);

		//何問目の問題を回答しているか保持する
		int countId = 1;

		//最初の問題を提示する
		InitialQuestionDAO initialQuestionDAO = new InitialQuestionDAO();
		InitialQuestion initialQuestion = initialQuestionDAO.selectInitialQuestion(countId);

		/*
		 * dateFromとdateToを宣言する
		 * dateTo - dateFromを計算し、それを項目所要時間とする
		 * 前のページでのdateToが次のページでのdateFromになる
		 * dateToはそのservletが読み込まれた時間
		 */
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		String dateTo = sdf.format(c.getTime());
		String dateFrom = null;

		//userIdを+1したものが、システム利用者のuserId
		session.setAttribute("userId", userId + 1);
		session.setAttribute("dateTo", dateTo);
		session.setAttribute("dateFrom", dateFrom);
		session.setAttribute("initialQuestion", initialQuestion);

		request.setAttribute("countId", countId);

		getServletContext().getRequestDispatcher("/Public/examination/initialQuestion.jsp").forward(request, response);
	}
}
