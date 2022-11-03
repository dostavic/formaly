CFLAGS=-std=c11 -Wall
LDLIBS=-lm
CC=gcc
OUTPUT=stm

all: $(OUTPUT)

stm.o: stm.c common.h
	$(CC) $(CFLAGS) -c stm.c -o stm.o $(LDLIBS)

clean:
	rm -rf $(OUTPUT) *.o