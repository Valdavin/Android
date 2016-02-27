import java.util.Scanner; 

/**
*
* Compiler : javac TestJeu.java
* Executer : java TestJeu
*
*/
public class TestJeu {

	public static void main (String[] args){

		// Lecture de la console
		Scanner scanner = new Scanner(System.in);
		String choix = null;
	
		// Resultat des jeux
		Resultat resultat = new Resultat();
	
		do {
	
			// Creation de objet de type Jeu
			// Le jeu est un one-shot (utilisable qu'une fois)
			// La main de l'ordinateur est initialisé dans le constructeur de Jeu
			Jeu jeu = new Jeu();

			// Main du joueur
			System.out.println("Quelle main voulez vous jouer ?");
			System.out.println("Cailloux : " + Jeu.CAILLOUX);
			System.out.println("Ciseaux : " + Jeu.CISEAUX);
			System.out.println("Papier : " + Jeu.PAPIER);
			int mainJouer = scanner.nextInt();
			scanner.nextLine();	// permet de vider le tampon (retour chariot)
		
			System.out.print("Main du joueur : ");
			if (mainJouer == Jeu.CAILLOUX) {
				jeu.setMainJoueur(Jeu.CAILLOUX);
				System.out.println("Cailloux");
				
			} else if (mainJouer == Jeu.CISEAUX) {
				jeu.setMainJoueur(Jeu.CISEAUX);
				System.out.println("Ciseaux");

			} else if (mainJouer == Jeu.PAPIER) {
				jeu.setMainJoueur(Jeu.PAPIER);
				System.out.println("Papier");

			} else {
				// par defaut
				jeu.setMainJoueur(Jeu.PAPIER);
				System.out.println("Papier");
			}
			
			// Affichage de la main de l'ordinateur
			System.out.print("Main de l'ordinateur : ");
			if (jeu.getMainOrdinateur() == Jeu.CAILLOUX) {
				System.out.println("Cailloux");

			} else if (jeu.getMainOrdinateur() == Jeu.PAPIER) {
				System.out.println("Papier");

			} if (jeu.getMainOrdinateur() == Jeu.CISEAUX) {
				System.out.println("Ciseaux");
			
			}
	  
			// On enregistre le jeu dans l'objet de type Resultat 
			if (jeu.joueurGagne()) {
				System.out.println("VICTOIRE");
				resultat.addVictoire();
			} else if (jeu.egalite()) {
				System.out.println("EGALITE");
				resultat.addEgalite();
			} else {
				System.out.println("DEFAITE");
				resultat.addDefaite();
			} 
		
			// Affichage des résultats
			System.out.println("Nombre de victoires : " + resultat.getNombreVictoire());
			System.out.println("Nombre d'égalités : " + resultat.getNombreEgalite());
			System.out.println("Nombre de défaites : " + resultat.getNombreDefaite());
		
			//
			System.out.println("Voulez-vous recommencer ? o/n");
			choix = scanner.nextLine();
		
		} while (choix.equals("o"));
	}
}