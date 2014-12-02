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
	    //����һ��JSONObject����  
        String data = "{}";  
        JSONObject jsonObj = JSONObject.fromObject(data);  
          
        //1.put ��������ֵ  
        int i=0;  
        for(i=0; i<5; i++){  
            jsonObj.put("id"+i, i);  
        }  
        System.out.println(jsonObj.toString());  
          
        //2.put һ��JSONObject����  
        JSONObject obj1 = JSONObject.fromObject(data);  
        for(i=0; i<5; i++){  
            obj1.put("child"+i, i);  
        }  
        jsonObj.put("JSONObject", obj1);  
        System.out.println(jsonObj.toString());  
          
        //3.put ����  
        String[] strArr = new String[]{"��","��","��"};  
        jsonObj.put("arr", strArr);  
        System.out.println(jsonObj.toString());  
          
        //4.put List  
        List<String> list = new ArrayList<String>();  
        list.add("��");  
        list.add("��");  
        list.add("��");  
        jsonObj.put("list", list);  
        System.out.println(jsonObj.toString());  
          
        //5.put Set  
        Set<String> set = new HashSet<String>();  
        set.add("��");  
        set.add("��");  
        set.add("��");  
        jsonObj.put("set", set);  
        System.out.println(jsonObj.toString());  
          
        //6.put Map  
        Map<String,Object> map = new HashMap<String, Object>();  
        map.put("map1", "��");  
        map.put("map2", "ӳ");  
        map.put("map3", "��");  
        jsonObj.put("map", map);  
        System.out.println(jsonObj.toString());  
          
        //7.putAll Map  
        jsonObj.putAll(map);  
        System.out.println(jsonObj.toString());  
          
        //8.���: (���صĽ��value��һ����������)   
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
          
        //9.��� : (���صĽ��value��һ���������飬�����ĳ����������һ������(Ƕ��))   
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
          
        //10.���: ��������Ƕ��  
        JSONObject superJson = JSONObject.fromObject(data);  
        superJson.put("super", jsonObj);  
        superJson.put("base", "base");  
        System.out.println(superJson.toString()); 
	}
}
