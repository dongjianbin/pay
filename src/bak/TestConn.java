package bak;

import java.sql.*;

public class TestConn {
	void test() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		System.out.println(new java.util.Date());
		try {
			// Class.forName("SQLite.JDBCDriver");
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:/C:/javapay/test.db", // jdbc:oracle:thin:@hex:1521:ORCL
					"", "");
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			// stmt.executeUpdate("drop   table if exists   hehe");

			// stmt.executeUpdate("create   table   hehe(id INTEGER PRIMARY KEY AUTOINCREMENT,   name   varchar(32))");
			System.out.println("建表hehe成功!");
			// for (int i=0; i<100; i++)
			// {
			int im = 0;
			stmt.executeUpdate("INSERT   INTO   hehe   VALUES(null,   '我爱中国aaaa')");
			rset = stmt.executeQuery("SELECT last_insert_rowid()");
			if (rset.next()) {
				im = rset.getInt(1);
			}
			// }
			rset.close();

			conn.commit();
			System.out.println("im is :" + im);
			System.out.println("不建索引查询:");
			System.out.println(new java.util.Date());

			rset = stmt
					.executeQuery("SELECT   id,   name   FROM   hehe   where   id   >   1   and   name   ='我爱中国11'");
			if (rset.next()) {
				System.out.println(rset.getInt("id"));
				System.out.println(rset.getString("name"));
			}
			if (rset != null) {
				rset.close();
				rset = null;
			}

			System.out.println(new java.util.Date());

		} catch (ClassNotFoundException cnfe) {
			System.out.println("Can't   find   class   for   driver:   "
					+ cnfe.getMessage());
			System.exit(-1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// System.exit(-1);
		} finally {
			try {
				if (rset != null)
					rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

			}
		}
	}

	public static void main(String[] args) {
		TestConn conn = new TestConn();
		conn.test();
	}
}
