package bak;

import java.awt.Dimension;  
import java.util.Date;  
import java.util.Vector;  
  
import javax.swing.JFrame;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.table.DefaultTableModel;  
  
public class TableJFrame extends JFrame {  
    public TableJFrame() {  
        init();  
  
        this.setTitle("��������");  
        this.setSize(new Dimension(400, 450));  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
  
    private void init() {  
//      // 1.��ά�����ʼ��  
//      String[] columnHeader = { "���", "����", "����", "��ַ" };  
//      String[][] data = new String[][] { { "user1", "С��", "26", "���ݹ�¥" },  
//              { "user2", "С��2", "28", "���ݹ�¥2" },  
//              { "user3", "С��3", "33", "���ݹ�¥" },  
//              { "user4", "С��4", "26", "���ݹ�¥" } };  
  
//        
//      //2��ʵ����tableModel�ӿ�  
//      DefaultTableModel defaultTableModel = new DefaultTableModel(data,columnHeader);  
        //����ģ�ͷ�ʽ  
//      table.setModel(defaultTableModel);  
      
          
          
        //3��vector  
        Vector<String> colHeader = new Vector<String>();  
        colHeader.add("���");  
        colHeader.add("����");  
        colHeader.add("�Ա�");  
        colHeader.add("����");  
          
        Vector<Vector<String>> dataVec = new Vector<Vector<String>>();  
        Vector<String> row1 = new Vector<String>();  
        row1.add("0001");  
        row1.add("����");  
        row1.add("��");  
        row1.add(new Date().toString());  
        Vector<String> row2 = new Vector<String>();  
        row2.add("0002");  
        row2.add("Сǿ");  
        row2.add("��");  
        row2.add(new Date().toString());  
        Vector<String> row3 = new Vector<String>();  
        row3.add("0003");  
        row3.add("ΤС��");  
        row3.add("Ů");  
        row3.add(new Date().toString());  
        Vector<String> row4 = new Vector<String>();  
        row4.add("0004");  
        row4.add("������");  
        row4.add("��");  
        row4.add(new Date().toString());  
          
        dataVec.add(row1);  
        dataVec.add(row2);  
        dataVec.add(row3);  
        dataVec.add(row4);  
          
        JTable table = new JTable(dataVec,colHeader);  
        //Ҫ��ʾ��ͷ����Ҫ���뵽������� ���������뵽����  
//      JScrollPane scrollPane = new JScrollPane(table);  
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setViewportView(table);  
        this.add(scrollPane);  
  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        new TableJFrame();  
  
    }  
  
}  