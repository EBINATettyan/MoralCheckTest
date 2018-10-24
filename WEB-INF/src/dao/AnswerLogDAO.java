package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.AnswerLog;
import utility.DriverAccessor;

public class AnswerLogDAO extends DriverAccessor {

	// 解答を蓄積する
	public void insertAnswerLog(AnswerLog answerLog) {

		Connection con = null;
		con = createConnection();

		try {

			String sql = "INSERT INTO answer_logs VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, answerLog.getId());
			stmt.setInt(2, answerLog.getUserId());
			stmt.setInt(3, answerLog.getQuestionId());
			stmt.setDouble(4, answerLog.getDiscrimination());
			stmt.setDouble(5, answerLog.getDifficulty());
			stmt.setInt(6, answerLog.getTrueOrFalse());
			stmt.setDouble(7, answerLog.getAbility());
			stmt.setDouble(8, answerLog.getSd());
			stmt.setInt(9, answerLog.getAnswer1());
			stmt.setInt(10, answerLog.getAnswer2());
			stmt.setInt(11, answerLog.getAnswer3());
			stmt.setInt(12, answerLog.getAnswer4());
			stmt.setDouble(13, answerLog.getAnswerItemTime());

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
			String sql = "SELECT SUM(answer_item_time) FROM answer_logs WHERE user_id =" + userId;

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
