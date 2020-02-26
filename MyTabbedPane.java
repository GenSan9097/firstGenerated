import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.event.*;
import javax.swing.text.Document;

import java.awt.*;
import java.util.ArrayList;


public class MyTabbedPane extends JTabbedPane implements DocumentListener {
	
	ArrayList<MyTextArea> textareas;
	
	MyTabbedPane() {
		textareas = new ArrayList<MyTextArea>();
		textareas.add(new MyTextArea() );
		textareas.get(0).getDocument().addDocumentListener(this);
		
		addTab(textareas.get(0).getFileName(), textareas.get(0).getScrollPane() );
		
	}
	
	public JTextArea getTextAt(int index) {
		return textareas.get(index);
	}
	
	/*新規タブを無題で作る*/
	public void addMyTab() {
		int index = getSelectedIndex();
		textareas.add(index + 1, new MyTextArea() );
		getTextAt(index + 1).getDocument().addDocumentListener(this);
		addTab(getTextAt(index + 1).getFileName(), getTextAt(index + 1).getScrollPane() );
		setSelectedIndex(index + 1);
	}
	
	public void deleteMyTab(JFrame frame) {
		int index = getSelectedIndex();
		int yesNoCancelOpt = 0;
		if (getTextAt(index).isFileSaved() == false) {
			yesNoCancelOpt = getTextAt(index).notSavedAllert(frame, this, index);
		}
		if (yesNoCancelOpt != 2) {
			remove(index);
			textareas.remove(index);
		}
	}
	
	/*以下、MyTextAreaからのオーバーライド*/
	public void createFile(JFrame frame) {
		int index = getSelectedIndex();
		getTextAt(index).createFile(frame, this, index);
	}
	
	public void openFile(JFrame frame) {
		int index = getSelectedIndex();
		getTextAt(index).openFile(frame, this, index);
	}
	
	public void saveAs(JFrame frame) {
		int index = getSelectedIndex();
		getTextAt(index).saveAs(frame, this, index);
	}
	
	public void save(JFrame frame) {
		int index = getSelectedIndex();
		getTextAt(index).save(frame, this, index);
	}
	
	public void copy() {
		int index = getSelectedIndex();
		getTextAt(index).copy();
	}
	
	public void cut() {
		int index = getSelectedIndex();
		getTextAt(index).cut();
	}
	
	public void paste() {
		int index = getSelectedIndex();
		getTextAt(index).paste();
	}
	
	/*次の3つのメソッドはテキストエリアに変更が加えられた時の処理*/
	public void insertUpdate(DocumentEvent e_D) {
		int index = getSelectedIndex();
		setTitleAt(index, "*" + getTextAt(index).getFileName());
		getTextAt(index).savedNot();
	}
	
	public void removeUpdate(DocumentEvent e_D) {
		int index = getSelectedIndex();
		setTitleAt(index, "*" + getTextAt(index).getFileName());
		getTextAt(index).savedNot();

	}
	public void changedUpdate(DocumentEvent e_D) {
		int index = getSelectedIndex();
		setTitleAt(index, "*" + getTextAt(index).getFileName());
		getTextAt(index).savedNot();
	}
}