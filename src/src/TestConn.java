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
		Config mConfig = new Config();
		String dbname = mConfig.getDBfullPath();
		System.out.println("dbname is : " + dbname);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/" + dbname);
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
		Config mConfig = new Config();
		String dbname = mConfig.getDBfullPath();
		System.out.println("dbname is : " + dbname);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/" + dbname);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate("drop table if exists 'goods'");
			stmt.executeUpdate("create table goods(goods_inc_id INTEGER PRIMARY KEY AUTOINCREMENT,goods_id integer,goods_name varchar(300),all_price,goods_price,tax_price,handle varchar(100),sku varchar(100))");
			System.out.println("ok");
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,1,'SkinCare',68,59.13,8.87,'CN100001','1289482910492')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,3,'Bubble XS S',33,30.13,2.87,'CN100002','1289482911212')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,4,'Bubble XS M',73,69.13,3.87,'CN100003','1289482910491')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,6,'Bubble XS L',84,79.13,4.87,'CN100004','1289482910494')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,7,'General Item',104,99.13,5.87,'CN100005','1289482910495')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,9,'程光快递大陆奶粉',96,89.13,6.87,'CN100006','1289482910496')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,10,'程光快递保健品',37,29.13,7.87,'CN100007','1289482910497')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,11,'平安保健品',58,49.13,8.87,'CN100008','1289482910498')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,14,'平安大陆奶粉',49,39.13,9.87,'CN100009','1289482910499')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods values(null,20,'程光KARICARE AP4整箱',11,9.13,1.87,'CN100010','1289482910410')"));

			conn.commit();
			stmt.executeUpdate("drop table if exists 'goods_default'");
			stmt.executeUpdate("create table goods_default(goods_default_inc_id INTEGER PRIMARY KEY AUTOINCREMENT,goods_id integer,goods_name varchar(300),all_price,goods_price,tax_price,handle varchar(100),sku varchar(100))");

			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods_default values(null,1,'SkinCare',68,59.13,8.87,'CN100001','1289482910492')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods_default values(null,3,'Bubble XS S',33,30.13,2.87,'CN100002','1289482911212')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods_default values(null,4,'Bubble XS M',73,69.13,3.87,'CN100003','1289482910491')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods_default values(null,6,'Bubble XS L',84,79.13,4.87,'CN100004','1289482910494')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods_default values(null,7,'General Item',104,99.13,5.87,'CN100005','1289482910495')"));
			System.out
					.println(stmt
							.executeUpdate("INSERT INTO goods_default values(null,9,'程光快递大陆奶粉',96,89.13,6.87,'CN100006','1289482910496')"));
			conn.commit();

			stmt.executeUpdate("drop table if exists 'orders'");
			stmt.executeUpdate("create table orders(orders_id INTEGER PRIMARY KEY AUTOINCREMENT,customer_id integer,customer_name varchar(300),topay,subtotal,tax,total,notes varchar(300),discount,gstincflag,order_count,operator,shopid,shopname,ip,uuid,status,createtime ,modifytime timestamp)");

			stmt.executeUpdate("drop table if exists 'orders_goods_lists'");
			stmt.executeUpdate("create table orders_goods_lists(orders_goods_lists_id INTEGER PRIMARY KEY AUTOINCREMENT,orders_id,goods_id integer,goods_name varchar(300),all_price,goods_price,tax_price,handle varchar(100),sku varchar(100))");

			stmt.executeUpdate("drop table if exists 'taskids'");
			stmt.executeUpdate("create table taskids(tasktype varchar(100),taskval varchar(100),modifytime timestamp)");
			stmt.executeUpdate("insert into taskids (tasktype,taskval,modifytime) values ('TaskUploadOrders','0',datetime())");
			stmt.executeUpdate("insert into taskids (tasktype,taskval,modifytime) values ('TaskSyncGoodsList','0',datetime())");

			
			conn.commit();

			System.out.println("INSERT FINISHED");
			System.out.println(new java.util.Date());
			rset = stmt.executeQuery("SELECT * FROM goods ");
			while (rset.next()) {
				System.out.println(rset.getString("goods_id"));
				System.out.println(rset.getString("goods_name"));
				System.out.println(rset.getString("goods_price"));
				System.out.println(rset.getString("tax_price"));
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
			// stmt.executeUpdate("CREATE INDEX hehe_idx2 on hehe(name)");
			conn.commit();
			System.out.println(new java.util.Date());
			System.out.println("query after index created");
			System.out.println(new java.util.Date());
			rset = stmt.executeQuery("SELECT * FROM goods where goods_id > 5 ");
			while (rset.next()) {
				System.out.println(rset.getString("goods_id"));
				System.out.println(rset.getString("goods_name"));
				System.out.println(rset.getString("goods_price"));
				System.out.println(rset.getString("tax_price"));
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
		// File f =new File("c:\\javapay");
		// if(f.exists()){
		// System.out.println("exists");
		// }
		// else{
		// System.out.println("not exists");
		// f.mkdir();
		// }

		TestConn conn = new TestConn();
		// conn.test();
		// conn.test();
		conn.init_paydatabase();
		System.out.print("Success!!");
	}
}
