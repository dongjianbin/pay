package bak;

import java.awt.BorderLayout;  
import java.awt.Dimension;  
import java.util.Date;  
import java.util.Vector;  
  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.ListSelectionModel;  
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;  
import javax.swing.table.DefaultTableModel;  
  
public class TableJFrame2 extends JFrame {  
      
    JButton buttonAlt = new JButton("�޸�");  
      
    JButton buttonDel = new JButton("ɾ��");  
//  /��񷽷�ʹ��  
    public TableJFrame2() {  
        init();  
  
        this.setTitle("��������2");  
        this.setSize(new Dimension(400, 450));  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
  
    private void init() {     
          
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
          
        final JTable table = new JTable(dataVec,colHeader){  
            //1��Ԫ�񲻿��Ա༭  
            @Override  
            public boolean isCellEditable(int row, int column) {  
                // TODO Auto-generated method stub  
                return false;  
            }  
        };  
          
        //2���ñ�ͷ�и�  
        table.getTableHeader().setPreferredSize(new Dimension(0,0));  
        //3���ñ������и�  
        table.setRowHeight(25);  
        //4���õ�ѡģʽ  
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        //5���õ�Ԫ�񲻿��϶�  
        table.getTableHeader().setReorderingAllowed(false);  
        //6���ò��ɸı��п�  
        table.getTableHeader().setResizingAllowed(false);  
          
        //7�����п�  
        table.getColumnModel().getColumn(0).setPreferredWidth(45);  
        table.getColumnModel().getColumn(1).setPreferredWidth(55);  
        table.getColumnModel().getColumn(2).setPreferredWidth(40);  
        table.getColumnModel().getColumn(3).setPreferredWidth(305);  
//      table.getColumnModel().getColumn(4).setPreferredWidth(305);//ע������Խ��  
        int rowIndex = table.getSelectedRow();  
        System.out.println("suoyin:"+rowIndex);  
        //�����¼�  
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){  
  
            public void valueChanged(ListSelectionEvent e) {  
                  
                if(e.getValueIsAdjusting()){//��������  
                    int rowIndex = table.getSelectedRow();  
                    if(rowIndex!=-1){  
                        System.out.println("����б�ѡ��"+rowIndex);  
                        buttonAlt.setEnabled(true);  
                        buttonDel.setEnabled(true);  
                    }                     
                }  
                  
            }});  
          
          
          
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setViewportView(table);  
        this.add(scrollPane);  
          
        buttonDel.setEnabled(false);  
        buttonAlt.setEnabled(false);  
          
        JPanel panel = new JPanel();  
        panel.add(buttonAlt);  
        panel.add(buttonDel);  
        this.add(panel,BorderLayout.SOUTH);  
  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        new TableJFrame2();  
  
    }  
  
}  