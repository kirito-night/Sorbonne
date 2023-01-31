from gurobipy import *

# nouveau model
m = Model("mogplex")

# initialisation des variables
k = 2

r_k = []
b_i1 = []
b_i2 = []
x = []
z = []

for i in range(k):
    r_k.append(m.addVar(vtype=GRB.CONTINUOUS, name="r%d" % (i+1)))
for i in range(k):
    b_i1.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d1" % (i+1)))
    b_i2.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d2" % (i+1)))
for i in range(5):
    x.append(m.addVar(vtype=GRB.BINARY, name="x%d" % (i+1)))

z.append(-5*x[0] - 6*x[1] - 4*x[2] - 8*x[3] - x[4])
z.append(-3*x[0] - 8*x[1] - 6*x[2] - 2*x[3] - 5*x[4])

m.update()

# fonction objectif 
obj = r_k[0] + 2*r_k[1] - b_i1[0] - b_i1[1] - b_i2[0] - b_i2[1]

m.setObjective(obj, GRB.MAXIMIZE)


# ajout des contraintes dans le model
m.addConstr(r_k[0] - b_i1[0] + z[0] <= 0)
m.addConstr(r_k[1] - b_i2[0] + z[0] <= 0)
m.addConstr(r_k[0] - b_i1[1] + z[1] <= 0)
m.addConstr(r_k[1] - b_i2[1] + z[1] <= 0)
m.addConstr(x[0] + x[1] + x[2] + x[3] + x[4] == 3)


# Optimisation 
m.optimize()


# les valeurs des variables 
for v in m.getVars():
    print(v.varName, v.x)


# l'optimum de fonction objectif
print('Maximized profit:',  m.objVal)