# -*- coding: utf-8 -*-

"""
Package: iads
File: utils.py
Année: LU3IN026 - semestre 2 - 2021-2022, Sorbonne Université
"""


# Fonctions utiles pour les TDTME de LU3IN026
# Version de départ : Février 2022

# import externe
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt



# ------------------------ 
def plot2DSet(desc,label):
    """ ndarray * ndarray -> affichage
        la fonction doit utiliser la couleur 'red' pour la classe -1 et 'blue' pour la +1
    """
   #TODO: A Compléter    
    # Extraction des exemples de classe -1:
    negatifs = desc[label == -1]
    # Extraction des exemples de classe +1:
    positifs = desc[label == +1]
    # Affichage de l'ensemble des exemples :
    plt.scatter(negatifs[:, 0], negatifs[:, 1], marker='o', color="red")  # 'o' rouge pour la classe -1
    plt.scatter(positifs[:, 0], positifs[:, 1], marker='x', color="blue")  # 'x' bleu pour la classe +1


# ------------------------ 
def plot_frontiere(desc_set, label_set, classifier, step=30):
    """ desc_set * label_set * Classifier * int -> NoneType
        Remarque: le 4e argument est optionnel et donne la "résolution" du tracé: plus il est important
        et plus le tracé de la frontière sera précis.        
        Cette fonction affiche la frontière de décision associée au classifieur
    """
    mmax=desc_set.max(0)
    mmin=desc_set.min(0)
    x1grid,x2grid=np.meshgrid(np.linspace(mmin[0],mmax[0],step),np.linspace(mmin[1],mmax[1],step))
    grid=np.hstack((x1grid.reshape(x1grid.size,1),x2grid.reshape(x2grid.size,1)))
    
    # calcul de la prediction pour chaque point de la grille
    res=np.array([classifier.predict(grid[i,:]) for i in range(len(grid)) ])
    res=res.reshape(x1grid.shape)
    # tracer des frontieres
    # colors[0] est la couleur des -1 et colors[1] est la couleur des +1
    plt.contourf(x1grid,x2grid,res,colors=["darksalmon","skyblue"],levels=[-1000,0,1000])
	
# ------------------------
# genere_dataset_uniform:
def genere_dataset_uniform(p, n, min, max):
    data1_desc = np.random.uniform(min,max,(n*2,p))
    data1_label = np.asarray([-1 for i in range(0,n)] + [+1 for i in range(0,n)])
    #np.random.shuffle(data1_label)
    return (data1_desc, data1_label)

	
# ------------------------
def genere_dataset_gaussian(positive_center, positive_sigma, negative_center, negative_sigma, nb_points):
    negative = np.random.multivariate_normal(negative_center, negative_sigma, nb_points)
    positive = np.random.multivariate_normal(positive_center, positive_sigma, nb_points)
    desc = np.vstack((negative, positive))
    label = np.asarray([-1 for i in range(0,nb_points)] + [+1 for i in range(0,nb_points)])
    return (desc, label)



# ------------------------

def create_XOR(n, sigma):
    desc1, label1 = genere_dataset_gaussian(np.array([0, 1]), np.array([[sigma, 0], [0, sigma]]),
                                            np.array([1, 1]), np.array([[sigma, 0], [0, sigma]]), n)
    desc2, label2 = genere_dataset_gaussian(np.array([1, 0]), np.array([[sigma, 0], [0, sigma]]),
                                            np.array([0, 0]), np.array([[sigma, 0], [0, sigma]]), n)

    desc = np.vstack((desc1, desc2))
    label = np.hstack((label1, label2))
    return (desc, label)


# ------------------------ 

def crossval_strat(X, Y, n_iterations, iteration):
    #############
    # A COMPLETER
    ############# 
    Xapp , Yapp ,Xtest, Ytest = crossval(X,Y,n_iterations, iteration)
    
    xap = Xapp[Yapp==1]
    xan = Xapp[Yapp == -1]
    yap = Yapp[Yapp == 1]
    yan  = Yapp[Yapp== -1]
    
    Xapp = np.concatenate((xap,xan))
    Yapp = np.concatenate((yap, yan))
    
    
    xtp = Xtest[Ytest==1]
    xtn = Xtest[Ytest == -1]
    ytp = Ytest[Ytest == 1]
    ytn  = Ytest[Ytest== -1]
    
    
    Xtest = np.concatenate((xtp,xtn))
    Ytest = np.concatenate((ytp, ytn))
            
    return Xapp, Yapp, Xtest, Ytest

# ------------------------
def crossval(X, Y, n_iterations, iteration):
    #############
    # A COMPLETER
    #############
    x1 = np.split(X , n_iterations)
    y1 = np.split(Y,n_iterations)
    
    Xtest = x1[iteration]
    
    Ytest = y1[iteration]
    del x1[iteration]
    del y1[iteration]
    
    Xapp = np.vstack(np.asarray(x1))
    Yapp = np.hstack(np.asarray(y1))
    return Xapp, Yapp, Xtest, Ytest



def plot_frontiere_V3(desc_set, label_set, w, kernel, step=30, forme=1, fname="out/tmp.pdf"):
    """ desc_set * label_set * array * function * int * int * str -> NoneType
        Note: le classifieur linéaire est donné sous la forme d'un vecteur de poids pour plus de flexibilité
    """
    # -----------
    # ETAPE 1: construction d'une grille de points sur tout l'espace défini par les points du jeu de données
    mmax=desc_set.max(0)
    mmin=desc_set.min(0)
    x1grid,x2grid=np.meshgrid(np.linspace(mmin[0],mmax[0],step),np.linspace(mmin[1],mmax[1],step))
    grid=np.hstack((x1grid.reshape(x1grid.size,1),x2grid.reshape(x2grid.size,1)))
    
    # -----------
    # Si vous avez du mal à saisir le concept de la grille, décommentez ci-dessous
    #plt.figure()
    #plt.scatter(grid[:,0],grid[:,1])
    #if True:
    #    return
    
    # -----------
    # ETAPE 2: calcul de la prediction pour chaque point de la grille
    res=np.array([kernel(grid[i,:])@w for i in range(len(grid)) ])
    # pour les affichages avancés, chaque dimension est présentée sous la forme d'une matrice
    res=res.reshape(x1grid.shape) 
    
    # -----------
    # ETAPE 3: le tracé
    #
    # CHOIX A TESTER en décommentant:
    # 1. lignes de contours + niveaux
    if forme <= 2 :
        fig, ax = plt.subplots() # pour 1 et 2
        ax.set_xlabel('X_1')
        ax.set_ylabel('X_2')
    if forme == 1:
        CS = ax.contour(x1grid,x2grid,res)
        ax.clabel(CS, inline=1, fontsize=10)
    #
    # 2. lignes de contour 0 = frontière 
    if forme == 2:
        CS = ax.contour(x1grid,x2grid,res, levels=[0], colors='k')
    #
    # 3. fonction de décision 3D
    if forme == 3 or forme == 4:
        fig = plt.gcf()
        ax = fig.gca(projection='3d') # pour 3 et 4
        ax.set_xlabel('X_1')
        ax.set_ylabel('X_2')
        ax.set_zlabel('f(X)')
    # 
    if forme == 3:
        surf = ax.plot_surface(x1grid,x2grid,res, cmap=cm.coolwarm)
    #
    # 4. fonction de décision 3D contour grid + transparence
    if forme == 4:
        norm = plt.Normalize(res.min(), res.max())
        colors = cm.coolwarm(norm(res))
        rcount, ccount, _ = colors.shape
        surf = ax.plot_surface(x1grid,x2grid,res, rcount=rcount, ccount=ccount, facecolors=colors, shade=False)
        surf.set_facecolor((0,0,0,0))
    
    # -----------
    # ETAPE 4: ajout des points
    negatifs = desc_set[label_set == -1]     # Ensemble des exemples de classe -1
    positifs = desc_set[label_set == +1]     # +1 
    # Affichage de l'ensemble des exemples en 2D:
    if forme <= 2:
        ax.scatter(negatifs[:,0],negatifs[:,1], marker='o', c='b') # 'o' pour la classe -1
        ax.scatter(positifs[:,0],positifs[:,1], marker='x', c='r') # 'x' pour la classe +1
    else:
        # on peut ajouter une 3ème dimension si on veut pour 3 et 4
        ax.scatter(negatifs[:,0],negatifs[:,1], -1, marker='o', c='b') # 'o' pour la classe -1
        ax.scatter(positifs[:,0],positifs[:,1], 1,  marker='x', c='r') # 'x' pour la classe +1
    
    # -----------
    # ETAPE 5 en 3D: régler le point de vue caméra:
    if forme == 3 or forme == 4:
        ax.view_init(20, 70) # a régler en fonction des données
    
    # -----------
    # ETAPE 6: sauvegarde (le nom du fichier a été fourni en argument)
    if fname != None:
        # avec les options pour réduires les marges et mettre le fond transprent
        plt.savefig(fname,bbox_inches='tight', transparent=True,pad_inches=0)


def calc(w, desc_set , label_set ): 
    sum  = 0
    for i in range(len(desc_set)):
        tmp = 1 - np.dot(desc_set[i], w) * label_set[i]
        sum += max(tmp, 0)
    return sum