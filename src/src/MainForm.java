/* 
 * Justin 20141014 15:58 nz
 */

package src;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.SwingConstants;

import java.awt.CardLayout;

import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SpringLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import java.awt.Point;
import java.awt.Component;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import src.TestConn;
import src.PayTableModel;
import src.DefaultListModel;
import src.SearchComboBox;

import java.sql.*;

import org.sqlite.JDBC;

import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import src.JAutoCompleteComboBox;

// The main program class

public class MainForm extends JFrame implements ActionListener {
	private JTextField text_GoodsSearch, searchTextField;
	private JTextField text_Custom;
	private JButton btn_X, btn_Void, btn_Park, btn_Notes, btn_Discount,
			btn_Pay, btn_Logout, btn_Add, btn_Quit,btn_Retrive_Sale,btn_Close_Register;
	private JTable mtable;
	private PayTableModel mMyTableModel, tMyTableModel,retrivetablemodel;
	private JScrollPane scroll_panel_Main_Left_Btm_Top_Top_Btm,
			scroll_Main_Right_Btm;
	private Vector content, defaultcontent, defaultgoodslist, allgoodslist,
			searchgoodslist;
	private JTable defaulttable,retrivetable;
	private JPanel panel_Main_Left_Top_Left;
	private JComboBox mJComboBox;
	private DefaultComboBoxModel mDefaultComboBoxModel;
	private Document mDocument;
	private boolean keyflag;
	private HashMap goods_id2id, id2goods_id, defaultid2goods_id,
			goods_id2defaultid;
	private Vector ordervector, cusomervector;
	private int gstincflag;
	private double discount_double;
	private String discount_type;
	private String discount_input, discount_err;
	private double total_topay, total_subtotal, total_tax, total_total;
	private JLabel lbl_Money_Topay, lbl_Money_Subtotal, lbl_Money_Tax,
			lbl_Money_Total;
	private String notes;

	public MainForm() {
		goods_id2id = new HashMap();
		id2goods_id = new HashMap();
		defaultid2goods_id = new HashMap();
		goods_id2defaultid = new HashMap();
		this.initForm();
		this.searchTextFieldsetfocus();
		this.initdata();

	}

	public void initdata() {

		this.initorder();
		this.initcustomer();
		this.initnotes();
		this.updatetable("deleteall", 1);

	}

	public void initForm() {
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 20));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 5, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnCurrentSale = new JButton("CURRENT SALE");
		btnCurrentSale.setPreferredSize(new Dimension(10, 10));
		btnCurrentSale.setMinimumSize(new Dimension(10, 10));
		btnCurrentSale.setMaximumSize(new Dimension(10, 10));
		panel_1.add(btnCurrentSale);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		btn_Retrive_Sale = new JButton("RETRIEVE SALE");
		btn_Retrive_Sale.setPreferredSize(new Dimension(10, 10));
		panel_2.add(btn_Retrive_Sale);
		btn_Retrive_Sale.addActionListener(this);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		btn_Close_Register = new JButton("CLOSE REGISTER");
		btn_Close_Register.setSize(new Dimension(20, 10));
		btn_Close_Register.setPreferredSize(new Dimension(10, 10));
		panel_3.add(btn_Close_Register);
		btn_Close_Register.addActionListener(this);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));

		btn_Logout = new JButton("LOGOUT");
		panel_4.add(btn_Logout);
		btn_Logout.setSize(new Dimension(20, 10));
		btn_Logout.setPreferredSize(new Dimension(100, 20));
		btn_Logout.setMinimumSize(new Dimension(20, 20));
		btn_Logout.setMaximumSize(new Dimension(20, 20));

		btn_Logout.addActionListener(this);

		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));

		btn_Quit = new JButton("QUIT");
		panel_5.add(btn_Quit);
		btn_Quit.setSize(new Dimension(20, 10));
		btn_Quit.setPreferredSize(new Dimension(100, 20));
		btn_Quit.setMinimumSize(new Dimension(20, 20));
		btn_Quit.setMaximumSize(new Dimension(20, 20));
		btn_Quit.addActionListener(this);

		JPanel panel_Main = new JPanel();
		panel_Main.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		getContentPane().add(panel_Main, BorderLayout.CENTER);
		panel_Main.setLayout(new GridLayout(1, 2, 0, 0));

		JSplitPane split_Main = new JSplitPane();
		split_Main.setDividerSize(1);
		split_Main.setEnabled(false);
		split_Main.setResizeWeight(0.5);
		panel_Main.add(split_Main);

		JPanel panel_Main_Left = new JPanel();
		panel_Main_Left.setPreferredSize(new Dimension(300, 10));
		split_Main.setLeftComponent(panel_Main_Left);
		panel_Main_Left.setLayout(new GridLayout(1, 0, 0, 0));

		JSplitPane split_Main_Left = new JSplitPane();
		split_Main_Left.setDividerSize(0);
		split_Main_Left.setEnabled(false);
		split_Main_Left.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_Main_Left.add(split_Main_Left);

		JPanel panel_Main_Left_Top = new JPanel();
		split_Main_Left.setLeftComponent(panel_Main_Left_Top);
		panel_Main_Left_Top.setLayout(new GridLayout(0, 1, 0, 0));

		JSplitPane split_Main_Left_Top = new JSplitPane();
		split_Main_Left_Top.setResizeWeight(1.0);
		split_Main_Left_Top.setDividerSize(0);
		split_Main_Left_Top.setEnabled(false);
		panel_Main_Left_Top.add(split_Main_Left_Top);

		panel_Main_Left_Top_Left = new JPanel();
		split_Main_Left_Top.setLeftComponent(panel_Main_Left_Top_Left);
		panel_Main_Left_Top_Left.setLayout(new GridLayout(1, 1, 0, 0));

		initGoodsSearchList();

		JPanel panel_Main_Left_TOP_Right = new JPanel();
		split_Main_Left_Top.setRightComponent(panel_Main_Left_TOP_Right);
		panel_Main_Left_TOP_Right.setLayout(new GridLayout(0, 1, 0, 0));

		btn_Add = new JButton("Add");
		panel_Main_Left_TOP_Right.add(btn_Add);

		JPanel panel_Main_Left_Btm = new JPanel();
		split_Main_Left.setRightComponent(panel_Main_Left_Btm);
		panel_Main_Left_Btm.setLayout(new GridLayout(1, 0, 0, 0));

		JSplitPane split_Main_Left_Btm = new JSplitPane();
		split_Main_Left_Btm.setEnabled(false);
		split_Main_Left_Btm.setDividerSize(0);
		split_Main_Left_Btm.setResizeWeight(1.0);
		split_Main_Left_Btm.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_Main_Left_Btm.add(split_Main_Left_Btm);

		JPanel panel_Main_Left_Btm_Top = new JPanel();
		split_Main_Left_Btm.setLeftComponent(panel_Main_Left_Btm_Top);
		panel_Main_Left_Btm_Top.setLayout(new GridLayout(0, 1, 0, 0));

		JSplitPane split_Main_Left_Btm_Top = new JSplitPane();
		split_Main_Left_Btm_Top.setResizeWeight(0.8);
		split_Main_Left_Btm_Top.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split_Main_Left_Btm_Top.setEnabled(false);
		split_Main_Left_Btm_Top.setDividerSize(0);
		panel_Main_Left_Btm_Top.add(split_Main_Left_Btm_Top);

		JPanel panel_Main_Left_Btm_Top_Top = new JPanel();
		split_Main_Left_Btm_Top.setLeftComponent(panel_Main_Left_Btm_Top_Top);
		panel_Main_Left_Btm_Top_Top.setLayout(new GridLayout(1, 0, 0, 0));

		JSplitPane split_Main_Left_Btm_Top_Top = new JSplitPane();
		split_Main_Left_Btm_Top_Top.setDividerSize(0);
		split_Main_Left_Btm_Top_Top.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_Main_Left_Btm_Top_Top.add(split_Main_Left_Btm_Top_Top);

		JPanel panel_Main_Left_Btm_Top_Top_Top = new JPanel();
		split_Main_Left_Btm_Top_Top
				.setLeftComponent(panel_Main_Left_Btm_Top_Top_Top);
		panel_Main_Left_Btm_Top_Top_Top.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_Main_Left_Btm_Top_Top_Btm = new JPanel();
		split_Main_Left_Btm_Top_Top
				.setRightComponent(panel_Main_Left_Btm_Top_Top_Btm);
		panel_Main_Left_Btm_Top_Top_Btm.setLayout(new GridLayout(1, 0, 0, 0));

		scroll_panel_Main_Left_Btm_Top_Top_Btm = new JScrollPane();
		panel_Main_Left_Btm_Top_Top_Btm
				.add(scroll_panel_Main_Left_Btm_Top_Top_Btm);

		initTable();

		JPanel panel_Main_Left_Btm_Top_Btm = new JPanel();
		split_Main_Left_Btm_Top.setRightComponent(panel_Main_Left_Btm_Top_Btm);
		panel_Main_Left_Btm_Top_Btm.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_Main_Left_Btm_Top_Btm_Left = new JPanel();
		panel_Main_Left_Btm_Top_Btm.add(panel_Main_Left_Btm_Top_Btm_Left);
		panel_Main_Left_Btm_Top_Btm_Left.setLayout(new GridLayout(1, 0, 0, 0));

		JSplitPane split_Main_Left_Btm_Top_Btm_Left = new JSplitPane();
		split_Main_Left_Btm_Top_Btm_Left.setDividerSize(0);
		split_Main_Left_Btm_Top_Btm_Left
				.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_Main_Left_Btm_Top_Btm_Left.add(split_Main_Left_Btm_Top_Btm_Left);

		JPanel panel_Main_Left_Btm_Top_Btm_Left_Top = new JPanel();
		panel_Main_Left_Btm_Top_Btm_Left_Top
				.setMinimumSize(new Dimension(1, 1));
		split_Main_Left_Btm_Top_Btm_Left
				.setLeftComponent(panel_Main_Left_Btm_Top_Btm_Left_Top);
		panel_Main_Left_Btm_Top_Btm_Left_Top.setLayout(new BoxLayout(
				panel_Main_Left_Btm_Top_Btm_Left_Top, BoxLayout.X_AXIS));

		text_Custom = new JTextField();
		panel_Main_Left_Btm_Top_Btm_Left_Top.add(text_Custom);

		JButton btn_Plus = new JButton("+");
		btn_Plus.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_Main_Left_Btm_Top_Btm_Left_Top.add(btn_Plus);

		JPanel panel_Main_Left_Btm_Top_Btm_Left_Btm = new JPanel();
		split_Main_Left_Btm_Top_Btm_Left
				.setRightComponent(panel_Main_Left_Btm_Top_Btm_Left_Btm);

		JPanel panel_Main_Left_Btm_Top_Btm_Right = new JPanel();
		panel_Main_Left_Btm_Top_Btm.add(panel_Main_Left_Btm_Top_Btm_Right);
		panel_Main_Left_Btm_Top_Btm_Right.setLayout(new GridLayout(0, 1, 0, 0));

		JSplitPane split_Main_Left_Btm_Top_Btm_Right = new JSplitPane();
		split_Main_Left_Btm_Top_Btm_Right.setDividerSize(0);
		split_Main_Left_Btm_Top_Btm_Right.setResizeWeight(0.9);
		panel_Main_Left_Btm_Top_Btm_Right
				.add(split_Main_Left_Btm_Top_Btm_Right);

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Left = new JPanel();
		split_Main_Left_Btm_Top_Btm_Right
				.setLeftComponent(panel_Main_Left_Btm_Top_Btm_Right_Left);
		panel_Main_Left_Btm_Top_Btm_Right_Left.setLayout(new GridLayout(4, 1,
				0, 0));

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Left_1 = new JPanel();
		panel_Main_Left_Btm_Top_Btm_Right_Left
				.add(panel_Main_Left_Btm_Top_Btm_Right_Left_1);
		panel_Main_Left_Btm_Top_Btm_Right_Left_1.setLayout(new GridLayout(0, 2,
				0, 0));

		JLabel lbl_Topay = new JLabel("  TOPAY");
		lbl_Topay.setPreferredSize(new Dimension(3, 3));
		lbl_Topay.setMinimumSize(new Dimension(3, 3));
		lbl_Topay.setMaximumSize(new Dimension(3, 3));
		panel_Main_Left_Btm_Top_Btm_Right_Left_1.add(lbl_Topay);

		lbl_Money_Topay = new JLabel("$0.00");
		lbl_Money_Topay.setPreferredSize(new Dimension(1, 15));
		lbl_Money_Topay.setMinimumSize(new Dimension(3, 3));
		lbl_Money_Topay.setMaximumSize(new Dimension(3, 3));
		lbl_Money_Topay.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_Main_Left_Btm_Top_Btm_Right_Left_1.add(lbl_Money_Topay);

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Left_2 = new JPanel();
		panel_Main_Left_Btm_Top_Btm_Right_Left
				.add(panel_Main_Left_Btm_Top_Btm_Right_Left_2);
		panel_Main_Left_Btm_Top_Btm_Right_Left_2.setMinimumSize(new Dimension(
				3, 3));
		panel_Main_Left_Btm_Top_Btm_Right_Left_2.setLayout(new GridLayout(0, 2,
				0, 0));

		JLabel lbl_Subtotal = new JLabel("  Subtotal");
		lbl_Subtotal.setPreferredSize(new Dimension(3, 3));
		lbl_Subtotal.setMinimumSize(new Dimension(3, 3));
		lbl_Subtotal.setMaximumSize(new Dimension(3, 3));
		panel_Main_Left_Btm_Top_Btm_Right_Left_2.add(lbl_Subtotal);

		lbl_Money_Subtotal = new JLabel("$0.00");
		lbl_Money_Subtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Money_Subtotal.setPreferredSize(new Dimension(1, 15));
		lbl_Money_Subtotal.setMinimumSize(new Dimension(5, 3));
		lbl_Money_Subtotal.setMaximumSize(new Dimension(3, 3));
		panel_Main_Left_Btm_Top_Btm_Right_Left_2.add(lbl_Money_Subtotal);

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Left_3 = new JPanel();
		panel_Main_Left_Btm_Top_Btm_Right_Left
				.add(panel_Main_Left_Btm_Top_Btm_Right_Left_3);
		panel_Main_Left_Btm_Top_Btm_Right_Left_3.setLayout(new GridLayout(0, 2,
				0, 0));

		JLabel lbl_Tax = new JLabel("  Tax(No Tax)");
		// lbl_Tax.setFont(new Font("锟斤拷锟斤拷", Font.PLAIN, 10));
		lbl_Tax.setPreferredSize(new Dimension(3, 3));
		lbl_Tax.setMinimumSize(new Dimension(3, 3));
		lbl_Tax.setMaximumSize(new Dimension(3, 3));
		panel_Main_Left_Btm_Top_Btm_Right_Left_3.add(lbl_Tax);

		lbl_Money_Tax = new JLabel("$0.00");
		lbl_Money_Tax.setPreferredSize(new Dimension(1, 15));
		lbl_Money_Tax.setMinimumSize(new Dimension(3, 3));
		lbl_Money_Tax.setMaximumSize(new Dimension(3, 3));
		lbl_Money_Tax.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_Main_Left_Btm_Top_Btm_Right_Left_3.add(lbl_Money_Tax);

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Left_4 = new JPanel();
		panel_Main_Left_Btm_Top_Btm_Right_Left
				.add(panel_Main_Left_Btm_Top_Btm_Right_Left_4);
		panel_Main_Left_Btm_Top_Btm_Right_Left_4.setLayout(new GridLayout(0, 2,
				0, 0));

		JLabel lbl_Total = new JLabel("  TOTAL");
		lbl_Total.setPreferredSize(new Dimension(3, 3));
		lbl_Total.setMinimumSize(new Dimension(3, 3));
		lbl_Total.setMaximumSize(new Dimension(3, 3));
		panel_Main_Left_Btm_Top_Btm_Right_Left_4.add(lbl_Total);

		lbl_Money_Total = new JLabel("$0.00");
		lbl_Money_Total.setPreferredSize(new Dimension(1, 15));
		lbl_Money_Total.setMinimumSize(new Dimension(3, 3));
		lbl_Money_Total.setMaximumSize(new Dimension(3, 3));
		lbl_Money_Total.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_Main_Left_Btm_Top_Btm_Right_Left_4.add(lbl_Money_Total);

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Right = new JPanel();
		split_Main_Left_Btm_Top_Btm_Right
				.setRightComponent(panel_Main_Left_Btm_Top_Btm_Right_Right);
		panel_Main_Left_Btm_Top_Btm_Right_Right.setLayout(new GridLayout(4, 0,
				0, 0));

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Right_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_Main_Left_Btm_Top_Btm_Right_Right_1
				.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_Main_Left_Btm_Top_Btm_Right_Right
				.add(panel_Main_Left_Btm_Top_Btm_Right_Right_1);

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Right_2 = new JPanel();
		panel_Main_Left_Btm_Top_Btm_Right_Right
				.add(panel_Main_Left_Btm_Top_Btm_Right_Right_2);

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Right_3 = new JPanel();
		panel_Main_Left_Btm_Top_Btm_Right_Right
				.add(panel_Main_Left_Btm_Top_Btm_Right_Right_3);
		panel_Main_Left_Btm_Top_Btm_Right_Right_3.setLayout(new GridLayout(0,
				1, 0, 0));

		btn_X = new JButton("X");
		btn_X.setAlignmentY(0.0f);
		btn_X.setBackground(new Color(220, 20, 60));
		btn_X.setForeground(new Color(248, 248, 255));
		btn_X.setPreferredSize(new Dimension(1, 1));
		btn_X.setMinimumSize(new Dimension(1, 1));
		btn_X.setMargin(new Insets(0, 0, 0, 0));
		btn_X.setMaximumSize(new Dimension(1, 1));
		panel_Main_Left_Btm_Top_Btm_Right_Right_3.add(btn_X);

		JPanel panel_Main_Left_Btm_Top_Btm_Right_Right_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_Main_Left_Btm_Top_Btm_Right_Right_4
				.getLayout();
		flowLayout_1.setHgap(0);
		panel_Main_Left_Btm_Top_Btm_Right_Right
				.add(panel_Main_Left_Btm_Top_Btm_Right_Right_4);

		JPanel panel_Main_Left_Btm_Btm = new JPanel();
		split_Main_Left_Btm.setRightComponent(panel_Main_Left_Btm_Btm);
		panel_Main_Left_Btm_Btm.setLayout(new GridLayout(0, 5, 0, 0));

		JPanel panel_Main_Left_Btm_Btm_1 = new JPanel();
		panel_Main_Left_Btm_Btm_1.setPreferredSize(new Dimension(0, 0));
		panel_Main_Left_Btm_Btm_1.setMinimumSize(new Dimension(1, 1));
		panel_Main_Left_Btm_Btm.add(panel_Main_Left_Btm_Btm_1);
		panel_Main_Left_Btm_Btm_1.setLayout(new BorderLayout(0, 0));

		btn_Void = new JButton("Void");
		panel_Main_Left_Btm_Btm_1.add(btn_Void);

		JPanel panel_Main_Left_Btm_Btm_2 = new JPanel();
		panel_Main_Left_Btm_Btm_2.setMinimumSize(new Dimension(1, 1));
		panel_Main_Left_Btm_Btm_2.setPreferredSize(new Dimension(0, 0));
		panel_Main_Left_Btm_Btm.add(panel_Main_Left_Btm_Btm_2);
		panel_Main_Left_Btm_Btm_2.setLayout(new BorderLayout(0, 0));

		btn_Park = new JButton("Park");
		panel_Main_Left_Btm_Btm_2.add(btn_Park);

		JPanel panel_Main_Left_Btm_Btm_3 = new JPanel();
		panel_Main_Left_Btm_Btm_3.setMinimumSize(new Dimension(1, 1));
		panel_Main_Left_Btm_Btm_3.setPreferredSize(new Dimension(0, 0));
		panel_Main_Left_Btm_Btm.add(panel_Main_Left_Btm_Btm_3);
		panel_Main_Left_Btm_Btm_3.setLayout(new BorderLayout(0, 0));

		btn_Notes = new JButton("Notes");
		panel_Main_Left_Btm_Btm_3.add(btn_Notes);

		JPanel panel_Main_Left_Btm_Btm_4 = new JPanel();
		panel_Main_Left_Btm_Btm_4.setMinimumSize(new Dimension(1, 1));
		panel_Main_Left_Btm_Btm_4.setPreferredSize(new Dimension(0, 0));
		panel_Main_Left_Btm_Btm.add(panel_Main_Left_Btm_Btm_4);
		panel_Main_Left_Btm_Btm_4.setLayout(new BorderLayout(0, 0));

		btn_Discount = new JButton("Discount");
		btn_Discount.setFont(new Font("Arial", Font.PLAIN, 11));
		panel_Main_Left_Btm_Btm_4.add(btn_Discount);

		JPanel panel_Main_Left_Btm_Btm_5 = new JPanel();
		panel_Main_Left_Btm_Btm_5.setPreferredSize(new Dimension(0, 0));
		panel_Main_Left_Btm_Btm.add(panel_Main_Left_Btm_Btm_5);
		panel_Main_Left_Btm_Btm_5.setLayout(new BorderLayout(0, 0));

		btn_Pay = new JButton("Pay");
		panel_Main_Left_Btm_Btm_5.add(btn_Pay);

		JPanel panel_Main_Right = new JPanel();
		panel_Main_Right.setPreferredSize(new Dimension(300, 10));
		split_Main.setRightComponent(panel_Main_Right);
		panel_Main_Right.setLayout(new GridLayout(1, 0, 0, 0));

		JSplitPane split_Main_Right = new JSplitPane();
		split_Main_Right.setEnabled(false);
		split_Main_Right.setDividerSize(0);
		split_Main_Right.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_Main_Right.add(split_Main_Right);

		JPanel panel_Main_Right_Top = new JPanel();
		panel_Main_Right_Top.setBackground(Color.DARK_GRAY);
		split_Main_Right.setLeftComponent(panel_Main_Right_Top);
		panel_Main_Right_Top.setLayout(new GridLayout(1, 1, 0, 0));

		JLabel lbl_Default = new JLabel("Default");
		panel_Main_Right_Top.add(lbl_Default);
		lbl_Default.setForeground(SystemColor.text);
		lbl_Default.setBackground(new Color(0, 0, 0));
		lbl_Default.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_Main_Right_Btm = new JPanel();
		split_Main_Right.setRightComponent(panel_Main_Right_Btm);
		panel_Main_Right_Btm.setLayout(new GridLayout(1, 0, 0, 0));

		scroll_Main_Right_Btm = new JScrollPane();
		panel_Main_Right_Btm.add(scroll_Main_Right_Btm);
		initDefaultFrame();

		btn_X.addActionListener(this);
		btn_Void.addActionListener(this);
		btn_Park.addActionListener(this);
		btn_Notes.addActionListener(this);
		btn_Discount.addActionListener(this);
		btn_Pay.addActionListener(this);
		btn_Add.addActionListener(this);

		addHandler();

	}

	/*
	 * Init goodsSearch List
	 */
	public void initGoodsSearchList() {
		initAllGoodsList();
		// searchgoodslist = new Vector();
		// mDefaultComboBoxModel = new DefaultComboBoxModel(searchgoodslist);
		mDefaultComboBoxModel = new DefaultComboBoxModel();

		// mJComboBox = new JAutoCompleteComboBox(model);
		mJComboBox = new SearchComboBox(mDefaultComboBoxModel,
				this.allgoodslist);
		// mJComboBox = new JComboBox(mDefaultComboBoxModel);
		mJComboBox.setEditable(true);// setEditable(true);
		mJComboBox.setSelectedIndex(-1);

		panel_Main_Left_Top_Left.add(mJComboBox);
	}

	public Vector getgoodslist(String s) {
		System.out.println("public Vector getgoodslist(String s)");
		Vector gsVector = new Vector();
		String gtype = s;
		String sql = "";
		if (gtype.endsWith("allgoodslist")) {
			System.out.println("allgoodslist");
			sql = "SELECT goods_inc_id as id, goods_id,goods_name,goods_price,tax_price,handle,sku,all_price FROM goods";

		} else if (gtype.endsWith("defaultgoodslist")) {
			System.out.println("defaultgoodslist");
			sql = "SELECT goods_default_inc_id as id, goods_id,goods_name,goods_price,tax_price,handle,sku,all_price FROM goods_default";
		}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Config mConfig = new Config();
		String dbname = mConfig.getDBfullPath();
		System.out.println("dbname is : " + dbname);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/" + dbname);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while (rset.next()) {
				Vector v = new Vector(7);
				v.add(0, rset.getInt("id"));
				v.add(1, rset.getInt("goods_id"));
				v.add(2, rset.getString("goods_name"));
				v.add(3, rset.getString("goods_price"));
				v.add(4, rset.getString("tax_price"));
				v.add(5, rset.getString("handle"));
				v.add(6, rset.getString("sku"));
				v.add(7, rset.getString("all_price"));
				gsVector.add(v);
				if (gtype.equals("defaultgoodslist")) {
					System.out.println("rset.getInt(goods_id)"
							+ rset.getInt("goods_id"));
					System.out.println("rsize" + gsVector.size());
					defaultid2goods_id.put(gsVector.size() - 1,
							rset.getInt("goods_id"));
					goods_id2defaultid.put(rset.getInt("goods_id"),
							(int) gsVector.size() - 1);

				} else if (gtype.equals("allgoodslist")) {
					goods_id2id.put(rset.getInt("goods_id"),
							gsVector.size() - 1);
					id2goods_id.put(gsVector.size() - 1,
							rset.getInt("goods_id"));
				}
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("can't find class drive " + cnfe.getMessage());
			System.exit(-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gsVector;
	}

	/**
	 * params null
	 * 
	 * select all data from database then add data to Global Var Vector
	 * allgoodslist; use in func initGoodsSearchList
	 * 
	 * */
	public void initAllGoodsList() {
		allgoodslist = new Vector();
		allgoodslist = getgoodslist("allgoodslist");
	}

	/**
	 * init the default goods on the right of the panel.
	 * 
	 * **/

	void initDefaultFrame() {

		String headName[] = { "Count", "Name", "Price", "Price", "Act" };
		defaultcontent = new Vector();
		defaultgoodslist = getgoodslist("defaultgoodslist");

		if (defaultgoodslist.size() > 0) {
			System.out.println(defaultgoodslist.size());
			int j = 0;
			Vector vec = new Vector(5);
			// for(int m=0;m<5;m++){
			// vec.add(m,((Vector) defaultgoodslist.get(m)).get(1));
			// }
			// defaultcontent.add(vec);
			//

			for (int i = 0; i < defaultgoodslist.size(); i++) {
				if (j >= 5) {
					j = 0;
				}
				System.out.println("i is " + i + "; j is " + j);
				vec.add(j, ((Vector) defaultgoodslist.get(i)).get(2));
				if (j == 4) {
					defaultcontent.add(vec);
					vec = null;
					vec = new Vector(5);
				}
				if (i == defaultgoodslist.size() - 1) {
					System.out.println("start to do");
					if (j != 4) {
						for (int k = j + 1; k < 5; k++) {
							System.out.println("k is " + k);
							vec.add(k, "");
						}
						if (j < 4) {
							defaultcontent.add(vec);
						}
					}
				}
				j++;
			}

		}

		tMyTableModel = new PayTableModel(headName, defaultcontent);
		defaulttable = new JTable(tMyTableModel);
		defaulttable.setCursor(Cursor
				.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		defaulttable.setPreferredScrollableViewportSize(new Dimension(250, 10));
		defaulttable.setDefaultRenderer(JButton.class,
				new ComboBoxCellRenderer());
		defaulttable.getTableHeader().setPreferredSize(new Dimension(0, 20));
		defaulttable.getTableHeader().setVisible(false);
		defaulttable.setRowHeight(50);
		defaulttable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		defaulttable.getTableHeader().setReorderingAllowed(false);
		defaulttable.getTableHeader().setResizingAllowed(false);

		// defaulttable.getColumnModel().getColumn(0).setPreferredWidth(45);
		// defaulttable.getColumnModel().getColumn(1).setPreferredWidth(200);
		// defaulttable.getColumnModel().getColumn(2).setPreferredWidth(40);
		// defaulttable.getColumnModel().getColumn(3).setPreferredWidth(40);
		// defaulttable.getColumnModel().getColumn(4).setPreferredWidth(10);
		scroll_Main_Right_Btm.setViewportView(defaulttable);

	}

	/**
	 * init table below the searchtextfield.
	 * 
	 * **/

	void initTable() {

		String headName[] = { "goods_id", "Count", "Name", "Price", "Price",
				"Act" };
		String path = "";
		JButton mJButton = new JButton("X");
		content = new Vector();

		// Connection conn = null;
		// Statement stmt = null;
		// ResultSet rset = null;
		// Config mConfig=new Config();
		// String dbname=mConfig.getDBfullPath();
		// System.out.println("dbname is : "+ dbname);
		// try {
		// Class.forName("org.sqlite.JDBC");
		// conn = DriverManager.getConnection("jdbc:sqlite:/"+dbname);
		// conn.setAutoCommit(false);
		// stmt = conn.createStatement();
		// rset = stmt.executeQuery("SELECT * FROM goods");
		// while (rset.next()) {
		// Vector v = new Vector(5);
		// v.add(0, rset.getInt("id"));
		// v.add(1, rset.getString("goods_name"));
		// v.add(2, rset.getString("goods_price"));
		// v.add(3, rset.getString("tax_price"));
		// v.add(4, mJButton);
		// content.add(v);
		// }
		// rset.close();
		// stmt.close();
		// conn.close();
		// } catch (ClassNotFoundException cnfe) {
		// System.out.println("can't find class drive " + cnfe.getMessage());
		// System.exit(-1);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		mMyTableModel = new PayTableModel(headName, content);
		mtable = new JTable(mMyTableModel);
		mtable.setPreferredScrollableViewportSize(new Dimension(250, 10));
		mtable.setDefaultRenderer(JButton.class, new ComboBoxCellRenderer());
		mtable.getTableHeader().setPreferredSize(new Dimension(0, 20));
		mtable.setRowHeight(30);
		mtable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		mtable.getTableHeader().setReorderingAllowed(false);
		mtable.getTableHeader().setResizingAllowed(false);
		mtable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
		mtable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(20);
		mtable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(20);
		mtable.getColumnModel().getColumn(0).setMaxWidth(0);
		mtable.getColumnModel().getColumn(0).setPreferredWidth(0);
		mtable.getColumnModel().getColumn(0).setMinWidth(0);
		mtable.getColumnModel().getColumn(1).setPreferredWidth(45);
		mtable.getColumnModel().getColumn(2).setPreferredWidth(200);
		mtable.getColumnModel().getColumn(3).setPreferredWidth(40);
		mtable.getColumnModel().getColumn(4).setPreferredWidth(40);
		mtable.getColumnModel().getColumn(5).setPreferredWidth(10);
		scroll_panel_Main_Left_Btm_Top_Top_Btm.setViewportView(mtable);

	}

	public void addHandler() {
		mtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("table");
				int row = mtable.getSelectedRow();
				int column = mtable.getSelectedColumn();
				System.out.println("row=" + row + ":" + "column=" + column);
				if (column == 5) {
					updatetable("delete", row);

				}
			}
		});

		defaulttable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("table");
				int row = defaulttable.getSelectedRow();
				int column = defaulttable.getSelectedColumn();
				System.out.println("row=" + row + ":" + "column=" + column);
				System.out.println(defaulttable.getValueAt(row, column));
				if (defaulttable.getValueAt(row, column) != "") {
					System.out.println("add data to list");
					int mindex = row * 5 + column;
					updatetable("insertdefaultgoodslist", mindex);
					// mMyTableModel.addRow(((Vector)
					// defaultgoodslist.get(mindex)));
					// mtable.updateUI();
				}
			}
		});
		searchTextField = (JTextField) mJComboBox.getEditor()
				.getEditorComponent();
		searchTextField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				System.out.println("public void keyTyped(KeyEvent e Main)");

			}

			public void keyPressed(KeyEvent e) {
				System.out.println("public void keyPressed(KeyEvent e Main)");
				if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
					keyflag = true;
				} else {
					keyflag = false;
				}
			}

			public void keyReleased(KeyEvent e) {
				System.out.println("public void keyReleased(KeyEvent e Main)");
				System.out.println("keyreleased is " + e.getKeyCode());

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out
							.println("press enter to select ,start to add to list");
					// Add
					addsearchtexttolist();
					// String s = searchTextField.getText().toString();
				}
			}
		});
		mJComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				System.out
						.println("public void itemStateChanged(ItemEvent event) main");
				if (event.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(mJComboBox.getItemCount());
					if (!keyflag) {
						System.out
								.println("click to select , start to add to list");
						// Add
						addsearchtexttolist();
					}
				}
			}

		});

		// searchTextField = (JTextField) mJComboBox.getEditor()
		// .getEditorComponent();
		// mDocument = searchTextField.getDocument();
		// mDocument.addDocumentListener(new DocumentListener() {
		// public void insertUpdate(DocumentEvent e) {
		// System.out.println("insertUpdate");
		// String m = searchTextField.getText();
		// System.out.println("Input String is : " + m);
		// changeGoodsSearchList(m);
		//
		// }
		//
		// public void removeUpdate(DocumentEvent e) {
		// System.out.println("removeUpdate");
		// String m = searchTextField.getText();
		// System.out.println("Input String is : " + m);
		// changeGoodsSearchList(m);
		// }
		//
		// public void changedUpdate(DocumentEvent e) {
		// System.out.println("changedUpdate");
		// }
		// });

	}

	/**
	 * params null add goods which is in the searchtext to the tablelist
	 * 
	 * **/

	public void addsearchtexttolist() {
		System.out.println("public void addsearchtexttolist()");
		String add = searchTextField.getText().toString();
		if (add.length() == 0) {
			System.out.println("length is 0");
			searchTextFieldsetfocus();
			return;
		}

		searchTextField.setSelectionStart(0);
		searchTextField.setSelectionEnd(searchTextField.getText().toString()
				.length());
		System.out.println("to add string is : " + add);
		Vector v = new Vector(this.allgoodslist);
		for (int i = 0; i < this.allgoodslist.size(); i++) {
			String sss = this.allgoodslist.get(i).toString();
			System.out.println("I : " + i + " is " + sss);
			if (sss.equals(add)) {
				updatetable("insertallgoodslist", i);
				// JButton mJButton = new JButton("X");
				// Vector cvector= new Vector((Vector) v.get(i));
				// cvector.add(5,mJButton);
				// mMyTableModel.addRow(cvector);
				// mtable.updateUI();
				// System.out.print(this.allgoodslist);
				break;
			}
		}
	}

	/**
	 * params String dealtype "insert","delete" params int i :id update table
	 * when 1:click default table;2:click table list the button X to
	 * delete;3;searchbox to insert
	 * 
	 * **/
	public void updatetable(String s, int i) {
		System.out.println("public void updatetable(String s,int i)");
		String dtype = s;
		int id = i;
		//
		if (dtype.equals("delete")) {

			mMyTableModel.removeRow(id);
		} else if (dtype.equals("deleteall")) {
			mMyTableModel.removeAllRow();
		}

		else {
			Vector fVector = new Vector();
			if (dtype.equals("insertdefaultgoodslist")) {
				fVector = (Vector) defaultgoodslist.get(id);

			} else if (dtype.equals("insertallgoodslist")) {
				fVector = (Vector) allgoodslist.get(id);
			}
			Vector ffVector = new Vector();
			ffVector.add(0, fVector.get(1));
			ffVector.add(1, 1);
			ffVector.add(2, fVector.get(2));
			ffVector.add(3, fVector.get(3));
			ffVector.add(4, fVector.get(3));
			ffVector.add(5, new JButton("X"));
			mMyTableModel.addRow(ffVector);
		}
		mtable.updateUI();
		updateorder();
	}

	/**
	 * init order variables
	 * 
	 * **/
	public void initorder() {
		this.ordervector = new Vector();
		this.gstincflag = 1;
		this.discount_double = 1;
		this.discount_type = "multiply";
		this.discount_input = "0%";
		this.discount_err = "";

	}

	/**
	 * init customer variables
	 * 
	 * **/
	public void initcustomer() {
		this.cusomervector = new Vector();
	}

	public void initnotes() {
		this.notes = "";

	}

	/**
	 * update order and labels;
	 * 
	 * update ordervector update value of lavels in the frame;
	 * 
	 * **/
	public void updateorder() {
		double topay = 0;
		double alltold = 0;
		double subtold = 0;
		double taxtold = 0;
		this.ordervector.clear();
		for (int i = 0; i < mMyTableModel.getRowCount(); i++) {
			System.out.println("i is :" + i);
			int good_id = (int) mMyTableModel.getValueAt(i, 0);
			int good_count = (int) mMyTableModel.getValueAt(i, 1);
			int tgood_id = (int) this.goods_id2id.get(good_id);
			Vector v = new Vector((Vector) this.allgoodslist.get(tgood_id));
			v.add(good_count);
			subtold += Double.parseDouble((String) v.get(3)) * good_count;
			taxtold += Double.parseDouble((String) v.get(4)) * good_count;
			this.ordervector.add(v);
		}
		if (this.gstincflag == 0) {
			taxtold = 0;
		}
		subtold = Double.parseDouble(String.format("%.2f", subtold));
		taxtold = Double.parseDouble(String.format("%.2f", taxtold));
		if (this.discount_type.equals("multiply")) {
			alltold = (subtold + taxtold) * this.discount_double;
		} else {
			alltold = (subtold + taxtold) - this.discount_double;
		}
		alltold = Double.parseDouble(String.format("%.2f", alltold));
		topay = alltold;
		System.out.println("subtotal : " + subtold + "taxtotal : " + taxtold
				+ "alltotal : " + alltold);
		this.total_subtotal = subtold;
		this.total_tax = taxtold;
		this.total_topay = topay;
		this.total_total = alltold;
		updatelabel();
	}

	/**
	 * update total label
	 * **/
	public void updatelabel() {
		this.lbl_Money_Topay.setText("$" + String.valueOf(this.total_topay));
		this.lbl_Money_Subtotal.setText("$"
				+ String.valueOf(this.total_subtotal));
		this.lbl_Money_Tax.setText("$" + String.valueOf(this.total_tax));
		this.lbl_Money_Total.setText("$" + String.valueOf(this.total_total));
		this.searchTextFieldsetfocus();
	}

	public void searchTextFieldsetfocus() {

		searchTextField.requestFocus();
		searchTextField.setSelectionStart(0);
		searchTextField.setSelectionEnd(searchTextField.getText().toString()
				.length());

	}

	// no use
	public void changeGoodsSearchList(String m) {
		mJComboBox.hidePopup();
		// searchgoodslist.clear();
		searchgoodslist.removeAllElements();
		searchgoodslist = new Vector();
		if (m.length() != 0) {
			System.out.println("Start");
			for (int i = 0; i < allgoodslist.size(); i++) {
				String sss = allgoodslist.get(i).toString();
				System.out.println("allgoodslist string is :" + sss);
				System.out.println(sss.toLowerCase());
				if (sss.toLowerCase().indexOf(m.toLowerCase()) != -1) {
					System.out.println("include :" + m);
					searchgoodslist.addElement(allgoodslist.get(i));
				}
			}
			// mDefaultComboBoxModel2
			// ComboBoxModel mDefaultComboBoxModel2 =
			// mJComboBox.getModel();//new
			// DefaultComboBoxModel(searchgoodslist);
			// DefaultComboBoxModel mDefaultComboBoxModel2 =new
			// DefaultComboBoxModel(searchgoodslist);
			mJComboBox.setModel(new DefaultComboBoxModel(searchgoodslist));
			// mJComboBox.updateUI();
			mJComboBox.showPopup();
		}
	}

	// Main program simply constructs the ButtonDemo

	// application object, and then sizes and shows the window

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_Add) {
			System.out.println("in btn_Add");
			this.click_add();
		} else if (e.getSource() == btn_Retrive_Sale) {
			System.out.println("in btn_retrive_sale");
			this.click_retrive_sale();
		} else if (e.getSource() == btn_Close_Register) {
			System.out.println("in btn_close_register");
			this.click_close_register();
		} else if (e.getSource() == btn_Logout) {
			System.out.println("in btn_Logout");
			this.click_logout();
		} else if (e.getSource() == btn_Quit) {
			System.out.println("in btn_Quit");
			this.click_quit();
		} else if (e.getSource() == btn_Void) {
			System.out.println("in btn_Void");
			this.click_void();
		} else if (e.getSource() == btn_Park) {
			System.out.println("in btn_Park");
			this.click_park();
		} else if (e.getSource() == btn_Notes) {
			System.out.println("in btn_Notes");
			this.click_notes();
		} else if (e.getSource() == btn_Discount) {
			System.out.println("in btn_Discount");
			this.click_discount();
		} else if (e.getSource() == btn_Pay) {
			System.out.println("in btn_Pay");
			this.click_pay();
		} else if (e.getSource() == btn_X) {
			System.out.print("in btn_X");
			this.click_x();
		} else if (e.getSource() == mJComboBox) {
			System.out.print("in mJcomboBox");

		}

	}

	public void click_void() {
		int it = JOptionPane.showConfirmDialog(null, "Do you want to void?",
				"Void", JOptionPane.YES_NO_OPTION);
		if (it == 0) {
			this.initdata();
		}

	}

	public void click_park() {
		if (mMyTableModel.getRowCount() <= 0) {
			System.out.println("no data to park");
			JOptionPane.showMessageDialog(null, "No goods to park!", "Park",
					JOptionPane.PLAIN_MESSAGE);
			return;
		}
		int it = JOptionPane.showConfirmDialog(null, "Do you want to park?",
				"Park", JOptionPane.YES_NO_OPTION);
		if (it == 0) {
			if (!this.save("park")) {
				JOptionPane.showMessageDialog(null, "Failed to park!", "Park",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.initdata();
		}

	}

	public boolean save(String s) {
		String ordersql = this.praseorderssql("pay");
		if (s.equals("park")) {
			ordersql = this.praseorderssql("park");
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Config mConfig = new Config();
		String dbname = mConfig.getDBfullPath();
		System.out.println("dbname is : " + dbname);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/" + dbname);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			System.out.println("sql: " + ordersql);
			int count = stmt.executeUpdate(ordersql);
			conn.commit();

			int id = 0;
			if (count > 0) {// 记录保存成功
				String sql = "select last_insert_rowid()";
				rset = stmt.executeQuery(sql);
				if (rset.next())
					id = rset.getInt(1);
				rset.close();
				Vector osql = this.praseorders_goods_listssql(this.ordervector,
						id);
				for (int i = 0; i < osql.size(); i++) {
					System.out.println("sql : " + osql.get(i).toString());
					System.out.println("stmt "
							+ stmt.executeUpdate(osql.get(i).toString()));
				}
				conn.commit();
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("can't find class drive " + cnfe.getMessage());
			System.exit(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

			}
		}
		return true;
	}

	public Vector praseorders_goods_listssql(Vector v, int s) {
		Vector vv = new Vector();
		for (int i = 0; i < v.size(); i++) {
			Vector t = (Vector) v.get(i);
			String morders_goods_lists_id = null;
			int morders_id = s;
			int mgoods_id = (int) t.get(1);
			String mgoods_name = (String) t.get(2);
			String mall_price = (String) t.get(7);
			String mgoods_price = (String) t.get(3);
			String mtax_price = (String) t.get(4);
			String mhandle = (String) t.get(5);
			String msku = (String) t.get(6);
			String sql = "INSERT INTO orders_goods_lists (orders_goods_lists_id,orders_id,goods_id,goods_name,all_price,goods_price,tax_price,handle,sku) values ("
					+ morders_goods_lists_id
					+ ",'"
					+ morders_id
					+ "'"
					+ ",'"
					+ mgoods_id
					+ "'"
					+ ",'"
					+ mgoods_name
					+ "'"
					+ ",'"
					+ mall_price
					+ "'"
					+ ",'"
					+ mgoods_price
					+ "'"
					+ ",'"
					+ mtax_price
					+ "'"
					+ ",'"
					+ mhandle
					+ "'"
					+ ",'"
					+ msku
					+ "'" + ")";
			vv.add(sql);
		}

		return vv;
	}

	public String praseorderssql(String s) {
		String t = s;
		String morder_id = null;
		String mcustomer_id = "";
		String mcustomer_name = "";
		String mtopay = String.valueOf(this.total_topay);
		String msubtotal = String.valueOf(this.total_subtotal);
		String mtax = String.valueOf(this.total_tax);
		String mtotal = String.valueOf(this.total_total);
		String mnotes = this.notes;
		String mdiscount = this.discount_input;
		String morder_count = String.valueOf(this.mMyTableModel.getRowCount());
		String moperator = "";
		String mshopid = "";
		String mshopname = "";
		String mip = "";
		try {
			mip = InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String muuid = "";
		String mstatus = "";
		String mcreatetime = "datetime()";
		String mmodifytime = "datetime()";

		if (t.equals("park")) {
			mstatus = "0";

		} else if (t.equals("pay")) {
			mstatus = "1";
		}
		if (this.cusomervector.size() > 0) {
			mcustomer_id = (String) this.cusomervector.get(0);
			mcustomer_name = (String) this.cusomervector.get(1);
		}

		String sql = "INSERT INTO orders (orders_id,customer_id,customer_name,topay,subtotal,tax,total,notes,discount,order_count,operator,shopid,shopname,ip,uuid,status,createtime,modifytime) values("
				+ morder_id
				+ ",'"
				+ mcustomer_id
				+ "','"
				+ mcustomer_name
				+ "','"
				+ mtopay
				+ "','"
				+ msubtotal
				+ "','"
				+ mtax
				+ "','"
				+ mtotal
				+ "','"
				+ mnotes
				+ "','"
				+ mdiscount
				+ "','"
				+ morder_count
				+ "','"
				+ moperator
				+ "','"
				+ mshopid
				+ "','"
				+ mshopname
				+ "','"
				+ mip
				+ "','"
				+ muuid
				+ "','"
				+ mstatus
				+ "'," 
				+ mcreatetime 
				+ "," + mmodifytime + ")";
		return sql;
	}

	public int execsqlupdate(String s) {
		int id = 0;
		System.out.println("public boolean execsql(String s)");
		String sql = s;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Config mConfig = new Config();
		String dbname = mConfig.getDBfullPath();
		System.out.println("dbname is : " + dbname);
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/" + dbname);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			conn.commit();
			if (count > 0) {// 记录保存成功
				rset = stmt.getGeneratedKeys();
				if (rset.next())
					id = rset.getInt(1);
				// System.out.println("execsql succes count is : "+count);
				// sql="select last_insert_rowid() from orders";
				// rset=stmt.executeQuery(sql);
				// if(rset.next()) id=rset.getInt(1);
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("can't find class drive " + cnfe.getMessage());
			System.exit(-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public void click_notes() {
		JTextArea text = new JTextArea(this.notes, 4, 30);
		Object[] message = { "Please input notes", new JScrollPane(text) };
		JOptionPane pane = new JOptionPane(message,
				JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(null, "Input");
		dialog.show();
		text.requestDefaultFocus();
		System.out.println(text.getText());
		this.notes = text.getText();
	}

	public void click_discount() {
		String s = JOptionPane.showInputDialog(null,
				"Discount [ percentage or $ amount ]\nE.g. 20% or 2.5 \n"
						+ this.discount_err, this.discount_input);
		if (s == null) {
			System.out.println("no input");
			return;
		}
		try {
			if (s.endsWith("%")) {// discount is percent
				System.out.println("discount is percent multiply");
				this.discount_type = "multiply";
				try {
					this.discount_double = 1 - new Double(Double.parseDouble(s
							.substring(0, s.indexOf("%")))) / 100;
					System.out.println("discount double is : "
							+ this.discount_double);
				} catch (Exception e) {
					this.discount_err = "Input error";
					this.click_discount();
					return;
				}
				this.discount_input = s;
			} else {
				try {// discount is Double
					System.out.println("discount is number minus");
					this.discount_type = "minus";
					this.discount_double = Double.parseDouble(s);
					System.out.println("discount double is : "
							+ this.discount_double);

					this.discount_input = s;
				} catch (Exception e) {
					this.discount_err = "Input error";
					this.click_discount();
					return;
				}
			}
		} catch (Exception e) {
			this.discount_err = "Input error";
			this.click_discount();
			return;
		}

		this.updateorder();
	}

	public void click_pay() {
		if (mMyTableModel.getRowCount() <= 0) {
			System.out.println("no data to park");
			JOptionPane.showMessageDialog(null, "No goods to pay!", "Pay",
					JOptionPane.PLAIN_MESSAGE);
			return;
		}
		int it = JOptionPane.showConfirmDialog(null, "Do you want to pay?",
				"Pay", JOptionPane.YES_NO_OPTION);
		if (it == 0) {
			if (!this.save("pay")) {
				JOptionPane.showMessageDialog(null, "Failed to pay!", "Pay",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.initdata();
		}
	}

	
	public void click_current_sale(){
		
		
	}
	
	public void click_retrive_sale(){
		System.out.println("click_retrive_sale");

		String headName[] = { "Count", "Name", "Price", "Price", "Act","aaa" };
		Vector pcontent = new Vector();
		
		PayTableModel pMyTableModel = new PayTableModel(headName,pcontent);
//		JTable ptable = new JTable(pMyTableModel);
		JTable ptable = new JTable(pMyTableModel);
		ptable.setPreferredScrollableViewportSize(new Dimension(250, 10));
		ptable.setDefaultRenderer(JButton.class, new ComboBoxCellRenderer());
		ptable.getTableHeader().setPreferredSize(new Dimension(0, 20));
		ptable.setRowHeight(30);
		ptable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		ptable.getTableHeader().setReorderingAllowed(false);
		ptable.getTableHeader().setResizingAllowed(false);
		ptable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
		ptable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(20);
		ptable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(20);
		ptable.getColumnModel().getColumn(0).setMaxWidth(0);
		ptable.getColumnModel().getColumn(0).setPreferredWidth(0);
		ptable.getColumnModel().getColumn(0).setMinWidth(0);
		ptable.getColumnModel().getColumn(1).setPreferredWidth(45);
		ptable.getColumnModel().getColumn(2).setPreferredWidth(200);
		ptable.getColumnModel().getColumn(3).setPreferredWidth(40);
		ptable.getColumnModel().getColumn(4).setPreferredWidth(40);
		ptable.getColumnModel().getColumn(5).setPreferredWidth(10);

		JScrollPane pJScrollPane= new JScrollPane();
		pJScrollPane.setViewportView(ptable);
		JDialog pJDialog = new JDialog(this, true);
		pJDialog.getContentPane().setLayout(new BorderLayout());
		pJDialog.getContentPane().add(pJScrollPane);
		JPanel pJPanel = new JPanel();
		pJPanel.setLayout(new GridLayout(1,1,0,0));
		//if need filter button and search box , add here.
//		JButton pButton = new JButton("okok");
//		pButton.addActionListener(new ActionListener() {
//		      public void actionPerformed(ActionEvent e) {
//		  		Vector ve= new Vector();
//		  		ve.add("a");
//		  		ve.add("a");
//		  		ve.add("a");
//		  		ve.add("a");
//		  		ve.add("a");
//		  		ve.add("a");
//
//		  		pcontent.addElement(ve);
//		  		pcontent.addElement(ve);
//		  		pcontent.addElement(ve);
//		  		ptable.updateUI();
//			      }
//			    });
//		pJPanel.add(pButton);
		pJPanel.add(pJScrollPane);
		
		pJDialog.getContentPane().add(pJPanel);
		
		pJDialog.pack();
		pJDialog.setSize(600, 400);
		pJDialog.setLocation(pJDialog.getParent().getX()+(pJDialog.getParent().getWidth() - pJDialog.getWidth()) / 2,
		pJDialog.getParent().getY()+(pJDialog.getParent().getHeight() - pJDialog.getHeight()) / 2);

		

		Vector ve= new Vector();
		ve.add("a");
		ve.add("a");
		ve.add("a");
		ve.add("a");
		ve.add("a");
		ve.add("a");

		pcontent.addElement(ve);
		pcontent.addElement(ve);
		pcontent.addElement(ve);
		pJDialog.show();

//		pJDialog.setVisible(true);
//		pcontent.add(ve);
		ptable.updateUI();


		
		
		
//		JTextArea text = new JTextArea(this.notes, 4, 30);
//		Object[] message = { "Please input notes", new JScrollPane(text) };
//		JOptionPane pane = new JOptionPane(message,
//				JOptionPane.INFORMATION_MESSAGE);
//		JDialog dialog = pane.createDialog(null, "Retrive Sale");
//		dialog.show();
//		System.out.println(text.getText());
//		this.notes = text.getText();
		
	}
	
	public void click_close_register(){
		
		
	}


	public void click_logout() {
		LoginForm mLoginForm = new LoginForm();
		mLoginForm.setTitle("Login");
		mLoginForm.setVisible(true);
		this.dispose();
	}
	
	public void click_quit() {
		System.exit(0);

	}

	public void click_add() {

		addsearchtexttolist();

	}
	public void click_x() {
		if (this.gstincflag == 0) {
			this.gstincflag = 1;
		} else {
			this.gstincflag = 0;
		}
		this.updateorder();

	}

	public static void main(String[] args) {
		MainForm app = new MainForm();
		app.setTitle("Test");
		app.setSize(900, 600);
		app.show();
		app.searchTextFieldsetfocus();
		// app.changeGoodsSearchList("87");

	}

}