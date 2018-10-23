package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.InitialQuestion;
import utility.DriverAccessor;

public class InitialQuestionDAO extends DriverAccessor {

	// 初期項目を検索する
	public InitialQuestion selectInitialQuestion(int countId) {
		Connection con = null;
		con = createConnection();

		try {
			String sql = "SELECT * FROM initial_questions WHERE id = " + countId;

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			InitialQuestion initialQuestion = new InitialQuestion(rs.getInt("id"), rs.getInt("type_id"),
					rs.getString("content"), rs.getString("choice1"), rs.getString("choice2"), rs.getString("choice3"),
					rs.getString("choice4"), rs.getInt("correct_answer1"), rs.getInt("correct_answer2"),
					rs.getInt("correct_answer3"), rs.getInt("correct_answer4"), rs.getDouble("discrimination"),
					rs.getDouble("difficulty"));

			stmt.close();
			rs.close();
			closeConnection(con);

			return initialQuestion;
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