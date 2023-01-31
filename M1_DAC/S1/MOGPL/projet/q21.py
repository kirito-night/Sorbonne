from gurobipy import *

# nouveau model
m = Model("mogplex")

# initialisation des variables
n = 3

r_k = []
b_i1 = []
b_i2 = []
b_i3 = []
x = []
z = []
# Decommentez pour tester avec des w differentes

#w = [1,1,1] 
w = [7,2,1]
for i in range(n):
    r_k.append(m.addVar(vtype=GRB.CONTINUOUS,lb= -GRB.INFINITY, name="r%d" % (i+1)))
for i in range(n):
    b_i1.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d1" % (i+1)))
    b_i2.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d2" % (i+1)))
    b_i3.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d3" % (i+1)))
for i in range(18):
    x.append(m.addVar(vtype=GRB.BINARY, name="x%d" % (i+1)))

z.append(-325*x[0] - 225*x[1] - 210*x[2] - 115*x[3] - 75*x[4] - 50*x[5])
z.append(-325*x[6] - 225*x[7] - 210*x[8] - 115*x[9] - 75*x[10] - 50*x[11])
z.append(-325*x[12] - 225*x[13] - 210*x[14] - 115*x[15] - 75*x[16] - 50*x[17])

m.update()

# fonction objectif 
obj = w[0]*(r_k[0] - b_i1[0] - b_i2[0] - b_i3[0]) + w[1]*(2*r_k[1] - b_i1[1] - b_i2[1] - b_i3[1]) + w[2]*(3*r_k[2] - b_i1[2] - b_i2[2] - b_i3[2])
m.setObjective(obj, GRB.MAXIMIZE)


# ajout des contraintes dans le model
m.addConstr(r_k[0] - b_i1[0] +z[0] <= 0)
m.addConstr(r_k[0] - b_i2[0] +z[1] <= 0)
m.addConstr(r_k[0] - b_i3[0] +z[2]<= 0)
m.addConstr(r_k[1] - b_i1[1] + z[0] <= 0)
m.addConstr(r_k[1] - b_i2[1] + z[1] <= 0)
m.addConstr(r_k[1] - b_i3[1] + z[2] <= 0)
m.addConstr(r_k[2] - b_i1[2] + z[0] <= 0)
m.addConstr(r_k[2] - b_i2[2] + z[1] <= 0)
m.addConstr(r_k[2] - b_i3[2] + z[2] <= 0)
m.addConstr(x[0] + x[6] + x[12] == 1)
m.addConstr(x[1] + x[7] + x[13] == 1)
m.addConstr(x[2] + x[8] + x[14] == 1)
m.addConstr(x[3] + x[9] + x[15] == 1)
m.addConstr(x[4] + x[10] + x[16] == 1)
m.addConstr(x[5] + x[11] + x[17] == 1)




# Optimisation 
m.optimize()


# les valeurs des variables 
for v in m.getVars():
    print(v.varName, v.x)

tmp = 0 
for i  in range(len(z)):
    tmp += -z[i]
    print("z",i , " : " , -z[i].getValue())

print("satisfaction moyenne : ", tmp.getValue()/n)


# l'optimum de fonction objectif
print('Maximized profit:',  m.objVal)