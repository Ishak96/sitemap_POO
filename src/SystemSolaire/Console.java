package SystemSolaire;

import java.util.ArrayList;

/**
 * Console is a Java Class which allows us to test our project on a line of command ,
 * this class represents a passage of argument to main |
 *	to genre the sitemap : java  -jar  cli.jar  -d  "Diractory path"  -o  sitemap.xml ,
 *	to genre the indexation : java  -jar  cli.jar  -d  "Diractory path"  -c  data.ser ,
 *	to search : java  -jar  cli.jar  -s  soleil ,
 *	Frames : java  -jar  gui.jar ,
 * @author Ayad Ishak and Arhab Samira
 * @since JDK8.0
 * @version 1.8.0_144 created at 23 Dec,2017
 */
public class Console {

	public static void main(String[] args) {
		
		ArrayList<String> rechercheH1 = new ArrayList<String>();
		
		ArrayList<String> rechercheTitle = new ArrayList<String>();
		
		SaveHtmlProg save = new SaveHtmlProg();
		/*
		 *Commandes pour la génération des deux fichier 
		 */
		if(args[0].equals("-d")) {
		
			if(args[2].equals("-o")) {
				//methode de la classe SaveHtmlProg
				save.sauvgardeSiteMap(args[1],"always",args[3]);
			}
			
			if(args[2].equals("-c")) {
				//methode de la classe SaveHtmlProg spécialisé pour ce cas si
				save.sauvgardeIndexationJar(args[1],args[3]);	
		
			}
		}
		/*
		 * Commandes pour la recherche par mot clefs
		 */
		if(args[0].equals("-s")) {
			
			args[1] = args[1].toLowerCase();
			
				rechercheH1 = save.rechercheByH1(args[1]);
		
				rechercheTitle = save.rechercheByTitle(args[1]);
			
			for(int i = 0 ; i < rechercheH1.size() ; i++) {
			
				if(!rechercheTitle.contains(rechercheH1.get(i))) {
				
					rechercheTitle.add(rechercheH1.get(i));
				
				}
			}
			
			for(int j = 0 ; j < rechercheTitle.size() ; j++) {
				
				System.out.println(rechercheTitle.get(j));
			
			}
		}
	}
}
