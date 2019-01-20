package SystemSolaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * GeneratSiteMap is a java class which has tow special methods( sauvgardXml , link addSiteMap)
 * this class have one attribute how is an ArrayList of {@linkplain SiteMap}
 * Each GeneratSiteMap instance has a capacity. The capacity is the size of the array 
 * used to store the elements in the list. 
 * It is always at least as large as the list size. 
 * As elements are added to an ArrayList, its capacity grows automatically. 
 * The details of the growth policy are not specified beyond the fact that adding an element has constant amortized time cost.
 * @see SiteMap
 * @see ArrayList
 * @author Ayad Ishak and Arhab Samira
 * @since JDK8.0
 * @version 1.8.0_144 created at 02 Dec,2017
 */

public class GeneratSiteMap{
	
	
	private ArrayList<SiteMap> SiteMapArray;
	
	/**
	 * Construct and initialize a GenerateSiteMap , we do initilize the {@link ArrayList}
	 */
	
	public GeneratSiteMap() {
		
		SiteMapArray = new ArrayList<SiteMap>();
		
	}
	
	/**
	 * this method can create a Sitemaps which is an easy way for web masters to inform search engines about pages 
	 * on their sites that are available for crawling. 
	 * In its simplest form, a Sitemap is an XML file that lists URLs for a site along with additional metadata about each 
	 * URL (when it was last updated, how often it usually changes, and how important it is, relative to other URLs in the site) 
	 * {@link SiteMap} , so that search engines can more intelligently crawl the site.
	 * @param file
	 * 			the  File that we will put in the sitemap.xml
	 * @see BufferedReader
	 * @see ArrayList
	 */
	
	public void sauvgardXml(File file) {
		
		if(!file.exists()) {
		
			try {
			
				BufferedWriter write = new BufferedWriter(new FileWriter(file));
			
					write.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			
					write.newLine();
			
					write.write("<urlset xmlns=\"http://www.exemple.org/\">");
			
					write.newLine();
			
					for(Iterator<SiteMap> it = SiteMapArray.iterator() ; it.hasNext() ; ) {
			
						String loc,freq;
						float priority;
						long lastMod;
				
						SiteMap st = it.next();
				
						loc = st.getLoc();
						freq = st.getFreq();
						priority = st.getPriority();
						lastMod = st.getLastmod();
				
						write.write("	<url>");
			
						write.newLine();
			
						write.write("		<loc>"+loc+"</loc>");
			
						write.newLine();
			
						if(lastMod != 0) {
			
							write.write("		<lastmod>"+new Date(lastMod)+"</lastmod>");
			
							write.newLine();
				
						}
			
						if(freq != null) {
			
							write.write("		<changefreq>"+freq+"</changefreq>");
			
							write.newLine();
			
						}
			
						if(priority != 0f) {
				
							write.write("		<priority>"+priority+"</priority>");
			
							write.newLine();
			
						}
				
						write.write("	</url>");
				
						write.newLine();
			
					}
			
						write.write("</urlset>");
			
						write.close();
				
						
			} catch (IOException e) {
			
				e.printStackTrace();
			
			}
			
		
		}else {
			
			System.err.println("Le Fichier "+file.getName()+" est deja crée");
			
		}
		
		
	}
	
	/**
	 * this methode can read any text File 
	 * @param titel
	 * 		the text File that we will read 
	 * @see BufferedReader
	 */
	
	public void reLectureXml(String titel) {
		
		try {
			
			String line;
			
			BufferedReader read= new BufferedReader(new FileReader(titel));
			
			while((line = read.readLine()) != null) {
				
				System.out.println(line);
				
			}
			
			read.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}
	/**
	 * this method can add a SiteMap to our ArrayList if it didn't exist yet
	 * if it
	 *  exist the method throw a {@linkplain SiteAlreadyExistsException}
	 * @param s
	 * 		the instance of SiteMap that we will add
	 * @throws SiteAlreadyExistsException
	 * 		when the Site excites in our sitemap.xml we throw this Exception
	 * @see SiteMap
	 */
	public void addSiteMap(SiteMap s) throws SiteAlreadyExistsException {
		
		int i=0;
		
		
		while( (i < SiteMapArray.size() ) && !(SiteMapArray.get(i).equals(s)) ) {
			
			i++;
			
		}
		
		if(i >= SiteMapArray.size()) {
		
			if(s.getLoc().endsWith("/51.html")) {
				
				SiteMapArray.add(0, s);
				
			}else {
			
				SiteMapArray.add(s);	
			
			}
		}else {
			
			throw new  SiteAlreadyExistsException("Site existe deja\n");
			
		}
		
		
	}
	/**
	 * this method gives us the number of pages on our sitemap File
	 * @return
	 * 		the numbers of pages in the sitemap.xml
	 */
	public int numbreOfPage() {
		
		return SiteMapArray.size();
		
	}
	/**
	 * this method can print our ArrayList of SiteMap
	 * @see StringBuffer
	 * @see Iterator
	 */
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		
		for(Iterator<SiteMap> it = SiteMapArray.iterator() ; it.hasNext();){
			
			sb.append(it.next().toString());
			
			sb.append("\n");
			
		}
		
		return sb.toString();
		
	}

}