EX = main
all : $(EX)

main: main.o biblioLC.o entreeSortieLC.o
	gcc  main.o biblioLC.o entreeSortieLC.o -o main -Wall

main.o : main.c biblioLC.h entreeSortieLC.h
	gcc -c main.c -Wall
	
biblioLC.o: biblioLC.c biblioLC.h
	gcc -c biblioLC.c -Wall

entreeSortieLC.o : entreeSortieLC.c entreeSortieLC.h
	gcc -c entreeSortieLC.c


clean : 
	rm  -f *.o $(EX)
