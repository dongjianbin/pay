package src;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class SearchList extends JFrame {
	private static JButton btn;
	private SearchList frame2;
	private MainForm frame1;

	public SearchList(MainForm frame) {
		frame1 = frame;
	}

	public void createGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			frame2 = new SearchList(frame1);
			frame2.setTitle("window2");
			frame2.setSize(200, 200);
			btn = new JButton("set visible");
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						frame1.setVisible(true);
						frame2.setVisible(false);
					} catch (HeadlessException e) {
						e.printStackTrace();
					}
				}
			});
			FlowLayout layout = new FlowLayout();
			layout.setAlignment(0);
			frame2.add(btn, BorderLayout.SOUTH);
			frame2.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}