Y= np.unique(data_synthese["Groupe d'aliment"])
labeltmp = []
for i in Y : 
    
    for j in data_synthese["Groupe d'aliment"]:
        
        if str(i) == str(j) : 
            #print(i,j)
            label.append(list(Y).index(i))
len(label)