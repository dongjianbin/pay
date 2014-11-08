package src;

import java.io.File;
import java.sql.*;

//import SQLite.*;
import org.sqlite.JDBC;

import src.Config;

public class TestConn {
	void test() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		System.out.println(new java.util.Date());
		Config mConfig=new Config();
		String dbname=mConfig.getDBfullPath();
		System.out.println("dbname is : "+ dbname);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/"+dbname);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate("create table hehe(id number, name varchar(32))");
			System.out.println("ok");
			for (int i = 0; i < 100; i++) {
				System.out.print("insert");
				System.out.println(stmt
						.executeUpdate("INSERT INTO hehe VALUES(" + i
								+ ", 'i love china" + i + "')"));
			}
			conn.commit();

			System.out.println("query:");
			System.out.println(new java.util.Date());
			rset = stmt.executeQuery("SELECT id, name FROM hehe where id>5");
			while (rset.next()) {
				System.out.println(rset.getInt("id"));
				System.out.println(rset.getString("name"));
			}
			if (rset != null) {
				rset.close();
				rset = null;
			}
			System.out.println(new java.util.Date());

			System.out.println("create index");
			System.out.println(new java.util.Date());
			stmt.executeUpdate("CREATE INDEX hehe_idx on hehe(id)");
			stmt.executeUpdate("CREATE INDEX hehe_idx2 on hehe(name)");
			conn.commit();
			System.out.println(new java.util.Date());
			System.out.println("query after index created");
			System.out.println(new java.util.Date());
			rset = stmt.executeQuery("SELECT id, name FROM hehe where id > 5 ");
			while (rset.next()) {
				System.out.println(rset.getInt("id"));
				System.out.println(rset.getString("name"));
			}
			System.out.println(new java.util.Date());
			stmt.executeUpdate("drop table hehe");
			System.out.println("drop table hehe succ");
			conn.commit();
			System.out.println(new java.util.Date());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("can't find class drive " + cnfe.getMessage());
			System.exit(-1);
		} catch (SQLException e) {
			System.out.println("SQLException :" + e.getMessage());
			System.exit(-1);
		} finally {
			try {
				if (rset != null)
					rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out
						.println("SQLException in finally :" + e.getMessage());
				System.exit(-1);
			}
		}
	}

	void init_paydatabase() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		System.out.println(new java.util.Date());
		Config mConfig=new Config();
		String dbname=mConfig.getDBfullPath();
		System.out.println("dbname is : "+ dbname);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/"+dbname);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate("drop table if exists 'goods'");
			stmt.executeUpdate("create table goods(id INTEGER PRIMARY KEY AUTOINCREMENT,goods_id integer,goods_name varchar(100),goods_price,tax_price,bar_code varchar(100))");
			System.out.println("ok");
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,1,'SkinCare',59.13,8.87,'CN100001')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,2,'Bubble XS S',30.13,2.87,'CN100002')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,3,'Bubble XS M',69.13,3.87,'CN100003')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,4,'Bubble XS L',79.13,4.87,'CN100004')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,5,'General Item',99.13,5.87,'CN100005')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,6,'�̹��ݴ�½�̷�',89.13,6.87,'CN100006')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,7,'�̹��ݱ���Ʒ',29.13,7.87,'CN100007')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,8,'ƽ������Ʒ',49.13,8.87,'CN100008')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,9,'ƽ����½�̷�',39.13,9.87,'CN100009')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods values(null,10,'�̹�KARICARE AP4����',9.13,1.87,'CN100010')"));
			
			conn.commit();
			stmt.executeUpdate("drop table if exists 'goods_default'");
			stmt.executeUpdate("create table goods_default(id INTEGER PRIMARY KEY AUTOINCREMENT,goods_id integer,goods_name varchar(100),goods_price,tax_price,bar_code varchar(100))");
			System.out.println(stmt.executeUpdate("INSERT INTO goods_default values(null,1,'SkinCare',59.13,8.87,'CN100001')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods_default values(null,2,'Bubble XS S',30.13,2.87,'CN100002')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods_default values(null,3,'Bubble XS M',69.13,3.87,'CN100003')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods_default values(null,4,'Bubble XS L',79.13,4.87,'CN100004')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods_default values(null,5,'General Item',99.13,5.87,'CN100005')"));
			System.out.println(stmt.executeUpdate("INSERT INTO goods_default values(null,6,'�̹��ݴ�½�̷�',89.13,6.87,'CN100006')"));
			conn.commit();
			
			System.out.println("INSERT FINISHED");
			System.out.println(new java.util.Date());
			rset = stmt.executeQuery("SELECT * FROM goods ");
			while (rset.next()) {
				System.out.println(rset.getInt("id"));
				System.out.println(rset.getString("goods_id"));
				System.out.println(rset.getString("goods_name"));
				System.out.println(rset.getString("goods_price"));
				System.out.println(rset.getString("tax_price"));
				System.out.println(rset.getString("bar_code"));
			}
			if (rset != null) {
				rset.close();
				rset = null;
			}
			System.out.println(new java.util.Date());

			System.out.println("create index");
			System.out.println(new java.util.Date());
			stmt.executeUpdate("CREATE INDEX idx_goods_name on goods(goods_name)");
			stmt.executeUpdate("CREATE INDEX idx_goods_id on goods(goods_id)");
//			stmt.executeUpdate("CREATE INDEX hehe_idx2 on hehe(name)");
			conn.commit();
			System.out.println(new java.util.Date());
			System.out.println("query after index created");
			System.out.println(new java.util.Date());
			rset = stmt.executeQuery("SELECT * FROM goods where goods_id > 5 ");
			while (rset.next()) {
				System.out.println(rset.getInt("id"));
				System.out.println(rset.getString("goods_id"));
				System.out.println(rset.getString("goods_name"));
				System.out.println(rset.getString("goods_price"));
				System.out.println(rset.getString("tax_price"));
				System.out.println(rset.getString("bar_code"));
			}
			conn.commit();
			System.out.println(new java.util.Date());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("can't find class drive " + cnfe.getMessage());
			System.exit(-1);
		} catch (SQLException e) {
			System.out.println("SQLException :" + e.getMessage());
			System.exit(-1);
		} finally {
			try {
				if (rset != null)
					rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out
						.println("SQLException in finally :" + e.getMessage());
				System.exit(-1);
			}
		}

	}

	public static void main(String[] args) {
//		File f =new File("c:\\javapay");
//		if(f.exists()){
//			System.out.println("exists");
//		}
//		else{
//			System.out.println("not exists");
//			f.mkdir();
//		}
		
		TestConn conn = new TestConn();
		// conn.test();
		// conn.test();
		conn.init_paydatabase();
		System.out.print("Success!!");
	}
}