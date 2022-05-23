#!/usr/bin/python3

import os
import copy
from prolog_parser import parser
from interp_v3 import interprete4
from exc import NotUnifiable
from utils import filter


print("================ Interpreteur Prolog ================")


def interp_help():
    """
    Fonction permettant d'afficher l'aide de l'interpreteur
    """
    print(
        "help:\n"
        "\tcommandes mode normal (>):\n"
        "\t\tload [PATH]: charger un programme depuis un chemin, un seul programme est interprete a la fois\n"
        "\t\thelp: affiche cette aide\n"
        "\t\texit: sortir de l'interpreteur\n"
        "\tcommandes mode programme (#>):\n"
        "\t\t[RULE]: ajout d'une regle au programme courant\n"
        "\t\t?-[GOAL]: interpretation d'un goal\n"
        "\t\ttrace [0/1]: desactiver/activer le mode trace\n"
        "\t\tsols [0/1]: desactiver/activer la recherche de toutes les solutions\n"
        "\t\texit: retour au mode normal\n"
    )


def load(path: str):
    """
    Fonction permettant de charger un programme depuis un fichier
    """
    if not os.path.exists(path):
        print(f"programme non trouvé: {path}")
        return None
    return parser.parseFile(path)


def interp(prg, cmd: str, trace: bool, allsols: bool):
    """
    Fonction permettant d'interpreter un programme, en prenant en compte les différentes options demandées (trace, plusieurs solutions ...)

    Args:
        prg: programme a interpreter
        cmd: but a resoudre
        trace: si l'affichage trace doit etre activé
        allsols: si toutes les solutions doivent etre affichées
    """
    prg = copy.deepcopy(prg)
    prg.decls += parser.parseString(cmd).decls

    lst = []
    try:
        while True:
            env = interprete4(prg, trace, lst)
            #print(env)
            lst.append(env)
            filtered_env = filter(env)
            if filtered_env == {}:
                print("solution: true")
            else:
                print(f"solution: {filtered_env}")
            if not allsols:
                break
            else:
                i = input("continue [y/n]: ")
                if i != "y":
                    break
    except NotUnifiable:
        print("pas de solution trouvée : false")



prog = None
shell = ">"
trace = False
all_sols = True

interp_help()

while True:
    i = input(shell + " ")
    if len(i) < 4:
        continue
    cmd = i[0:4]
    if shell == ">":
        if cmd == "load":
            if len(i) < 6:
                print("veuillez entrer un chemin valide")
                continue
            prog = load(i[5:])
            if prog is not None:
                shell = "#>"
        elif cmd == "help":
            interp_help()
        elif cmd == "exit":
            exit()
        else:
            print("commande inconnue")
    else:
        if cmd == "trac":
            if len(i) == 7:
                trace = i[6] == "1"
                if trace:
                    print("trace activée")
                else:
                    print("trace desactivée")
            else:
                print("entrée invalide")
        elif cmd == "sols":
            if len(i) == 7:
                all_sols = i[6] == "1"
                if all_sols:
                    print("affichage toutes les solutions activée")
                else:
                    print("affichage toutes les solutions desactivée")
            else:
                print("entrée invalide")
        elif cmd == "exit":
            prog = None
            shell = ">"
        else:
            if i[0] == "?":
                interp(prog, i, trace, all_sols)
            else:
                prog.decls += parser.parseString(cmd).decls
