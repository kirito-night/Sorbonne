1) 
une voiture herite de Vehicule 
voiture composee de roue

2) 
- velo , voiture , camion heritent de vehiculeRoulant
-VehiculeRolant est compose de Roue
-char herite de VehiculeChenille 
-VehiculeRoulant , Vehicule Chenille heritent de VehiculeChenille

3)
-cartable <>---- fourniture
-trousse <>---- stylo
-trousse herite du fourniture
-stylo herite de fourniture 

4)
-foret <>---- Animal
-carnivore herite de Animal
-herbivore herite de Animal
-renard herite de Carnivore 
-lievre herite de Herbivore


exercice 43 

43.1
public void afficherInfo(){
	System.out.println("Nom :"+nom)   // OK
	System.out.println("NumTel :"+numTel)   // OK
	System.out.println("NbEnfants :"+nbEnfant) // NON , nvEnfant est private
	System.out.println("Cursus :"+cursus )  //  OK
	
public voidmodifierInfo (){
	nom="toto"; // NON, nom est final
	numTel="0102030405"; //OK
	nbEnfants=−1; // NON , nvEnfant est private
	cursus="L0"; // OK


/*quand private les class fille n'ont pas acces a cettre variable */


43.2
public class Salarie extends Personne{ 
	private double salaire;
	public Salarie (String nom, double s) { 
		super(nom);
		salaire = s;
	}
	public double getSalaire(){
		return salaire;
	}
43.3

la method prime() doit eter ajoutee dans la class Salaire, car la classe Personne n'a pas acces a l'attribut salaire 
parcontre le nbEnfant on peut avoir acces grace a l'accesseur

public double prime(){
	return 5 * salaire* super.getNbEnfants() / 100.0
	
}
Personne p = newe Salarie("toto" , 5);	

43.4

public void modifierNumTel(String numTel){
	super.numtel = numTel;
	System.out.println(" Le salarie : "+nom+" a pour numero : "+ numTel)
}
 
43.5
p.prime() // ERROR, car prime ne se trouve pas dans la classe Personne mais Salarie

p.estenL2() // ERROR, estenL2 n'est pas dans la class personne

e.prime()// ERROR, car la methode prime() n'est pas dans la class etudiant
Salarie s1 = new Salarie("amelle");// NON, pas de constructeur  a un seul parametre 
S a l a r i e  s2 =newS a l a r i e ("Pauline","0122334455") ;	// NON , le second parametre du constructeur de salarie est un double

S a l a r i e  s3 =newS a l a r i e ("Yves","0123401234",2000) ; // nom, salarie n'a pas de constructeur a 3 parametre 




exercice 44
44.1

plante <-Arbre <- Chene 
		<- Fleur <- Marguereite
					Rose
					
ligne 15 : je suis une Plante
ligne 16 : je suis une Plante 
ligne 17 : je suis une Fleur 
ligne 18 : je suis une Marguerite 
ligne 19 :  je suis une Plante 
ligne 20 : je suis une Fleur



44.2 
par heritage ou par redefinition , chaque classes de cet exercice a la methode toString() 
Et pour un objet donné,  la methode toString() qui est excutee est celle qui est definie(ou redefinit ) dans la classe la plus proche lorsque on remonte la hierarchie des classe

44.3 

Plante  p2 =newArbre () ;  System . out . println ( p2 ) ;// je suis une plante 
Plante  p3 =newFleur () ;  System . out . println ( p3 ) ; // je suis une Fleur
Plante  p4 =newMarguerite () ;  System . out . println ( p4 ) ;// je suis une Marguerite
Plante  p5 =newRose () ;  System . out . println ( p5 ) ;//je suis une Fleur
Plante  p6 =newChene () ;  System . out . println ( p6 ) ; // je suis une Plante
