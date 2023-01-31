#!/usr/bin/python

# Copyright 2013, Gurobi Optimization, Inc.


from gurobipy import *
import numpy as np

nbvar=4
npers=2

# Matrice des contraintes
arcs = [['b','c','d'],
        ['e','c','d'],
        ['e','f'],
        ['c','f'],
        ['g'],['g']]
# matrice de cout pour S=1
cout1 = [[5,10,2],
        [4,4,1],
        [3,1],
        [1,3],
        [1],[1]]
#matrice de cout  pour S=2
cout2 = [[3,4,6],
        [6,2,3],
        [1,2],
        [4,5],
        [1],[1]]

m = Model("mogplex")  
  
# declaration variables de decision
a = [m.addVar(vtype=GRB.BINARY , lb=0, name="a%s"%x) for x in arcs[0]]
b = [m.addVar(vtype=GRB.BINARY , lb=0, name="b%s"%x) for x in arcs[1]]
c = [m.addVar(vtype=GRB.BINARY , lb=0, name="c%s"%x) for x in arcs[2]]
d = [m.addVar(vtype=GRB.BINARY , lb=0, name="d%s"%x) for x in arcs[3]]
e = [m.addVar(vtype=GRB.BINARY , lb=0, name="eg")]
f = [m.addVar(vtype=GRB.BINARY , lb=0, name="fg")]
mat = [a,b,c,d,e,f]

z = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb= 0 , name="z%d" % (i+1)) for i in range(npers)])
r = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb= -GRB.INFINITY, name="r%d" % (i+1)) for i in range(npers)])
b = np.array([[m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(npers)] for k in range(npers)])

w = np.array([1, 1])
k = np.arange(1,npers+1)

 
obj = sum([mat[i][j]*cout1[i][j] for i in range(len(arcs)) for j in range(len(arcs[i]))])
        
# definition de l'objectif
obj = sum(w*(k*r - b.sum(0)))
        
# definition de l'objectif
m.setObjective(obj,GRB.MAXIMIZE)

# Definition des contraintes
m.addConstr(quicksum(a[i] for i in range(len(a))) == 1, "Contrainte%d")
L = ['a', 'b','c','d','e','f', 'g']

for indice in range(1,6):
    s=L[indice]
    m.addConstr(quicksum(mat[indice][j] for j in range(len(mat[indice]))) - quicksum(mat[i][j] for i in range(len(arcs)) for j in range(len(arcs[i])) if arcs[i][j]==s)  == 0)


m.addConstr(quicksum(mat[i][j]*cout1[i][j] for i in range(len(arcs)) for j in range(len(arcs[i]))) - z[0] == 0)
m.addConstr(quicksum(mat[i][j]*cout2[i][j] for i in range(len(arcs)) for j in range(len(arcs[i]))) - z[1] == 0)

for k in range(npers):
    for i in range(npers):
        m.addConstr(r[k] - b[i,k] + z[i] <= 0, "Contrainte%d" % i)

m.optimize()


print("")                
print('Solution optimale:')
print('Valeur de la fonction objectif :', m.objVal)
for i in range(len(mat)):
    for j in range(len(mat[i])):
        if mat[i][j].x == 1:
            print(mat[i][j].VarName,mat[i][j].x)

print("Valeur des critères")
for i in range(len(z)):
    print("z = ", z[i].x)