public class Nom{
	private static char [] voyelles = {'a','e','u','i','y','o'};
	private static char [] consonnes = {'z','r','t','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
	
	
	public static int rendAlea(int inf, int sup){	
		return (int)(Math.random()*(sup +1 -inf) + inf ) ;
	}
	
	public static boolean estPair(int n){
		return n%2 ==0;
	}
	
	public static char rendVoyelle (){
		return voyelles[(int)(Math.random()*voyelles.length)];
	}
	
	
	public static char rendConsonnes(){
		return consonnes[ (int)(Math.random()*consonnes.length)];
	}
	public static String genereNom(){
		String s = "";
		
		for (int i = 0;i< rendAlea(3,6); i++)
		{	
			if(estPair(i)){
			
				s +=rendConsonnes();
			}
			else{
				s += rendVoyelle();
			}
		}
						
		
		return s;
	}
}
			
	
	
		
	
	
	
