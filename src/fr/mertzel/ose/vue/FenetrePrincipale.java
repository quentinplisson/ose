 package fr.mertzel.ose.vue;


import java.awt.* ;

import javax.swing.* ;

import fr.mertzel.ose.modele.*;

import fr.mertzel.ose.controleur.*;

import java.awt.event.* ;

public class FenetrePrincipale extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private PlanSalle modele ;
	private Plan lePlan ;
	
	private JMenuItem itemNouveau ;
	private JMenuItem itemOuvrir ;
	private JMenuItem itemEnregistrer ;
	private JMenuItem itemQuitter ;
	
	private JPopupMenu menuActions ;
	
	private JMenu menuPlacer ;
	private JMenuItem itemPlacerNord ;
	private JMenuItem itemPlacerEst ;
	private JMenuItem itemPlacerSud ;
	private JMenuItem itemPlacerOuest ;
	private JMenuItem itemRetirer ;

	private JMenu menuOrienter;
	private JMenuItem itemOrienterNord ;
	private JMenuItem itemOrienterEst ;
	private JMenuItem itemOrienterSud ;
	private JMenuItem itemOrienterOuest ;

	
	public FenetrePrincipale(PlanSalle modele){
		super() ;
		this.modele = modele ;
		this.setTitle(modele.getNom() + " - OSE") ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		this.creerBarreMenus() ;
		this.creerMenuContextuel() ;
		JLayeredPane lp = new JLayeredPane() ;
		lp.setPreferredSize(new Dimension(Parametres.NB_TRAVEES*Parametres.LARGEUR_TRAVEE,Parametres.NB_RANGEES*Parametres.HAUTEUR_RANGEE)) ;
		lePlan = new Plan(modele) ;
		lePlan.setBounds(0,0,Parametres.NB_TRAVEES*Parametres.LARGEUR_TRAVEE,Parametres.NB_RANGEES*Parametres.HAUTEUR_RANGEE) ;
		lp.add(lePlan,new Integer(0)) ;
		Container conteneur = this.getContentPane() ;
		conteneur.setLayout(new FlowLayout()) ;
	
		conteneur.add(lp) ;
		this.pack() ;
		this.setLocationRelativeTo(null) ;
		this.setVisible(true) ;
	}
	
	private void creerBarreMenus(){
		JMenuBar barreMenus = new JMenuBar() ;
		
		JMenu menuFichier = new JMenu("Fichier") ;
		itemNouveau = new JMenuItem("Nouveau") ;
		itemOuvrir = new JMenuItem("Ouvrir") ;
		itemEnregistrer = new JMenuItem("Enregistrer") ;
		itemQuitter = new JMenuItem("Quitter") ;
		menuFichier.add(itemNouveau) ;
		menuFichier.add(itemOuvrir) ;
		menuFichier.add(itemEnregistrer) ;
		menuFichier.addSeparator() ;
		menuFichier.add(itemQuitter) ;
		barreMenus.add(menuFichier) ;
		
		this.setJMenuBar(barreMenus) ;
		itemNouveau.setEnabled(false) ;
		itemOuvrir.setEnabled(false) ;
		itemEnregistrer.setEnabled(false) ;
	}
	
	private void creerMenuContextuel(){
		menuActions = new JPopupMenu() ;
		menuPlacer = new JMenu("Placer") ;
		menuActions.add(menuPlacer) ;
		itemRetirer = new JMenuItem("Retirer") ;
		menuActions.add(itemRetirer) ;
		itemPlacerNord = new JMenuItem("vers Nord") ;
		menuPlacer.add(itemPlacerNord) ;
		itemPlacerEst = new JMenuItem("vers Est") ;
		menuPlacer.add(itemPlacerEst) ;
		itemPlacerSud = new JMenuItem("vers Sud") ;
		menuPlacer.add(itemPlacerSud) ;
		itemPlacerOuest = new JMenuItem("vers Ouest") ;
		menuPlacer.add(itemPlacerOuest) ;
		
		menuOrienter = new JMenu("Orienter");
		menuActions.add(menuOrienter);
		itemOrienterNord = new JMenuItem("vers Nord") ;
		menuOrienter.add(itemOrienterNord) ;
		itemOrienterEst = new JMenuItem("vers Est") ;
		menuOrienter.add(itemOrienterEst) ;
		itemOrienterSud = new JMenuItem("vers Sud") ;
		menuOrienter.add(itemOrienterSud) ;
		itemOrienterOuest = new JMenuItem("vers Ouest") ;
		menuOrienter.add(itemOrienterOuest) ;
		
	}
	
	public JMenuItem getItemOuvrir(){
		return this.itemOuvrir ;
	}
	
	public JMenuItem getItemEnregistrer(){
		return this.itemEnregistrer ;
	}
	
	public JMenuItem getItemQuitter(){
		return this.itemQuitter ;
	}
	
	public JMenuItem getItemPlacerNord(){
		return this.itemPlacerNord ;
	}
	
	public JMenuItem getItemPlacerEst(){
		return this.itemPlacerEst ;
	}
	
	public JMenuItem getItemPlacerSud(){
		return this.itemPlacerSud ;
	}
	
	public JMenuItem getItemPlacerOuest(){
		return this.itemPlacerOuest ;
	}
	
	public JMenuItem getItemRetirer(){
		return this.itemRetirer ;
	}
	
	//-----------------------
	
	public JMenuItem getItemOrienterNord() {
		return itemOrienterNord;
	}


	public JMenuItem getItemOrienterEst() {
		return itemOrienterEst;
	}


	public JMenuItem getItemOrienterSud() {
		return itemOrienterSud;
	}

	public JMenuItem getItemOrienterOuest() {
		return itemOrienterOuest;
	}

	public Plan getPlanSalle(){
		return this.lePlan ;
	}
	
	public void visualiserPlan(){
		this.lePlan.repaint() ;
	}
	
	public void afficherMenuContextuel(int x,int y){
		Position position = new Position(y/Parametres.HAUTEUR_RANGEE,x/Parametres.LARGEUR_TRAVEE) ;
		if(this.modele.positionOccupee(position)){
			this.menuPlacer.setEnabled(false) ;
			this.itemRetirer.setEnabled(true) ;
			this.menuOrienter.setEnabled(true);

		}
		else {
			this.menuPlacer.setEnabled(true) ;
			this.itemRetirer.setEnabled(false) ;
			this.menuOrienter.setEnabled(false);

		}
		menuActions.show((Component)lePlan,x,y) ;
	}

	//--------------------------------------------------------------------------------------


	public class Plan extends JPanel {
		private static final long serialVersionUID = 1L;

		private PlanSalle modele ;



		public Plan(PlanSalle modele){
			super();
			this.modele = modele ;
			this.setBackground(Color.white) ;
		}

		public void paintComponent(Graphics g){
			super.paintComponent(g) ;
			Graphics2D g2d = (Graphics2D) g ;
			this.quadriller(g2d) ;
			this.placerPostes(g2d) ;
			this.tracerVisualisations(g2d) ;
		}

		private void quadriller(Graphics2D g){
			float dash[]={2f,0f,2f} ;
			BasicStroke pointilles = new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,1.0f,dash,2f) ;
			g.setStroke(pointilles) ;
			for(int i = 1 ; i <= Parametres.NB_TRAVEES-1 ; i++){
				g.drawLine(Parametres.LARGEUR_TRAVEE*i,0,Parametres.LARGEUR_TRAVEE*i,Parametres.NB_RANGEES*Parametres.HAUTEUR_RANGEE-1) ;
			}
			for(int i = 1 ; i <= Parametres.NB_RANGEES-1 ; i++){
				g.drawLine(0,Parametres.HAUTEUR_RANGEE*i,Parametres.NB_TRAVEES*Parametres.LARGEUR_TRAVEE-1,Parametres.HAUTEUR_RANGEE*i) ;
			}
		}

		private void placerPostes(Graphics2D g){
			g.setStroke(new BasicStroke()) ;
			for(PlanSalle.Poste poste : modele.listerPostes()){
				g.setColor(Color.gray) ;
				int xPoste = Parametres.posteX(poste.getPosition().getTravee(),poste.getOrientation()) ;
				int yPoste = Parametres.posteY(poste.getPosition().getRangee(),poste.getOrientation()) ;
				int xPersonne = Parametres.personneX(poste.getPosition().getTravee(),poste.getOrientation()) ;
				int yPersonne = Parametres.personneY(poste.getPosition().getRangee(),poste.getOrientation()) ;
				if(poste.getOrientation() == Orientation.NORD || poste.getOrientation() == Orientation.SUD){
					g.fill3DRect(xPoste,yPoste,Parametres.LONGUEUR_POSTE,Parametres.LARGEUR_POSTE,true) ;
				}
				else {
					g.fill3DRect(xPoste,yPoste,Parametres.LARGEUR_POSTE,Parametres.LONGUEUR_POSTE,true) ;
				}
				g.drawOval(xPersonne,yPersonne,Parametres.LARGEUR_PERSONNE,Parametres.LARGEUR_PERSONNE) ;
				if(poste.peutVoir()){
					g.setColor(Color.red) ;
				}
				else {
					g.setColor(Color.blue) ;
				}
				g.fillOval(xPersonne,yPersonne,20,20) ;
			}
		}

		private void tracerVisualisations(Graphics2D g){
			float dash[]={2f,0f,2f} ;
			BasicStroke pointilles = new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,1.0f,dash,2f) ;
			g.setStroke(pointilles) ;
			g.setColor(Color.red) ;
			for(PlanSalle.Poste poste : modele.listerPostes()){
				int centreX = Parametres.centrePersonneX(poste.getPosition().getTravee(),poste.getOrientation()) ;
				int centreY = Parametres.centrePersonneY(poste.getPosition().getRangee(),poste.getOrientation()) ;
				for(PlanSalle.Poste posteVisible : poste.getPostesVisibles()){
					int visibleX = Parametres.centrePositionX(posteVisible.getPosition().getTravee()) ;
					int visibleY = Parametres.centrePositionY(posteVisible.getPosition().getRangee()) ;
					g.drawLine(centreX,centreY,visibleX,visibleY) ;
				}
			}
		}



	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}