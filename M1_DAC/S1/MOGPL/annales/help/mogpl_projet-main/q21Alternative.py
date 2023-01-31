from gurobipy import *

# Create a new model
m = Model()

# Create variables
k = 3

rk = []

bi1 = []
bi2 = []
bi3 = []

A = []
B = []
C = []

z = []

for i in range(k):
    rk.append(m.addVar(vtype=GRB.CONTINUOUS, name="r%d" % (i+1)))
for i in range(k):
    bi1.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d1" % (i+1)))
    bi2.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d2" % (i+1)))
    bi3.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%d3" % (i+1)))
for i in range(6):
    A.append(m.addVar(vtype=GRB.BINARY, name="A%d" % (i+1)))
    B.append(m.addVar(vtype=GRB.BINARY, name="B%d" % (i+1)))
    C.append(m.addVar(vtype=GRB.BINARY, name="C%d" % (i+1)))

z.append(-325*A[0] - 225*A[1] - 210*A[2] - 115*A[3] - 75*A[4] - 50*A[5])
z.append(-325*B[0] - 225*B[1] - 210*B[2] - 115*B[3] - 75*B[4] - 50*B[5])
z.append(-325*C[0] - 225*C[1] - 210*C[2] - 115*C[3] - 75*C[4] - 50*C[5])

m.update()

# Set objective function
obj = (rk[0]- bi1[0] - bi2[0] - bi3[0]) + (2*rk[1] - bi1[1] - bi2[1] - bi3[1]) + (3*rk[2] - bi1[2] - bi2[2] - bi3[2])

m.setObjective(obj, GRB.MAXIMIZE)


# Add constraints
m.addConstr(rk[0] - bi1[0] + z[0] <= 0)
m.addConstr(rk[1] - bi2[0] + z[0] <= 0)
m.addConstr(rk[2] - bi3[0] + z[0] <= 0)

m.addConstr(rk[0] - bi1[1] + z[1] <= 0)
m.addConstr(rk[1] - bi2[1] + z[1] <= 0)
m.addConstr(rk[2] - bi3[1] + z[1] <= 0)

m.addConstr(rk[0] - bi1[2] + z[2] <= 0)
m.addConstr(rk[1] - bi2[2] + z[2] <= 0)
m.addConstr(rk[2] - bi3[2] + z[2] <= 0)

m.addConstr(A[0]+B[0]+C[0]==1)
m.addConstr(A[1]+B[1]+C[1]==1)
m.addConstr(A[2]+B[2]+C[2]==1)
m.addConstr(A[3]+B[3]+C[3]==1)
m.addConstr(A[4]+B[4]+C[4]==1)
m.addConstr(A[5]+B[5]+C[5]==1)

# Optimize model
m.optimize()

#Print values for decision variables
for v in m.getVars():
    print(v.varName, v.x)

#Print maximized profit value
print('Maximized profit:',  m.objVal)

# m.write("model.lp")