package SystemSolaire;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/**
 * SaveHtmlProg is a Java Class whit it we can generate a sitemap.xml and the indexation of the Html Files
 * by giving the file of departure which contains all the pages.
 * -this class need two attribute : 
 * -an ArrayList of {@link HtmlFile} to save all Html files who are present in the Directory
 * -and a {@link GeneratSiteMap} to genre the sitemapx.xml
 * -by using the method compresseAll we can brows recursively the Directory and collect ,
 *  all Html Files And stored those instance of {@link HtmlFile} in the ArrayList , 
 *  then we can generate the sitemap.xml file by the method sauvgardeSiteMap , and the indexation by the method sauvgardeIndexation
 * @see HtmlFile
 * @see GeneratSiteMap
 * @author Ayad Ishak and Arhab Samira
 * @since JDK8.0
 * @version 1.8.0_144 created at 19 Dec,2017
 */
public class SaveHtmlProg {
	
	private ArrayList<HtmlFile> ArrayHtml;
	
	private GeneratSiteMap siteMap ;
	/**
	 * Construct and initialize a SaveHtmlProg , we initialize the ArrayList of {@link HtmlFile}
	 * and initialize the {@link GeneratSiteMap} by using her Construct.
	 */
	public SaveHtmlProg() {
		
		ArrayHtml = new ArrayList<HtmlFile>();
		
		siteMap = new GeneratSiteMap();
		
	}
	/**
	 * this method can add a instanceOf HtmlFile in the ArrayList
	 * we add it if and only if one of her tags(TITLE,H1) are not null
	 * @param html
	 * 		the instance of HtmlFile that we will add
	 */
	public void addHtml(HtmlFile html) {
		
		if(html.getH1() != null || html.getTitle() != null ) {
		
				ArrayHtml.add(html);
		}
		
	}
	/**
	 * this method browse the tree of Html Directory's recursively
	 * by this browse we create the instances of {@link SiteMap} and add it to the {@link GeneratSiteMap}
	 * also create the instances of {@link HtmlFile} and add it to the ArrayList after that we collect her tags TITLE and H1
	 * @param dossier
	 * 		the Directory path of departure which contains all the Html Files
	 * @param freq
	 * 		How frequently the page is likely to change. 
	 * 		This value provides general information to search engines,
	 * 		and may not correlate exactly to how often they crawl the page,
	 * 		the correct Values are : 
	 * 
    	always,
    	hourly,
    	daily,
    	weekly,
    	monthly,
    	yearly,
    	never,
    	
     * @see HtmlFile
     * @see GeneratSiteMap
	 */
	public void compresseAll(String dossier,String freq) {
		
		String Link,Loc;
		
		long lasmod;
	
		HtmlFile html;
		
		SiteMap s;
	
		File fileP = new File(dossier);
		
		if (fileP.exists()) {
			
			File[] files = fileP.listFiles();
			
			for (File file : files) {
				
				if (file.isDirectory()) {
					
					compresseAll(fileP.getPath() + "/" + file.getName(),freq);
					
				}else if(file.getName().endsWith(".html")){
					
					Loc = file.getAbsolutePath();
					
					int i = Loc.lastIndexOf("p");
					
					Link = Loc.replace(Loc.substring(0,i),"https://promenade.imcce.fr/fr/");
					
					Link = Link.replace('\\','/');
					
					lasmod = file.lastModified();
	
					html = new HtmlFile(Loc,lasmod);
					
					html.findH1();
	
					html.findTitle();
					
					addHtml(html);
					
					if(!html.getH1().equals("") || !html.getTitle().equals("")) {
						
						s = new SiteMap(Link,lasmod,freq);
						
						try {
							
							siteMap.addSiteMap(s);
							
						} catch (SiteAlreadyExistsException e) {
						
						}
						
					}
				}
			}
		}
	}
	/**
	 * this method can genre a site map by using the method of writing in {@link GeneratSiteMap}
	 * @param dossier
	 * 		the Directory path of departure which contains all the Html Files
	 * @param freq
	 * 		How frequently the page is likely to change. 
	 * 		  	always,hourly,daily,weekly,monthly,yearly,never,
	 * @param title
	 * 		the title of the Xml File that we will genre by this method
	 */
	public void sauvgardeSiteMap(String dossier,String freq,String title) {
		
		compresseAll(dossier,freq);
		
		File sitemap = new File(title);
		
		this.siteMap.sauvgardXml(sitemap);
		
	}
	/**
	 * this method can generate a Ser File serial file which Contain the ArrayList of {@link HtmlFile}
	 * Serialization is a process introduced in JDK version 1.1 
	 * that makes it possible to create an object or object graph of the JVM persistent 
	 * for storage or exchange and vice versa
	 * @param title
	 * 		the title of the Ser File that we will genre by this method
	 * @see ObjectOutputStream 
	 */
	public void sauvgardeIndexation(String title) {
		
		ObjectOutputStream stream;
		
		File file = new File(title);
		
		if(!file.exists()) {
		
			try {
			
				stream = new ObjectOutputStream(new FileOutputStream(title));
			
				stream.writeObject(ArrayHtml);
			
				stream.close();
			
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
		}else {
			
			System.err.println("Le Fichier "+title+" existe deja!");
			
		}
				
	}
	/**
	 * it's the same method as compresseAll but here we use it in the console mode
	 * because in the console model at each execution we leave the code so the ArrayList of {@link HtmlFile}
	 * will be empty so in this case we have to re-brows the directory and generate the Indexation 
	 * @param dossier
	 * 		the title of the Ser File that we will genre by this method
	 */
	public void compresseAllJarIndex(String dossier) {
		
		String Loc;
		
		long lasmod;
	
		HtmlFile html;
	
		File fileP = new File(dossier);
		
		if (fileP.exists()) {
			
			File[] files = fileP.listFiles();
			
			for (File file : files) {
				
				if (file.isDirectory()) {
					
					compresseAllJarIndex(fileP.getPath() + "/" + file.getName());
					
				}else if(file.getName().endsWith(".html")){
					
					Loc = file.getAbsolutePath();
					
					lasmod = file.lastModified();
	
					html = new HtmlFile(Loc,lasmod);
					
					html.findH1();
	
					html.findTitle();
					
					addHtml(html);
				}
			}
		}
	}
	/**
	 * *it's the same method as sauvgardeIndexation but here we use it in the console mode
	 * because in the console model at each execution we leave the code so the ArrayList of {@link HtmlFile}
	 * will be empty so in this case we have to re-brows the directory to generate the Indexation 
	 * @param dossier
	 * 		the title of the Ser File that we will genre by this method
	 * @param title
	 * 		the title of the Ser File that we will genre by this method
	 * @see ObjectOutputStream 
	 * @see FileOutputStream
	 */
	public void sauvgardeIndexationJar(String dossier,String title) {
		
		compresseAllJarIndex(dossier);
		
		ObjectOutputStream stream;
		
		File file = new File(title);
		
		if(!file.exists()) {
		
			try {
			
				stream = new ObjectOutputStream(new FileOutputStream(title));
		
				stream.writeObject(ArrayHtml);
		
				stream.close();
		
			} catch (IOException e) {
		
				e.printStackTrace();
			}
		
		}else {
		
			System.err.println("Le Fichier "+title+" existe deja!");
		
		}
		
	}
	/**
	 * this method use The reverse process of creating object from sequence of bytes 
	 * is called deserialization.
	 * we recover the data.ser file in a ArrayList of {@link HtmlFile} then we will do our search
	 * by browsing the ArrayList
	 * @param linke
	 * 		the link to search 
	 * @return
	 * 		the H1 tag of this Html file how have the title passed as a parameter if he exist 
	 * 		else null
	 * @see ObjectInputStream
	 * @see FileInputStream
	 */
	@SuppressWarnings("unchecked")
	public String getH1ByLinkFile(String linke) {
		
		ArrayList<HtmlFile> array = new ArrayList<HtmlFile>();
		
		try {
			
			@SuppressWarnings("resource")
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream("data.ser"));
			
			HtmlFile html;
			
			array = (ArrayList<HtmlFile>) stream.readObject();
			
			for(Iterator<HtmlFile> it = array.iterator() ; it.hasNext() ;) {

				html = it.next();
				
				if(html.getLink().contains(linke)) {
					
					return html.getH1();
					
				}
				
			}
			
			stream.close();
		
		}catch (EOFException e) {
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		}
		
		return null;
				
	}
	/**
	 * this method use The reverse process of creating object from sequence of bytes 
	 * is called deserialization.
	 * we recover the data.ser file in a ArrayList of {@link HtmlFile} then we will do our search
	 * by browsing the ArrayList
	 * @param linke
	 * 		the link to search 
	 * @return
	 * 		the TITLE tag of this Html file how have the title passed as a parameter if he exist 
	 * 		else null
	 * @see ObjectInputStream
	 * @see FileInputStream
	 */
	@SuppressWarnings("unchecked")
	public String getTitelByLinkFile(String linke) {
		
		ArrayList<HtmlFile> array = new ArrayList<HtmlFile>(); ;
		
		try {
			
			@SuppressWarnings("resource")
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream("data.ser"));
			
			HtmlFile html;
			
			array = (ArrayList<HtmlFile>) stream.readObject();
			
			for(Iterator<HtmlFile> it = array.iterator() ; it.hasNext() ;) {

				html = it.next();
				
				if(html.getLink().contains(linke)) {
					
					return html.getTitle();
					
				}
				
			}
						
			stream.close();
		
		}catch (EOFException e) {
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		}
		
		
		return null;
	}
	/**
	 * this method use The reverse process of creating object from sequence of bytes 
	 * is called deserialization.
	 * we recover the data.ser file in a ArrayList of {@link HtmlFile} then we will do our search
	 * by browsing the ArrayList
	 * @param title
	 * 		the title to search 
	 * @return
	 * 		ArrayList of String contain the Location of Html File who there Title
	 * 		tag contain the title to search passed as a parameter. 
	 * @see ObjectInputStream
	 * @see FileInputStream
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> rechercheByTitle(String title) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		ArrayList<HtmlFile> array = new ArrayList<HtmlFile>();
		
		try {

			ObjectInputStream stream = new ObjectInputStream(new FileInputStream("data.ser"));
			
			HtmlFile html;

			array = (ArrayList<HtmlFile>) stream.readObject();
			
			for(Iterator<HtmlFile> it = array.iterator() ; it.hasNext() ;) {

				html = it.next();
				
				if(html.getTitle().contains(title)) {
					
					list.add(html.getLink());
					
				}
				
			}
			
			stream.close();
			
		}catch (EOFException e) {
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		}
		
		return list;
		
	}
	/**
	 * this method use The reverse process of creating object from sequence of bytes 
	 * is called deserialization.
	 * we recover the data.ser file in a ArrayList of {@link HtmlFile} then we will do our search
	 * by browsing the ArrayList
	 * @param H1
	 * 		the H1 to search 
	 * @return
	 * 		ArrayList of String contain the Location of Html File who there H1
	 * 		tag contain the H1 to search passed as a parameter. 
	 * @see ObjectInputStream
	 * @see FileInputStream
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> rechercheByH1(String H1) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		ArrayList<HtmlFile> array = new ArrayList<HtmlFile>();
		
		try {

			ObjectInputStream stream = new ObjectInputStream(new FileInputStream("data.ser"));
			
			HtmlFile html;
				
			array = (ArrayList<HtmlFile>) stream.readObject();
			
			for(Iterator<HtmlFile> it = array.iterator() ; it.hasNext() ;) {

				html = it.next();
				
				if(html.getH1().contains(H1)) {
					
					list.add(html.getLink());
					
				}
				
			}
				
			
			stream.close();
			
		}catch (EOFException e) {
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		}
		
		return list;
		
	}
	/**
	 * this method use The reverse process of creating object from sequence of bytes 
	 * is called deserialization.
	 * we recover the data.ser file in a ArrayList of {@link HtmlFile} then we will do our search
	 * by browsing the ArrayList
	 * @param H1
	 * 		the H1 to search 
	 * @return
	 * 		ArrayList of String contain the Location of Html File who there H1
	 * 		tag Start with the H1 to search passed as a parameter. 
	 * @see ObjectInputStream
	 * @see FileInputStream
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getAllH1(String H1) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		ArrayList<HtmlFile> array = new ArrayList<HtmlFile>();
		
		try {

			ObjectInputStream stream = new ObjectInputStream(new FileInputStream("data.ser"));
			
			HtmlFile html;
				
			array = (ArrayList<HtmlFile>) stream.readObject();
			
			for(Iterator<HtmlFile> it = array.iterator() ; it.hasNext() ;) {

				html = it.next();
				
				if(html.getH1().startsWith(H1) && !list.contains(html.getH1())) {
					
					list.add(html.getH1());
					
				}
				
			}
			
			stream.close();
			
		}catch (EOFException e) {
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		}
		
		return list;
		
		
	}
	/**
	 * this method use The reverse process of creating object from sequence of bytes 
	 * is called deserialization.
	 * we recover the data.ser file in a ArrayList of {@link HtmlFile} then we will do our search
	 * by browsing the ArrayList
	 * @param title
	 * 		the title to search 
	 * @return
	 * 		ArrayList of String contain the Location of Html File who there title
	 * 		tag Start with the title to search passed as a parameter. 
	 * @see ObjectInputStream
	 * @see FileInputStream
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getAllTitles(String title) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		ArrayList<HtmlFile> array = new ArrayList<HtmlFile>();
		
		try {

			ObjectInputStream stream = new ObjectInputStream(new FileInputStream("data.ser"));
			
			HtmlFile html;
			
			array = (ArrayList<HtmlFile>) stream.readObject();
			
			for(Iterator<HtmlFile> it = array.iterator() ; it.hasNext() ;) {

				html = it.next();
				
				if(html.getTitle().startsWith(title) && !list.contains(html.getTitle())) {
					
					list.add(html.getTitle());
					
				}
				
			}
			
			stream.close();
			
		}catch (EOFException e) {
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		}
		
		return list;
		
		
	}
	/**
	 * this method gives us the number of Files Html which are present 
	 * on the indexation and the site map
	 * @return
	 * 		the numbers of Html Files present
	 */
	public long getNumberOfPages() {
		
		return this.ArrayHtml.size();
		
	}
	/**
	 * this method can print our ArrayList of HtmlFile
	 * @see StringBuffer
	 * @see Iterator
	 */
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		Collection<HtmlFile> values = this.ArrayHtml;
		
		for(HtmlFile file : values) {
			
			sb.append(file.toString());
			
			sb.append("\n");
				
		}
		
		return sb.toString();
	}

}
