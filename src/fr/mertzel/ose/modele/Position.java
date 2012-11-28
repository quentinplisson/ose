 package fr.mertzel.ose.modele;
import fr.mertzel.ose.controleur.Parametres;
import fr.mertzel.ose.vue.Orientation;

public class Position {
	private int rangee ;
	private int travee ;
	
	public Position(int rangee, int travee) {
		super();
		this.rangee = rangee;
		this.travee = travee;
	}
	
	public Position() {
		super();
		this.rangee = -1;
		this.travee = -1;
	}
	
	public int getRangee() {
		return rangee;
	}
	public void setRangee(int rangee) {
		this.rangee = rangee;
	}
	public int getTravee() {
		return travee;
	}
	public void setTravee(int travee) {
		this.travee = travee;
	}
	
	public boolean equals(Position position){
		if(this.rangee == position.rangee && this.travee == position.travee){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	public String toString(){
		return "("+rangee+","+travee+")" ;
	}
	
	public static Position voisin(Position origine,int orientation){
		Position position = new Position() ;
		switch(orientation){
			case Orientation.NORD :
				position.setRangee(origine.getRangee()-1) ;
				position.setTravee(origine.getTravee()) ;
				break ;
			case Orientation.NORDEST :
				position.setRangee(origine.getRangee()-1) ;
				position.setTravee(origine.getTravee()+1) ;
				break ;
			case Orientation.EST :
				position.setRangee(origine.getRangee()) ;
				position.setTravee(origine.getTravee()+1) ;
				break ;
			case Orientation.SUDEST :
				position.setRangee(origine.getRangee()+1) ;
				position.setTravee(origine.getTravee()+1) ;
				break ;
			case Orientation.SUD :
				position.setRangee(origine.getRangee()+1) ;
				position.setTravee(origine.getTravee()) ;
				break ; 
			case Orientation.SUDOUEST :
				position.setRangee(origine.getRangee()+1) ;
				position.setTravee(origine.getTravee()-1) ;
				break ;
			case Orientation.OUEST :
				position.setRangee(origine.getRangee()) ;
				position.setTravee(origine.getTravee()-1) ;
				break ;
			case Orientation.NORDOUEST :
				position.setRangee(origine.getRangee()-1) ;
				position.setTravee(origine.getTravee()-1) ;
				break ;
		}
		return position ;
	}
	
	public static boolean estPositionValide(Position position){
		if(position.travee >= 0 && position.travee < Parametres.NB_TRAVEES && position.rangee >= 0 && position.rangee < Parametres.NB_RANGEES){
			return true ;
		}
		else {
			return false ;
		}
	}
}
