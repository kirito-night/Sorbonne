#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Nov 23 12:46:27 2022

@author: 28601285
"""

def generateW(n):
    U = list(np.arange(50))
    U = random.sample(U, n)
    U.sort(reverse=True)
    print("Before")
    U.append(0)
    U = [U[i]-U[i+1] for i in range(len(U)-1)]
    return U

for n in [5,10,15,20]:
    print(generateW(n))