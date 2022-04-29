#include "LRU.h"

#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int initLRU(Swapper *);
void referenceLRU(Swapper *, unsigned int frame);
unsigned int chooseLRU(Swapper *);
void finalizeLRU(Swapper *);

typedef struct
{
	unsigned int clock;
	unsigned int *age;
} InfoLRU;

int initLRUSwapper(Swapper *swap, unsigned int frames)
{
	return initSwapper(swap, frames, initLRU, referenceLRU, chooseLRU, finalizeLRU);
}

int initLRU(Swapper *swap)
{
	swap->private_data = calloc(swap->frame_nb, sizeof(InfoLRU));
	InfoLRU *info = swap->private_data;
	info->clock = 0;
	info->age = calloc(swap->frame_nb, sizeof(unsigned int));
	for (int i = 0; i < swap->frame_nb; i++)
	{
		info->age[i] = 0;
	}
	return 0;
}

void referenceLRU(Swapper *swap, unsigned int frame)
{
	InfoLRU *info = swap->private_data;
	info->age[frame] = ++info->clock;
}

unsigned int chooseLRU(Swapper *swap)
{
	InfoLRU *info = swap->private_data;
	unsigned int frame = 0;
	unsigned int min = info->age[0];
	for (int i = 0; i < swap->frame_nb; i++)
	{
		if (swap->frame[i] == -1)
		{
			frame = i;
			break;
		}
		if (info->age[i] < min)
		{
			min = info->age[i];
			frame = i;
		}
	}
	return frame;
}

void finalizeLRU(Swapper *swap)
{
	InfoLRU *info = swap->private_data;
	free(info->age);
	free(info);
}
