import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class MyTextArea extends JTextArea {
	
	private File file;
	private JFileChooser filechooser;
	private JScrollPane scrollpane;
	private boolean savedOrNot;
	private static FileWriter filewriter;
	private static FileReader filereader;
	private static StringBuilder sb = new StringBuilder();
	
	
	public MyTextArea() {
		setLineWrap(true);
		setFont(new Font("ＭＳ 明朝", Font.PLAIN, 24) );
		
		scrollpane = new JScrollPane(this);
		scrollpane.setViewportView(this);
		
		file = null;
		savedOrNot = true;
		
		filechooser = new JFileChooser("C:/Users/ヤス/Desktop");
		
	}
	
	public boolean isFileSaved() {
		return savedOrNot;
	}
	
	public void successfullySaved() {
		savedOrNot = true;
	}
	
	public void savedNot() {
		savedOrNot = false;
	}
	
	public String getFileName() {
		if (file == null) {
			return "無題";
		} else {
			return file.getName();
		}
	}
	
	public Component getScrollPane() {
		return scrollpane;
	}
	
	public void createFile(JFrame frame, JTabbedPane tabbedpane, int index) {
		int yesNoCancelOpt = 0;
		if (isFileSaved() == false) {
			yesNoCancelOpt = notSavedAllert(frame, tabbedpane, index);
		}
		if (yesNoCancelOpt != 2) {
			file = null;
			setText("");
			tabbedpane.setTitleAt(index, getFileName() );
			successfullySaved();
		}
	}
	
	public void openFile(JFrame frame, JTabbedPane tabbedpane, int index) {
		int yesNoCancelOpt = 0;
		if (isFileSaved() == false) {
			yesNoCancelOpt = notSavedAllert(frame, tabbedpane, index);
		}
		
		if (yesNoCancelOpt != 2) {
			int selected = filechooser.showOpenDialog(this);
			if (selected == JFileChooser.APPROVE_OPTION) {		/*APPROVE以外の処理も必要*/
				file = filechooser.getSelectedFile();
				
				try {
					filereader = new FileReader(file);
					
					int ch;
					while ( (ch = filereader.read() ) != -1) {
						sb.append( (char)ch);
					}
					
					setText(sb.toString() );
					sb.setLength(0);
					filereader.close();
					tabbedpane.setTitleAt(index, getFileName() );
					successfullySaved();
					
				} catch (FileNotFoundException e_FNF) {
						System.out.println(e_FNF);
				} catch (IOException e_IO) {
					System.out.println(e_IO);
				}
			}
		}
	}
	
	public void saveAs(JFrame frame, JTabbedPane tabbedpane, int index) {
		int selected = filechooser.showSaveDialog(this);
		if(selected == JFileChooser.APPROVE_OPTION) {
			file = filechooser.getSelectedFile();
			try {
				filewriter = new FileWriter(file);
				filewriter.write(getText() );
				filewriter.close();
				tabbedpane.setTitleAt(index, getFileName() );
				successfullySaved();
				
			} catch(IOException e_IO) {
				System.out.println(e_IO);
			}
		}
	}
	
	public void save(JFrame frame, JTabbedPane tabbedpane, int index) {
		if(file == null) {
			saveAs(frame, tabbedpane, index);
		} else {
			try {
				filewriter = new FileWriter(file);
				filewriter.write(getText() );
				filewriter.close();
				tabbedpane.setTitleAt(index, getFileName() );
				successfullySaved();
			} catch (IOException e_IO) {
				System.out.println(e_IO);
			}
		}
	}
	
	/*保存されていないファイルを閉じようとした時の警告*/
	public int notSavedAllert(JFrame frame, JTabbedPane tabbedpane, int index) {
		int option;
		option = JOptionPane.showConfirmDialog(
			frame, "ファイル名:" + getFileName() + "\n内容が変更されています。保存しますか?", "MyTextEditor",
			JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
		);
		
		if (option == 0) {		/*「はい」選択時*/
			save(frame, tabbedpane, index);
		}
		
		return option;
	}
}
