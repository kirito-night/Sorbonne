public class Point{
	private int posx;
	private int posy;
	
	public Point(){};
	public Point(int x, int y){
		posx = x;
		posy =y;
	}
	public void setPosx(int x){
		posx = x;
	}
	public void setPosy(int y){
		posy =y;
	}
	public int getPosx(){
		return posx;
	}
	
	public int getPosy(){
		return posy;
	}
	
	public String toString(){
		return "( "+x", "+y+ " )";
	}
	
	public int distance(Point p){
		return Math.sqrt((p.x - this.posx)^2 + (p.y - this.y));
	}
	
		
	
