# -*- coding: utf-8 -*-

"""
Package: iads
File: Classifiers.py
Année: LU3IN026 - semestre 2 - 2021-2022, Sorbonne Université
"""

# Classfieurs implémentés en LU3IN026
# Version de départ : Février 2022

# Import de packages externes
import numpy as np
import pandas as pd

# ---------------------------
# ------------------------ A COMPLETER :

# Recopier ici la classe Classifier (complète) du TME 2
class Classifier:
    """ Classe (abstraite) pour représenter un classifieur
        Attention: cette classe est ne doit pas être instanciée.
    """

    def __init__(self, input_dimension):
        """ Constructeur de Classifier
            Argument:
                - intput_dimension (int) : dimension de la description des exemples
            Hypothèse : input_dimension > 0
        """
        raise NotImplementedError("Please Implement this method")

    def train(self, desc_set, label_set):
        """ Permet d'entrainer le modele sur l'ensemble donné
            desc_set: ndarray avec des descriptions
            label_set: ndarray avec les labels correspondants
            Hypothèse: desc_set et label_set ont le même nombre de lignes
        """
        raise NotImplementedError("Please Implement this method")

    def score(self, x):
        """ rend le score de prédiction sur x (valeur réelle)
            x: une description
        """
        raise NotImplementedError("Please Implement this method")

    def predict(self, x):
        """ rend la prediction sur x (soit -1 ou soit +1)
            x: une description
        """
        raise NotImplementedError("Please Implement this method")

    def accuracy_v1(self, desc_set, label_set):
        """ Permet de calculer la qualité du système sur un dataset donné
            desc_set: ndarray avec des descriptions
            label_set: ndarray avec les labels correspondants
            Hypothèse: desc_set et label_set ont le même nombre de lignes
        """
        accurate = 0
        for i in range(len(desc_set)):
            if (self.predict(desc_set[i]) == label_set[i]):
                accurate += 1
        return accurate / len(desc_set)

    def accuracy(self, desc_set, label_set):
        
        yhat = np.array([self.predict(x) for x in desc_set])

        return np.where(label_set == yhat, 1., 0.).mean()


# ---------------------------
# ------------------------ A COMPLETER :

class ClassifierKNN(Classifier):
    """ Classe pour représenter un classifieur par K plus proches voisins.
        Cette classe hérite de la classe Classifier
    """

    # ATTENTION : il faut compléter cette classe avant de l'utiliser !

    def __init__(self, input_dimension, k):
        """ Constructeur de Classifier
            Argument:
                - intput_dimension (int) : dimension d'entrée des exemples
                - k (int) : nombre de voisins à considérer
            Hypothèse : input_dimension > 0
        """
        super(ClassifierKNN, self).__init__(input_dimension)
        self.k = k

    def score(self, x):
        """ rend la proportion de +1 parmi les k ppv de x (valeur réelle)
            x: une description : un ndarray
        """
        # get the different with all the points in dataset
        diff = np.tile(x, (len(self.label), 1)) - self.dataset
        # calculate the sum of the square (the square of the distance)
        square_diff = np.sum(diff ** 2, axis=1)
        # sort the distance square and get the index
        sorted_dist_index = np.argsort(square_diff)
        return np.sum(self.label[sorted_dist_index[0:self.k]])

    def predict(self, x):
        """ rend la prediction sur x (-1 ou +1)
            x: une description : un ndarray
        """
        return +1 if self.score(x) > 0 else -1

    def train(self, desc_set, label_set):
        """ Permet d'entrainer le modele sur l'ensemble donné
            desc_set: ndarray avec des descriptions
            label_set: ndarray avec les labels correspondants
            Hypothèse: desc_set et label_set ont le même nombre de lignes
        """
        self.dataset = desc_set
        self.label = label_set


# ---------------------------
# ------------------------ A COMPLETER :

class ClassifierLineaireRandom(Classifier):
    """ Classe pour représenter un classifieur linéaire aléatoire
        Cette classe hérite de la classe Classifier
    """

    def __init__(self, input_dimension):
        """ Constructeur de Classifier
            Argument:
                - intput_dimension (int) : dimension de la description des exemples
            Hypothèse : input_dimension > 0
        """
        super(ClassifierLineaireRandom, self).__init__(input_dimension)
        v = np.random.uniform(-1, 1, input_dimension)
        # normalize
        self.w = v / math.sqrt(np.sum(v ** 2))

    def train(self, desc_set, label_set):
        """ Permet d'entrainer le modele sur l'ensemble donné
            desc_set: ndarray avec des descriptions
            label_set: ndarray avec les labels correspondants
            Hypothèse: desc_set et label_set ont le même nombre de lignes
        """
        print("Pas d'apprentissage pour ce classifieur")

    def score(self, x):
        """ rend le score de prédiction sur x (valeur réelle)
            x: une description
        """
        return x @ self.w.T

    def predict(self, x):
        """ rend la prediction sur x (soit -1 ou soit +1)
            x: une description
        """
        return 1 if self.score(x) > 0 else -1



# ---------------------------
# ------------------------ A COMPLETER : DEFINITION DU CLASSIFIEUR PERCEPTRON

class ClassifierPerceptron(Classifier):
    """ Perceptron de Rosenblatt
    """

    def __init__(self, input_dimension, learning_rate, init=0):
        """ Constructeur de Classifier
            Argument:
                - input_dimension (int) : dimension de la description des exemples (>0)
                - learning_rate : epsilon
                - init est le mode d'initialisation de w:
                    - si 0 (par défaut): initialisation à 0 de w,
                    - si 1 : initialisation par tirage aléatoire de valeurs petites
        """
        self.input_dimension = input_dimension
        self.learning_rate = learning_rate
        if init == 0:
            self.w = np.zeros(input_dimension)
        else:
            self.w = np.random.uniform(0, 1, input_dimension)
            lst = []
            for i in self.w:
                lst.append((2 * i - 1) * 0.001)
            self.w = np.array(lst)

        self.count = 0

    def train_step(self, desc_set, label_set):
        """ Réalise une unique itération sur tous les exemples du dataset
            donné en prenant les exemples aléatoirement.
            Arguments:
                - desc_set: ndarray avec des descriptions
                - label_set: ndarray avec les labels correspondants
        """
        index = [i for i in range(len(desc_set))]
        np.random.shuffle(index)
        for i in index:
            y_cha = np.dot(desc_set[i], self.w)
            # print(y_cha , desc_set[i], self.w)
            y_eto = 1 if y_cha >= 0.0 else -1
            if y_eto != label_set[i]:  # cas mal classé
                self.w = self.w + self.learning_rate * label_set[i] * desc_set[i]

    def train(self, desc_set, label_set, niter_max=100, seuil=0.001):
        """ Apprentissage itératif du perceptron sur le dataset donné.
            Arguments:
                - desc_set: ndarray avec des descriptions
                - label_set: ndarray avec les labels correspondants
                - niter_max (par défaut: 100) : nombre d'itérations maximale
                - seuil (par défaut: 0.001) : seuil de convergence
            Retour: la fonction rend une liste
                - liste des valeurs de norme de différences
        """
        l_res = []
        for i in range(0, niter_max):
            old = self.w.copy()
            self.train_step(desc_set, label_set)
            new = self.w
            self.count = i + 1

            temp = np.linalg.norm(np.absolute(new - old))
            l_res.append(temp)
            if temp <= seuil:
                break;
        return l_res

    def score(self, x):
        """ rend le score de prédiction sur x (valeur réelle)
            x: une description
        """

        return np.dot(x, self.w)

    def predict(self, x):
        """ rend la prediction sur x (soit -1 ou soit +1)
            x: une description
        """
        return 1 if self.score(x) >= 0 else -1



# ---------------------------
# ------------------------ A COMPLETER :


class ClassifierPerceptronKernel(Classifier):
    """ Perceptron de Rosenblatt kernelisé
    """

    def __init__(self, input_dimension, learning_rate, noyau, init=0):
        """ Constructeur de Classifier
            Argument:
                - input_dimension (int) : dimension de la description des exemples (espace originel)
                - learning_rate : epsilon
                - noyau : Kernel à utiliser
                - init est le mode d'initialisation de w:
                    - si 0 (par défaut): initialisation à 0 de w,
                    - si 1 : initialisation par tirage aléatoire de valeurs petites
        """
        self.input_dimension = input_dimension
        self.learning_rate = learning_rate
        self.noyau = noyau
        if init == 0:
            self.w = np.zeros(self.noyau.get_output_dim())
        else:
            self.w = np.random.uniform(0, 1, self.noyau.get_output_dim())
            lst = []
            for i in self.w:
                lst.append((2 * i - 1) * 0.001)
            self.w = np.array(lst)

        self.count = 0

    def train_step(self, desc_set, label_set):
        """ Réalise une unique itération sur tous les exemples du dataset
            donné en prenant les exemples aléatoirement.
            Arguments: (dans l'espace originel)
                - desc_set: ndarray avec des descriptions
                - label_set: ndarray avec les labels correspondants
        """
        index = [i for i in range(len(desc_set))]
        np.random.shuffle(index)
        for i in index:
            y_cha = np.dot(desc_set[i], self.w)
            # print(y_cha , desc_set[i], self.w)
            y_eto = 1 if y_cha >= 0.0 else -1
            if y_eto != label_set[i]:  # cas mal classé
                self.w = self.w + self.learning_rate * label_set[i] * desc_set[i]

    def train(self, desc_set, label_set, niter_max=100, seuil=0.001):
        """ Apprentissage itératif du perceptron sur le dataset donné.
            Arguments: (dans l'espace originel)
                - desc_set: ndarray avec des descriptions
                - label_set: ndarray avec les labels correspondants
                - niter_max (par défaut: 100) : nombre d'itérations maximale
                - seuil (par défaut: 0.01) : seuil de convergence
            Retour: la fonction rend une liste
                - liste des valeurs de norme de différences
        """
        l_res = []
        for i in range(0, niter_max):
            old = self.w.copy()
            kernel_desc = self.noyau.transform(desc_set)
            kernel_label = label_set
            self.train_step(kernel_desc, kernel_label)
            new = self.w
            self.count = i + 1

            temp = np.linalg.norm(np.absolute(new - old))
            l_res.append(temp)
            if temp <= seuil:
                break;
        return l_res

    def score(self, x):
        """ rend le score de prédiction sur x
            x: une description (dans l'espace originel)
        """
        newx = self.noyau.transform(np.array([x]))
        return np.dot(newx, self.w)[0]

    def predict(self, x):
        """ rend la prediction sur x (soit -1 ou soit +1)
            x: une description (dans l'espace originel)
        """
        return 1 if self.score(x) >= 0 else -1


# ---------------------------

# ---------------------------
#perceptronBiais




class ClassifierPerceptronBiais(Classifier):
    
    def __init__(self, input_dimension, learning_rate, init=0):
        """ Constructeur de Classifier
            Argument:
                - input_dimension (int) : dimension de la description des exemples (>0)
                - learning_rate : epsilon
                - init est le mode d'initialisation de w:
                    - si 0 (par défaut): initialisation à 0 de w,
                    - si 1 : initialisation par tirage aléatoire de valeurs petites
        """
        self.input_dimension = input_dimension
        self.learning_rate = learning_rate
        if init == 0:
            self.w = np.zeros(input_dimension)
        else:
            self.w = np.random.uniform(0, 1, input_dimension)
            lst = []
            for i in self.w:
                lst.append((2 * i - 1) * 0.001)
            self.w = np.array(lst)

        self.count = 0
        self.allw = []
        #self.allw.append(self.w)    
    
    
    def train_step(self, desc_set, label_set):
        """ Réalise une unique itération sur tous les exemples du dataset
            donné en prenant les exemples aléatoirement.
            Arguments:
                - desc_set: ndarray avec des descriptions
                - label_set: ndarray avec les labels correspondants
        """
        index = [i for i in range(len(desc_set))]
        np.random.shuffle(index)
        for i in index:
            y_cha = np.dot(desc_set[i], self.w)
            # print(y_cha , desc_set[i], self.w)
            y_eto = 1 if y_cha >= 0.0 else -1
            if  y_cha * label_set[i] < 1:  # cas mal classé
                self.w = self.w + (self.learning_rate *(label_set[i] - y_cha) * desc_set[i])
                
        
                
    
    def train(self, desc_set, label_set, niter_max=2000, seuil=0.001):
        """ Apprentissage itératif du perceptron sur le dataset donné.
            Arguments:
                - desc_set: ndarray avec des descriptions
                - label_set: ndarray avec les labels correspondants
                - niter_max (par défaut: 100) : nombre d'itérations maximale
                - seuil (par défaut: 0.001) : seuil de convergence
            Retour: la fonction rend une liste
                - liste des valeurs de norme de différences
        """
        l_res = []
        for i in range(0, niter_max):
            old = self.w.copy()
            self.train_step(desc_set, label_set)
            new = self.w
            self.allw.append(new)
            self.count = i + 1
            
            temp = np.linalg.norm(np.absolute(new - old))
            l_res.append(temp)
            if temp <= seuil:
                break;
        return l_res
        
    def score(self, x):
        """ rend le score de prédiction sur x (valeur réelle)
            x: une description
        """
        return np.dot(x, self.w)
    
    def predict(self, x):
        """ rend la prediction sur x (soit -1 ou soit +1)
            x: une description
        """
        return 1 if self.score(x) >= 0 else -1
        
        
    def get_allw(self) : 
        return self.allw


####
import copy 
class ClassifierMultiOAA(Classifier) : 
    def  __init__(self , els) :
        self.els =els 
        self.classifier = []


    

    def train( self, desc_set , label_set, niter_max= 100  , seuil = 0.0001) : 
        self.ally = np.unique(label_set)
        
        for i in range(len(self.ally)) : 
            self.classifier.append(copy.deepcopy(self.els))
        for j in range(len(self.ally)):
            tmp = np.where(label_set == self.ally[j], 1 ,-1)
            self.classifier[j].train(desc_set, tmp)
    def score(self, x) : 
        return [cl.score(x) for cl in self.classifier]
    def predict(self , x) : 
        """ rend la prediction sur x (soit -1 ou soit +1)
            x: une description
        """
        return self.ally[np.argmax(self.score(x))]



class ClassifierADALINE(Classifier):
    """ Perceptron de ADALINE
    """
    def __init__(self, input_dimension, learning_rate, history=False, niter_max=1000):
        """ Constructeur de Classifier
            Argument:
                - input_dimension (int) : dimension de la description des exemples
                - learning_rate : epsilon
                - history : stockage des poids w en cours d'apprentissage
                - niter_max : borne sur les iterations
            Hypothèse : input_dimension > 0
        """
        self.w = np.random.uniform(0, 1, input_dimension)
        self.history = history
        self.learning_rate = learning_rate
        self.allw = [self.w]
        
    def calc_gradient(self,x, y): 
        xt  = x.T
        tmp = np.dot(x, self.w) -y
        return np.dot(xt , tmp)

    def train_step(self, desc_set, label_set ):
        """ Réalise une unique itération sur tous les exemples du dataset
            donné en prenant les exemples aléatoirement.
            Arguments: (dans l'espace originel)
                - desc_set: ndarray avec des descriptions
                - label_set: ndarray avec les labels correspondants
        """
        index = [i for i in range(len(desc_set))]
        np.random.shuffle(index)
        for i in index:

            y_cha = np.dot(desc_set[i], self.w)
            # print(y_cha , desc_set[i], self.w)
            y_eto = 1 if y_cha >= 0.0 else -1
            if y_eto != label_set[i]:  # cas mal classé
                grd = self.calc_gradient(desc_set[i], label_set[i])
                
                self.w = self.w -  self.learning_rate *grd
        
    def train(self, desc_set, label_set ,niter_max = 1000 ,seuil = 0.001):
        """ Permet d'entrainer le modele sur l'ensemble donné
            réalise une itération sur l'ensemble des données prises aléatoirement
            desc_set: ndarray avec des descriptions
            label_set: ndarray avec les labels correspondants
            Hypothèse: desc_set et label_set ont le même nombre de lignes
        """
        
        for i in range(0, niter_max):
            old = self.w.copy()
            self.train_step(desc_set, label_set)
            new = self.w
            self.allw.append(self.w)
            
            
            temp = np.linalg.norm(np.absolute(new - old))
            if temp <= seuil:
                break

        

    

    def score(self, x):
        """ rend le score de prédiction sur x (valeur réelle)
            x: une description
        """

        return np.dot(x, self.w)

    def predict(self, x):
        """ rend la prediction sur x (soit -1 ou soit +1)
            x: une description
        """
        return 1 if self.score(x) >= 0 else -1