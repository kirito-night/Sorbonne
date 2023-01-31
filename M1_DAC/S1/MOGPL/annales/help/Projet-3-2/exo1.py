#!/usr/bin/python

# Copyright 2013, Gurobi Optimization, Inc.


from gurobipy import *
import numpy as np

nbcont=4 
nbvar=2

# Range of plants and warehouses
lignes = range(nbcont)
colonnes = range(nbvar)

# Matrice des contraintes
a = ([5,6,4,8,1], [3,8,6,2,5])
a3 = [1,1,1,1]
# Second membre
b = [0, 0, 3]

# Coefficients de la fonction objectif
c = [2, 1]
m = Model("mogplex")     
        
# declaration variables de decision
x = np.array([m.addVar(vtype=GRB.BINARY , lb=0, name="x%d" % (i+1)) for i in range(5)])
z = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="z%d" % (i+1)) for i in range(2)])
r = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="r%d" % (i+1)) for i in range(2)])
b = np.array([[m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(2)] for k in range(2)])
w = np.array([1, 1])
k = np.arange(1,3)
# maj du modele pour integrer les nouvelles variables
m.update()

obj = LinExpr();
obj = sum(w*(k*r - b.sum(0)))
        
# definition de l'objectif
m.setObjective(obj,GRB.MAXIMIZE)

# Definition des contraintes
for i in range(2):
    m.addConstr(z[i] - quicksum(a[i][j]*x[j] for j in range(5)) == 0, "Contrainte%d" % i)

for k in range(2):
    for i in range(2):
        m.addConstr(r[k] - b[i,k] - z[i] <= 0, "Contrainte%d" % i)

m.addConstr(quicksum(x[j] for j in range(5)) == 3, "Contrainte%d" % i)

# Resolution
m.optimize()


print("")                
print('Solution optimale:')
for i in range(5):
    print('x%d'%(i+1), '=', x[i].x)
print("")
print('Valeur de la fonction objectif :', m.objVal)
for i in range(len(b)):
    for j in range(len(b[i])):
        print("b is :",b[i,j].x)