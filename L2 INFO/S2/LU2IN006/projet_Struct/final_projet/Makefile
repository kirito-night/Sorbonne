EX = ChainesMain ReconstitueReseau main_chiffre_compa main_graph_compa ReorganiseReseau
CFLAGS = -g -Wall -Wextra -pedantic
all : $(EX)

ChainesMain: ChainesMain.o Chaine.o SVGwriter.o
	gcc Chaine.o ChainesMain.o SVGwriter.o -o ChainesMain -Wall -g -lm

Chaine.o : Chaine.c Chaine.h SVGwriter.o
	gcc -c Chaine.c  -Wall -g -lm

ChainesMain.o : ChainesMain.c
	gcc -c ChainesMain.c -Wall -g -lm 

SVGwriter.o: SVGwriter.c
	gcc -c SVGwriter.c $(CFLAGS)

ReconstitueReseau.o : ReconstitueReseau.c SVGwriter.o
	gcc -c ReconstitueReseau.c $(CFLAGS)

ReconstitueReseau : ReconstitueReseau.o Reseau.o SVGwriter.o 
	gcc SVGwriter.o Reseau.o Chaine.o ReconstitueReseau.o  -o ReconstitueReseau $(CFLAGS) -lm

Reseau.o : Reseau.c SVGwriter.o 
	gcc -c Reseau.c $(CFLAGS)

comparaison.o : comparaison.c Chaine.o SVGwriter.o
	gcc -c comparaison.c $(CFLAGS)

main_chiffre_compa.o : main_chiffre_compa.c SVGwriter.o
	gcc -c main_chiffre_compa.c $(CFLAGS) -lm

main_graph_compa.o : main_graph_compa.c SVGwriter.o
	gcc -c main_graph_compa.c $(CFLAGS) -lm SVGwriter.o

main_chiffre_compa : comparaison.o Chaine.o Reseau.o main_chiffre_compa.o SVGwriter.o
	gcc main_chiffre_compa.o comparaison.o Chaine.o Reseau.o -o main_chiffre_compa SVGwriter.o $(CFLAGS) -lm

main_graph_compa : comparaison.o Chaine.o Reseau.o SVGwriter.o main_graph_compa.o
	gcc main_graph_compa.o comparaison.o Chaine.o Reseau.o SVGwriter.o -o main_graph_compa  $(CFLAGS) -lm

Struct_File.o : Struct_File.c
	gcc -c Struct_File.c $(CFLAGS)

Struct_Liste.o : Struct_Liste.c
	gcc -c Struct_Liste.c $(CFLAGS)

Graphe.o : Graphe.c Struct_File.o Struct_Liste.o
	gcc -c Graphe.c $(CFLAGS)



ReorganiseReseau.o : ReorganiseReseau.c 
	gcc -c ReorganiseReseau.c $(CFLAGS)

ReorganiseReseau : ReorganiseReseau.o   Reseau.o SVGwriter.o Graphe.o Struct_File.o Struct_Liste.o
	gcc SVGwriter.o Reseau.o Chaine.o ReorganiseReseau.o Graphe.o Struct_File.o Struct_Liste.o -o ReorganiseReseau $(CFLAGS) -lm

clean : 
	rm  -f *.o $(EX)




