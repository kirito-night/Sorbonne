#!/usr/bin/python

# Copyright 2013, Gurobi Optimization, Inc.


from gurobipy import *
import numpy as np

nbcont=4 
nbvar=2

# Range of plants and warehouses
lignes = range(nbcont)
colonnes = range(nbvar)

# Coefficients de la fonction objectif
m = Model("mogplex")     
O = []
for k in np.arange(1,7):
    # declaration variables de decision
    z = [4, 7, 1, 3, 9, 2]
    r = m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="r%d" % (k+1))
    b = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(6)])
    # maj du modele pour integrer les nouvelles variables
    m.update()
    
    obj = LinExpr();
    obj = k*r - b.sum()
            
    # definition de l'objectif
    m.setObjective(obj,GRB.MAXIMIZE)
    
    for i in range(6):
        m.addConstr(r- b[i] - z[i] <= 0, "Contrainte%d" % i)
            
    # Resolution
    m.optimize()
    O.append(m.objVal)
    
print("Les composants du vecteur L",O)