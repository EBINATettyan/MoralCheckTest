package servlet;

//問題を選択し、次の画面に渡すサーブレット
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.InitialQuestion;

public class SelectInitialQuestionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);

		//問題に正答しているかどうかを判別
		//まず、sessionで保持している、受験者が解いた問題を取り出す
		InitialQuestion initialQuestion = (InitialQuestion) session.getAttribute("initialQuestion");
		int[] correctAnswerList = { initialQuestion.getCorrect_answer1(), initialQuestion.getCorrect_answer2(),
				initialQuestion.getCorrect_answer3(), initialQuestion.getCorrect_answer4() };
		//次に、実際に回答することによって得られる反応データを取得する
		int answerList[] = new int[4];
		// 選択された番号を1、選択されてない番号を0の配列に直す
		int answer = Integer.parseInt(request.getParameter("answer"));
		for (int i = 0; i < 4; i++) {
			if (i + 1 == answer) {
				answerList[i] = 1;
			} else {
				answerList[i] = 0;
			}
		}
		//correctAnswerListとanswerListの内容が一致しているか確認
		Arrays.equals(answerList,correctAnswerList);


	}
}
