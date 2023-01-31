from gurobipy import *

# Create a new model
m = Model()

# Create variables
k = 6

w1=[3,2,1]
w2=[10,3,1]

ValeurObj=[325,225,210,115,75,50]

Ai=[]
Bi=[]
Ci=[]

z=[]

for i in range(k):
    Ai.append(m.addVar(vtype=GRB.BINARY, name="A%d" % (i+1)))
for i in range(k):
    Bi.append(m.addVar(vtype=GRB.BINARY, name="B%d" % (i+1)))
for i in range(k):
    Ci.append(m.addVar(vtype=GRB.BINARY, name="C%d" % (i+1)))

z.append(ValeurObj[0]*Ai[0]+ValeurObj[1]*Ai[1]+ValeurObj[2]*Ai[2]+ValeurObj[3]*Ai[3]+ValeurObj[4]*Ai[4]+ValeurObj[5]*Ai[5])
z.append(ValeurObj[0]*Bi[0]+ValeurObj[1]*Bi[1]+ValeurObj[2]*Bi[2]+ValeurObj[3]*Bi[3]+ValeurObj[4]*Bi[4]+ValeurObj[5]*Bi[5])
z.append(ValeurObj[0]*Ci[0]+ValeurObj[1]*Ci[1]+ValeurObj[2]*Ci[2]+ValeurObj[3]*Ci[3]+ValeurObj[4]*Ci[4]+ValeurObj[5]*Ci[5])

m.update()

# Set objective function
obj = w1[0]*z[0]+w1[1]*z[1]+w1[2]*z[2]
m.setObjective(obj, GRB.MAXIMIZE)


# Add constraints
m.addConstr(Ai[0]+Ai[1]+Ai[2]+Ai[3]+Ai[4]+Ai[5]==2)
m.addConstr(Bi[0]+Bi[1]+Bi[2]+Bi[3]+Bi[4]+Bi[5]==2)
m.addConstr(Ci[0]+Ci[1]+Ci[2]+Ci[3]+Ci[4]+Ci[5]==2)

m.addConstr(Ai[0]+Bi[0]+Ci[0]==1)
m.addConstr(Ai[1]+Bi[1]+Ci[1]==1)
m.addConstr(Ai[2]+Bi[2]+Ci[2]==1)
m.addConstr(Ai[3]+Bi[3]+Ci[3]==1)
m.addConstr(Ai[4]+Bi[4]+Ci[4]==1)
m.addConstr(Ai[5]+Bi[5]+Ci[5]==1)

m.addConstr(z[0]+z[1]+z[2]<=1000)

# Optimize model
m.optimize()

#Print values for decision variables
for v in m.getVars():
    print(v.varName, v.x)

#Print maximized profit value
print('Maximized profit:',  m.objVal)

# m.write("model.lp")