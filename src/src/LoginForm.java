/* 
 * Justin 20141014 15:58 nz
 */

package src;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import src.MainForm;
import javax.swing.SwingConstants;

public  class LoginForm extends JFrame implements ActionListener{

    private JTextField text_UserName;
    private JPasswordField password_Password;
    private JButton btn_Login,btn_Quit;
    public LoginForm()
    {
	
	JPanel panel_Main = new JPanel();
	getContentPane().add(panel_Main, BorderLayout.CENTER);
	panel_Main.setLayout(new GridLayout(3, 1, 0, 0));
	
	JPanel panel_Main_Top = new JPanel();
	panel_Main.add(panel_Main_Top);
	
	JPanel panel_Main_Mid = new JPanel();
	panel_Main.add(panel_Main_Mid);
	panel_Main_Mid.setLayout(new GridLayout(2, 0, 0, 0));
	
	JPanel panel_Main_Mid_Up = new JPanel();
	panel_Main_Mid.add(panel_Main_Mid_Up);
	panel_Main_Mid_Up.setLayout(new GridLayout(0, 2, 0, 0));
	
	JPanel panel_Main_Mid_Up_Left = new JPanel();
	panel_Main_Mid_Up.add(panel_Main_Mid_Up_Left);
	
	JLabel lbl_UserName = new JLabel("Username  :");
	panel_Main_Mid_Up_Left.add(lbl_UserName);
	
	JPanel panel_Main_Mid_Up_Right = new JPanel();
	panel_Main_Mid_Up.add(panel_Main_Mid_Up_Right);
	
	text_UserName = new JTextField();
	panel_Main_Mid_Up_Right.add(text_UserName);
	text_UserName.setColumns(10);
	
	JPanel panel_Main_Mid_Down = new JPanel();
	panel_Main_Mid.add(panel_Main_Mid_Down);
	panel_Main_Mid_Down.setLayout(new GridLayout(0, 2, 0, 0));
	
	JPanel panel_Main_Mid_Down_Left = new JPanel();
	panel_Main_Mid_Down.add(panel_Main_Mid_Down_Left);
	
	JLabel lbl_Password = new JLabel("Password  :");
	panel_Main_Mid_Down_Left.add(lbl_Password);
	
	JPanel panel_Main_Mid_Down_Right = new JPanel();
	panel_Main_Mid_Down.add(panel_Main_Mid_Down_Right);
	
	password_Password = new JPasswordField();
	password_Password.setColumns(10);
	panel_Main_Mid_Down_Right.add(password_Password);
	
	JPanel panel_Main_Btm = new JPanel();
	panel_Main.add(panel_Main_Btm);
	panel_Main_Btm.setLayout(new GridLayout(2, 0, 0, 0));
	
	JPanel panel_Main_Btm_Up = new JPanel();
	panel_Main_Btm.add(panel_Main_Btm_Up);
	panel_Main_Btm_Up.setLayout(new GridLayout(0, 2, 0, 0));
	
	JPanel panel_Main_Btm_Up_Left = new JPanel();
	panel_Main_Btm_Up.add(panel_Main_Btm_Up_Left);
	
	btn_Login = new JButton("Login");
	btn_Login.addActionListener(this);
	panel_Main_Btm_Up_Left.add(btn_Login);
	
	JPanel panel_Main_Btm_Up_Right = new JPanel();
	panel_Main_Btm_Up.add(panel_Main_Btm_Up_Right);
	
	btn_Quit = new JButton("Quit");
	btn_Quit.addActionListener(this);
	panel_Main_Btm_Up_Right.add(btn_Quit);
	
	JPanel panel_Main_Btm_Down = new JPanel();
	panel_Main_Btm.add(panel_Main_Btm_Down);
	this.getRootPane().setDefaultButton(btn_Login);

	this.initForm();
    }
    
    
    
    
    private void initForm(){
	this.setSize(350, 220);
	this.setResizable(false);
	this.setLocationRelativeTo(this);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == btn_Quit){
		    System.exit(0);
    	}
    	else if(e.getSource()== btn_Login){
			String tUserName=this.text_UserName.getText();
			String tPassword=this.password_Password.getText();
			if(tUserName.equals("admin") && tPassword.equals("admin")){
				MainForm mMainForm = new MainForm();
				mMainForm.setTitle("Main");
				mMainForm.setVisible(true);
				this.dispose();
			}
			else{
				this.password_Password.setText("");
				this.text_UserName.requestFocusInWindow();
				
			}
    				
    		
    	}
    	else{
    		
    		
    	}
    	
    }
    

    public static void main(String[] args) {
    	LoginForm mLoginForm = new LoginForm();
    	mLoginForm.setTitle("Please Login");
    	mLoginForm.show();
    }
}