from gurobipy import *
import numpy as np
import random
import time
import matplotlib.pyplot as plt

nbpers = 2

arcs = [['b','c','d'],
        ['e','c','d'],
        ['e','f'],
        ['c','f'],
        ['g'],['g']]

def wi_alpha(i, alpha):
    return ((2-i+1)/2)**alpha - ((2-i)/2)**alpha

def generateW(alpha):
    U = [wi_alpha(1,alpha), wi_alpha(2,alpha)]
    U.append(0)
    U = [U[i]-U[i+1] for i in range(len(U)-1)]
    return U

def generateTij():
    return np.random.randint(1,20, size=(6,3))

# declaration variables de decision
m = Model("mogplex") 
a = [m.addVar(vtype=GRB.BINARY , lb=0, name="a%s"%x) for x in arcs[0]]
b = [m.addVar(vtype=GRB.BINARY , lb=0, name="b%s"%x) for x in arcs[1]]
c = [m.addVar(vtype=GRB.BINARY , lb=0, name="c%s"%x) for x in arcs[2]]
d = [m.addVar(vtype=GRB.BINARY , lb=0, name="d%s"%x) for x in arcs[3]]
e = [m.addVar(vtype=GRB.BINARY , lb=0, name="eg")]
f = [m.addVar(vtype=GRB.BINARY , lb=0, name="fg")]

z = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="z%d" % (i+1)) for i in range(nbpers)])
r = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=-GRB.INFINITY, name="r%d" % (i+1)) for i in range(nbpers)])
B = np.array([[m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(nbpers)] for k in range(nbpers)])
M = [a,b,c,d,e,f]
k = np.arange(1,nbpers+1)
m.update()

S = ['a', 'b','c','d','e','f', 'g']
for indice in range(1,len(S)-1):
    s=S[indice]
    for j in range(len(arcs[indice])):
        print(j)
        M[indice][j]
    m.addConstr(quicksum(M[indice][j] for j in range(len(arcs[indice]))) - quicksum(M[i][j] for i in range(len(arcs)) for j in range(len(arcs[i])) if arcs[i][j]==s)  == 0)

# Definition des contraintes
m.addConstr(quicksum(a[i] for i in range(len(a))) == 1, "Contrainte%d")
for k in range(nbpers): # Contrainte (1)
    for i in range(nbpers):
        m.addConstr(r[k] - B[i,k] - z[i] <= 0, "Cr")

LZ = [] #liste des 20 valeurs de z
L_path = [] # liste des chemins

for w in range(20):
    L = []
    L_path_alpha = []
    cout1 = generateTij()
    cout2 = generateTij()
    for alpha in range(1,6):
        C = []
        W = generateW(alpha)
        c1 =m.addConstr(quicksum(M[i][j]*cout1[i][j] for i in range(len(arcs)) for j in range(len(arcs[i]))) - z[0] == 0)
        c2 =m.addConstr(quicksum(M[i][j]*cout2[i][j] for i in range(len(arcs)) for j in range(len(arcs[i]))) - z[1] == 0)
        C.append(c1)
        C.append(c2)
        # definition de l'objectif
        obj = sum(W*(k*r - B.sum(0)))
        m.setObjective(obj,GRB.MAXIMIZE)
        
        m.update()
        
        # Resolution du modele
        m.optimize()
        L.append((z[0].x, z[1].x))
        path =[]
        for i in range(len(M)):
            for j in range(len(M[i])):
                if M[i][j].x == 1:
                    path.append(M[i][j].VarName)
        L_path_alpha.append(path)
        for c in C:
            m.remove(c)
        m.update()  
    LZ.append(L)
    L_path.append(L_path_alpha)

for i in range(len(L_path)):
    print("t = ", i+1, " : ", L_path[i],"\n")
LZ = np.array(LZ)
print(LZ.shape)
fig, axs = plt.subplots(2, 3)

axs[0, 0].scatter(LZ[:,0,0], LZ[:,0,1], label="1",color='red')
axs[0, 0].set_title('a = 1, w = %s' % np.round([wi_alpha(1,1),wi_alpha(2,1)],2))
axs[0, 1].scatter(LZ[:,1,0], LZ[:,1,1], label="2",color='orange')
axs[0, 1].set_title('a = 2, w = %s' % np.round([wi_alpha(1,2),wi_alpha(2,2)],2))
axs[0, 2].scatter(LZ[:,2,0], LZ[:,2,1], label="3" , color='yellow')
axs[0, 2].set_title('a = 3, w = %s' % np.round([wi_alpha(1,3),wi_alpha(2,3)],2))
axs[1, 0].scatter(LZ[:,3,0], LZ[:,3,1], label="4" , color='green')
axs[1, 0].set_title('a = 4, w = %s' % np.round([wi_alpha(1,4),wi_alpha(2,4)],2))
axs[1, 1].scatter(LZ[:,4,0], LZ[:,4,1], label="5" , color='pink')
axs[1, 1].set_title('a = 5, w = %s' % np.round([wi_alpha(1,5),wi_alpha(2,5)],2))
for ax in axs.flat:
    ax.set(xlabel='S = 1 ', ylabel='S = 2')
# Hide x labels and tick labels for top plots and y ticks for right plots.
for ax in axs.flat:
    ax.label_outer()
fig.tight_layout()
plt.show()