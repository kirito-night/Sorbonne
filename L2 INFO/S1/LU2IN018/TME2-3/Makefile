CFLAGS = -g -Wall
CC = gcc

PROGRAMS = test ecosystem

.PHONY:	all clean

all: $(PROGRAMS)

test: ecosystem.o test.o
	$(CC) -o $@ $(CFLAGS) $^

ecosystem: ecosystem.o main.o
	$(CC) -o $@ $(CFLAGS) $^

%.o: %.c %.h
	$(CC) $(CFLAGS) -c $<

clean:
	rm -f *.o $(PROGRAMS) *~
