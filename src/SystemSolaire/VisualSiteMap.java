package SystemSolaire;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
/**
 * this is a Java JFrame Class : (unResizable , bounds = (650, 615) , labelImage ,...)
 * this Frame allows us to visualize the sitemap generated with the previous Frame {@link GraphiqueSiteMapGenerator} and
 * {@link GraphiqueRecherche}.
 * @author Ayad Ishak and Arhab Samira
 * @since JDK8.0
 * @version 1.8.0_144 created at 21 Dec,2017
 * @see JFrame
 */
public class VisualSiteMap {
	
	private JFrame frame1;

	/**
	 * Create the application.
	 */
	public VisualSiteMap() {
		initialize();
	}
	/**
	 * this is a class which extends from a simple highlight painter that renders in a solid color.
	 * @author Ayad Ishak
	 * @since JDK8.0
	 * @version 1.8.0_144 created at 21 Dec,2017
	 * @see Highlighter
	 */
	public class MyHighlight extends DefaultHighlighter.DefaultHighlightPainter{

		public MyHighlight(Color c) {
			
			super(c);
			
		}	
	}
	/**
	 * This method traverses the tool used to write the text, 
	 * and locates all the characters that come in as parameter in the method 
	 * and highlight them with the color selected.
	 * @param compo
	 * 	the tool used to write the text
	 * @param pattern
	 * 	the String who we gonna highlight
	 * @param c
	 * 	the color of your choice
	 * @see JTextComponent
	 * @see Document
	 */
	public void highlit(JTextComponent compo ,String pattern,Color c) {
		
		Highlighter.HighlightPainter MyHighliter = new MyHighlight(c);
		
		try {
			
			Highlighter hilite = compo.getHighlighter();
			
			Document doc = compo.getDocument();
			
			String text = doc.getText(0,doc.getLength());
			
			int pos = 0;
			
			while((pos = text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0) {
				
				hilite.addHighlight(pos,pos+pattern.length(),MyHighliter);
				
				pos += pattern.length();
				
			}
			
		}catch(Exception e) {
			
			
			
		}	
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//tableau de String qui contien les élément a souligner pour une mise en forme
		String[] balises = {"<url>","</url>","<loc>","</loc>","<lastmod>","</lastmod>","<changefreq>","</changefreq>","<priority>","</priority>","</urlset>","urlset","http://www.exemple.org/"};
		
		frame1 = new JFrame();
		frame1.setTitle("visual sitemap");
		frame1.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame1.setResizable(false);
		frame1.setType(Type.UTILITY);
		frame1.setBackground(Color.WHITE);
		frame1.getContentPane().setBackground(Color.WHITE);
		frame1.getContentPane().setForeground(Color.GRAY);
		frame1.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 614, 554);
		frame1.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.GRAY);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Yu Gothic Light", Font.PLAIN, 13));
		frame1.setBounds(100, 100, 650, 615);
		/*
		 * ici je je récupère le fichier sitemap.xml genéré 
		 * et je l'insére dans m'a JtextArea
		 */
		try {
			
			BufferedReader read = new BufferedReader(new FileReader("sitemap.xml"));

			textArea.read(read,null);
			
			read.close();
			
			
		} catch (FileNotFoundException e) {
		
			JOptionPane.showMessageDialog(null,"Le Fichier sitemap.xml n'existe pas");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		/*
		 * je parcours le tableau de "balises" et j'utilise la methode qui surligne les caractere
		 */
		for(int i = 0 ; i < balises.length ; i++) {
			
			if(balises[i].contains("http:")){
				
				highlit(textArea,balises[i],Color.red);
				
			}
			if(!balises[i].contains("urlset")) {
				
				highlit(textArea,balises[i],Color.blue);
			
			}
			if(balises[i].contains("urlset")){
				
				highlit(textArea,balises[i],Color.cyan);
				
			}
			
		}
		
	}
	/**
	 *  this method can run us this Frame from any other JFrame
	 * @see Runnable
	 */
	public void run() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualSiteMap window = new VisualSiteMap();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
