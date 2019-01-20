package SystemSolaire;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
/**
 * this is a Java JFrame Class : (unResizable , bounds = (902, 528) , labelImage ,...)
 * this class have one special attribte an {@link SaveHtmlProg} that we use to do all searches
 * we have also a HashMap of {@link SiteMap} with it's Link as Key , we use it to display the carateristiques SiteMap
 * @author Ayad Ishak and Arhab Samira
 * @since JDK8.0
 * @version 1.8.0_144 created at 19 Dec,2017
 * @see JFrame
 */
public class GraphiqueRecherche extends JFrame{
/**
 * @deprecated
 */
	private static final long serialVersionUID = 1L;
	private JFrame frmPromenadeDansLe;
	private JTextField textFeild1;
	private SaveHtmlProg save;
	private Timer time;
	private HashMap<String,SiteMap> hashSitemap;
	private ArrayList<String> list ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphiqueRecherche window = new GraphiqueRecherche();
					window.frmPromenadeDansLe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public GraphiqueRecherche() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {
		
		save = new SaveHtmlProg();
		hashSitemap = new HashMap<String,SiteMap>();
		frmPromenadeDansLe = new JFrame();
		frmPromenadeDansLe.setTitle("Promenade dans le systéme solaire");
		frmPromenadeDansLe.setResizable(false);
		frmPromenadeDansLe.setType(Type.UTILITY);
		frmPromenadeDansLe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPromenadeDansLe.getContentPane().setForeground(Color.WHITE);
		frmPromenadeDansLe.getContentPane().setLayout(null);
		
		JLabel TextTitel = new JLabel("promenade dans le système solaire");
		TextTitel.setBounds(102, 12, 601, 27);
		TextTitel.setForeground(Color.BLUE);
		TextTitel.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 30));
		frmPromenadeDansLe.getContentPane().add(TextTitel);
		
		textFeild1 = new JTextField();
		/*
		 * en gros ici je dis quand on clic sur le
		 * textFeild1 je clear son contenue 
		 */
		textFeild1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				textFeild1.setText("");
				
			}
		});
		textFeild1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textFeild1.setText("recherche....");
		textFeild1.setBounds(291, 49, 302, 20);
		frmPromenadeDansLe.getContentPane().add(textFeild1);
		textFeild1.setColumns(10);
		
		JLabel lblRecherche = new JLabel(" Recherche :");
		lblRecherche.setBounds(164, 49, 117, 19);
		lblRecherche.setFont(new Font("Verdana", Font.BOLD, 15));
		lblRecherche.setForeground(Color.WHITE);
		frmPromenadeDansLe.getContentPane().add(lblRecherche);
		frmPromenadeDansLe.setBounds(100, 100, 902, 528);
		
		JScrollPane scrollPane_Page = new JScrollPane();
		scrollPane_Page.setBounds(31, 265, 430, 204);
		scrollPane_Page.setEnabled(false);
		frmPromenadeDansLe.getContentPane().add(scrollPane_Page);
		
		JScrollPane scrollPane_Cara = new JScrollPane();
		scrollPane_Cara.setBounds(516, 264, 374, 203);
		frmPromenadeDansLe.getContentPane().add(scrollPane_Cara);
		
		JList list_Cara = new JList();
		scrollPane_Cara.setViewportView(list_Cara);
		list_Cara.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JList list_Link = new JList();
		list_Link.setBounds(261, 178, 366, 20);
		frmPromenadeDansLe.getContentPane().add(list_Link);
		
				
		JList list_Page = new JList();
		/*
		 * sur ma JList qui contien l'ensemble des pages trouve apres la recherche j'ai ajouteé un :
		 * MousAdapter == > mousePressed
		 * en gros quand en click sur un lien de la JList : 
		 * 1-je le recuper et je cherche dans mon HashMap de SiteMap avec ce line
		 * 2-je recherhce le Title de la page Html correspondante a ce lien 
		 * 3-je lis mon sitemap deja genéré et je recupere le contenu de la balise changefreq
		 * 4-je sauvgarde ces caracteristique de cet instance de SiteMap dans une DefaultListModel
		 * 5-j'actualise ma JList de caractère et l'autre JList qui contien  le lien a visualiser
		 */
		list_Page.addMouseListener(new MouseAdapter() {
			
			@SuppressWarnings("unchecked")
			public void mousePressed(MouseEvent e) {
				
				String slected = list_Page.getSelectedValue().toString();
				
				String freq = null;
				
				File file = new File(slected);
				
				int i = slected.lastIndexOf("p");
				
				slected = slected.replace(slected.substring(0,i),"https://promenade.imcce.fr/fr/");
				
				slected = slected.replace('\\','/');
				
				SiteMap st = hashSitemap.get(slected);
				
				String title = save.getTitelByLinkFile(file.getName());
				
				DefaultListModel dlm = new DefaultListModel();
				
				try {
					
					String line;
					
					BufferedReader read = new BufferedReader(new FileReader("sitemap.xml"));
					
					while((line = read.readLine()) != null) {
						
						Pattern pr = Pattern.compile(".*<changefreq>(.*)\\</changefreq\\>");
					     
						Matcher m = pr.matcher(line);

						if(m.matches()) {
							
							freq = m.group(1);
							
						}
						
					}
					
					read.close();
					
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				
				String lastmod = sdf.format(st.getLastmod());
				
				dlm.addElement("le lien de la page : "+st.getLoc());
				dlm.addElement("la date de la dernier modification de la page : "+lastmod);
				dlm.addElement("la frequence de la page : "+freq);
				dlm.addElement("la priority de la page : "+st.getPriority());
				dlm.addElement("le titre de la page : "+title);
				dlm.addElement(""+list.size()+" pages qui contien : "+textFeild1.getText());
				
				list_Cara.setModel(dlm);
					
				DefaultListModel dlm1 = new DefaultListModel();
					
				dlm1.addElement(st.getLoc());
					
				list_Link.setModel(dlm1);				
				
			}
		});
		scrollPane_Page.setViewportView(list_Page);
			
		JButton btnRecherche = new JButton("");
		/*
		 * j'ajoute a mon JButton de recherche un ActionListener pour lancé une recherche ,
		 * avec le contenu de textFeild1.
		 * 1-j'ai déclaré L'arrayList "list" qui contien l'ensemble des pages du a la recherche .
		 * comme variable globale(attribus) parceque j'utilise m'as list sur une autre Llist voir "list_link".
		 * 2-j'effectu la rechercheByH1 avec le contu du textFeild1 .
		 * 3-j'effectu la rechercheByTitle avec le contu du textFeild1.
		 * 4-je fusionne les deux ArrayList du a la recherche.
		 * 5-je parcour mon ArrayList creé et pour chaque valeur de cette ArrayList 
		 * je creé une instance de SiteMap et je la met dans m'a HashMap de SiteMap pour l'utiliser sur la Jlist qui précède.
		 * 4-je sauvgarde tout les element de l'ArrayList dans une DefaultListModel.
		 * 5-j'actualise ma JList qui contien l'ensemble des pages.
		 */
		btnRecherche.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				
				 list = save.rechercheByH1(textFeild1.getText());
				
				ArrayList<String> list2 = save.rechercheByTitle(textFeild1.getText());
				
				DefaultListModel dlm = new DefaultListModel();
				
				File file;
				
				long lasmod;
				
				SiteMap s;
				
				for(Iterator<String> it = list2.iterator() ; it.hasNext() ;) {
					
					String link = it.next();
					
					if(!list.contains(link)) {
						
						list.add(link);
						
					}					
				}
				
				for(Iterator<String> it = list.iterator() ; it.hasNext() ;) {
					
					String link = it.next();
					
					file = new File(link);
					
					lasmod = file.lastModified();
					
					int j = link.lastIndexOf("p");
					
					link = link.replace(link.substring(0,j),"https://promenade.imcce.fr/fr/");
					
					link = link.replace('\\','/');
					
					s = new SiteMap(link,lasmod);
					
					hashSitemap.put(link,s);
					
					dlm.addElement(link);				
				}
				
				list_Page.setModel(dlm);
				
			}
		});
		btnRecherche.setBounds(603, 49, 100, 20);
		frmPromenadeDansLe.getContentPane().add(btnRecherche);
		btnRecherche.setIcon(new ImageIcon(GraphiqueRecherche.class.getResource("/images/cherche.png")));
		
		JScrollPane scrollPane_recherch = new JScrollPane();
		scrollPane_recherch.setBounds(291, 68, 302, 99);
		scrollPane_recherch.setEnabled(false);
		frmPromenadeDansLe.getContentPane().add(scrollPane_recherch);
		
		JList list_Recher = new JList();
		scrollPane_recherch.setViewportView(list_Recher);
		/*
		 *\<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	partis improvisé	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		 * j'ai creé une JList qui va contenir l'ensemble des H1 et TITLE qui StartWith 
		 * le contenu du textFeild1 en fonction D'un Timer plus d'explication voir : ligne"439"\ 
		 * -----------------------------------------------------------------------------------------
		 * sur ma JList qui contien l'ensemble des H1 TITLE qui commence avec le contenu de textFeild1 j'ai ajouteé un :
		 * MousAdapter == > mousePressed
		 * y'a pas beaucoup de trucs a expliqué ici
		 * en gros je dis quand je selectionne un element de m'a JList je change le contenu du textFeild1 en lui passent l'element selectionne
		 */
		list_Recher.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			
			public void mousePressed(MouseEvent arg0) {
				
				DefaultListModel dlm = new DefaultListModel();
				
				String selected = list_Recher.getSelectedValue().toString();
				
				textFeild1.setText(selected);
				
				dlm.clear();
				
				list_Recher.setModel(dlm);
				
			}
		});
		 /*
		  * sur ma JList qui contien le lien du Fichier Html a visualiser j'ai ajouteé un :
		  * MousAdapter == > mousePressed
		  * j'ai utiliser la class :
		  *  b-java.awt.Desktop : 1-lancer le navigateur par défaut de l'utilisateur pour afficher un URI spécifié;
     	  *						2-le lancement du client de messagerie par défaut avec un URI mailto facultatif;
     	  *						3-lancer une application enregistrée pour ouvrir, éditer ou imprimer un fichier spécifié;
		  *  a-java.net.URI : Les opérations clés supportées par cette classe sont celles de normalisation, de résolution et de relativisation.
		  *  
		  *  une combinaison entre ces deux classe peut lancer notre WebBrowser sur le lien selectionne
		  */
		list_Link.addMouseListener(new MouseAdapter() {
		
			public void mousePressed(MouseEvent arg0) {
				
				String slected;
				Desktop desktop;
				
				if(list_Link.getSelectedIndex() == 0) {
					
					slected = list_Link.getSelectedValue().toString();
					
					desktop = Desktop.getDesktop();
					
					try {
						
						desktop.browse(new URI(slected));
						
					} catch (IOException | URISyntaxException e) {
					
						e.printStackTrace();
					}
					
					frmPromenadeDansLe.setVisible(true);
					
				}
				
			}
		});
		list_Link.setForeground(Color.BLUE);
		list_Link.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		
		JLabel label_Image = new JLabel("");
		label_Image.setIcon(new ImageIcon(GraphiqueRecherche.class.getResource("/images/systeme-solaire.jpg")));
		label_Image.setBounds(0, 0, 902, 478);
		frmPromenadeDansLe.getContentPane().add(label_Image);
		
		JMenuBar menuBar = new JMenuBar();
		frmPromenadeDansLe.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		/*
		 * sur le mntmNewMenuItem j'ai ajouter un ActionListener
		 * en cliquant sur ce JMenuItem l'application nous lance une nouvel JFram qui est spécialiser
		 * pour la genération du sitemap et l'indexation
		 */
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				GraphiqueSiteMapGenerator run = new GraphiqueSiteMapGenerator () ;
				run.run();			

			}
		});
		mnFile.add(mntmNewMenuItem);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmAfficher = new JMenuItem("Afficher");
		/*
		 * sur le mntmAfficherj'ai ajouter un ActionListener
		 * en cliquant sur ce JMenuItem l'application nous lance une nouvel JFram qui est spécialiser
		 * a l'affichage du contenu du sitemap.xml genéré depuis le JMenuItem précédent
		 */
		mntmAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VisualSiteMap run = new VisualSiteMap();
				run.run();
			}
		});
		mnFile.add(mntmAfficher);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmNew = new JMenuItem("Exit");
		/*
		 * sur le mntmAfficherj'ai ajouter un ActionListener
		 * en cliquant sur ce JMenuItem l'application ce ferme
		 */
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}
		});
		mnFile.add(mntmNew);
		/*
		 * <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	partis improvisé	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		 * sur le textFeild1 j'ai ajouter un ActionListener ==> ancestorAdded
		 * 1-la creation d'un Timer et initialise le délai "= 5" initial et le délai entre les événements "= ActionListener"
		 *  pour retarder les millisecondes.
		 * 2-sur actionPerformed de la deuxieme ActionListener de l'événement on fait une recherche de la class SaveHtmlProg
		 *  avec le contenu du textFeild1 au moment t , 
		 *  getAllH1 : une methode de recherche qui retourn tout les H1 qui commence avec le paramètre passé
		 *  getAllTitles : une methode de recherche qui retourn tout les H1 qui commence avec le paramètre passé
		 * 3-je sauvgarde les element du resultat de la recherche dans une DefaultListModel
		 * 4-j'actualise ma JList de Recherche avec le DefaultListModel
		 * 5-je lance mon timer 
		 * 
		 * <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	but de l'evenement	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		 *  a-faciliter la recherche pour l'utilisateur
		 *  b-éviter le cas d'avoir une recherche nulle			
		 */
		textFeild1.addAncestorListener(new AncestorListener() {
			@SuppressWarnings("unchecked")
			public void ancestorAdded(AncestorEvent event) {
				
				 time = new Timer(5,new ActionListener() {
						
					public void actionPerformed(ActionEvent e) {
						
						DefaultListModel dlm = new DefaultListModel();
						
						if(!textFeild1.getText().equals("") && !textFeild1.getText().equals("recherche....")) {
							
							ArrayList<String> list = save.getAllH1(textFeild1.getText());
							ArrayList<String> list2 = save.getAllTitles(textFeild1.getText());
							
								for(int i = 0 ; i<list.size();i++)
								
									dlm.addElement(list.get(i));
							
								for(int i = 0 ; i<list2.size();i++)
						
									dlm.addElement(list2.get(i));
							
							list_Recher.setModel(dlm);
							
							
						}
						
						if(textFeild1.getText().equals("")) {
							
							dlm.clear();
							list_Recher.setModel(dlm);
							
						}
						
					}					
					
				});
				
				time.start();
			
			}
			public void ancestorMoved(AncestorEvent event) {
				
			}
			public void ancestorRemoved(AncestorEvent event) {
	
			}
		});
	}
}
