package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Result;
import utility.DriverAccessor;

public class ResultDAO extends DriverAccessor {

	public void insertResultTesting(Result result) {

		Connection con = null;
		con = createConnection();

		try {

			String sql = "INSERT INTO results VALUES(?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setInt(2, result.getUserId());
			stmt.setDouble(3, result.getInitialAbility());
			stmt.setDouble(4, result.getAbility());
			stmt.setDouble(5, result.getAnswerAllTime());

			stmt.executeUpdate();
			stmt.close();
			closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}
}