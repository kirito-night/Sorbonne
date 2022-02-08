all : tab
tab : tab.o maintab.o
	gcc maintab.o tab.o -o tab -Wall 

tab.o : tab.c
	gcc -c tab.c -Wall
maintab.o : maintab.c tab.h
	gcc -c maintab.c -Wall