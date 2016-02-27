import java.util.Random;

public class Jeu {

    public static final int CAILLOUX = 0;
    public static final int CISEAUX = 1;
    public static final int PAPIER = 2;
    private static final Random random = new Random();

	private int mainJoueur;
	private int mainOrdinateur;
	
	public Jeu() {
		initMainOrdinateur();
	}
	
	public int getMainJoueur() {
		return mainJoueur;
	}

	public void setMainJoueur(int mainJoueur) {
		this.mainJoueur = mainJoueur;
	}

	public int getMainOrdinateur() {
		return mainOrdinateur;
	}
	
	private void setMainOrdinateur(int mainOrdinateur) {
		this.mainOrdinateur = mainOrdinateur;
	}

	//////////////////////////////////
	private void initMainOrdinateur() {
        mainOrdinateur = random.nextInt(3);
	}
	
	//////////////////////////////////
	public boolean egalite() {
		return mainJoueur == mainOrdinateur;
	}
	
	public boolean joueurGagne() {
		return (mainJoueur == CAILLOUX && mainOrdinateur == CISEAUX)
				|| (mainJoueur == CISEAUX && mainOrdinateur == PAPIER)
				|| (mainJoueur == PAPIER && mainOrdinateur == CAILLOUX);
	}

}
