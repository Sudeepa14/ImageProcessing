package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class ImportImage extends JFrame {

	private JPanel contentPane;
	private FileChooser imageCho;
	private File image;
	private FileChooser wDirectoryCho;
	private File wDirectory;
	private ImportImage importImage;

	public ImportImage() {
		getContentPane().setLayout(null);
		
		JButton btnSelectImage = new JButton("New button");
		btnSelectImage.setBounds(153, 43, 89, 23);
		getContentPane().add(btnSelectImage);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Select The Image");
		btnNewButton.addActionListener(new ActionListener() {
			//importImage
			public void actionPerformed(ActionEvent e) {
				imageCho=new FileChooser("Select the Image", "image");
				//imageCho.setVisible(true);
			}
		});
		btnNewButton.setBounds(139, 67, 175, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSelectFolder = new JButton("Select the Working Directory");
		btnSelectFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wDirectoryCho=new FileChooser("Select the Working Directry", "folder");
				//wDirectoryCho.setVisible(true);
			}
		});
		btnSelectFolder.setBounds(145, 120, 169, 23);
		contentPane.add(btnSelectFolder);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    wDirectory =imageCho.getFile();
			    image=imageCho.getFile(); 
			}
		});
		btnNext.setBounds(292, 280, 89, 23);
		contentPane.add(btnNext);
		
	}
	public void validateFiles(){
		
	}
}
