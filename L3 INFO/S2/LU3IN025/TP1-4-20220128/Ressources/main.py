import ex1
import exemple # Pour pouvoir utiliser les methodes de exemple.py

import sys


def main(): 
    listeEtu = ex1.readEtuPref("PrefEtu.txt")

    print (listeEtu)
    listeSpe = ex1.readSpePref("PrefSpe.txt")
    print("###\n")
    print (listeSpe)

    ex1.coteEtu(listeEtu,listeSpe)
    print("\n")
    ex1.cote_parcours(listeSpe,listeEtu)



#print("bonjour")
#maListe=exemple.lectureFichier("test.txt") # Execution de la methode lectureFichier du fichier exemple.
#print(maListe)
#print(len(maListe)) #Longueur de la liste.
#exemple.createFichierLP(maListe[0][0],int(maListe[1][0])) #Methode int(): transforme la chaine de caracteres en entier


main()
