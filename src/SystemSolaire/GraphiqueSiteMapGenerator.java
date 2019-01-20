package SystemSolaire;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JComboBox;
/**
 * this is a Java JFrame Class : (unResizable , bounds = (707, 259) , labelImage ,...)
 * this Frame can launche the generation of the sitemap and the Indexatio
 * this class have one special attribte an {@link SaveHtmlProg} that was use it to create our sitemap.xml end data.ser
 * @author Ayad Ishak
 * @since JDK8.0
 * @version 1.8.0_144 created at 21 Dec,2017
 * @see JFrame
 */
@SuppressWarnings("serial")
public class GraphiqueSiteMapGenerator extends JFrame {

	private JFrame frmSitemapGenerator;
	private JTextField textField;
	private JButton btnAddButton;
	private JButton btnRunButton;
	private SaveHtmlProg save;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
	/**
	 * Create the application
	 */
	public GraphiqueSiteMapGenerator() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		save = new SaveHtmlProg();
		frmSitemapGenerator = new JFrame();
		frmSitemapGenerator.setTitle("Sitemap Generator");
		frmSitemapGenerator.getContentPane().setBackground(Color.GRAY);
		frmSitemapGenerator.setBackground(Color.LIGHT_GRAY);
		frmSitemapGenerator.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmSitemapGenerator.getContentPane().setForeground(Color.BLACK);
		frmSitemapGenerator.setResizable(false);
		frmSitemapGenerator.setType(Type.UTILITY);
		frmSitemapGenerator.setBounds(100, 100, 707, 259);
		frmSitemapGenerator.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(114, 162, 536, 20);
		textField.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		frmSitemapGenerator.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnAddButton = new JButton("");
		btnAddButton.setBounds(662, 159, 32, 33);
		/*
		 * j'ajoute a mon JButton btnAddButton un ActionListener 
		 * qui lance un JFileChoser pour choisir le Dossier de depart 
		 * apres j'ai changer le contenu du textFeild avec le path de ce dossier choisi
		 */
		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
				if(e.getSource() == btnAddButton ) {
					
					int returnVal = fc.showOpenDialog(GraphiqueSiteMapGenerator.this);
					
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						
						File file = fc.getSelectedFile();
						
						textField.setText(file.getAbsolutePath());
						
					}
					
				}
				
			}
		});
		btnAddButton.setIcon(new ImageIcon(GraphiqueSiteMapGenerator.class.getResource("/images/add.png")));
		frmSitemapGenerator.getContentPane().add(btnAddButton);
		
		JLabel lblFile = new JLabel("   File :");
		lblFile.setBounds(32, 148, 77, 44);
		lblFile.setFont(new Font("Yu Gothic Light", Font.PLAIN, 20));
		lblFile.setForeground(Color.BLACK);
		lblFile.setToolTipText("File");
		frmSitemapGenerator.getContentPane().add(lblFile);
		
		btnRunButton = new JButton("");
		btnRunButton.setBounds(336, 194, 32, 33);
		btnRunButton.setForeground(Color.BLACK);
		btnRunButton.setBackground(Color.BLACK);
		btnRunButton.setIcon(new ImageIcon(GraphiqueSiteMapGenerator.class.getResource("/images/run.png")));
		frmSitemapGenerator.getContentPane().add(btnRunButton);
		// comboBox pour choisir la fréquence
		comboBox = new JComboBox();
		comboBox.setBackground(Color.GRAY);
		comboBox.setForeground(Color.BLACK);
		comboBox.addItem("always");
		comboBox.addItem("hourly");
		comboBox.addItem("daily");
		comboBox.addItem("weekly");
		comboBox.addItem("monthly");
		comboBox.addItem("yearly");
		comboBox.addItem("never");
		comboBox.setSelectedItem(null);
		comboBox.setBounds(280, 11, 146, 20);
		frmSitemapGenerator.getContentPane().add(comboBox);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(GraphiqueSiteMapGenerator.class.getResource("/images/Xml.png")));
		lblImage.setBounds(124, 0, 518, 227);
		frmSitemapGenerator.getContentPane().add(lblImage);
		/*
		 * j'ajoute a mon JButton btnRunButton un ActionListener pour lancé la genération des deux fichier
		 * 1-si le contenu de notre textField et different de "" et l'element selection du comboBox 
		 * 						on procéde comme suit:
		 * 		a-on lance la generation du fichier sitemap.xml avec la methode de SaveHtmlProg
		 * 		b-on lance la generation du fichier data.ser avec la methode de SaveHtmlProg
		 */
		btnRunButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ev) {
				
				if(!textField.getText().equals("") && comboBox.getSelectedItem() != null) {
					
					save.sauvgardeSiteMap(textField.getText(),(String)comboBox.getSelectedItem(),"sitemap.xml");
					
					save.sauvgardeIndexation("data.ser");
					
					JOptionPane.showMessageDialog(null,"Fin de la création du sitemap et de l'indexation!");
					
				}else {
					
					if(textField.getText().equals("")) {
					
						JOptionPane.showMessageDialog(null,"Selectionne votre Document de depart!");
						
					}
					if(comboBox.getSelectedItem() == null) {
						
						JOptionPane.showMessageDialog(null,"Selectionne la Fréquence probable de modification des pages!");
						
					}
						
				}
			}
			
		});
	}
	/**
	 * this method can run us this Frame from any other JFrame
	 * @see Runnable
	 */
	public void run() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphiqueSiteMapGenerator window = new GraphiqueSiteMapGenerator();
					window.frmSitemapGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
