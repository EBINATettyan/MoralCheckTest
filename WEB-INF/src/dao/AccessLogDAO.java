package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utility.DriverAccessor;

public class AccessLogDAO extends DriverAccessor {

	// ユーザの検索
	public int identifyUser() {

		Connection con = null;
		con = createConnection();

		try {
			String sql = "SELECT MAX(id) FROM access_logs";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt(1);
			stmt.close();
			rs.close();
			closeConnection(con);

			return id;

		} catch (

		SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
		}
	}

	//アクセスログの追加
	public void addAccessLog(int userId) {

		/*
		 * フォーマットパターンを指定して表示する
		 * 今回は、2018/10/16(火)/10:10
		 */
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/(E)/HH:mm");
		String startTime = sdf.format(c.getTime());

		Connection con = null;
		con = createConnection();

		try {
			String sql = "INSERT INTO access_logs(id,user_id,start_time,end_time,start_end_time) values(?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setInt(2, userId);
			stmt.setString(3, startTime);
			stmt.setString(4, null);
			stmt.setString(5, null);
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
