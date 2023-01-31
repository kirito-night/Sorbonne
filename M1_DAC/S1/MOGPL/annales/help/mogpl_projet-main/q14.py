from gurobipy import *

# Create a new model
m = Model()

# Create variables
k = 2

rk = []
bi1 = []
bi2 = []
x = []
z = []

for i in range(k):
    rk.append(m.addVar(vtype=GRB.CONTINUOUS, name="r%d" % (i+1)))
for i in range(k):
    bi1.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d1" % (i+1)))
    bi2.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d2" % (i+1)))
for i in range(5):
    x.append(m.addVar(vtype=GRB.BINARY, name="x%d" % (i+1)))

z.append(-5*x[0] - 6*x[1] - 4*x[2] - 8*x[3] - x[4])
z.append(-3*x[0] - 8*x[1] - 6*x[2] - 2*x[3] - 5*x[4])

m.update()

# Set objective function
obj = rk[0] + 2*rk[1] - bi1[0] - bi1[1] - bi2[0] - bi2[1]

m.setObjective(obj, GRB.MAXIMIZE)


# Add constraints
m.addConstr(rk[0] - bi1[0] + z[0] <= 0)
m.addConstr(rk[1] - bi2[0] + z[0] <= 0)
m.addConstr(rk[0] - bi1[1] + z[1] <= 0)
m.addConstr(rk[1] - bi2[1] + z[1] <= 0)
m.addConstr(x[0] + x[1] + x[2] + x[3] + x[4] == 3)


# Optimize model
m.optimize()

#Print values for decision variables
for v in m.getVars():
    print(v.varName, v.x)

#Print maximized profit value
print('Maximized profit:',  m.objVal)

# m.write("model.lp")