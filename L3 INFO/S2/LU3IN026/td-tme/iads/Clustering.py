# -*- coding: utf-8 -*-

# Ben Kabongo B.
# Avril 2022

# Sorbonne Université
# LU3IN026 - Sciences des données
# Clustering


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import scipy.cluster.hierarchy



def normalisation(df):
    return (df-df.min())/(df.max()-df.min())


def dist_euclidienne(v1, v2):
    return np.linalg.norm(v1-v2)


def dist_manhattan(v1, v2):
    return np.abs(v1 - v2).sum()


def dist_vect(choix, v1, v2):
    if choix == "euclidienne":
        return dist_euclidienne(v1, v2)
    return dist_manhattan(v1, v2)


def centroide(df):
    return np.mean(df, axis=0)


def dist_centroides(v1, v2):
    return dist_euclidienne(centroide(v1), centroide(v2))

def initialise(df):
    return {i: [i] for i in range(len(df))}


def fusionne(df, partition, verbose=False):
    min = -1
    tmin = (0,0)
    partition2 = partition.copy()
    
    for i in partition:
        for j in partition:
            if i != j:
                dist = dist_centroides(df.iloc[partition[i]], df.iloc[partition[j]])
                if min == -1:
                    min = dist
                    tmin = (i,j)
                else:
                    if dist < min:
                        min = dist
                        tmin = (i,j)
    add = []
    index = 0
    for i in partition2[tmin[0]]:
        add.append(i)
    for i in partition2[tmin[1]]:
        add.append(i)
    for i in partition2:
        index=i
    partition2[i+1] = add
    partition2.pop(tmin[0], None)
    partition2.pop(tmin[1], None)
    if verbose:
        print("Distance mininimale trouvée entre", tmin, " =", min)
    return (partition2, tmin[0], tmin[1], min)


def clustering_hierarchique(df, verbose=False, dendrogramme=False):
    partition = initialise(df)
    res = []
    while(len(partition) > 1):
        p1, i, j, min = fusionne(df, partition, verbose)
        total = len(partition[i]) + len(partition[j])
        partition = p1
        step = [i, j, min, total]
        res.append(step)
    if dendrogramme:
        plt.figure(figsize=(30, 15))
        plt.title('Dendrogramme', fontsize=25)    
        plt.xlabel("Indice d'exemple", fontsize=25)
        plt.ylabel('Distance', fontsize=25)
        scipy.cluster.hierarchy.dendrogram(res, leaf_font_size=24.)
        plt.show()
    return res


def dist_linkage(linkage, choix, v1, v2):
    d = []
    v1B = np.array(v1)
    v2B = np.array(v2)
    for i in v1B:
        temp = []
        for j in v2B:
            temp.append(dist_vect(choix, i, j))
        d.append(temp)
    if linkage == "complete":
        return np.max(d)
    elif linkage == "simple":
        return np.min(d)
    elif linkage == "average":
        return np.mean(d)
    else:
        perror("Erreur paramètre linkage")


def fusionne_linkage(linkage, choix, df, partition, verbose=False):
    min = -1
    tmin = (0,0)
    partition2 = partition.copy()
    
    for i in partition:
        for j in partition:
            if i != j:
                dist = dist_linkage(linkage, choix, df.iloc[partition[i]], df.iloc[partition[j]])
                if min == -1:
                    min = dist
                    tmin = (i,j)
                else:
                    if dist < min:
                        min = dist
                        tmin = (i,j)
    add = []
    index = 0
    for i in partition2[tmin[0]]:
        add.append(i)
    for i in partition2[tmin[1]]:
        add.append(i)
    for i in partition2:
        index=i
    partition2[i+1] = add
    partition2.pop(tmin[0], None)
    partition2.pop(tmin[1], None)
    if verbose:
        print("Distance mininimale trouvée entre", tmin, " =", min)
    return (partition2, tmin[0], tmin[1], min)


def clustering_hierarchique_linkage(linkage, choix, df, verbose=False, dendrogramme=False):
    partition = initialise(df)
    res = []
    while(len(partition) > 1):
        p1, i, j, min = fusionne_linkage(linkage, choix, df, partition, verbose)
        total = len(partition[i]) + len(partition[j])
        partition = p1
        step = [i, j, min, total]
        res.append(step)
    if dendrogramme:
        plt.figure(figsize=(30, 15))
        plt.title('Dendrogramme', fontsize=25)    
        plt.xlabel("Indice d'exemple", fontsize=25)
        plt.ylabel('Distance', fontsize=25)
        scipy.cluster.hierarchy.dendrogram(res, leaf_font_size=24.)
        plt.show()
    return res