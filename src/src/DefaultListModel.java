package src;

import javax.swing.AbstractListModel;

class DefaultListModel extends AbstractListModel{
//	String[] words={"广东","湖南","湖北","广西","四川","黑龙江",
//			   "河北","甘肃","宁夏","辽宁","吉林","上海","重庆","北京","河南"};
////	 String[] words;
//	 
//	 public DefaultListModel(){
////			this.words=words;
//		}
//	public DefaultListModel(String[] words){
//		this.words=words;
//	}
//	
//	 @Override
//	 public int getSize() {
//	  // TODO Auto-generated method stub
//	  //得到列表的长度
//	  return this.words.length;
//	 }
//	 @Override
//	 public Object getElementAt(int index) {
//	  //得到值
//	  return this.words[index];
//	 }
//	}
	
	
	String[] words={"广东","湖南","湖北","广西","四川","黑龙江",
			   "河北","甘肃","宁夏","辽宁","吉林","上海","重庆","北京","河南"};
			 @Override
			 public int getSize() {
			  // TODO Auto-generated method stub
			  //得到列表的长度
			  return this.words.length;
			 }
			 @Override
			 public Object getElementAt(int index) {
			  //得到值
			  return this.words[index];
			 }
			}