package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.AnswerLog;
import beans.InitialAnswerLog;
import utility.DriverAccessor;

public class InitialAnswerLogDAO extends DriverAccessor {

	// 解答を蓄積する
	public void insertAnswerLog(InitialAnswerLog answerLog) {

		Connection con = null;
		con = createConnection();

		try {

			String sql = "INSERT INTO initial_answer_logs VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, answerLog.getId());
			stmt.setInt(2, answerLog.getUserId());
			stmt.setInt(3, answerLog.getQuestionId());
			stmt.setDouble(4, answerLog.getDiscrimination());
			stmt.setDouble(5, answerLog.getDifficulty());
			stmt.setInt(6, answerLog.getTrueOrFalse());
			stmt.setDouble(7, answerLog.getAbility());
			stmt.setInt(8, answerLog.getAnswer1());
			stmt.setInt(9, answerLog.getAnswer2());
			stmt.setInt(10, answerLog.getAnswer3());
			stmt.setInt(11, answerLog.getAnswer4());
			stmt.setString(12, answerLog.getAnswerItemTime());

			stmt.executeUpdate();
			stmt.close();
			closeConnection(con);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

		}
	}

	// この学習者の解答の中で、今回の受検の解答データを検索する
	public List<AnswerLog> selectAnswerLogByUser(int userId, int section) {
		try {

			String sql = "SELECT * FROM initial_answer_logs WHERE user_id ='" + userId + "' AND section='" + section
					+ "';";

			Connection con = null;
			con = createConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			List<AnswerLog> answerLogList = new ArrayList<AnswerLog>();
			while (rs.next()) {
				AnswerLog answerLog = new AnswerLog(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("question_id"),
						rs.getDouble("discrimination"), rs.getDouble("difficulty"), rs.getInt("true_or_false"),
						rs.getDouble("ability"), rs.getInt("answer1"), rs.getInt("answer2"),
						rs.getInt("answer3"), rs.getInt("answer4"), rs.getString("answer_item_time"));
				answerLogList.add(answerLog);
			}

			stmt.close();
			rs.close();
			closeConnection(con);

			return answerLogList;

		} catch (

		SQLException e)

		{
			e.printStackTrace();
			return null;
		} finally

		{
		}
	}

}
