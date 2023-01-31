import random
from gurobipy import *
import numpy as np
import time
import matplotlib.pyplot as plt

nbcont=3 
nbvar=4
npers=2

def w_prime(n):
    liste = random.sample(range(1,51),n)
    liste.sort(reverse=True)
    wprime=np.zeros(len(liste))
    wprime[-1]=liste[-1]
    
    for i in range(len(liste)-1):
        wprime[i]=liste[i]-liste[i+1]
    
    return wprime

def generateU(p,n):
    return [ [ random.randint(1,20) for i in range(p) ] for j in range(n) ]

def generateCout(p):
    return [ random.randint(1,10) for i in range(p)  ]

matn=[2, 5, 10]
matp=[5, 10, 15, 20]

listetime=[]
for iteration in matn:

    n = iteration
    tmptime=[]
    for iterp in matp:
        t0=time.time()
        p = iterp
        # liste des utilites  
        for itermat in range(10):
            m = Model("mogplex")
            
            acurr=generateU(p,n)
            couts = generateCout(p)
            w = w_prime(n)
                    
            # declaration variables de decision
            x = np.array([m.addVar(vtype=GRB.BINARY , lb=0, name="x%d" % (i+1)) for i in range(p)])
            z = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="z%d" % (i+1)) for i in range(n)])
            r = np.array([m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="r%d" % (i+1)) for i in range(n)])
            b = np.array([[m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d" % (i+1))  for i in range(n)] for k in range(n)])
            
    
            k = np.arange(1,n+1)
            m.update()
            obj = sum(w*(k*r - b.sum(0)))
                    
            # definition de l'objectif
            m.setObjective(obj,GRB.MAXIMIZE)
            
            # Definition des contraintes
            for i in range(n):
                m.addConstr(z[i] - quicksum(acurr[i][j]*x[j] for j in range(p)) == 0, "Contrainte%d" % i)
            
            for k in range(n):
                for i in range(n):
                    m.addConstr(r[k] - b[i,k] - z[i] <= 0, "Contrainte%d" % i)
            
            m.addConstr(quicksum(couts[j]*x[j] for j in range(p)) <= 10)
            
            # Resolution
            
            
            m.optimize()
            
            print("")                
            print('Solution optimale:')
            print('Valeur de la fonction objectif :', m.objVal)
            
        tmptime.append(time.time()-t0)
    listetime.append(tmptime)

plt.plot(matp,listetime[0],label="n = 2")
plt.plot(matp,listetime[1],label="n = 5")
plt.plot(matp,listetime[2],label="n = 10")
plt.legend()
plt.show()

