package fr.mertzel.ose.vue;
public class Orientation {
	public final static int NORD = 0 ;
	public final static int NORDEST = 1 ;
	public final static int EST = 2 ;
	public final static int SUDEST = 3 ;
	public final static int SUD = 4 ;
	public final static int SUDOUEST = 5 ;
	public final static int OUEST = 6 ;
	public final static int NORDOUEST = 7 ;
	
	private final static boolean visibilite[][][] = {
			{
				{true,false,false,false},
				{true,true,false,false},
				{true,true,false,false},
				{false,false,false,false},
				{false,false,false,false},
				{false,false,false,false},
				{true,false,false,true},
				{true,false,false,true}
			} ,
			{
				{true,true,false,false},
				{true,true,false,false},
				{false,true,false,false},
				{false,true,true,false},
				{false,true,true,false},
				{false,false,false,false},
				{false,false,false,false},
				{false,false,false,false}
			} ,
			{
				{false,false,false,false},
				{false,false,false,false},
				{false,true,true,false},
				{false,true,true,false},
				{false,false,true,false},
				{false,false,true,true},
				{false,false,true,true},
				{false,false,false,false}
			} ,
			{
				{true,false,false,true},
				{false,false,false,false},
				{false,false,false,false},
				{false,false,false,false},
				{false,false,true,true},
				{false,false,true,true},
				{false,false,false,true},
				{true,false,false,true}
			}
		} ;
		
	public static boolean estVisible(int orientationSource, int positionCible, int orientationCible){
		int x = orientationSource / 2 ;
		int y = positionCible ; 
		int z = orientationCible / 2 ;
		return visibilite[x][y][z] ;
	}
}
