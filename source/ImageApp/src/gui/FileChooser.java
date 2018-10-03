package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

public class FileChooser extends JFrame {

	private JPanel contentPane;
	private FileNameExtensionFilter filter;
	private static File file;
	private JFileChooser jchooser;

	public FileChooser(String title,String fileType ) {
		
		
		JFileChooser input = new JFileChooser();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif");
		input.setFileFilter(filter);

		int result = input.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    file=input.getSelectedFile();
		    System.out.println("file just got");
		    System.out.println(file.getAbsolutePath());
		   // input.showSaveDialog(null);

		   // input.showOpenDialog(null);
		    
		} else if (result == JFileChooser.CANCEL_OPTION) {
		    System.out.println("Cancel was selected");
		}
	}
	public static File getFile(){
		return file;
	}
	public void setFile(){
		int returnValue = this.jchooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = this.jchooser.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
		}
	}
	
}
