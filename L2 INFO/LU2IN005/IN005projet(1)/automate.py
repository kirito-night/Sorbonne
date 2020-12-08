# -*- coding: utf-8 -*-
from transition import *
from state import *
import os
import copy
from sp import *
from parser import *
from itertools import product
from automateBase import AutomateBase



class Automate(AutomateBase):
        
    def succElem(self, state, lettre):
        """State x str -> list[State]
        rend la liste des états accessibles à partir d'un état
        state par l'étiquette lettre
        """
        successeurs = []
        # t: Transitions
        for t in self.getListTransitionsFrom(state):
            if t.etiquette == lettre and t.stateDest not in successeurs:
                successeurs.append(t.stateDest)
        return successeurs


    def succ (self, listStates, lettre):
        """list[State] x str -> list[State]
        rend la liste des états accessibles à partir de la liste d'états
        listStates par l'étiquette lettre
        """
        # res: list[State]
        res = []
        # state : State
        for state in listStates :
            # transition : Transition
            for dest_state in self.succElem(state, lettre):
                if dest_state not in res : 
                    res.append(dest_state)
        return res
    

    """ Définition d'une fonction déterminant si un mot est accepté par un automate.
    Exemple :
            a=Automate.creationAutomate("monAutomate.txt")
            if Automate.accepte(a,"abc"):
                print "L'automate accepte le mot abc"
            else:
                print "L'automate n'accepte pas le mot abc"
    """
    @staticmethod
    def accepte(auto,mot) :
        """ Automate x str -> bool
        rend True si auto accepte mot, False sinon
        """
        # accepte : bool
        accepte = False
        # stateList : list[State]
        stateList = auto.getListInitialStates()
        for lettre in mot :
            stateList = auto.succ(stateList, lettre)
        for state in stateList:
            if state in auto.getListFinalStates():
                accepte = True
        return accepte


    @staticmethod
    def estComplet(auto,alphabet) :
        """ Automate x str -> bool
         rend True si auto est complet pour alphabet, False sinon
        """
        # stateList : list[State]
        stateList = auto.getListInitialStates()
        # currentList : list[State]
        currentList = stateList
        # nextList : list[State]
        nextList = []
        # end : bool
        end = False
        while(not end):
            for state in currentList:
                for lettre in alphabet:
                    if auto.succElem(state, lettre) == []:
                        return False
            for lettre in alphabet :
                nextList += auto.succ(currentList, lettre)
            for state in nextList:
                    if state not in stateList:
                        stateList.append(state)
            # # 上面那段或者这么写
            # stateList = list(set(nextList+ stateList))
            currentList = list(set(nextList))
            if set(auto.listStates) == set(stateList): 
                end = True
        return True


        
    @staticmethod
    def estDeterministe(auto) :
        """ Automate  -> bool
        rend True si auto est déterministe, False sinon
        """
        # currentList : list[State]
        currentList  = auto.getListInitialStates()
        if len(currentList) > 1:
            return False
        # stateList : list[State]
        stateList = currentList
        # nextList : list[State]
        nextList = []
        # end : bool
        end = False
        # alphabet : list[str]
        alphabet = []
        # deterministe : bool
        while(not end):
            for state in currentList:
                alphabet = []
                nextList = []
                for transition in auto.getListTransitionsFrom(state):
                    if(transition.etiquette in alphabet) : 
                        return False
                    alphabet.append(transition.etiquette)
                    if(transition.stateDest not in nextList) : 
                        nextList.append(transition.stateDest)
                if stateList == auto.listStates: 
                    end = True
                currentList = list(set(nextList))
                for state in nextList:
                    if state not in stateList:
                        stateList.append(state)
                # # or we can write
                # stateList = list(set(stateList + nextList))
        return True
        

       
    @staticmethod
    def completeAutomate(auto,alphabet) :
        """ Automate x str -> Automate
        rend l'automate complété d'auto, par rapport à alphabet
        """
        if(Automate.estComplet(auto, alphabet)):
            return auto
        auto_new = copy.deepcopy(auto)
        # currentList : list[State]
        currentList = auto_new.getListInitialStates()
        # stateList : list[State]
        stateList = currentList
        # nextList : list[State]
        nextList = []
        # end : bool
        end = False
        # pureState : State
        pureState = State(-1, False, False)
        while(not end):
            for state in currentList:
                for lettre in alphabet:
                    if auto_new.succElem(state, lettre) == []:
                        auto_new.addTransition(Transition(state, lettre, pureState))
                    nextList = list(set(nextList + auto_new.succ(currentList, lettre)))
            if set(stateList) > set(auto.listStates): 
                end = True
            stateList = list(set(stateList + nextList))
            currentList = list(set(nextList))
        for lettre in alphabet:
            auto_new.addTransition(Transition(pureState, lettre, pureState))
        return auto_new

       

    @staticmethod
    def determinisation(auto) :
        """ Automate  -> Automate
        rend l'automate déterminisé d'auto
        """
        if(estDeterministe(auto)):
            return auto.copy.deepcopy()
        # auto_new : Automate 
        auto_new = auto.copy.deepcopy()
        # currentList : list[State]
        currentList = auto_new.getListInitialStates()
        # stateList : list[State]
        stateList = currentList
        # nextList : list[State]
        nextList = []
        # end : bool
        end = False
        # alphabet
        alphabet = []
        # newStateList : list[State]
        newStateList = []
        # newTransitionList : list[transition]
        newTransitionList = []
        while(not end):
            # transitionList : list[transition]
            transitionList = auto.getListTransitionsFrom(currentList)
            for transition in transitionList:
                if transition.etiquette not in alphabet:
                    alphabet.append(transition.etiquette)
                if transition.stateDest not in nextList:
                    nextList.append(transition.stateDest)
                # auto.succ(stateSrc, )
            if stateList != auto.listStates:
                end = True
        # return auto_new
        
    @staticmethod
    def complementaire(auto,alphabet):
        """ Automate -> Automate
        rend  l'automate acceptant pour langage le complémentaire du langage de a
        """
              
   
    @staticmethod
    def intersection (auto0, auto1):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage l'intersection des langages des deux automates
        """
        return

    @staticmethod
    def union (auto0, auto1):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage l'union des langages des deux automates
        """
        return
        

   
       

    @staticmethod
    def concatenation (auto1, auto2):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage la concaténation des langages des deux automates
        """
        return
        
       
    @staticmethod
    def etoile (auto):
        """ Automate  -> Automate
        rend l'automate acceptant pour langage l'étoile du langage de a
        """
        return




