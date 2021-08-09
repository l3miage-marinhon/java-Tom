package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class Application {
	
	/*
	public Application() {
		super("Test première fenètre");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		this.setSize(new Dimension(1000, 800));
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		Application appli = new Application();
		appli.setVisible(true);
	}
	*/
	private JFrame frmApplication;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Application() {
		initialize();
	}
	
	public void initialize() {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frmApplication = new JFrame();
		frmApplication.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmApplication.setSize(new Dimension(1000, 800));
		frmApplication.setLocationRelativeTo(null);
		
		JPanel content = (JPanel) frmApplication.getContentPane();
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.setBorder(new EmptyBorder(200, 200, 220, 200));
		JPanel menu1 = new JPanel();
		menu1.setLayout(new GridLayout(1, 2, 20, 20));
		JButton btnClient = new JButton("CLIENT");
		JButton btnQuinc = new JButton("QUINCAILLERIE");
		content.add(menu1);
		menu1.add(btnClient);
		menu1.add(btnQuinc);
		
		/*
		content.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel menu1 = new JPanel();
		menu1.setLayout(new BoxLayout(menu1, BoxLayout.Y_AXIS));
		//content.setBorder(new EmptyBorder(200, 200, 220, 200));
		
		
		frmApplication.setTitle("Application Quincaillerie");
		JButton btnClient = new JButton("CLIENT");
		btnClient.setPreferredSize(new Dimension(300, 200));
		JButton btnQuinc = new JButton("QUINCAILLERIE");
		btnQuinc.setPreferredSize(new Dimension(300, 200));
		menu1.add(btnClient);
		menu1.add(btnQuinc);
		content.add(menu1);
	*/	
	}
}
