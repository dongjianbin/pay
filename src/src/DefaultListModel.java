package src;

import javax.swing.AbstractListModel;

class DefaultListModel extends AbstractListModel{
//	String[] words={"�㶫","����","����","����","�Ĵ�","������",
//			   "�ӱ�","����","����","����","����","�Ϻ�","����","����","����"};
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
//	  //�õ��б�ĳ���
//	  return this.words.length;
//	 }
//	 @Override
//	 public Object getElementAt(int index) {
//	  //�õ�ֵ
//	  return this.words[index];
//	 }
//	}
	
	
	String[] words={"�㶫","����","����","����","�Ĵ�","������",
			   "�ӱ�","����","����","����","����","�Ϻ�","����","����","����"};
			 @Override
			 public int getSize() {
			  // TODO Auto-generated method stub
			  //�õ��б�ĳ���
			  return this.words.length;
			 }
			 @Override
			 public Object getElementAt(int index) {
			  //�õ�ֵ
			  return this.words[index];
			 }
			}