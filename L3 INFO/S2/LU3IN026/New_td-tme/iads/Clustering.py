# -*- coding: utf-8 -*-

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

def dist_vect(v1, v2):
    return dist_euclidienne(v1, v2)

def inertie_cluster(Ens):
    cent = centroide(Ens)
    res = 0
    for i in range(len(Ens)): 
        res += dist_vect(Ens.iloc[i] , cent)**2
    return res

import random
def init_kmeans(K,Ens):
#     res  = []
#     index = list(range(len(Ens)))
#     for i in  range( K) : 
#         ch = np.random.choice(index)
        
#         res.append(Ens.iloc[i])
    L = [i for i in range(len(Ens))]
    index = random.sample(L , K)
    res = Ens.iloc[index]
    return np.array(res)

def plus_proche(Exe,Centres):
    res = 0
    for i in range(1, len(Centres)): 
        if dist_vect(Exe , Centres[i]) < dist_vect(Exe , Centres[res]) : 
            res = i
    return res

def affecte_cluster(Base,Centres):
    res = dict() 
    
    for i in range(len(Base)) : 
        p = plus_proche(Base.iloc[i] , Centres)
        if p  not in res: 
            res[p] = [i]
        else :
            res[p].append(i)
    return res

def nouveaux_centroides(Base,U):
    res = []
    for k,v in U.items() : 
        b = np.array(Base.iloc[v])
        res.append(np.mean(b,axis =0))
        
    return np.asarray(res)

def inertie_globale(Base, U):
    res = 0
    for k,v in U.items() : 
        l = Base.iloc[v] 
        res += inertie_cluster(l)
    return res

def kmoyennes(K, Base, epsilon, iter_max):
    cluster = init_kmeans(K , Base)
    old = 100
    for i in range( iter_max) :
       
        #si l'inertie global < epsilon  on break 
        U = affecte_cluster(Base , cluster)
        nouv = nouveaux_centroides(Base ,U) 
        cluster = nouv
        new =  inertie_globale(Base, U)
        diff = old - new 
        #print(f"iteration {i} Inertie : {new} Difference: {diff}")
        if diff < epsilon : 
            break 
        old = new
    return np.array(cluster), U

def affiche_resultat(Base,Centres,Affect):
    colors  = ['g', 'b', 'y','c', 'm']
    for k ,v in Affect.items() : 
        b  = Base.iloc[v]
        plt.scatter(Centres[:,0],Centres[:,1],color='r',marker='x')
        plt.scatter(b['X'],b['Y'],color= colors[k])

def indexDunn(Base, Centres, Affect): 
    #trouver inter_dist min 
    inter_dist = 10000
    for i in range(len(Centres)) :
        for j in range(len(Centres)) : 
            if i == j :
                continue 
            new_dist =  dist_centroides(Centres[i] , Centres[j])
            if new_dist < inter_dist : 
                inter_dist = new_dist
    l_intra_dist = []
    for k, v in Affect.items():
        current = np.array(Base.iloc[v])
        
        max_intra = dist_vect(current[0] , current[1])
        
        for i in range(len(current)):
            for j in range(len(current)):
                if i == j : 
                    continue 
                new_intra = dist_vect(current[i] , current[j])
                if new_intra >  max_intra:
                    max_intra =new_intra
        l_intra_dist.append(max_intra)
    
    L = np.array(l_intra_dist)
    i = np.argmax(L)
    max_intra = np.array(l_intra_dist)[i]
    return inter_dist / max_intra
