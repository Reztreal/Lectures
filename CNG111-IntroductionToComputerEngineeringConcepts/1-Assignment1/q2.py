#$ python q2.py

down  = input("Enter the first value of x> ")
up    = input("Enter the last value of x> ")
step  = input("Enter the step size> ")

while down <= up:
   print "f({}) = {}".format(down, (down**2 + 4*down - 4))
   down += step
