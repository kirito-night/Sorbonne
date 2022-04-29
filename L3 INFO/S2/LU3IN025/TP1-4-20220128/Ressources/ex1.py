def readEtuPref(fileName):
    monFichier = open(fileName, "r")
    contenue = monFichier.readlines()
    for i in range(0, len(contenue)):
        contenue[i] = contenue[i].split()
        contenue[i].pop(0)

    contenue.pop(0)
    for i in range(0, len(contenue)):
        for j in range(1, len(contenue[i])):
            contenue[i][j] = int(contenue[i][j])
    return contenue


def readSpePref(fileName):
    monFichier = open(fileName, "r")
    contenue = monFichier.readlines()
    for i in range(0, len(contenue)):
        contenue[i] = contenue[i].split()
        if i == 1:
            continue
        contenue[i].pop(0)

    contenue.pop(0)
    for i in range(0, len(contenue)):
        for j in range(1, len(contenue[i])):
            contenue[i][j] = int(contenue[i][j])

    return contenue


def coteEtu(lEtu, lSpe):
    dES = dict()
    freeEtu = [lEtu[i][0] for i in range(len(lEtu))]

    nbEtuSpe = []
    for i in range(len(lSpe[0]) - 1):
        nbEtuSpe.append(0)
    # print(nbEtuSpe,"\n")
    lPurpose = [1 for i in range(len(lEtu))]

    while freeEtu != []:
        for i in range(0, len(lEtu)):
            for j in range(lPurpose[i], len(lEtu[i])):
                if lEtu[i][0] not in freeEtu:
                    continue;
                else:
                    if nbEtuSpe[lEtu[i][j]] < lSpe[0][lEtu[i][j] + 1]:
                        if lSpe[lEtu[i][j] + 1][0] in dES:

                            dES[lSpe[lEtu[i][j] + 1][0]].append(lEtu[i][0])


                        else:
                            dES[lSpe[lEtu[i][j] + 1][0]] = [lEtu[i][0]]

                        freeEtu.pop(freeEtu.index(lEtu[i][0]))
                        nbEtuSpe[lEtu[i][j]] += 1
                        lPurpose[i] = j + 1
                    else:
                        for z in dES[lSpe[lEtu[i][j] + 1][0]]:
                            ztmp = int(z[3])
                            if lSpe[lEtu[i][j] + 1].index(i) < lSpe[lEtu[i][j] + 1].index(ztmp):
                                dES[lSpe[lEtu[i][j] + 1][0]].remove(z)
                                freeEtu.append(z)
                                freeEtu.pop(freeEtu.index(lEtu[i][0]))
                                dES[lSpe[lEtu[i][j] + 1][0]].append(lEtu[i][0])
                                lPurpose[i] = j + 1
                                break;

                            else:
                                continue;

    print ("cote Etudiant")
    for key in dES:
        print(key, " : ", dES[key])
    return dES


def cote_parcours(lSpe, lEtu):
    dES = dict()
    freeEtu = [i for i in range(len(lEtu))]

    freeSpe = [lSpe[i][0] for i in range(1, len(lSpe))]
    nbEtuSpe = []
    for i in range(len(lSpe[0]) - 1):
        nbEtuSpe.append(0)
    #print(nbEtuSpe, "\n")
    lPurpose = [1 for i in range(len(lSpe))]

    while freeSpe != []:
        for i in range(1, len(lSpe)):
            for j in range(lPurpose[i], len(lSpe[i])):
                if lSpe[i][0] not in freeSpe:
                    continue
                else:
                    if lSpe[i][j] in freeEtu:
                        if lSpe[i][0] in dES:

                            dES[lSpe[i][0]].append(lSpe[i][j])



                        else:
                            dES[lSpe[i][0]] = [lSpe[i][j]]
                        nbEtuSpe[i - 1] += 1
                        tmp1, tmp2 = update_spe(nbEtuSpe, freeSpe, lSpe)
                        freeSpe = tmp1
                        nbEtuSpe = tmp2
                        freeEtu.remove(lSpe[i][j])
                        lPurpose[i - 1] = j + 1
                    else:
                        for x in dES:
                            if lSpe[i][j] in dES[x]:
                                for z in dES[x]:

                                    xtmp = found_index(lSpe, x)
                                    if lEtu[lSpe[i][j]].index(i - 1) < lEtu[lSpe[i][j]].index(
                                            xtmp):  # trouver l'index de la cle
                                        dES[x].remove(z)

                                        if lSpe[i][0] in dES:
                                            dES[lSpe[i][0]].append(lSpe[i][j])
                                        else:
                                            dES[lSpe[i][0]] = [lSpe[i][j]]

                                        tmp = found_index(lSpe, x)
                                        nbEtuSpe[tmp - 1] -= 1
                                        nbEtuSpe[i - 1] += 1
                                        tmp1, tmp2 = update_spe(nbEtuSpe, freeSpe, lSpe)
                                        freeSpe = tmp1
                                        nbEtuSpe = tmp2

                                        freeEtu.append(z)

                                        freeEtu.remove(lSpe[i][j])
                                        lPurpose[i - 1] = j + 1
                                        break;
                                    else:
                                        continue
                            break;

    res = dict()
    for a in dES:
        for b in dES[a]:
            if a in res:
                res[a].append("Etu" + str(b))
            else:
                res[a] = ["Etu" + str(b)]

    print("cote parcours")
    for key in res:
        print(key, " : ", res[key])
    return res

def instable_check(dico, lSpe , lEtu) :
    d = convert_liststrtoint(dico)
    res = []

    for key in d :
        for i in d[key]:
            l = found_unstable(i,key,lSpe,lEtu)
            res.extend(l)
    return res


def found_unstable(index_etu , key , lSpe, lEtu) :
    res = []
    index_spe = found_index(lSpe, key) -1
    for i in range(1 , len(lSpe)) :
        for j in range(1 , len(lSpe[i])):
            if lSpe[i].index(index_etu) > j:
                if lEtu[lSpe[i][j]].index(index_spe) > lEtu[lSpe[i][j]].index(i - 1):
                    res.append((lSpe[i][0], lSpe[i][j]))


            else :
                break;
    return res

def pair_instable_etu(dico , lEtu ,lSpe):
    d  = convert_liststrtoint(dico)
    pair = []
    for key in d:
        for i in d[key] :
            index_spe = found_index(lSpe , key) -1
            for j in range(1, lEtu[i])
                if lEtu[i].index(index_spe) > j:
                    for k in range ( 1, lSpe[index_spe +1]) :
                        if ( )


def convert_liststrtoint(dico):
    res = dict()
    for key in dico:
        for i in dico[key] :
            if key in res :
                res[key].append(int (i[3]))
            else :
                res[key] = [int(i[3])]

    return  res


def found_index(lSpe, item):
    res = 0
    for a in range(1, len(lSpe)):
        if lSpe[a][0] == item:
            res = a
            break;
    return res


def update_spe(nbEtu, freeSpe: list, lSpe):
    for i in range(0, len(nbEtu)):
        if lSpe[i + 1][0] in freeSpe:
            if nbEtu[i] >= lSpe[0][i + 1]:
                freeSpe.remove(lSpe[i + 1][0])
        else:
            if nbEtu[i] < lSpe[0][i + 1]:
                freeSpe.append(lSpe[i + 1][0])
    res = freeSpe
    resnb = nbEtu
    return (res, resnb)
