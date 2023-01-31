from gurobipy import *

# Create a new model
m = Model()

# Create variables
k = 6 # à modifier de 1 à 6
lz = [1,2,3,4,7,9]

rk = m.addVar(name="rk")
bik = []

for i in range(len(lz)):
    bik.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%dk" % (i+1)))

m.update()

# Set objective function
obj = k*rk
for b in bik:
    obj -= b

m.setObjective(obj, GRB.MAXIMIZE)


# Add constraints
for i in range(len(lz)):
    m.addConstr(rk - bik[i] <= lz[i])


# Optimize model
m.optimize()

#Print values for decision variables
for v in m.getVars():
    print(v.varName, v.x)

#Print maximized profit value
print('Maximized profit:',  m.objVal)
