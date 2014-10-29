package bak;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JList;
public class MainGUI extends JFrame {
	private static JPanel panel;
	private static JButton btn2;
	private static JCheckBox chk;
	private static MainGUI frame;
	
	
	public static void main(String[] args) {
		createGUI();
	}
	public static void createGUI() {
		try {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			frame = new MainGUI();
			frame.setTitle("window1");
			frame.setSize(100,200);
			panel = new JPanel();
			chk = new JCheckBox("test");
			chk.setSelected(true);
			btn2 = new JButton("set invisible");
			btn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
//					frame.setVisible(false);
			CopyOfMainGUI frame2 = new CopyOfMainGUI(frame);
					frame2.createGUI();
				}
			});
			FlowLayout layout = new FlowLayout();
			layout.setAlignment(0);
			panel.setLayout(layout);
			panel.add(chk);
			frame.add(panel, BorderLayout.CENTER);
			frame.add(btn2, BorderLayout.NORTH);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}