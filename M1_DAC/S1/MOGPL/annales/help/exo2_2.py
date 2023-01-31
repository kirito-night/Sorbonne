#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Nov 23 11:29:35 2022

@author: 28601285
"""

#!/usr/bin/python

# Copyright 2013, Gurobi Optimization, Inc.


from gurobipy import *
import numpy as np
import random
import time

def generateW(n):
    U = list(np.arange(50))
    U = random.sample(U, n)
    U.sort(reverse=True)
    U.append(0)
    U = [U[i]-U[i+1] for i in range(len(U)-1)]
    return U

def generateU(n,p):
    return np.random.randint(1,20, size=(n,p))

np.random.seed(42)
random.seed(42)

M = []
# Coefficients de la fonction objectif
for nbpers in [20]:
    m = Model("mogplex")  
    nbprojet = 5*nbpers
    # declaration variables de decision
    x = np.array( [[m.addVar(vtype=GRB.BINARY , lb=0, name="x%d" % (i+1)) for i in range(nbprojet)] for j in range(nbpers)] )
    #z = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="z%d" % (i+1)) for i in range(nbpers)])
    r = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="r%d" % (i+1)) for i in range(nbpers)])
    b = np.array([[m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(nbpers)] for k in range(nbpers)])
    k = np.arange(1,nbpers+1)
    # maj du modele pour integrer les nouvelles variables
    m.update()
      
    # Definition des contraintes
    """
    for k in range(nbpers): # Contrainte (1)
        for i in range(nbpers):
            m.addConstr(r[k] - b[i,k] - z[i] <= 0, "Cr")
    """
    for j in range(nbprojet): # Contrainte sur l'appartenance de bien
        m.addConstr(quicksum(x[i,j] for i in range(nbpers)) <= 1, "Cx")
    
    L = []
    for w in range(10):
        C = []
        W = generateW(nbpers)
        print(W)
        U = generateU(nbpers, nbprojet)
        #for i in range(nbpers): # Definition de la satisfaction de la personne
        #   C.append(m.addConstr(z[i] - quicksum(U[i,j]*x[i,j] for j in range(nbprojet)) == 0, "Contrainte z%d" % i))
        for k in range(nbpers): # Contrainte (1)
            for i in range(nbpers):
                C.append(m.addConstr(r[k] - b[i,k] - quicksum(U[i,j]*x[i,j] for j in range(nbprojet)) <= 0, "Cr"))
        obj = LinExpr();
        obj = sum(W*(k*r - b.sum(0)))
        m.setObjective(obj,GRB.MAXIMIZE)
        # definition de l'objectif
        m.update()
        # Resolution
        m.optimize()
        L.append(m.objVal)
        for c in C:
            m.remove(c)
        m.update()
    M.append(L)

print(M)
