package bak;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class tttt {
 
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  JFrame frame=new ListModelFrame("列表监听事件实例");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);
 }
}
class ListModelFrame extends JFrame{
 public ListModelFrame(String title){
  super(title);
  setSize(350,150);
  
  final JList wordList;
  //文本框
  final JTextField textField;
  //以列表模型构建框
  wordList=new JList(new ListModelA());
  textField=new JTextField();
//  wordList.setVisibleRowCount(8);//默认列表构件可以显示8个选项，调整为4个
//  wordList.setVisibleRowCount(5);
  JPanel listPanel=new JPanel();
  listPanel.add(new JScrollPane(wordList));//将列表插入滚动条
  
  //列表框默认可以选择多个选项，方法是：按住CTRL健，在要选择的选项上单击。
  //要连续选择选项，必须选择第一个选项，然后按住SHIFT键，在最后一个选项上单击。
  //以下这个设置只能选择一个选项。
  //wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
  wordList.setLayoutOrientation(JList.HORIZONTAL_WRAP);//列表按照水平方向摆放
  //添加列表框到窗体
  add(listPanel,BorderLayout.CENTER);
  //添加文本框到窗体
  add(textField,BorderLayout.SOUTH);
  //添加列表选择监听器
  wordList.addListSelectionListener(new ListSelectionListener(){
   //实现它的方法
   @Override
   public void valueChanged(ListSelectionEvent e) {
    // TODO Auto-generated method stub
    StringBuilder str=new StringBuilder("您好,");
    
    //得到列表框中选定的值
    Object valueList[]=wordList.getSelectedValues();
    
    for(int i=0;i<valueList.length;i++){
     String s=(String)valueList[i];
     str.append(s);
    }
    str.append(" 欢迎您！");
    //将选定的值显示在文本框
    textField.setText(str.toString());
   }
  });
 }
}
//列表模型
class ListModelA extends AbstractListModel{
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