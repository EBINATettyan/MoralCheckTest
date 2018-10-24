package irt;

import java.util.ArrayList;
import java.util.List;

import beans.AnswerLog;
import beans.Question;
import dao.AnswerLogDAO;
import dao.QuestionDAO;

public class SelectMaxInformationQuestion {

	public Question selectMaxInformationQuestion(double ability, int userId, int section) {
		Question question = new Question();

		// 問題全部引っ張ってくるリスト
		List<Question> questionList = new ArrayList<Question>();
		QuestionDAO questionDAO = new QuestionDAO();
		questionList = questionDAO.selectQuestionAll();

		// 解答データのリスト
		List<AnswerLog> answerLogs = new ArrayList<AnswerLog>();
		AnswerLogDAO answerLogDAO = new AnswerLogDAO();
		answerLogs = answerLogDAO.selectAnswerLogByUser(userId, section);

		// 情報量最大の問題を保持するオブジェクト
		Question maxInformationQuestion = new Question();

		// 最大の情報量を保持する変数
		double maxInformation = 0.0;
		int judge;

		// 各問題の情報量を計算しながら，最大の情報量の問題を保持する
		// 各問題の情報量を計算
		for (int i = 0; i < questionList.size(); i++) {
			question = questionList.get(i);
			double D = 1.7;

			double e1 = Math.exp(-D * question.getDiscrimination() * (ability - question.getDifficulty()));
			double pj1a = 1 / (1 + e1);
			double pre = D * D * question.getDiscrimination() * question.getDiscrimination();
			double info = pre * pj1a * (1 - pj1a);

			System.out.println("問題番号 " + question.getId() + " 情報量 " + info);

			// もし，今までで最大の情報量だった場合で，まだ解いたことのない問題だったら保持
			if (maxInformation < info) {
				judge = 0;
				// 情報量最大の問題を既に解いていたら、それは選択しない
				for (int j = 0; j < answerLogs.size(); j++) {
					if (question.getId() == answerLogs.get(j).getQuestionId()) {
						judge = 1;
						System.out.println("すでに解いてた問題番号 " + question.getId());
					}
				}
				if (judge == 0) {
					maxInformation = info;
					maxInformationQuestion = question;
					System.out.println("問題番号 " + question.getId() + " 最大情報量 " + maxInformation);
				}
			}
		}
		System.out.println("最大だったやつの問題番号 " + maxInformationQuestion.getId() + " 最大情報量 " + maxInformation);
		return maxInformationQuestion;
	}
}
