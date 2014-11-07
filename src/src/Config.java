package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Vector;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;


import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Config {

	private String LOCALCONFIGDIR = "c:/javapay/";
	private String LOCALCONFIGFILE = "javapay.txt";
	private HashMap ConfigHash;
	private HashMap DefaultConfigHash;
	private String ConfigUrl;

	public Config() {
		String configUrl=null;
		configUrl = getConfigUrl();
		if (configUrl != null && configUrl.length() == 0) {
			System.out.println("not set configurl , now to use default");
			setDefaultConfigHash();
			this.ConfigHash = this.DefaultConfigHash;
		} else {
			this.ConfigHash = getConfigHashbyurl(configUrl);

		}

	}

	public HashMap getConfigHashbyurl(String s) {
		// String strsss = new String(s);
		HashMap hm = new HashMap();
		BufferedReader reader = null;
		String line;
		StringBuffer strb = new StringBuffer();
		try {
			URL mURL = new URL(s);

			try {
				reader = new BufferedReader(new InputStreamReader(
						mURL.openStream()));
				while ((line = reader.readLine()) != null) {
					strb.append(line);

				}
				hm=getHashMapbyxmlstring(strb.toString());
				

			} catch (IOException id) {

			} finally {
				try {
					if (reader != null)
						reader.close();

				} catch (Exception e) {

				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return hm;
	}
	
	public HashMap getHashMapbyxmlstring(String xmlDoc){
		HashMap hm= new HashMap();
		
		StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            System.out.println("tasktypename:"+root.getAttributeValue("tasktypename"));
            System.out.println("perfrenceNum:"+root.getAttributeValue("perfrenceNum"));
            System.out.println(root.getName());//输出根元素的名称（测试）
            //得到根元素所有子元素的集合
            List jiedian = root.getChildren();
            
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
                
                if(et.getAttributeValue("inputindex").equals("1")){
                    et.setAttribute("name","1");
                }
                et.setAttribute("age","15");
                System.out.println("name:"+et.getAttributeValue("name"));
                System.out.println("value:"+et.getAttributeValue("value"));
                System.out.println("inputindex:"+et.getAttributeValue("inputindex"));
                System.out.println("perfrence:"+et.getAttributeValue("perfrence"));
                System.out.println("age:"+et.getAttributeValue("age"));
            }
//            /**//*
//             * 如要取<row>下的子元素的名称
//             */
//            et = (Element) jiedian.get(0);
//            List zjiedian = et.getChildren();
//            for(int j=0;j<zjiedian.size();j++){
//                Element xet = (Element) zjiedian.get(j);
//                System.out.println(xet.getName());
//            }
        } catch (JDOMException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
		return hm;
//		String xml = "<?xml version=/"1.0/" encoding=/"UTF-8/"?>"+
//		        "<submittask tasktypename=/"kind1/" perfrenceNum=/"2/">"+
//		        "<input name=/"name/" value=/"123/" inputindex=/"1/" perfrence=/"2/"/>"+
//		        "<input name=/"sex/" value=/"F/" inputindex=/"2/" perfrence=/"2/"/>"+
//		        "</submittask>"
//		        ;
//		
	}

	public HashMap getConfigHash() {
		return this.ConfigHash;

	}

	public HashMap getDefaultConfigHash() {
		return this.DefaultConfigHash;

	}

	public void setDefaultConfigHash() {
		HashMap h = new HashMap();
		h.put("DBNAME", "pay.db");
		h.put("PUBLICURL", "http://");
		this.DefaultConfigHash = h;

	}

	public void saveConfigUrl(String s) {
		// this.ConfigVector=v;

	}

	public void setConfigHash(HashMap v) {
		this.ConfigHash = v;
		//
	}

	public void reLoadConfig() {

	}

	public String getConfigUrl() {
		String s = null;
		File d = new File(LOCALCONFIGDIR);
		if (!d.exists()) {
			d.mkdir();
		}
		File f = new File(LOCALCONFIGDIR + LOCALCONFIGFILE);
		if (!f.exists()) {// If not exists file then return null
			System.out.println("Not exists Config File, now to use default : "
					+ LOCALCONFIGDIR + LOCALCONFIGFILE);
			return s;
		} else {// if exists then read file
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(f));
				String tempString = null;
				int line = 1;
				while ((tempString = reader.readLine()) != null) {
					s = s + tempString;
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
		}
		if (f.length() == 0) {
			return null;
		}

		return s;

	}

	public static void main(String[] args) {
		Config m= new Config();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
        "<submittask tasktypename=\"kind1\" perfrenceNum=\"2\">"+
        "<input name=\"name\" value=\"123\" inputindex=\"1\" perfrence=\"2\"/>"+
        "<input name=\"sex\" value=\"F\" inputindex=\"2\" perfrence=\"2\"/>"+
        "</submittask>"
        ;
		HashMap s = m.getHashMapbyxmlstring(xml);
		System.out.print(s);
	}
}
