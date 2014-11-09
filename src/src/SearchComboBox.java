package src;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.util.Collections;
import java.util.Arrays;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.text.Document;
import java.awt.Robot;


/**
 * @author SamZheng 带有自动检查功能的CombBox
 */
public class SearchComboBox extends JComboBox {
	private AutoCompleter completer;
	private Vector orilist;

	public void setOrilist(Vector v) {
		System.out.println("public void setOrilist(Vector v)");
		orilist = v;
	}

	public Vector getOrilist() {
		System.out.println("public Vector getOrilist()");
		return this.orilist;

	}

	public SearchComboBox() {
		super();
		System.out.println("public SearchComboBox()");
		addCompleter();
	}

	public SearchComboBox(ComboBoxModel cm) {
		super(cm);
		System.out.println("public SearchComboBox(ComboBoxModel cm)");
		setEditable(true);
		addCompleter();
	}

	public SearchComboBox(DefaultComboBoxModel dcm, Vector v) {
		super(dcm);
		System.out.println("public SearchComboBox(DefaultComboBoxModel dcm, Vector v)");
		this.orilist = v;
//		setEditable(true);
		addCompleter();
	}

	public SearchComboBox(Object[] items) {
		super(items);
		System.out.println("public SearchComboBox(Object[] items)");
		addCompleter();
	}

	public SearchComboBox(List v) {
		super((Vector) v);
		System.out.println("public SearchComboBox(List v)");
		addCompleter();
	}

	private void addCompleter() {
		System.out.println("private void addCompleter()");
		setEditable(true);
		completer = new AutoCompleter(this);
	}

	public void autoComplete(String str) {
		System.out.println("public void autoComplete(String str)");
		this.completer.autoComplete(str, str.length());
	}

	public String getText() {
		System.out.println("public String getText()");
		return ((JTextField) getEditor().getEditorComponent()).getText();
	}

	public void setText(String text) {
		System.out.println("public void setText(String text)");
		((JTextField) getEditor().getEditorComponent()).setText(text);
	}

	public boolean containsItem(String itemString) {
		System.out.println("public boolean containsItem(String itemString)");
		for (int i = 0; i < this.getModel().getSize(); i++) {
			String _item = "" + this.getModel().getElementAt(i);
			if (_item.equals(itemString)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 测试方法
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Object[] items = new Object[] { "zzz", "zba", "aab", "abc", "aab",
				"dfg", "aba", "hpp", "pp", "hlp" };

		Vector labels1 = new Vector();
		Vector labels2 = new Vector();
		labels2.add("aaa");
		labels2.add("bcd");
		labels2.add("efg");
		labels2.add("sda");
		labels2.add("adef");
		DefaultComboBoxModel mDefaultComboBoxModel = new DefaultComboBoxModel();
//		DefaultComboBoxModel mDefaultComboBoxModel = new DefaultComboBoxModel(labels2);
		// 排序内容
		// java.util.ArrayList list = new
		// java.util.ArrayList(Arrays.asList(items));
		// Collections.sort(list);
		// JComboBox cmb = new JAutoCompleteComboBox(list.toArray());
//		Arrays.sort(items);
		JComboBox cmb = new SearchComboBox(mDefaultComboBoxModel, labels2);
//		JComboBox cmb = new JComboBox(mDefaultComboBoxModel);
		cmb.setEditable(true);
		cmb.setSelectedIndex(-1);
		frame.getContentPane().add(cmb);
		frame.setSize(400, 80);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/**
 * 自动完成器。自动找到最匹配的项目，并排在列表的最前面。
 * 
 * @author Justin Dong
 */

class AutoCompleter implements KeyListener, ItemListener {

	private JComboBox owner = null;
	private JTextField editor = null;

	private ComboBoxModel model = null;
	private Vector orilist = null;
	private Document mDocument = null;
	private Robot robot;

	public AutoCompleter(SearchComboBox comboBox) {
		System.out.println("public AutoCompleter(SearchComboBox comboBox)");
		owner = comboBox;
		orilist = ((SearchComboBox) owner).getOrilist();
		System.out.println(orilist.toString());
		editor = (JTextField) comboBox.getEditor().getEditorComponent();
//		mDocument = editor.getDocument();
//		mDocument.addDocumentListener(new DocumentListener() {
//			public void insertUpdate(DocumentEvent e) {
//				System.out.println("insertUpdate");
//				String m = editor.getText();
//				System.out.println("Input String is : " + m);
//
//
//			}
//
//			public void removeUpdate(DocumentEvent e) {
//				System.out.println("removeUpdate");
//			}
//
//			public void changedUpdate(DocumentEvent e) {
//				System.out.println("changedUpdate");
//			}
//		});

		editor.addKeyListener(this);
		model = comboBox.getModel();
		owner.addItemListener(this);

	}

	public void keyTyped(KeyEvent e) {
		System.out.println("public void keyTyped(KeyEvent e)");
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("public void keyPressed(KeyEvent e)");
	}

	public void keyReleased(KeyEvent e) {
		System.out.println("public void keyReleased(KeyEvent e)");
		char ch = e.getKeyChar();
//		System.out.println("char is "+ ch);
////		if (ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch)
////				|| ch == KeyEvent.VK_DELETE) {
////			return;
////		}
		if (ch == KeyEvent.CHAR_UNDEFINED ) {
			System.out.println("char undefined");
			return;
		}
//
//		int caretPosition = editor.getCaretPosition();
//		String str = editor.getText();
//		System.out.println("Input strings : " + str + "position :"
//				+ caretPosition);
//		if (str.length() == 0) {
//			return;
//		}
//		// autoComplete(str, caretPosition);
//		autoComplete(str);
		String str = editor.getText();
		autoComplete(str);
	}
//
	protected void autoComplete(String strf) {
		System.out.println("protected void autoComplete(String strf)");

		Vector opts;
		opts = getMatchingVector(strf);
		if (owner != null) {
			model = new DefaultComboBoxModel(opts);
			owner.setModel(model);

			owner.setSelectedItem(null);
//			owner.setSelectedItem(-1);
			editor.setText(strf.trim());
//			 owner.setSelectedIndex(-1);
		}
		if (opts.size() > 0) {
			String str = opts.get(0).toString();
			if (owner != null) {
				try {
					owner.showPopup();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * 自动完成。根据输入的内容，在列表中找到相似的项目.
	 */
	protected void autoComplete(String strf, int caretPosition) {
		System.out.println("protected void autoComplete(String strf, int caretPosition)");
		Object[] opts;
		opts = getMatchingOptions(strf.substring(0, caretPosition));
		if (owner != null) {
			model = new DefaultComboBoxModel(opts);
			owner.setModel(model);
//			owner.setSelectedItem(null);
		}
		if (opts.length > 0) {
			String str = opts[0].toString();
			if (caretPosition > editor.getText().length())
				return;
			editor.setCaretPosition(caretPosition);
			editor.setText(editor.getText().trim().substring(0, caretPosition));
			if (owner != null) {
				try {
					owner.showPopup();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 *
	 * 找到相似的项目, 并且将之排列到数组的最前面。
	 * 
	 * @param str
	 * @return 返回所有项目的列表。
	 */
	protected Object[] getMatchingOptions(String str) {
		System.out.println("protected Object[] getMatchingOptions(String str)");

		List v = new Vector();
		List v1 = new Vector();

		for (int k = 0; k < model.getSize(); k++) {
			Object itemObj = model.getElementAt(k);
			if (itemObj != null) {
				String item = itemObj.toString().toLowerCase();
				if (item.startsWith(str.toLowerCase())) {
					v.add(model.getElementAt(k));
				} else {
					v1.add(model.getElementAt(k));
				}
			} else {
				v1.add(model.getElementAt(k));
			}
		}
		for (int i = 0; i < v1.size(); i++) {
			v.add(v1.get(i));
		}
		if (v.isEmpty()) {
			v.add(str);
		}
		return v.toArray();
	}

	protected Vector getMatchingVector(String str) {
		System.out.println("protected Vector getMatchingVector(String str)");
		System.out.println("Input String is : " + str);
		Vector v = new Vector();
		if (str.length() != 0) {
			for (int i = 0; i < orilist.size(); i++) {
				String sss = orilist.get(i).toString();
				if (sss.toLowerCase().indexOf(str.toLowerCase()) != -1) {
					System.out.println("Do  Have String : \"" + sss +"\",\"" + str + "\"");
					v.addElement(orilist.get(i));
				}else {
					System.out.println("Not Have String : \"" + sss +"\",\"" + str + "\"");
				}
			}
		}
		return v;
	}

	public void itemStateChanged(ItemEvent event) {
		System.out.println("public void itemStateChanged(ItemEvent event)");
		String str = editor.getText();
//		autoComplete(str);
//		if (event.getStateChange() == ItemEvent.SELECTED) {
//
//			System.out.println("bbb");
//			int caretPosition = editor.getCaretPosition();
//			if (caretPosition != -1) {
//				try {
//
//					System.out.println("ccc");
//					// editor.moveCaretPosition(caretPosition);
//				} catch (IllegalArgumentException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
	}
}
