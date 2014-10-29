package bak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class JTableButton extends JPanel {
 private JTable table;
 private JScrollPane scrollPane;
 private JButton[] buttons;
 private String path = System.getProperty("user.dir") + File.separator
 + "images" + File.separator;
 
 public JTableButton() {
  setBorder(BorderFactory.createLineBorder(Color.red, 1));
  init();
 }

 private void init() {
  String headName[] = { "Name", "age", "sex", "adress", "image" };
  
  buttons = new JButton[5];
  for(int i=0;i<buttons.length;i++){
   buttons[i] = new JButton(""+i);
  }
  Object obj[][] = {
    { "LiMing", 23, Boolean.TRUE, buttons[0],
      new ImageIcon(path + "icon.png") },
    { "ZhangSan", 25, Boolean.TRUE,buttons[1],
      new ImageIcon(path + "icon.png") },
    { "WangWu", 21, Boolean.FALSE, buttons[2],
      new ImageIcon(path + "icon.png") },
    { "LiSi", 28, Boolean.TRUE, buttons[3],
      new ImageIcon(path + "icon.png") },
    { "LuBo", 20, Boolean.FALSE, buttons[4],
      new ImageIcon(path + "icon.png") }, };
  
  table = new JTable(new MyTableModel(headName,obj));
  table.setDefaultRenderer(JButton.class, new ComboBoxCellRenderer());
  scrollPane = new JScrollPane(table);
  setLayout(new BorderLayout());
  add(scrollPane, BorderLayout.CENTER);
  addHandler();
 }
 private void addHandler(){
  //添加事件
  table.addMouseListener(new MouseAdapter(){
   public void mouseClicked(MouseEvent e) {
    System.out.println("table");
    int row = table.getSelectedRow();
    int column = table.getSelectedColumn();
    System.out.println("row="+row+":"+"column="+column);
    if(column==3){
     //处理button事件写在这里...
     System.out.println(((JButton)table.getValueAt(row, column)).getText());
    }
   }
  });
 }
 public static void main(String[] args) {
  JFrame frame = new JFrame();
  frame.add(new JTableButton());
  frame.setSize(new Dimension(800, 400));
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
 class MyTableModel extends AbstractTableModel {
  private String headName[];
  private Object obj[][];
  
  public MyTableModel() {
   super();
  }
  
  public MyTableModel(String[] headName, Object[][] obj) {
   this();
   this.headName = headName;
   this.obj = obj;
  }

  public int getColumnCount() {
   return headName.length;
  }

  public int getRowCount() {
   return obj.length;
  }

  public Object getValueAt(int r, int c) {
   return obj[r][c];
  }

  public String getColumnName(int c) {
   return headName[c];
  }

  public Class<?> getColumnClass(int columnIndex) {
   return obj[0][columnIndex].getClass();
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
   if (columnIndex == 3 || columnIndex == 4) {
    return false;
   }
   return true;
  }

 }
}

class ComboBoxCellRenderer implements TableCellRenderer {
 public Component getTableCellRendererComponent(JTable table, Object value,
   boolean isSelected, boolean hasFocus, int row, int column) {
  JButton cmb = (JButton) value;
  if (isSelected) {
   cmb.setForeground(table.getSelectionForeground());
   cmb.setBackground(table.getSelectionBackground());
  } else {
   cmb
     .setForeground((unselectedForeground != null) ? unselectedForeground
       : table.getForeground());
   cmb
     .setBackground((unselectedBackground != null) ? unselectedBackground
       : table.getBackground());
  }
  cmb.setFont(table.getFont());
  if (hasFocus) {
   cmb
     .setBorder(UIManager
       .getBorder("Table.focusCellHighlightBorder"));
   if (!isSelected && table.isCellEditable(row, column)) {
    Color col;
    col = UIManager.getColor("Table.focusCellForeground");
    if (col != null) {
     cmb.setForeground(col);
    }
    col = UIManager.getColor("Table.focusCellBackground");
    if (col != null) {
     cmb.setBackground(col);
    }
   }
  } else {
   cmb.setBorder(noFocusBorder);
  }
  return cmb;
 }

 protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

 private Color unselectedForeground;
 private Color unselectedBackground;
}