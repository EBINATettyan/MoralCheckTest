package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.DriverAccessor;

public class UserDAO extends DriverAccessor {
	public String searchUserId(String userName) {
		Connection con = null;
		con = createConnection();

		try {
			String sql = "INSERT INTO `users` (`id`, `account_id`, `user_name`, `password`) VALUES (NULL, '', '"+userName+"', '');";
			 String id = "select id from users where `user_name` ='"+ userName + "';";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			stmt.close();
			rs.close();
			closeConnection(con);

			return id;
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
