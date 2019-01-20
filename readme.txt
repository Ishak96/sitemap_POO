# PROJET Generateur de Fichier SITEMAP.XML Et Moteur de Recherche
## AYAD Ishak Groupe D
### DATE 26/12/2017
### univ CERGY PONTOISE
--------------------------------------------------------------------------------------------------------------------------
Comment utiliser l'application : 
===============================
#1-Mode Graphique : s'il vous plaît respecter l'ordre de l'etape 1!.
------------------
etape 1 : generation du sitemap.xml et l'indexation data.ser cliquer sur (File ==> New)
	  choisissez le dossier de depart et lancé ça va prendre (20 a 30)-seconds
	  puis vous Auriez un message de confirmation cliquer sur Ok.

==> vous pouvez faire votre recherche sur la premier JFrame de recherche.
==> vous pouvez visualiser le contenu du sitemap.xml pour cela clique sur(File ==> Affiher) l'Affichage prend un peut de temps.

!si vous avez des problème d'utilisation regarder le rapport de projet y'a plus d'explications.

#2-Mode Console : sur un terminal
----------------
java -jar cli.jar -d /chemin vers le dossier/ -o sitemap.xml : creation du sitemap
java -jar cli.jar -d /chemin vers le dossier/ -c data.ser : creation du fichier de l'indexation
java -jar cli.jar -s mot : recherche par mot clef

	! il ne faut pas changer le nom des deux fichiers car certain methodes repose sur leurs nom ! 
--------------------------------------------------------------------------------------------------------------------------
Rapide introduction sur le sujet du projet :
===========================================
#1-Un sitemap, littéralement plan de site, 
est un fichier XML (ou plus rarement texte) qui permet d'indiquer 
aux robots d'indexation des moteurs de recherche une liste des URL à indexer pour un site donné. 
Le sitemap permet également de transmettre des informations sur la nature des éléments à indexer et éventuellement sur la façon de les indexer.

#2-Un Moteur de Recherche des pages Html 
qui se repose sur les deux grandes balises des Fichiers Html (<h1> et <title>).
--------------------------------------------------------------------------------------------------------------------------
Qu'est ce que c'est : une petite application Java qui gener un SiteMap d'un jeu de donneés et referencé le jeu pour creé un moteur de recherche
====================
#1-Un mode Console qui s'exucute avec des commandes sur terminal.

#2-Un mode Graphique.
