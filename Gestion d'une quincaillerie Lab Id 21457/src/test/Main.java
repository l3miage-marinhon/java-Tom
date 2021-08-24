package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class Main extends JPanel {
   private static final Dimension THUMB_SIZE = new Dimension(170, 150);
   private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
   private JPanel thumbPanel = new JPanel(new GridLayout(0, 6, 5, 5));

   public Main() {
      JPanel holderPanel = new JPanel(new BorderLayout());
      holderPanel.add(thumbPanel, BorderLayout.NORTH);
      holderPanel.add(Box.createGlue(), BorderLayout.CENTER);

      setLayout(new BorderLayout());
      add(new JScrollPane(holderPanel), BorderLayout.CENTER);
   }

   public JMenuBar createMenuBar() {
      JMenuBar top_menu_bar = new JMenuBar();
      JMenu main_menu = new JMenu("Menu");
      main_menu.setMnemonic(KeyEvent.VK_M);
      top_menu_bar.add(main_menu);
      JMenuItem menu_item;

      menu_item = new JMenuItem("Add New");
      menu_item.setMnemonic(KeyEvent.VK_N);
      menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
            ActionEvent.ALT_MASK));
      menu_item.setActionCommand("new");
      menu_item.addActionListener(e->createThumb());
      main_menu.add(menu_item);
      return top_menu_bar;
   }
   protected void createThumb() {
      JButton thumb = new JButton("Thumb");
      thumb.setPreferredSize(THUMB_SIZE);
      thumbPanel.add(thumb);
      revalidate();
      repaint();
   }
   @Override
   public Dimension getPreferredSize() {
      return new Dimension(screen.width / 10 * 6,
            screen.height / 10 * 6);
   }

   public static void main(String[] args) {
      Main mainPanel = new Main();
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.getContentPane().add(mainPanel);
      f.setJMenuBar(mainPanel.createMenuBar());
      f.pack();
      f.setVisible(true);
   }
}
