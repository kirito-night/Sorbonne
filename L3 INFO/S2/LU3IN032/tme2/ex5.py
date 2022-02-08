from sys import getsizeof


from sys import getsizeof
liste = [1,2,3]
for i in range (40) : 
    liste.append(2)
a = getsizeof(liste)
l2 =[1,2,3,4]
b = getsizeof(l2)
c = getsizeof(liste, l2)
print (a,b,c)