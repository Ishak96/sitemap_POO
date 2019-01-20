package SystemSolaire;

/**
 * SiteMAp is a Java Class
 * SiteMap represent a part of a sitemap.xml file which has four parameters (location,priority,last-modification) 
 * @author Ayad Ishak and Arhab Samira
 * @since JDK8.0
 * @version 1.8.0_144 created at 02 Dec,2017
 */

public class SiteMap {
	
	private String loc;
	
	private String freq;
	
	private float priority;
	
	private long lastmod ;
	
	/**
	 * constructs and initializes a SiteMap on (loc,lastmod,freq) also initialize his priority on 0.5 
	 * @param loc	
	 * 		the location of this SiteMap
	 * @param lastmod	
	 * 		the last modification of this SiteMap
	 * @param freq	
	 * 		the frequency of this SiteMap
	 */
	
	public SiteMap(String loc,long lastmod, String freq) {
		
		this.lastmod = lastmod ;
		
		priority = 0.5f;
		
		this.freq = freq;
		
		this.loc = loc;
		
	}	
	/**
	 * constructs and initializes a SiteMap on (loc,freq,priority,lastmod)
	 * @param loc	
	 * 		the location of this SiteMap
	 * @param lastmod	
	 * 		the last modification of this SiteMap
	 * @param freq	
	 * 		the frequency of this SiteMap
	 * @param priority
	 * 		the priority of this SiteMap
	 */
	
	public SiteMap(String loc,String freq,float priority,long lastmod) {
		
		this.loc = loc;
		
		this.freq = freq;
		
		this.priority = priority;
		
		this.lastmod = lastmod;
		
		
	}	
	/**
	 * constructs and initializes a SiteMap on (loc,lastmod) also initialize his priority on 0.5 
	 * @param loc
	 * 		the location of this SiteMap
	 * @param lastmod 
	 * 		the last modification of the html file
	 */
	
	public SiteMap(String loc,long lastmod) {
		
		this.loc = loc;
		
		this.lastmod = lastmod;
		
		priority = 0.5f;
		
	}
	/**
	 * 		get the location of this SiteMap
	 * @return	location of the html file
	 */
	
	public String getLoc() {
		
		return loc;
		
	}
	/**
	 * 		change the location of this SiteMap
	 * @param loc the new location of this SiteMap
	 */
	public void setLoc(String loc) {
		
		this.loc = loc;
		
	}
	/**
	 * 		get the frequency of this SiteMap
	 * @return	frequency of the page
	 */
	public String getFreq() {
		
		return freq;
		
	}
	/**
	 * 		change the frequency of this SiteMap
	 * @param freq	the new frequency of this SiteMap
	 */
	public void setFreq(String freq) {
		
		this.freq = freq;
		
	}
	/**
	 * 		get the priority of this SiteMap
	 * @return	priority of the page
	 */
	public float getPriority() {
		
		return priority;
		
	}
	/**
	 * 		change the priority of this SiteMap
	 * @param priority	the new priority of this SiteMap
	 */
	public void setPriority(float priority) {
		
		this.priority = priority;
		
	}
	/**
	 * 		return the last modification of this SieMap
	 * @return	last-modification of the html file
	 */
	public long getLastmod() {
		
		return lastmod;
		
	}
	/**
	 * 		change the last modification of this SiteMap
	 * @param lastmod	the new last-modification of this SiteMap
	 */
	public void setLastmod(long lastmod) {
		
		this.lastmod = lastmod;
		
	}
	/**
	 * 		compare this SiteMap with another object that was passed as an argument 
	 * @param obj the object to compare to this SiteMap
	 * @return a boolean (true,false) true if it's equals , false othewise   
	 */
	public boolean equals(Object obj) {
		
		boolean egalite = false;
		
		if(obj instanceof SiteMap) {
			
			egalite = (this.loc.equals(((SiteMap) obj).getLoc())) && (this.freq.equals(((SiteMap) obj).getFreq())) && (this.lastmod == ((SiteMap) obj).getLastmod()) && (this.priority == ((SiteMap) obj).getPriority());
			
		}
		
		return egalite;
				
	}
	/**
	 * 		print this SiteMap 
	 * @return	a print SiteMap
	 */
	public String toString() {
		
		return ":"+loc+":"+freq+":"+priority+":"+lastmod ;
	}
}
