package fr.mertzel.ose.modele;
import java.util.ArrayList ;


import fr.mertzel.ose.vue.*;
public class PlanSalle {
	private String nom ;
	private ArrayList<Poste> lesPostes = new ArrayList<Poste>() ;

	public PlanSalle(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void ajouterPoste(Position position,int orientation){
		lesPostes.add(new Poste(position,orientation)) ;
		this.rechercherPostesVisibles() ;
	}
	
	public void retirerPoste(Position position){
		int indice = this.rechercherPoste(position) ;
		if(indice != -1){
			lesPostes.remove(indice) ;
			this.rechercherPostesVisibles() ;
		}
	}
	
	public void visualiserPostes(){
		int i = 0 ;
		for(Poste poste : lesPostes){
			System.out.println(i+" : "+poste) ;
			i++ ;
		}
	}
	
	public void orienterPoste(Position position,int orientation){
		int indice = this.rechercherPoste(position) ;
		if(indice != -1){
			lesPostes.get(indice).setOrientation(orientation) ;
			this.rechercherPostesVisibles() ;
			}
		}
	
	public int rechercherPoste(Position position){
		int i = 0 ;
		int indice = -1 ;
		while(i < lesPostes.size() && indice == -1){
			if(position.equals(lesPostes.get(i).getPosition())){
				indice = i ;
			}
			else {
				i++ ;
			}
		}
		return indice ;
	}
	
	public boolean positionOccupee(Position position){
		int indice = this.rechercherPoste(position) ;
		if(indice != -1){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	public ArrayList<Poste> listerPostes(){
		return this.lesPostes ;
	}
	
	private void rechercherPostesVisibles(){
		for(Poste poste : lesPostes){
			poste.initialiserPostesVisibles() ;
			for(int orientation = Orientation.NORD ; orientation <= Orientation.NORDOUEST ; orientation += 1){
				Position positionCible = Position.voisin(poste.getPosition(),orientation) ;
				if(Position.estPositionValide(positionCible)){
					int indicePosteVoisin = this.rechercherPoste(positionCible) ;
					if(indicePosteVoisin != -1){
						if(Orientation.estVisible(poste.getOrientation(),orientation,lesPostes.get(indicePosteVoisin).getOrientation())){
							poste.ajouterPosteVisible(lesPostes.get(indicePosteVoisin)) ;
						}
					}
				}
			}
		}
	}
	
	//-------------------------------------------------------------------------------------------
	
	public class Poste {
	
	private ArrayList<Poste> postesVisibles ;
	private Position position ;
	private int orientation ;
	
	public Poste(Position position, int orientation) {
		super() ;
		this.position = position ;
		this.orientation = orientation ;
		this.initialiserPostesVisibles() ;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	public String toString(){
		return position + " : " + orientation + " : " + this.peutVoir() ;
	}
	
	public int getTravee(){
		return this.position.getTravee() ;
	}
	
	public int getRangee(){
		return this.position.getRangee() ;
	}
	
	public boolean peutVoir(){
		if(this.postesVisibles.size() == 0){
			return false ;
		}
		else {
			return true ;
		}
	}
	
	public void initialiserPostesVisibles(){
		this.postesVisibles = new ArrayList<Poste>() ;
	}
	
	public void ajouterPosteVisible(Poste poste){
		this.postesVisibles.add(poste) ;
	}
	
	public ArrayList<Poste> getPostesVisibles(){
		return this.postesVisibles ;
	}
	
	public int nbPostesVisibles(){
		return this.postesVisibles.size() ;
	}
	}
}
