package fr.mertzel.ose.controleur;
import fr.mertzel.ose.vue.*;

import java.util.ArrayList;

public abstract class Observe {
	private ArrayList<Observateur> lesObservateurs = new ArrayList<Observateur>();
	
	public void ajouter(Observateur observateur){
		lesObservateurs.add(observateur);
	}
	public void supprimer(Observateur observateur){
		lesObservateurs.remove(observateur);
	}
	public void notifier(){
		for(Observateur observateur : lesObservateurs){
			observateur.actualiser();
		}
	}
	
}
