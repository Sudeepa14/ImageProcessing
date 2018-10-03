package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import algorithm.Convert;
import algorithm.EdgeDetection;
import algorithm.Filter;
import algorithm.Scaling;
import algorithm.Transeform;

import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;

public class App extends JFrame {

	private JPanel contentPane;
	private static App app;
	private static File importFile;
	private static JLabel imageLable;
	private static BufferedImage currentImage;
	private FileNameExtensionFilter filter;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public App() {
		app=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 982, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 27, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JPanel panel = new JPanel();
	    ButtonGroup group1 = new ButtonGroup();
	    panel.setBounds(608, 11, 156, 192);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Scaling");
		lblNewLabel_1.setBounds(43, 11, 71, 23);
		panel.add(lblNewLabel_1);
		
		JRadioButton rdbtnNearestNeighbour = new JRadioButton("Nearest Neighbour");
		rdbtnNearestNeighbour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group1.clearSelection();
				rdbtnNearestNeighbour.setSelected(true);
			}
		});
		rdbtnNearestNeighbour.setBounds(5, 41, 123, 23);
		panel.add(rdbtnNearestNeighbour);
		
		JRadioButton rdbtnBilinearInteporation = new JRadioButton("Bilinear Inteporation");
		rdbtnBilinearInteporation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group1.clearSelection();
				rdbtnBilinearInteporation.setSelected(true);
			}
		});
		rdbtnBilinearInteporation.setBounds(5, 66, 123, 23);
		panel.add(rdbtnBilinearInteporation);
		
		group1.add(rdbtnBilinearInteporation);
		group1.add(rdbtnNearestNeighbour);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(100), new Integer(1), null, new Integer(1)));
		spinner.setBounds(60, 96, 38, 23);
		panel.add(spinner);
		
		JButton btnUp = new JButton("Apply");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNearestNeighbour.isSelected()){
					try {
						int x= (Integer) spinner.getValue();
					//	System.out.println(x);
						setNewImage(Scaling.nearestNeighbour((float) x/100.00, getCurrentImage()));
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(rdbtnBilinearInteporation.isSelected()){
					int x= (Integer) spinner.getValue();
//	System.out.println(x);
					try{
					setNewImage(Scaling.bilinearInterpolation((float) x/100.00, getCurrentImage()));
					}
					catch (Exception e) {
						// TODO: handle exception
					}
 				}
			}
		});
		btnUp.setBounds(39, 140, 89, 23);
		panel.add(btnUp);
		
		
		
		JLabel label = new JLabel("%");
		label.setBounds(108, 100, 24, 14);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(608, 214, 334, 117);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblRotate = new JLabel("Transformation");
		lblRotate.setBounds(136, 11, 106, 14);
		panel_1.add(lblRotate);
		
		JButton btnNewButton = new JButton("left");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage image=Transeform.rotate(3, getCurrentImage());
				setNewImage(image);
			}
		});
	//	btnNewButton.setIcon(new ImageIcon("E:\\ImageApp\\left.png"));
	//	Image newimg = new ImageIcon("E:\\ImageApp\\left.png").getImage().getScaledInstance( btnNewButton.getWidth(), btnNewButton.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
	//	btnNewButton.setIcon(newimg);
		btnNewButton.setBounds(23, 48, 75, 46);
		panel_1.add(btnNewButton);
		
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					BufferedImage image=Transeform.rotate(1, getCurrentImage());
					setNewImage(image);
				}
		});
		btnRight.setBounds(127, 48, 87, 46);
		panel_1.add(btnRight);
		
		JButton btnFlip = new JButton("Flip");
		btnFlip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					BufferedImage image=Transeform.flip(getCurrentImage());
					setNewImage(image);
				}
		});
		btnFlip.setBounds(233, 48, 75, 46);
		panel_1.add(btnFlip);
		
		JPanel panel_2 = new JPanel();
		 ButtonGroup group2 = new ButtonGroup();
		panel_2.setBounds(786, 11, 156, 192);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setBounds(48, 11, 46, 14);
		panel_2.add(lblFilters);
		
		JRadioButton rdbtnMean = new JRadioButton("Mean");
		rdbtnMean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group2.clearSelection();
				rdbtnMean.setSelected(true);
			}
		});
		 
		rdbtnMean.setBounds(20, 44, 109, 23);
		panel_2.add(rdbtnMean);
		
		JRadioButton rdbtnMedian = new JRadioButton("Median");
		rdbtnMedian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group2.clearSelection();
				rdbtnMedian.setSelected(true);
			}
		});
		rdbtnMedian.setBounds(20, 70, 109, 23);
		panel_2.add(rdbtnMedian);
		
		JRadioButton rdbtnAlfaTrimmedFilter = new JRadioButton("Mid point");
		rdbtnAlfaTrimmedFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group2.clearSelection();
				rdbtnAlfaTrimmedFilter.setSelected(true);
			}
		});
		rdbtnAlfaTrimmedFilter.setBounds(20, 95, 130, 23);
		panel_2.add(rdbtnAlfaTrimmedFilter);
		group2.add(rdbtnMean);
		group2.add(rdbtnMedian);
		group2.add(rdbtnAlfaTrimmedFilter);
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAlfaTrimmedFilter.isSelected()){
					App.setNewImage(Filter.midPoint(getCurrentImage()));
				}
                if(rdbtnMean.isSelected()){
					App.setNewImage(Filter.meanFilter(getCurrentImage()));
				}
                if(rdbtnMedian.isSelected()){
					App.setNewImage(Filter.medianFilter(App.getCurrentImage()));
				}
			}
		});
		btnFilter.setBounds(31, 142, 89, 23);
		panel_2.add(btnFilter);
		
		JPanel panel_3 = new JPanel();
		ButtonGroup group4=new ButtonGroup();
		
		panel_3.setBounds(793, 342, 149, 145);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblEdgeDetection = new JLabel("Edge Detection");
		lblEdgeDetection.setBounds(32, 11, 140, 14);
		panel_3.add(lblEdgeDetection);
		
		JRadioButton rdbtnSobel = new JRadioButton("Sobel");
		rdbtnSobel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group4.clearSelection();
				rdbtnSobel.setSelected(true);
			}
		});
		rdbtnSobel.setBounds(18, 32, 109, 23);
		panel_3.add(rdbtnSobel);
		
		JRadioButton rdbtnRobert = new JRadioButton("Robert");
		rdbtnRobert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group4.clearSelection();
				rdbtnRobert.setSelected(true);
			}
		});
		rdbtnRobert.setBounds(18, 81, 109, 23);
		panel_3.add(rdbtnRobert);
		group4.add(rdbtnRobert);
		group4.add(rdbtnSobel);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnRobert.isSelected()){
					setNewImage(EdgeDetection.robert(getCurrentImage()));
				}
				if(rdbtnSobel.isSelected()){
					setNewImage(EdgeDetection.sobel(getCurrentImage()));
					
				}
			}
		});
		btnApply.setBounds(32, 111, 89, 23);
		panel_3.add(btnApply);
		ButtonGroup group3=new ButtonGroup();
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(608, 342, 174, 145);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblConvert = new JLabel("Convert");
		lblConvert.setBounds(52, 11, 46, 14);
		panel_4.add(lblConvert);
		
		JRadioButton rdbtnNegative = new JRadioButton("Negative");
		rdbtnNegative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group3.clearSelection();
				rdbtnNegative.setSelected(true);
			}
		});
		rdbtnNegative.setBounds(27, 35, 109, 23);
		panel_4.add(rdbtnNegative);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("GrayScale");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group3.clearSelection();
				rdbtnNewRadioButton_1.setSelected(true);
			}
		});
		rdbtnNewRadioButton_1.setBounds(27, 81, 109, 23);
		panel_4.add(rdbtnNewRadioButton_1);
		
//		JRadioButton rdbtnGrayscale = new JRadioButton("GrayScale");
//		rdbtnGrayscale.setBounds(6, 69, 109, 23);
//		panel_4.add(rdbtnGrayscale);
		
		group3.add(rdbtnNewRadioButton_1);
		group3.add(rdbtnNegative);
		
		JButton button = new JButton("Apply");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNegative.isSelected()){
					setNewImage(Convert.negative(getCurrentImage()));
				}
				if(rdbtnNewRadioButton_1.isSelected()){
					setNewImage(Convert.grayscaling(getCurrentImage()));
				}
			}
		});
		button.setBounds(40, 111, 89, 23);
		panel_4.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 588, 502);
		contentPane.add(scrollPane);
		JLabel lblImage = new JLabel("");
		//scrollPane.setRowHeaderView(lblImage);
		scrollPane.setViewportView(lblImage);
		imageLable=lblImage;
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser fc=new FileChooser("Select the Image","image");
				fc.setVisible(true);
				importFile=FileChooser.getFile();
				try {
					currentImage=ImageIO.read(importFile);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					lblImage.setIcon(new ImageIcon(ImageIO.read(importFile)));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				JFileChooser output = new JFileChooser();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					filter = new FileNameExtensionFilter(
					        "JPG & GIF Images", "jpg", "gif");
					output.setFileFilter(filter);
				int result = output.showSaveDialog(app);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File file=output.getSelectedFile();
				    try {
						ImageIO.write(currentImage, "jpg", file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (result == JFileChooser.CANCEL_OPTION) {
				    System.out.println("Cancel was selected");
				}
				///
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Exit");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setNewImage(ImageIO.read(importFile));
				
				}catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
		}});
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setBounds(608, 501, 335, 30);
		contentPane.add(btnNewButton_1);
	}
	public static void setNewImage(BufferedImage image){
		imageLable.setIcon(new ImageIcon(image));
		currentImage=image;
	}
	public static BufferedImage getCurrentImage() {
		if(currentImage==null){
			JOptionPane.showMessageDialog(app,
				    "Load an image first");
			//return;
		}
		return currentImage;
		
	}
}
