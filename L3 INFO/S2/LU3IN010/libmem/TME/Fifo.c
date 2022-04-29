#include "Fifo.h"

#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int initFifo(Swapper*);
unsigned int fifoChoose(Swapper*);
void fifoReference(Swapper*,unsigned int frame);
void finalizeFifo(Swapper*);

int initFifoSwapper(Swapper*swap,unsigned int frames){
	return	initSwapper(swap,frames,initFifo,NULL,fifoChoose,finalizeFifo);
}

int initFifo(Swapper*swap){
	swap->private_data = calloc(swap->frame_nb,sizeof(unsigned int));
	int *fifo = swap->private_data;
	for(int i=0;i<swap->frame_nb;i++){
		fifo[i] = 0;
	}
	return 0;
}

unsigned int fifoChoose(Swapper*swap){
	int *fifo = swap->private_data;
	unsigned int frame = 0;
	for(int i=0;i<swap->frame_nb;i++){
		if(fifo[i]==0){
			frame = i;
			break;
		}
	}
	fifo[frame] = (fifo[frame]+1)%swap->frame_nb;
	return frame;
}

void finalizeFifo(Swapper*swap){
	free(swap->private_data);
}
