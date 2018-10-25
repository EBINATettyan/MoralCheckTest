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

import beans.AnswerLog;
import beans.Question;
import beans.Result;
import dao.AccessLogDAO;
import dao.AnswerLogDAO;
import dao.InitialAnswerLogDAO;
import dao.ResultDAO;
import irt.EstimateAbility;
import irt.SelectMaxInformationQuestion;

public class SelectQuestionServlet extends HttpServlet {

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
		Question question = (Question) session.getAttribute("question");
		int[] correctAnswerList = { question.getCorrect_answer1(), question.getCorrect_answer2(),
				question.getCorrect_answer3(), question.getCorrect_answer4() };
		//次に、実際に回答することによって得られる反応データを取得する
		// 選択された番号を1、選択されてない番号を0の配列に直す
		int answerList[] = new int[4];
		if (question.getTypeId() == 0) {// 今解いた問題が単数選択だった場合
			// 選択された番号を1、選択されてない番号を0の配列に直す
			int answerNum = Integer.parseInt(request.getParameter("answer"));
			for (int i = 0; i < correctAnswerList.length; i++) {
				if (i + 1 == answerNum) {
					answerList[i] = 1;
				} else {
					answerList[i] = 0;
				}
			}
		} else {// 今解いた問題が複数選択だった場合
			String answerNum[] = request.getParameterValues("answer");
			int j = 0;
			for (int i = 0; i < correctAnswerList.length; i++) {
				if (i + 1 == Integer.parseInt(answerNum[j])) {
					answerList[i] = 1;
					if (j + 1 < answerNum.length) {
						j++;
					}
				} else {
					answerList[i] = 0;
				}
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
		 * 必要な変数は、trueOrFale(u),discrimination(a),difficulty(b),idList
		 */

		u = (ArrayList<Integer>) session.getAttribute("u");
		a = (ArrayList<Double>) session.getAttribute("a");
		b = (ArrayList<Double>) session.getAttribute("b");
		idList = (ArrayList<Integer>) session.getAttribute("idList");
		idListForDB = (String) session.getAttribute("idListForDB");

		u.add(trueOrFalse);
		a.add(question.getDiscrimination());
		b.add(question.getDifficulty());
		idList.add(question.getId());
		idListForDB = idListForDB + ":" + question.getId();

		session.setAttribute("u", u);
		session.setAttribute("a", a);
		session.setAttribute("b", b);
		session.setAttribute("idList", idList);
		session.setAttribute("idListForDB", idListForDB);

		/*
		 * 能力値を算出する(ベイズのEAP)
		 * returnListの一番目は能力値、二番目は事後標準偏差
		 */
		EstimateAbility estimateThetaEAP = new EstimateAbility();
		ArrayList<Double> returnList = estimateThetaEAP.estimateTheta(u, a, b);

		//オブジェクトの宣言
		AnswerLog answerLog = new AnswerLog(0, (Integer) session.getAttribute("userId"),
				question.getId(), question.getDiscrimination(), question.getDifficulty(),
				trueOrFalse, returnList.get(0), returnList.get(1), answerList[0], answerList[1], answerList[2],
				answerList[3], answerItemTime);

		//DBに回答情報を蓄積する
		AnswerLogDAO answerLogDAO = new AnswerLogDAO();
		answerLogDAO.insertAnswerLog(answerLog);

		//初期であろうとなかろうと関係のあるsession等はここで宣言する
		session.setAttribute("dateTo", dateTo);
		session.setAttribute("dateFrom", dateFrom);
		request.setAttribute("countId", (countId + 1));

		/*
		 * まず、前の能力値をpreAbilityとして、呼び出す
		 * 次に、EAPで能力値を推定して、それをpostAbilityとする
		 * preAbilityとpostAbilityの差を求めて、収束条件を考える
		 */
		double preAbility = (Double) session.getAttribute("preAbility");
		double postAbility = returnList.get(0);

		if (Math.abs(preAbility - postAbility) < 0.03) {

			//initialAnswerLogとanswerLogから、answerItemTimeを取り出し、合計を計算する
			InitialAnswerLogDAO initialAnswerLogDAO = new InitialAnswerLogDAO();
			double allInitialAnswerTime = initialAnswerLogDAO.calcSumAnswer((Integer) session.getAttribute("userId"));
			double allAnswerTime = answerLogDAO.calcSumAnswer((Integer) session.getAttribute("userId"));
			double answerAllTime = allInitialAnswerTime + allAnswerTime;

			//accessLogとresultの蓄積
			AccessLogDAO accessLogDAO = new AccessLogDAO();
			accessLogDAO.updateAccessLog((Integer) session.getAttribute("userId"), answerAllTime, idListForDB);

			ResultDAO resultDAO = new ResultDAO();
			Result result = new Result(0, (Integer) session.getAttribute("userId"),
					(Double) session.getAttribute("initialAbility"), postAbility, answerAllTime);
			resultDAO.insertResultTesting(result);

			request.setAttribute("coutId", countId);
			request.setAttribute("postAbility", postAbility);

			//sessionの破棄
			session.invalidate();

			//System.out.println("session破棄");

			getServletContext().getRequestDispatcher("/Public/examination/result.jsp").forward(request, response);

		} else {
			session.setAttribute("preAbility", postAbility);

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
