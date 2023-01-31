from gurobipy import *

# nouveau model
m = Model("mogplex")

# initialisation des variables
l_z = [1,2,3,4,7,9]
res = []
for  n in range(1,7):
    r_k = m.addVar(name="rk")
    b_ik = []

    for i in range(len(l_z)):
        b_ik.append(m.addVar(vtype=GRB.CONTINUOUS, lb=0, name="b%dk" % (i+1)))

    m.update()

    # fonction objectif 
    obj = n*r_k
    for b in b_ik:
        obj -= b

    m.setObjective(obj, GRB.MAXIMIZE)
    # ajout des contraintes dans le model
    for i in range(len(l_z)):
        m.addConstr(r_k - b_ik[i] <= l_z[i])

    # Optimisation 
    m.optimize()

    # les valeurs des variables 
    for v in m.getVars():
        print(v.varName, v.x)

    # l'optimum de fonction objectif
    #print('Maximized profit:',  m.objVal)
    res.append(m.objVal)
    m.reset()
print(res)
