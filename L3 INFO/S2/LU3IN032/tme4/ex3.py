class noncomplet(Exception):
    pass


class Node:
    def __init__(self, e, g, d):
        self.e = e
        self.g = g
        self.d = d

    def hauteur(self):
        if self.leaf:
            return 0
        if self.g is None :
            raise noncomplet()
        if self.d is None :
            return noncomplet()
        if self.g.hauteur() != self.d.hauteur():
             raise noncomplet()
        return 1 + self.g.hauteur() + self.d.hauteur()

    @property
    def leaf(self):
        return self.g is None and self.d is None

x = Node("x",
         Node("3", None, None),
         Node("4", None, None))

