
public class TestAnimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Animal p1 = new Poule(1, 2);
	Animal p2 = new Poule(4, 8);
	Animal p3 = new Poule(9, 6);
	p1.afficher();
	p2.afficher();
	p3.afficher();
	p1.move(2, 5);
	p1.afficher();
	System.out.println(p1.distance(p2));
	
	
	Animal r1 = new Renard(1, 4);
	Animal r2 = new Renard(5, 1);
	Animal r3 = new Renard(7, 4);
	r1.afficher();
	r2.afficher();
	r3.afficher();
	r1.move(2, 5);
	r1.afficher();
	System.out.println(r1.distance(r2));
	
	
	Animal v1 = new Vipere(8, 5);
	Animal v2 = new Vipere(6, 8);
	Animal v3 = new Vipere(1, 6);
	v1.afficher();
	v2.afficher();
	v3.afficher();
	v1.move(2, 5);
	v1.afficher();
	System.out.println(v1.distance(v2));
	
	

	}

}
