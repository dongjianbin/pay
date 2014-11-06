package bak;


import java.awt.BorderLayout;   
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
  

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;   
import javax.swing.JButton;   
import javax.swing.JComboBox;   
import javax.swing.JFrame;   
import javax.swing.JPanel;   
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
  
public class ComboSample {   
	public static void main(String args[]) {
		Vector labels= new Vector();
		labels.add("aaa");
		labels.add("bcd");
		labels.add("efg");
		labels.add("sda");
		labels.add("adef");
		Vector labels2= new Vector();
		labels2.add("aaa");
		labels2.add("bcd");
		labels2.add("efg");
		labels2.add("sda");
		labels2.add("adef");
		
//	    final String labels[] = { "A", "B", "C", "D", "E", "F", "G" };   
	  
	    final DefaultComboBoxModel model = new DefaultComboBoxModel(labels);   
	  
	    JFrame frame = new JFrame("Shared Data");   
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	  
	    JPanel panel = new JPanel();   
	    JComboBox comboBox1 = new JComboBox(model);   
	    comboBox1.setEditable(true); 

	    comboBox1.setSelectedIndex(-1);
	   
	    panel.add(comboBox1);   
	    frame.add(panel, BorderLayout.NORTH);   

	    JButton button = new JButton("Add");   
	    frame.add(button, BorderLayout.SOUTH);   
	    JButton button2 = new JButton("Del");   
	    frame.add(button2,BorderLayout.EAST);   
	    ActionListener actionListener = new ActionListener() {   
		      public void actionPerformed(ActionEvent actionEvent) {   
		    	  labels.addElement("New Added");   
		      }   
		    };   
		    button.addActionListener(actionListener);  
		    ActionListener actionListener2 = new ActionListener() {   
			      public void actionPerformed(ActionEvent actionEvent) {   
			    	  labels.removeAllElements();
			      }   
			    };   
			    button2.addActionListener(actionListener2); 
	  
	    frame.setSize(300, 200);   
	    frame.setVisible(true);   
	    
	    JTextField searchTextField = (JTextField) comboBox1.getEditor()
				.getEditorComponent();
	    Document mDocument = searchTextField.getDocument();
		mDocument.addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				System.out.println("insertUpdate");
				String m = searchTextField.getText();
				System.out.println("Input String is : " + m);
//				changeGoodsSearchList(m);
				labels.addElement("aa");
				comboBox1.updateUI();
				comboBox1.showPopup();

			}

			public void removeUpdate(DocumentEvent e) {
				System.out.println("removeUpdate");
				String m = searchTextField.getText();
				System.out.println("Input String is : " + m);
//				changeGoodsSearchList(m);

				labels.removeAllElements();
				comboBox1.showPopup();
			}

			public void changedUpdate(DocumentEvent e) {
				System.out.println("changedUpdate");
			}
		});
	  }
	
}   

