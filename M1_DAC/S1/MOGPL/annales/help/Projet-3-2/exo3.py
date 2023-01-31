#!/usr/bin/python

# Copyright 2013, Gurobi Optimization, Inc.


from gurobipy import *
import numpy as np

nbcont=3 
nbvar=4
npers=2

# Range of plants and warehouses
lignes = range(nbcont)
colonnes = range(nbvar)

# Matrice des contraintes
a = ([19,6,17,2], [2,11,4,18])
couts= ([40,50,60,50])

# Coefficients de la fonction objectif
c = [2, 1]

m = Model("mogplex")     
        
# declaration variables de decision
x = np.array([m.addVar(vtype=GRB.BINARY , lb=0, name="x%d" % (i+1)) for i in range(nbvar)])
z = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="z%d" % (i+1)) for i in range(npers)])
r = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="r%d" % (i+1)) for i in range(npers)])
b = np.array([[m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(npers)] for k in range(npers)])
#w = np.array([1, 1])
#w = np.array([9, 1])
w = np.array([0, 1])
k = np.arange(1,npers+1)
# maj du modele pour integrer les nouvelles variables
m.update()

obj = LinExpr();
obj = sum(w*(k*r - b.sum(0)))
        
# definition de l'objectif
m.setObjective(obj,GRB.MAXIMIZE)

# Definition des contraintes
for i in range(npers):
    m.addConstr(z[i] - quicksum(a[i][j]*x[j] for j in range(nbvar)) == 0, "Contrainte%d" % i)

for k in range(npers):
    for i in range(npers):
        m.addConstr(r[k] - b[i,k] - z[i] <= 0, "Contrainte%d" % i)

m.addConstr(quicksum(couts[j]*x[j] for j in range(nbvar)) <= 100)

# Resolution
m.optimize()


print("")                
print('Solution optimale:')
for i in range(nbvar):
    print('x%d'%(i+1), '=', x[i].x)
print("")
print('Valeur de la fonction objectif :', m.objVal)
for i in range(len(b)):
    for j in range(len(b[i])):
        print("b is :",b[i,j].x)