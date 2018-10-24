package servlet;

//問題を選択し、次の画面に渡すサーブレット
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.InitialAnswerLog;
import beans.InitialQuestion;
import beans.Question;
import dao.InitialAnswerLogDAO;
import dao.InitialQuestionDAO;
import irt.EstimateAbility;
import irt.SelectMaxInformationQuestion;

public class SelectInitialQuestionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);

		//何項目か受け取る
		int countId = Integer.parseInt(request.getParameter("countId"));

		//項目所要時間を計算する
		Date date = new Date();
		long dateTo = date.getTime();
		long dateFrom = (long) session.getAttribute("dateTo");
		long diff = (dateTo - dateFrom) / 1000;
		double answerItemTime = Double.valueOf(diff);

		/*
		 * 問題に正答しているかどうかを判別
		 * まず、sessionで保持している、受験者が解いた問題を取り出す
		 */
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
		int trueOrFalse;
		boolean judge = Arrays.equals(answerList, correctAnswerList);
		if (judge == false) {
			trueOrFalse = 0;
		} else {
			trueOrFalse = 1;
		}

		ArrayList<Integer> u = new ArrayList<Integer>();
		ArrayList<Double> a = new ArrayList<Double>();
		ArrayList<Double> b = new ArrayList<Double>();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		String idListForDB = null;

		/*
		 * 能力値を算出するために必要な回答履歴をsessionで保持する
		 * 必要な変数は、trueOrFale(u),discrimination(a),difficulty(b)
		 * countIdが1だったときと2以上で場合分け
		 */

		if (countId == 1) {
			u.add(trueOrFalse);
			a.add(initialQuestion.getDiscrimination());
			b.add(initialQuestion.getDifficulty());
			idList.add(initialQuestion.getId());
			idListForDB = String.valueOf(initialQuestion.getId());

			session.setAttribute("u", u);
			session.setAttribute("a", a);
			session.setAttribute("b", b);
			session.setAttribute("idList", idList);
			session.setAttribute("idListForDB", idListForDB);
		} else if (countId >= 2) {
			u = (ArrayList<Integer>) session.getAttribute("u");
			a = (ArrayList<Double>) session.getAttribute("a");
			b = (ArrayList<Double>) session.getAttribute("b");
			idList = (ArrayList<Integer>) session.getAttribute("idList");
			idListForDB = (String) session.getAttribute("idListForDB");

			u.add(trueOrFalse);
			a.add(initialQuestion.getDiscrimination());
			b.add(initialQuestion.getDifficulty());
			idList.add(initialQuestion.getId());
			idListForDB = idListForDB + ":" + initialQuestion.getId();

			session.setAttribute("u", u);
			session.setAttribute("a", a);
			session.setAttribute("b", b);
			session.setAttribute("idList", idList);
			session.setAttribute("idListForDB", idListForDB);
		}

		/*
		 * 能力値を算出する(ベイズのEAP)
		 * returnListの一番目は能力値、二番目は事後標準偏差
		 */
		EstimateAbility estimateThetaEAP = new EstimateAbility();
		ArrayList<Double> returnList = estimateThetaEAP.estimateTheta(u, a, b);

		//オブジェクトの宣言
		InitialAnswerLog initialAnswerLog = new InitialAnswerLog(0, (Integer) session.getAttribute("userId"),
				initialQuestion.getId(), initialQuestion.getDiscrimination(), initialQuestion.getDifficulty(),
				trueOrFalse, returnList.get(0), returnList.get(1), answerList[0], answerList[1], answerList[2],
				answerList[3], answerItemTime);

		//DBに回答情報を蓄積する
		InitialAnswerLogDAO initialAnswerLogDAO = new InitialAnswerLogDAO();
		initialAnswerLogDAO.insertAnswerLog(initialAnswerLog);

		//初期であろうとなかろうと関係のあるsession等はここで宣言する
		session.setAttribute("dateTo", dateTo);
		session.setAttribute("dateFrom", dateFrom);
		request.setAttribute("countId", (countId + 1));

		/*
		 * countId == 5であったら、初期項目受験は終了
		 * 次に出す項目をsessionに保持する(上書き)
		 * 項目を更新する
		 */
		if (countId < 5) {
			InitialQuestionDAO initialQuestionDAO = new InitialQuestionDAO();
			initialQuestion = initialQuestionDAO.selectInitialQuestion((countId + 1));
			session.setAttribute("initialQuestion", initialQuestion);
			getServletContext().getRequestDispatcher("/Public/examination/initialQuestion.jsp").forward(request,
					response);
		} else if (countId == 5) {
			session.setAttribute("preAbility", returnList.get(0));
			session.setAttribute("initialAbility", returnList.get(0));

			//情報量最大の項目を受け取る
			Question maxInformationQuestion = new Question();
			SelectMaxInformationQuestion select = new SelectMaxInformationQuestion();
			maxInformationQuestion = select.selectMaxInformationQuestion(returnList.get(0), idList);
			session.setAttribute("question", maxInformationQuestion);

			// 問題のタイプが単数選択であれば、ラジオボタンの画面へ。複数選択であれば、チェックリストの画面へ。
			if (maxInformationQuestion.getTypeId() == 0) {
				getServletContext().getRequestDispatcher("/Public/examination/radioButtonQuestion.jsp").forward(request,
						response);
			} else {
				getServletContext().getRequestDispatcher("/Public/examination/checkBoxQuestion.jsp").forward(request,
						response);
			}
		}
	}
}
