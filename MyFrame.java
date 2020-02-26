import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener {
	
	/*menuiteを増やすときは以下の5ステップを守ること*/
	//1.フィールドに宣言
	//2.newでインスタンス化
	//3.アクションリスナーを付ける
	//4.menuにaddする
	//5.actionPerformedメソッドに処理を追加
	
	private JMenuItem menuitem0_0, menuitem0_1, menuitem0_2, menuitem0_3;
	private JMenuItem menuitem1_0, menuitem1_1, menuitem1_2;
	private JMenuItem menuitem9_0, menuitem9_1;
	MyTabbedPane tabbedpane;
	
	MyFrame() {
		setTitle("MyTextEditor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
		ImageIcon icon = new ImageIcon("C:/Users/ヤス/Desktop/java_files/MyTextEditor1_0/Tx.png");
		setIconImage(icon.getImage() );
		
		tabbedpane = new MyTabbedPane();
		getContentPane().add(tabbedpane);
		
		
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu menu0 = new JMenu("ファイル");
		JMenu menu1 = new JMenu("編集");
		JMenu menu9 = new JMenu("タブ");
		
		menuitem0_0 = new JMenuItem("新規作成");
		menuitem0_1 = new JMenuItem("開く");
		menuitem0_2 = new JMenuItem("上書き保存");
		menuitem0_3 = new JMenuItem("名前を付けて保存");
				
		menuitem1_0 = new JMenuItem("コピー");
		menuitem1_1 = new JMenuItem("切り取り");
		menuitem1_2 = new JMenuItem("貼り付け");
		
		menuitem9_0 = new JMenuItem("新規タブで新規ファイルを作成");
		menuitem9_1 = new JMenuItem("現在のタブを閉じる");
		menuitem9_1.setEnabled(true);
		
		menuitem0_0.addActionListener(this);
		menuitem0_1.addActionListener(this);
		menuitem0_2.addActionListener(this);
		menuitem0_3.addActionListener(this);
		
		menuitem1_0.addActionListener(this);
		menuitem1_1.addActionListener(this);
		menuitem1_2.addActionListener(this);
		
		menuitem9_0.addActionListener(this);
		menuitem9_1.addActionListener(this);
		
		menuitem0_0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK) );
		menuitem0_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK) );
		menuitem0_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK) );
		menuitem0_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK) );
		menuitem1_0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK) );
		menuitem1_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK) );
		menuitem1_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK) );
		
		menuitem9_0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK) );
		menuitem9_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK) );
		
		
		menu0.add(menuitem0_0);
		menu0.add(menuitem0_1);
		menu0.add(menuitem0_2);
		menu0.add(menuitem0_3);
		menu1.add(menuitem1_0);
		menu1.add(menuitem1_1);
		menu1.add(menuitem1_2);
		menu9.add(menuitem9_0);
		menu9.add(menuitem9_1);
		
		menubar.add(menu0);
		menubar.add(menu1);
		menubar.add(menu9);
		setJMenuBar(menubar);
	}
	
	public void actionPerformed(ActionEvent eAct) {
		Object obj = eAct.getSource();
		if (obj == menuitem0_0) {
			tabbedpane.createFile(this);
		} else if (obj == menuitem0_1) {
			tabbedpane.openFile(this);
		} else if (obj == menuitem0_2) {
			tabbedpane.save(this);
		} else if (obj == menuitem0_3) {
			tabbedpane.saveAs(this);
		} else if (obj == menuitem1_0) {
			tabbedpane.copy();
		} else if (obj == menuitem1_1) {
			tabbedpane.cut();
		} else if (obj == menuitem1_2) {
			tabbedpane.paste();
		} else if (obj == menuitem9_0) {
			tabbedpane.addMyTab();
		} else if (obj == menuitem9_1) {
			tabbedpane.deleteMyTab(this);
			if (tabbedpane.getTabCount() == 0) {
				menuitem0_0.setEnabled(false);
				menuitem0_1.setEnabled(false);
				menuitem0_2.setEnabled(false);
				menuitem0_3.setEnabled(false);
				menuitem1_0.setEnabled(false);
				menuitem1_1.setEnabled(false);
				menuitem1_2.setEnabled(false);
				menuitem9_1.setEnabled(false);
			}
		}
	}
	
}