package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.UserInformation;
import utility.DriverAccessor;

public class UserDAO extends DriverAccessor {

	public void insertUserInformation(UserInformation userInformation) {
		Connection con = null;
		con = createConnection();

		try {
			String sql = "INSERT INTO user_informations VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setInt(2, userInformation.getUserId());
			stmt.setInt(3, userInformation.getSex());
			stmt.setInt(4, userInformation.getAge());
			stmt.setInt(5, userInformation.getPurpose());
			stmt.setString(6, userInformation.getMachine());
			stmt.setInt(7, userInformation.getUseInternetTime());
			stmt.setString(8, userInformation.getJudgeExplain());
			stmt.setString(9, userInformation.getJudgeExperience());

			stmt.executeUpdate();
			stmt.close();
			closeConnection(con);

		} catch (

		SQLException e)

		{
			e.printStackTrace();
		} finally

		{
		}
	}

}
