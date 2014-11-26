package bak;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame{

	  private JDialog SysManage;

	  public MainFrame() {
	    SysManage = new JDialog(this, true);
	    SysManage.getContentPane().setLayout(new BorderLayout());
	    SysManage.getContentPane().add(new JLabel("This is SysManage"),
	        BorderLayout.CENTER);
	    JButton cancel = new JButton("CANCEL");
	    cancel.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        SysManage.dispose();
	      }
	    });
	    SysManage.getContentPane().add(cancel, BorderLayout.SOUTH);
	    
	    
	    
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(new JLabel("This is MainFrame"),
	        BorderLayout.CENTER);
	    JButton ok = new JButton("OK");
	    ok.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        SysManage.pack();
	        SysManage.setLocation(SysManage.getParent().getX()+(SysManage.getParent().getWidth() - SysManage.getWidth()) / 2,
	            SysManage.getParent().getY()+(SysManage.getParent().getHeight() - SysManage.getHeight()) / 2);
	        SysManage.setVisible(true);
	      }
	    });
	    getContentPane().add(ok, BorderLayout.SOUTH);
	    setLocation((getToolkit().getScreenSize().width - getWidth()) / 2,
	        (getToolkit().getScreenSize().height - getHeight()) / 2);
	    setVisible(true);
	    pack();
	  }
	 
	  public static void main(String[] args) {
	    new MainFrame();
	  }
}