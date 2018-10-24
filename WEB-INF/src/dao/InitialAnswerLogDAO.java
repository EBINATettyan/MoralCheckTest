package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.InitialAnswerLog;
import utility.DriverAccessor;

public class InitialAnswerLogDAO extends DriverAccessor {

	// 解答を蓄積する
	public void insertAnswerLog(InitialAnswerLog initialAnswerLog) {

		Connection con = null;
		con = createConnection();

		try {

			String sql = "INSERT INTO initial_answer_logs VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, initialAnswerLog.getId());
			stmt.setInt(2, initialAnswerLog.getUserId());
			stmt.setInt(3, initialAnswerLog.getQuestionId());
			stmt.setDouble(4, initialAnswerLog.getDiscrimination());
			stmt.setDouble(5, initialAnswerLog.getDifficulty());
			stmt.setInt(6, initialAnswerLog.getTrueOrFalse());
			stmt.setDouble(7, initialAnswerLog.getAbility());
			stmt.setDouble(8, initialAnswerLog.getSd());
			stmt.setInt(9, initialAnswerLog.getAnswer1());
			stmt.setInt(10, initialAnswerLog.getAnswer2());
			stmt.setInt(11, initialAnswerLog.getAnswer3());
			stmt.setInt(12, initialAnswerLog.getAnswer4());
			stmt.setDouble(13, initialAnswerLog.getAnswerItemTime());

			stmt.executeUpdate();
			stmt.close();
			closeConnection(con);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

		}
	}

	//answer_item_timeの合計を計算する
	public double calcSumAnswer(int userId) {

		Connection con = null;
		con = createConnection();

		try {
			String sql = "SELECT SUM(answer_item_time) FROM initial_answer_logs WHERE user_id = " + userId;

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			double answerTime = rs.getInt(1);
			stmt.close();
			rs.close();
			closeConnection(con);

			return answerTime;

		} catch (

		SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
		}
	}

}
