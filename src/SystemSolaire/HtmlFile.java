package SystemSolaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * HtmlFile is a Java Class which have four attributes who specialize a Html File (Location , baliseTitle , baliseH1 , last-modification)
 * -(baliseTitle,baliseH1) is tags : a basic element of HTML Files 
 * -Html File is used to describe the visual appearance and the content of a WEB page
 * -the most known tags are (html,head,body,title,h1..)
 * -so this class can recover the principal tags of an (this class instance) with two specialized method(findH1(),findTitle)
 * @author Ayad Ishak and Arhab Samira
 * @since JDK8.0
 * @version 1.8.0_144 created at 15 Dec,2017
 */
public class HtmlFile implements Serializable{

	/**
	 * @deprecated
	 * @serial
	 */
	private static final long serialVersionUID = 1L;

	private String link;	
	
	private String baliseTitle;
	
	private long lastmod;
	
	private String baliseH1;
	/**
	 * Construct and initialize a HtmlFile with passing the four parameters
	 * @param link link of the Html File
	 * @param baliseTitle the first tag of the Html File
	 * @param baliseH1 the second tag of the Html File 
	 * @param lastmod the last-modification of the Html File
	 */
	public HtmlFile(String link,String baliseTitle,String baliseH1,long lastmod) {
		
		this.baliseH1 = baliseH1;
		
		this.baliseTitle = baliseTitle;
		
		this.link = link;
		
		this.lastmod = lastmod;
		
	}
	/**
	 * Construct and initialize a HtmlFile with two parameters
	 * using the constructor with two parameters allows us
	 * to have an instance of the FileHtml class without the other two
	 * (tagTitle, tagH1) that will be determined with this instance.
	 * @param link the Link of the Html File
	 * @param lastmod the last-modification of Html File
	 */
	public HtmlFile(String link,long lastmod) {
		
		this.link = link;
		this.lastmod = lastmod;
				
	}
	
	/**
	 * this method allows us to find the H1 tag of an Html file
	 * we take into account the case where an Html file has multiple H1
	 * for that I use the class Pattern and Matcher
	 * @see Pattern
	 * @see Matcher
	 * 
	 */
	public void findH1() {
		
		String line;
		
		StringBuffer sb = new StringBuffer();
		
		try {
			
			BufferedReader read = new BufferedReader (new FileReader(this.link));
			
			while((line = read.readLine()) != null) {
				
				Pattern pr = Pattern.compile(".*<H1>(.*)\\</H1\\>");
				
				Pattern pr1 = Pattern.compile(".*<h1>(.*)\\</h1\\>");
			     
				Matcher m = pr.matcher(line);
				
				Matcher m1 = pr1.matcher(line);
				
				if(m.matches()) {
					
					String k = m.group(1);
					
					String k1,k2;
					
					Pattern prs = Pattern.compile(".*<strong class=\"gras\">(.*)\\</strong>.*");
					
					Matcher ms = prs.matcher(k);
				
					if(ms.matches()) {
					
						k1 = ms.group(1);
						
						k = Pattern.compile("<strong class=\"gras\">(.*)\\</strong>").matcher(k).replaceAll(k1);			
					
					}
					
					Pattern pre = Pattern.compile(".*<em class=\"italique\">(.*)</em>.*");
					
					Matcher me = pre.matcher(k);
				
					if(me.matches()) {
					
						k2 = me.group(1);
						
						k = Pattern.compile("<em class=\\\"italique\\\">(.*)</em>").matcher(k).replaceAll(k2);			
					
					}
					
					k = Pattern.compile("<A NAME=\"debut\"></A>").matcher(k).replaceAll("");
					
					k = Pattern.compile("<br />").matcher(k).replaceAll("");
										
					k = Pattern.compile("&agrave;").matcher(k).replaceAll("à");
					
					k = Pattern.compile("&eacute;").matcher(k).replaceAll("é");
					
					k = Pattern.compile("&Eacute;").matcher(k).replaceAll("é");
					
					k = Pattern.compile("&egrave;").matcher(k).replaceAll("è");
					
					k = Pattern.compile("&Egrave;").matcher(k).replaceAll("è");
					
					k = Pattern.compile("&ecirc;").matcher(k).replaceAll("ê");
					
					k = Pattern.compile("&Ecirc;").matcher(k).replaceAll("ê");
					
					k = Pattern.compile("&euml;").matcher(k).replaceAll("ë");
					
					k = Pattern.compile("&icirc;").matcher(k).replaceAll("î");
					
					k = Pattern.compile("&Iuml;").matcher(k).replaceAll("ï");
					
					k = Pattern.compile("&iuml;").matcher(k).replaceAll("ï");
					
					k = Pattern.compile("&ocirc").matcher(k).replaceAll("ô");
					
					k = Pattern.compile("&ucirc;").matcher(k).replaceAll("û");
					
					k = Pattern.compile("&acirc;").matcher(k).replaceAll("â");
					
					k = Pattern.compile("&nbsp;").matcher(k).replaceAll(" ");
										
							sb.append(k);
							
							sb.append(" ");
														
				}
				
				if(m1.matches()) {
						
					String k = m1.group(1);
					
					String k1,k2;
					
					Pattern prs = Pattern.compile(".*<strong class=\"gras\">(.*)\\</strong>.*");
					
					Matcher ms = prs.matcher(k);
				
					if(ms.matches()) {
					
						k1 = ms.group(1);
						
						k = Pattern.compile("<strong class=\"gras\">(.*)\\</strong>").matcher(k).replaceAll(k1);
						
					
					}
					
					Pattern pre = Pattern.compile(".*<em class=\"italique\">(.*)</em>.*");
					
					Matcher me = pre.matcher(k);
				
					if(me.matches()) {
					
						k2 = me.group(1);
						
						k = Pattern.compile("<em class=\\\"italique\\\">(.*)</em>").matcher(k).replaceAll(k2);			
					
					}
					
					k = Pattern.compile("<A NAME=\"debut\"></A>").matcher(k).replaceAll("");
					
					k = Pattern.compile("<br />").matcher(k).replaceAll("");
										
					k = Pattern.compile("&agrave;").matcher(k).replaceAll("à");
					
					k = Pattern.compile("&eacute;").matcher(k).replaceAll("é");
					
					k = Pattern.compile("&Eacute;").matcher(k).replaceAll("é");
					
					k = Pattern.compile("&egrave;").matcher(k).replaceAll("è");
					
					k = Pattern.compile("&Egrave;").matcher(k).replaceAll("è");
					
					k = Pattern.compile("&ecirc;").matcher(k).replaceAll("ê");
					
					k = Pattern.compile("&Ecirc;").matcher(k).replaceAll("ê");
					
					k = Pattern.compile("&euml;").matcher(k).replaceAll("ë");
					
					k = Pattern.compile("&icirc;").matcher(k).replaceAll("î");
					
					k = Pattern.compile("&Iuml;").matcher(k).replaceAll("ï");
					
					k = Pattern.compile("&iuml;").matcher(k).replaceAll("ï");
					
					k = Pattern.compile("&ocirc").matcher(k).replaceAll("ô");
					
					k = Pattern.compile("&ucirc;").matcher(k).replaceAll("û");
					
					k = Pattern.compile("&acirc;").matcher(k).replaceAll("â");
					
					k = Pattern.compile("&nbsp;").matcher(k).replaceAll(" ");
										
						sb.append(k);
		
						sb.append(" ");
														
				}
											
					
			}
			
			read.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		this.baliseH1 = sb.toString().toLowerCase();
		
		if(this.baliseH1.startsWith(" ")){
			
			this.baliseH1 = this.baliseH1.replaceFirst(" ","");
			
		}
		
		if(this.baliseH1.endsWith("  ")){
			
			this.baliseH1 = this.baliseH1.replaceAll(".\\Z","");
			
			this.baliseH1 = this.baliseH1.replaceAll(".\\Z","");
		}
		
		if(this.baliseH1.endsWith(" ")){
			
			this.baliseH1 = this.baliseH1.replaceAll(".\\Z","");
			
		}
				
	}
	/**
	 * this method allows us to find the TITLE tag of an Html file
	 * we take into account the case where an Html file has multiple H1
	 * for that I use the class Pattern and Matcher
	 * @see Pattern
	 * @see Matcher
	 * 
	 */
	public void findTitle() {
		
		String line;
		
		StringBuffer sb = new StringBuffer();
		
		try {
			
			BufferedReader read = new BufferedReader (new FileReader(this.link));
			
			while((line = read.readLine()) != null) {
				
				Pattern pr = Pattern.compile(".*<TITLE>(.*)\\</TITLE\\>");
				
				Pattern pr1 = Pattern.compile(".*<title>(.*)\\</title\\>");
			     
				Matcher m = pr.matcher(line);
				
				Matcher m1 = pr1.matcher(line);
				
				if(m.matches()) {
					
					String k = m.group(1);
					
					String k1,k2;
					
					Pattern prs = Pattern.compile(".*<strong class=\"gras\">(.*)\\</strong>.*");
					
					Matcher ms = prs.matcher(k);
				
					if(ms.matches()) {
					
						k1 = ms.group(1);
						
						k = Pattern.compile("<strong class=\"gras\">(.*)\\</strong>").matcher(k).replaceAll(k1);			
					
					}
					
					Pattern pre = Pattern.compile(".*<em class=\"italique\">(.*)</em>.*");
					
					Matcher me = pre.matcher(k);
				
					if(me.matches()) {
					
						k2 = me.group(1);
						
						k = Pattern.compile("<em class=\\\"italique\\\">(.*)</em>").matcher(k).replaceAll(k2);			
					
					}
					
					k = Pattern.compile("<A NAME=\"debut\"></A>").matcher(k).replaceAll("");
					
					k = Pattern.compile("<br />").matcher(k).replaceAll("");
										
					k = Pattern.compile("&agrave;").matcher(k).replaceAll("à");
					
					k = Pattern.compile("&eacute;").matcher(k).replaceAll("é");
					
					k = Pattern.compile("&Eacute;").matcher(k).replaceAll("é");
					
					k = Pattern.compile("&egrave;").matcher(k).replaceAll("è");
					
					k = Pattern.compile("&Egrave;").matcher(k).replaceAll("è");
					
					k = Pattern.compile("&ecirc;").matcher(k).replaceAll("ê");
					
					k = Pattern.compile("&Ecirc;").matcher(k).replaceAll("ê");
					
					k = Pattern.compile("&euml;").matcher(k).replaceAll("ë");
					
					k = Pattern.compile("&icirc;").matcher(k).replaceAll("î");
					
					k = Pattern.compile("&Iuml;").matcher(k).replaceAll("ï");
					
					k = Pattern.compile("&iuml;").matcher(k).replaceAll("ï");
					
					k = Pattern.compile("&ocirc").matcher(k).replaceAll("ô");
					
					k = Pattern.compile("&ucirc;").matcher(k).replaceAll("û");
					
					k = Pattern.compile("&acirc;").matcher(k).replaceAll("â");
					
					k = Pattern.compile("&nbsp;").matcher(k).replaceAll(" ");
										
							sb.append(k);
							
							sb.append(" ");
														
				}
				
				if(m1.matches()) {
						
					String k = m1.group(1);
					
					String k1,k2;
					
					Pattern prs = Pattern.compile(".*<strong class=\"gras\">(.*)\\</strong>.*");
					
					Matcher ms = prs.matcher(k);
				
					if(ms.matches()) {
					
						k1 = ms.group(1);
						
						k = Pattern.compile("<strong class=\"gras\">(.*)\\</strong>").matcher(k).replaceAll(k1);
						
					
					}
					
					Pattern pre = Pattern.compile(".*<em class=\"italique\">(.*)</em>.*");
					
					Matcher me = pre.matcher(k);
				
					if(me.matches()) {
					
						k2 = me.group(1);
						
						k = Pattern.compile("<em class=\\\"italique\\\">(.*)</em>").matcher(k).replaceAll(k2);			
					
					}
					
					k = Pattern.compile("<A NAME=\"debut\"></A>").matcher(k).replaceAll("");
					
					k = Pattern.compile("<br />").matcher(k).replaceAll("");
										
					k = Pattern.compile("&agrave;").matcher(k).replaceAll("à");
					
					k = Pattern.compile("&eacute;").matcher(k).replaceAll("é");
					
					k = Pattern.compile("&Eacute;").matcher(k).replaceAll("é");
					
					k = Pattern.compile("&egrave;").matcher(k).replaceAll("è");
					
					k = Pattern.compile("&Egrave;").matcher(k).replaceAll("è");
					
					k = Pattern.compile("&ecirc;").matcher(k).replaceAll("ê");
					
					k = Pattern.compile("&Ecirc;").matcher(k).replaceAll("ê");
					
					k = Pattern.compile("&euml;").matcher(k).replaceAll("ë");
					
					k = Pattern.compile("&icirc;").matcher(k).replaceAll("î");
					
					k = Pattern.compile("&Iuml;").matcher(k).replaceAll("ï");
					
					k = Pattern.compile("&iuml;").matcher(k).replaceAll("ï");
					
					k = Pattern.compile("&ocirc").matcher(k).replaceAll("ô");
					
					k = Pattern.compile("&ucirc;").matcher(k).replaceAll("û");
					
					k = Pattern.compile("&acirc;").matcher(k).replaceAll("â");
					
					k = Pattern.compile("&nbsp;").matcher(k).replaceAll(" ");
										
						sb.append(k);
		
						sb.append(" ");
														
				}
											
					
			}
			
			read.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		this.baliseTitle = sb.toString().toLowerCase();
		
		if(this.baliseTitle.startsWith(" ")){
			
			this.baliseTitle = this.baliseTitle.replaceFirst(" ","");
			
		}
		
		if(this.baliseTitle.endsWith("  ")){
			
			this.baliseTitle = this.baliseTitle.replaceAll(".\\Z","");
			
			this.baliseTitle = this.baliseTitle.replaceAll(".\\Z","");
		}
		
		if(this.baliseTitle.endsWith(" ")){
			
			this.baliseTitle = this.baliseTitle.replaceAll(".\\Z","");
			
		}
				
	}
	/**
	 * compare this HtmlFile with another object that was passed as an argument 
	 * @param obj the Object that we will compare to this HtmlFile
	 * @see Object
	 * @return a boolean (true,false) true if it's equals , false othewise 	
	 */
	public boolean equals(Object obj) {
		
		boolean eglite = false;
		
		if(obj instanceof HtmlFile) {
			
			eglite = ((HtmlFile)obj).getLink().equals(this.link) && ((HtmlFile)obj).getH1().equals(this.baliseH1) && ((HtmlFile)obj).getTitle().equals(this.baliseTitle);
			
		}
		
		return eglite;
	}
	/**
	 * 	get the Link of this HtmlFile
	 * @return 
	 * 		Link of the HtmlFile
	 */
	public String getLink() {
		
		return this.link;
		
	}
	/**
	 * 	get the tag H1 of this HtmlFile
	 * @return 
	 * 		tag H1 of the HtmlFile
	 */
	public String getH1() {
		
		return this.baliseH1;
	}
	/**
	 * 	get the tag Title of this HtmlFile
	 * @return 
	 * 		tag Title of the HtmlFile
	 */
	public String getTitle() {
		
		return this.baliseTitle;
		
	}
	/**
	 * 	get the last modification of this HtmlFile
	 * @return 
	 * 		last-modification of the HtmlFile
	 */
	public long getLastmod() {
		
		return this.lastmod;
		
	}
	/**
	 * print this HtmlFile
	 * @return a print HtmlFile
	 */
	public String toString() {
				
		return link+" "+baliseTitle+" "+baliseH1;
		
	}

}
