package bak;

import java.awt.Dimension;  
import java.util.Date;  
import java.util.Vector;  
  
import javax.swing.DefaultListModel;  
import javax.swing.JFrame;  
import javax.swing.JList;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.ListSelectionModel;  
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;  
import javax.swing.table.DefaultTableModel;  
  
public class TestJListJFrame extends JFrame {  
    public TestJListJFrame() {  
        init();  
  
        this.setTitle("JList����");  
        this.setSize(new Dimension(200, 450));  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
  
    private void init() {  
        final JList list = new JList();  
        DefaultListModel defaultListModel = new DefaultListModel();  
          
        defaultListModel.addElement("�Ʒɺ�");  
        defaultListModel.addElement("��Ԫ��");  
        defaultListModel.addElement("Ҧ����");  
        defaultListModel.addElement("����");  
        defaultListModel.addElement("Ҷ��");  
        defaultListModel.addElement("��С��");  
        defaultListModel.addElement("������");  
          
        list.setModel(defaultListModel);  
          
        //��ѡ  
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        //����ѡ���¼�  
        list.getSelectionModel().addListSelectionListener(new ListSelectionListener(){  
  
            public void valueChanged(ListSelectionEvent e) {  
                if(e.getValueIsAdjusting()){  
                    int index = list.getSelectedIndex();  
                    System.out.println(index);  
                    String selectedItem = list.getSelectedValue().toString();  
                    System.out.println("ѡ��ֵ:"+selectedItem);  
                      
                }  
                  
            }});          
  
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setViewportView(list);  
        this.add(scrollPane);  
  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        new TestJListJFrame();  
  
    }  
  
}  
