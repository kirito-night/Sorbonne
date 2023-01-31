from gurobipy import *
import numpy as np
import random
import time
import matplotlib.pyplot as plt
# generation des vecteurs poids  w  a composant positive strictement decroissants
def generateW(n):
    W = list(np.arange(50))
    W = random.sample(W, n)
    W.sort(reverse=True)
    W.append(0)
    W = [W[i]-W[i+1] for i in range(len(W)-1)]
    return W
# generation de la matrice U a coefficients entiers positifs de taille (n,p) tirer aleatoirement
def generateU(n,p):
    return np.random.randint(1,20, size=(n,p))

np.random.seed(42)
random.seed(42)

LT = [] # liste des temps d'optimiation moyenne pour chaque valeur de n
# Coefficients de la fonction objectif
for nbpers in [5,10,15,20]:
    m = Model("mogplex")  
    nbobj = 5*nbpers
    # declaration des variables
    x = np.array( [[m.addVar(vtype=GRB.BINARY , lb=0, name="x%d" % (i+1)) for i in range(nbobj)] for j in range(nbpers)] )
    r_k = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="r%d" % (i+1)) for i in range(nbpers)])
    b = np.array([[m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(nbpers)] for k in range(nbpers)])
    k = np.arange(1,nbpers+1)
    
    m.update()
    # Definition des contraintes
    for j in range(nbobj): # Contrainte sur l'appartenance de bien
        m.addConstr(quicksum(x[i,j] for i in range(nbpers)) <= 1, "Cx")  
    L = [] #liste des temps d'optimisation pour 10 vecteur w
    for _ in range(10):
        C = []
        W = generateW(nbpers)
        print(W)
        U = generateU(nbpers, nbobj)
        for k in range(nbpers): # ajout des Contrainte
            for i in range(nbpers):
                C.append(m.addConstr(r_k[k] - b[i,k] - quicksum(U[i,j]*x[i,j] for j in range(nbobj)) <= 0, "Cr"))
        
        # fonction objectif
        obj = sum(W*(k*r_k - b.sum(axis =0)))
        m.setObjective(obj,GRB.MAXIMIZE)
        m.update()
        # Resolution
        t = time.time()
        m.optimize()
        L.append(time.time()-t)
        if m.status == GRB.Status.OPTIMAL:
            print('Ok')
        else:
            print('No solution')
        for c in C:
            m.remove(c)
        m.update()
    LT.append(sum(L)/10)
LT = np.array(LT)
plt.plot([5,10,15,20], LT, label="n")
plt.legend()
plt.show()