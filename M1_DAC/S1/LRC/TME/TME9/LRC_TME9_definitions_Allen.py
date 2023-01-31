"""

Dictionnaires décrivants les transposés et symétries de relations, 
ainsi que les listes de relations obtenues avec les compositions de base
dans le tableau donné en TD

"""

# transpose : dict[str:str]
transpose = {
    '<':'>',
    '>':'<',
    'e':'et',
    's':'st',
    'et':'e',
    'st':'s',
    'd':'dt',
    'm':'mt',
    'dt':'d',
    'mt':'m',
    'o':'ot',
    'ot':'o',
    '=':'='                 
    }

# symetrie : dict[str:str]
symetrie = {
    '<':'>',
    '>':'<',
    'e':'s',
    's':'e',
    'et':'st',
    'st':'et',
    'd':'d',
    'm':'mt',
    'dt':'dt',
    'mt':'m',
    'o':'ot',
    'ot':'o',
    '=':'='
    }            

# compositionBase : dict[tuple[str,str]:set[str]]             
compositionBase = {        
        ('<','<'):{'<'},
        ('<','m'):{'<'},
        ('<','o'):{'<'},
        ('<','et'):{'<'},
        ('<','s'):{'<'},
        ('<','d'):{'<','m','o','s','d'},
        ('<','dt'):{'<'},
        ('<','e'):{'<','m','o','s','d'},
        ('<','st'):{'<'},
        ('<','ot'):{'<','m','o','s','d'},
        ('<','mt'):{'<','m','o','s','d'},
        ('<','>'):{'<','>','m','mt','o','ot','e','et','s','st','d','dt','='},
        ('m','m'):{'<'},
        ('m','o'):{'<'},
        ('m','et'):{'<'},
        ('m','s'):{'m'},
        ('m','d'):{'o','s','d'},
        ('m','dt'):{'<'},
        ('m','e'):{'o','s','d'},
        ('m','st'):{'m'},
        ('m','ot'):{'o','s','d'},
        ('m','mt'):{'e','et','='},
        ('o','o'):{'<','m','o'},
        ('o','et'):{'<','m','o'},
        ('o','s'):{'o'},
        ('o','d'):{'o','s','d'},
        ('o','dt'):{'<','m','o','et','dt'},
        ('o','e'):{'o','s','d'},
        ('o','st'):{'o','et','dt'},
        ('o','ot'):{'o','ot','e','et','d','dt','st','s','='},
        ('s','et'):{'<','m','o'},
        ('s','s'):{'s'},
        ('s','d'):{'d'},
        ('s','dt'):{'<','m','o','et','dt'},
        ('s','e'):{'d'},
        ('s','st'):{'s','st','='},
        ('et','s'):{'o'},
        ('et','d'):{'o','s','d'},
        ('et','dt'):{'dt'},
        ('et','e'):{'e','et','='},
        ('d','d'):{'d'},
        ('d','dt'):{'<','>','m','mt','o','ot','e','et','s','st','d','dt','='},
        ('dt','d'):{'o','ot','e','et','d','dt','st','s','='}
                           
                   }
def transposeSet(S):
    """
    transposeSet : set[str] -> set[str]
    """
    return {transpose[r] for r in S}
def symetrieSet(S):
    """
    symetrieSet : set[str] -> set[str]
    """
    return {symetrie[r] for r in S}
def compose(r1,r2):
    """
    compose : str str -> set[str]
    """
    if r1 == '=':
        return {r2}
    if r2 == '=':
        return {r1}
    if (r1,r2) in compositionBase:
        return compositionBase[(r1,r2)]
    if (transpose[r2], transpose[r1]) in compositionBase:
        return transposeSet(compositionBase[(transpose[r2], transpose[r1])])

    if (symetrie[r1], symetrie[r2]) in compositionBase:
        return symetrieSet(compositionBase[(symetrie[r1], symetrie[r2])])

    if (transpose[symetrie[r2]], transpose[symetrie[r1]]) in compositionBase:
        return transposeSet(symetrieSet(compositionBase[(transpose[symetrie[r2]], transpose[symetrie[r1]])]))
  
    
    

def composeSet(S1,S2):
    """
    composeSet : set[str] set[str] -> set[str]
    """
    res = set()
    for r1 in S1:
        for r2 in S2:
            res = res.union(compose(r1,r2))
    return res

class Graph : 
    noeuds = set()
    relation = dict() # relation : dict[tuple[str,str]:set[str]]
    def __init__(self, noeuds, relation):
        self.noeuds = noeuds
        self.relation = relation
    def getRelation(self, noeud1, noeud2):
        if (noeud1,noeud2) in self.relation:
            return self.relation[(noeud1,noeud2)]
        elif (noeud2, noeud1) in self.relation:
            return transposeSet(self.relation[(noeud2, noeud1)])
        else:
            return set(symetrie.keys())
    def setRelation(self,noeud1, noeud2,R):
        if (noeud2,noeud1) in self.relation:
            self.relation[noeud2,noeud1] = transposeSet(R)
        else: 
            self.relation[noeud1,noeud2] = R
    def propagation(self, noeud1, noeud2):
        L = set()
        L.add((noeud1, noeud2))
        while L != set():
            (i,j) = L.pop()
            for k in self.noeuds:
                if k != i and k != j:
                    if (i,k) in self.relation:
                        rik = self.getRelation(i,k)
                        tmp =  rik.intersection(composeSet(self.getRelation(i,j),self.getRelation(j,k)))
                        if tmp == set():
                            return False
                        elif tmp != self.relation[(i,k)]: 
                            self.setRelation(i,k,tmp)
                            L = L.union(tmp)
                    
                    if (k,j) in self.relation:
                        rkj = self.getRelation(k,j)
                        tmp = rkj.intersection(composeSet(self.getRelation(k,i), self.getRelation(i,j)))
                        if tmp == set():
                            return False
                        elif tmp != self.relation[(k,j)]:
                            self.setRelation(k,j,tmp)
                            L = L.union(tmp)
        return True
    def ajouter(self, noeud1, noeud2, relation):
        if noeud1 not in self.noeuds:
            self.noeuds.add(noeud1)
        if noeud2 not in self.noeuds:
            self.noeuds.add(noeud2)
        if (noeud1,noeud2) in self.relation:
            self.setRelation(noeud1, noeud2 , self.relation[(noeud1,noeud2)].union(relation))
            self.propagation(noeud1, noeud2)
        else:
            self.relation[(noeud1,noeud2)] = relation
            self.propagation(noeud1, noeud2)
    
        
    
    
        

print(compose('ot','m'))
print(composeSet({'ot','<'},{'m'}))
