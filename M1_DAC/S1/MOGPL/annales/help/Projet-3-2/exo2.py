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
tmp= np.array([325,225,210,115,75,50])

# Coefficients de la fonction objectif
c = [2, 1]

m = Model("mogplex")     
        
# declaration variables de decision
x = np.array( [[m.addVar(vtype=GRB.BINARY , lb=0, name="x%d" % (i+1)) for i in range(6)] for j in range(3)] )
z = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="z%d" % (i+1)) for i in range(3)])
r = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="r%d" % (i+1)) for i in range(3)])
b = np.array([[m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(3)] for k in range(3)])

w = np.array([7,2,1])
k = np.arange(1,4)
# maj du modele pour integrer les nouvelles variables
m.update()

obj = LinExpr();
obj = sum(w*(k*r - b.sum(0)))
        
# definition de l'objectif
m.setObjective(obj,GRB.MAXIMIZE)

# Definition des contraintes
for i in range(3):
    m.addConstr(z[i] - quicksum(tmp[j]*x[i,j] for j in range(6)) == 0, "Contrainte%d" % i)

for k in range(3):
    for i in range(3):
        m.addConstr(r[k] - b[i,k] - z[i] <= 0, "Contrainte%d" % i)
        
for j in range(6):
    m.addConstr(quicksum(x[i,j] for i in range(3)) <= 1, "Contrainte%d" % j)


# Resolution
m.optimize()


print("")                
print('Solution optimale:')
print("")
print('Valeur de la fonction objectif :', m.objVal)
for i in range(len(x)):
    L= [j for j in range(len(x[i])) if x[i,j].x==1]
    print("x is :", L , tmp[L], sum(tmp[L]))
    
for i in range(len(b)):
    for j in range(len(b[i])):
        print("b is :",b[i,j].x)