EX = tab matrice produit
all : $(EX)

produit: testProduitMatrice.o matrice.o
	gcc testProduitMatrice.o matrice.o -o produit -Wall

testProduitMatrice.o : testProduitMatrice.c matrice.h
	gcc -c testProduitMatrice.c
	
matrice: testMatrice.o matrice.o
	gcc testMatrice.o matrice.o -o matrice -Wall

matrice.o : matrice.c matrice.h
	gcc -c matrice.c

testMatrice.o : testMatrice.c matrice.h
	gcc -c testMatrice.c

tab : testtab.o tab1.o
	gcc testtab.o tab1.o -o tab -Wall
testtab.o : testtab.c tab1.h
	gcc -c testtab.c
tab1.o : tab1.c tab1.h
	gcc -c tab1.c
clean : 
	rm  -f *.o $(EX)
