EX = ChainesMain ReconstitueReseau
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
	gcc SVGwriter.o Reseau.o Chaine.o ReconstitueReseau.o -o ReconstitueReseau $(CFLAGS) -lm

Reseau.o : Reseau.c SVGwriter.o 
	gcc -c Reseau.c $(CFLAGS)
clean : 
	rm  -f *.o $(EX)




