package fr.mertzel.ose.modele;
import fr.mertzel.ose.controleur.Controleur;
import fr.mertzel.ose.vue.*;
public class Ose {
	public static void main(String [] args){
		PlanSalle modele = new PlanSalle("Sans Nom") ;
		FenetrePrincipale vue = new FenetrePrincipale(modele) ;
		new Controleur(vue,modele) ;
	}
}
