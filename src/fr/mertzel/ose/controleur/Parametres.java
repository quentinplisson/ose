 package fr.mertzel.ose.controleur;
import fr.mertzel.ose.vue.Orientation;

public class Parametres {
	public final static int NB_RANGEES =  4 ;
	public final static int NB_TRAVEES = 4 ;
	
	public final static int HAUTEUR_RANGEE = 100 ;
	public final static int LARGEUR_TRAVEE = 100 ;
	
	public final static int LARGEUR_POSTE = 20 ;
	public final static int LONGUEUR_POSTE = 50 ;
	public final static int LARGEUR_PERSONNE = 20 ;
	
	
	public static int centrePositionX(int travee) {
		return travee * LARGEUR_TRAVEE + LARGEUR_TRAVEE / 2 ;
	}
	
	public static int centrePositionY(int rangee){
		return rangee * HAUTEUR_RANGEE + HAUTEUR_RANGEE / 2 ;
	}
	
	public static int posteX(int travee,int orientation){
		int centreX = centrePositionX(travee) ;
		switch(orientation){
			case Orientation.NORD :
				centreX -= 25 ;
				break ;
			case Orientation.EST :
				break ;
			case Orientation.SUD :
				centreX -= 25 ;
				break ;
			case Orientation.OUEST :
				centreX -= 20 ;
				break ;
		}
		return centreX ;
	}
	
	public static int posteY(int rangee,int orientation){
		int centreY = centrePositionY(rangee) ;
		switch(orientation){
			case Orientation.NORD :
				centreY -= 20 ;
				break ;
			case Orientation.EST :
				centreY -= 25 ;
				break ;
			case Orientation.SUD :
				break ;
			case Orientation.OUEST :
				centreY -= 25 ;
				break ;
		}
		return centreY ;
	}
	
	public static int personneX(int travee,int orientation){
		int centreX = centrePositionX(travee) ;
		switch(orientation){
			case Orientation.NORD :
				centreX -= 10 ;
				break ;
			case Orientation.EST :
				centreX -= 25 ;
				break ;
			case Orientation.SUD :
				centreX -= 10 ;
				break ;
			case Orientation.OUEST :
				centreX += 5 ;
				break ;
		}
		return centreX ;
	}
	
	public static int personneY(int rangee,int orientation){
		int centreY = centrePositionY(rangee) ;
		switch(orientation){
			case Orientation.NORD :
				centreY += 5 ;
				break ;
			case Orientation.EST :
				centreY -= 10 ;
				break ;
			case Orientation.SUD :
				centreY -= 25 ;
				break ;
			case Orientation.OUEST :
				centreY -= 10 ;
				break ;
		}
		return centreY ;
	}
	
	public static int centrePersonneX(int travee,int orientation){
		int x = personneX(travee,orientation) ;
		x += LARGEUR_PERSONNE / 2 ;
		return x ;
	}
	
	public static int centrePersonneY(int rangee,int orientation){
		int y = personneY(rangee,orientation) ;
		y += LARGEUR_PERSONNE / 2 ;
		return y ;
	}
	
}