package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TaskTest {

	public static void main(String[] args){
	    //生成一个JSONObject对象  
        String data = "{}";  
        JSONObject jsonObj = JSONObject.fromObject(data);  
          
        //1.put 基本类型值  
        int i=0;  
        for(i=0; i<5; i++){  
            jsonObj.put("id"+i, i);  
        }  
        System.out.println(jsonObj.toString());  
          
        //2.put 一个JSONObject对象  
        JSONObject obj1 = JSONObject.fromObject(data);  
        for(i=0; i<5; i++){  
            obj1.put("child"+i, i);  
        }  
        jsonObj.put("JSONObject", obj1);  
        System.out.println(jsonObj.toString());  
          
        //3.put 数组  
        String[] strArr = new String[]{"加","数","组"};  
        jsonObj.put("arr", strArr);  
        System.out.println(jsonObj.toString());  
          
        //4.put List  
        List<String> list = new ArrayList<String>();  
        list.add("加");  
        list.add("列");  
        list.add("表");  
        jsonObj.put("list", list);  
        System.out.println(jsonObj.toString());  
          
        //5.put Set  
        Set<String> set = new HashSet<String>();  
        set.add("加");  
        set.add("集");  
        set.add("合");  
        jsonObj.put("set", set);  
        System.out.println(jsonObj.toString());  
          
        //6.put Map  
        Map<String,Object> map = new HashMap<String, Object>();  
        map.put("map1", "加");  
        map.put("map2", "映");  
        map.put("map3", "射");  
        jsonObj.put("map", map);  
        System.out.println(jsonObj.toString());  
          
        //7.putAll Map  
        jsonObj.putAll(map);  
        System.out.println(jsonObj.toString());  
          
        //8.组合: (返回的结果value是一个对象数组)   
        List<JSONObject> jlist = new ArrayList<JSONObject>();  
        JSONObject j = null;  
        for(i=0; i<3; i++){  
            j = JSONObject.fromObject(data);  
            j.put("aaaa"+i, i);  
            j.put("bbbb"+i, i);  
            j.put("cccc"+i, i);  
            jlist.add(j);  
        }  
        jsonObj.put("succList", jlist);  
        System.out.println(jsonObj.toString());  
          
        //9.组合 : (返回的结果value是一个对象数组，对象的某个属性又是一个对象(嵌套))   
        List<JSONObject> jlist1 = new ArrayList<JSONObject>();  
        for(i=0; i<3; i++){  
            j = JSONObject.fromObject(data);  
            j.put("xxxx"+i, i);  
            j.put("yyyy"+i, i);  
            j.put("zzzz"+i, obj1);  
            jlist1.add(j);  
        }  
        jsonObj.put("failList", jlist1);  
        System.out.println(jsonObj.toString());  
          
        //10.组合: 可以任意嵌套  
        JSONObject superJson = JSONObject.fromObject(data);  
        superJson.put("super", jsonObj);  
        superJson.put("base", "base");  
        System.out.println(superJson.toString()); 
	}
}
