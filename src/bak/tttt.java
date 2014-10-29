package bak;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class tttt {
 
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  JFrame frame=new ListModelFrame("�б�����¼�ʵ��");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);
 }
}
class ListModelFrame extends JFrame{
 public ListModelFrame(String title){
  super(title);
  setSize(350,150);
  
  final JList wordList;
  //�ı���
  final JTextField textField;
  //���б�ģ�͹�����
  wordList=new JList(new ListModelA());
  textField=new JTextField();
//  wordList.setVisibleRowCount(8);//Ĭ���б���������ʾ8��ѡ�����Ϊ4��
//  wordList.setVisibleRowCount(5);
  JPanel listPanel=new JPanel();
  listPanel.add(new JScrollPane(wordList));//���б���������
  
  //�б��Ĭ�Ͽ���ѡ����ѡ������ǣ���סCTRL������Ҫѡ���ѡ���ϵ�����
  //Ҫ����ѡ��ѡ�����ѡ���һ��ѡ�Ȼ��סSHIFT���������һ��ѡ���ϵ�����
  //�����������ֻ��ѡ��һ��ѡ�
  //wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
  wordList.setLayoutOrientation(JList.HORIZONTAL_WRAP);//�б���ˮƽ����ڷ�
  //����б�򵽴���
  add(listPanel,BorderLayout.CENTER);
  //����ı��򵽴���
  add(textField,BorderLayout.SOUTH);
  //����б�ѡ�������
  wordList.addListSelectionListener(new ListSelectionListener(){
   //ʵ�����ķ���
   @Override
   public void valueChanged(ListSelectionEvent e) {
    // TODO Auto-generated method stub
    StringBuilder str=new StringBuilder("����,");
    
    //�õ��б����ѡ����ֵ
    Object valueList[]=wordList.getSelectedValues();
    
    for(int i=0;i<valueList.length;i++){
     String s=(String)valueList[i];
     str.append(s);
    }
    str.append(" ��ӭ����");
    //��ѡ����ֵ��ʾ���ı���
    textField.setText(str.toString());
   }
  });
 }
}
//�б�ģ��
class ListModelA extends AbstractListModel{
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