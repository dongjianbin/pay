package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TaskUploadOrders extends TimerTask {

	public void run() {
		System.out.println("TaskUploadOrderssssss");
		this.execthreads();

	}

	public void execthreads() {
		JSONObject jo = new JSONObject();
		List<Map> orderlist = new ArrayList<Map>();
		List<Map> ordergoodslist = new ArrayList<Map>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Config mConfig = new Config();
		String dbname = mConfig.getDBfullPath();
		System.out.println("dbname is : " + dbname);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/" + dbname);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String orders_id = null;
			String taskval = "0";
			String sql="select taskval from taskids where tasktype='TaskUploadOrders'";
			System.out.println(sql);
			rset = stmt.executeQuery(sql);
			while(rset.next()){

				System.out.println("taskval is : ");
				taskval=rset.getString("taskval");
			}
			System.out.println("taskval is : "+ taskval);
			rset = stmt
					.executeQuery("select orders_id,customer_id,customer_name ,topay,subtotal,tax,total,notes ,discount,gstincflag,order_count,operator,shopid,"
							+ "shopname,ip,uuid,status,createtime ,modifytime from orders where status='1' and orders_id>'"+taskval+"' limit 1");
			while (rset.next()) {
				orders_id = rset.getString("orders_id");
				Map<String, String> vMap = new HashMap<String, String>();
				vMap.put("orders_id", rset.getString("orders_id"));
				vMap.put("customer_id", rset.getString("customer_id"));
				vMap.put("customer_name", rset.getString("customer_name"));
				vMap.put("topay", rset.getString("topay"));
				vMap.put("subtotal", rset.getString("subtotal"));
				vMap.put("tax", rset.getString("tax"));
				vMap.put("total", rset.getString("total"));
				vMap.put("notes", rset.getString("notes"));
				vMap.put("discount", rset.getString("discount"));
				vMap.put("gstincflag", rset.getString("gstincflag"));
				vMap.put("order_count", rset.getString("order_count"));
				vMap.put("operator", rset.getString("operator"));
				vMap.put("shopid", rset.getString("shopid"));
				vMap.put("shopname", rset.getString("shopname"));
				vMap.put("ip", rset.getString("ip"));
				vMap.put("uuid", rset.getString("uuid"));
				vMap.put("status", rset.getString("status"));
				vMap.put("createtime", rset.getString("createtime"));
				vMap.put("modifytime", rset.getString("modifytime"));
				orderlist.add(vMap);
			}

			if (orders_id != null) {
				rset = stmt
						.executeQuery("select orders_goods_lists_id,orders_id,goods_id,goods_name ,all_price,goods_price,tax_price,handle,sku from orders_goods_lists where orders_id='"
								+ orders_id + "' ");
				while (rset.next()) {
					Map<String, String> vMap = new HashMap<String, String>();
					vMap.put("orders_goods_lists_id",
							rset.getString("orders_goods_lists_id"));
					vMap.put("orders_id", rset.getString("orders_id"));
					vMap.put("goods_id", rset.getString("goods_id"));
					vMap.put("goods_name", rset.getString("goods_name"));
					vMap.put("all_price", rset.getString("all_price"));
					vMap.put("goods_price", rset.getString("goods_price"));
					vMap.put("tax_price", rset.getString("tax_price"));
					vMap.put("handle", rset.getString("handle"));
					vMap.put("sku", rset.getString("sku"));
					ordergoodslist.add(vMap);
				}
			}
			JSONArray jaorderlist = JSONArray.fromObject(orderlist);
			JSONArray jaordergoodslist = JSONArray.fromObject(ordergoodslist);
			jo.put("orders", jaorderlist);
			jo.put("ordersgoodslist", jaordergoodslist);

			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("can't find class drive " + cnfe.getMessage());
			System.exit(-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jo);
	}
}
