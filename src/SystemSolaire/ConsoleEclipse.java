package SystemSolaire;
import java.util.ArrayList;

public class ConsoleEclipse {

	public static void main(String[] args) {
		
		SaveHtmlProg save = new SaveHtmlProg();
		
		ArrayList<String> rechercheH1;
		
		ArrayList<String> rechercheTitle;
		
		save.sauvgardeSiteMap("/root/promenade_fr.d","dayli","sitemap.xml");
		
		save.sauvgardeIndexation("data.ser");
		
		rechercheH1 = save.rechercheByH1("terre");
		
		rechercheTitle = save.rechercheByTitle("terre");
		
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
