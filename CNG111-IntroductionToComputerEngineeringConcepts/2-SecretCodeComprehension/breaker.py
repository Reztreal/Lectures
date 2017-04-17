from random import randint

def comparer(pw, gs):
   matches = 0
   members = 0
   pw = str(pw)
   gs = str(gs)
   for i in xrange(4):
      if pw[i] == gs[i]:
         matches+=1
      elif gs[i] in pw:
         members+=1
   return matches, members

def fullCandidates():
   cand=0
   sayi = 0
   candidates.append("")
   for i in xrange(1,10):
      cand = str(i);
      for j in xrange(0,10):
         if str(j) in cand:
            continue
         cand = cand[0:1] + str(j);
         for k in xrange(0,10):
            if str(k) in cand:
               continue
            cand = cand[0:2] + str(k);
            for l in xrange(0,10):
               if str(l) in cand:
                  continue
               candidates[sayi] = cand+str(l)
               sayi+=1
               candidates.append("")
   candidates.pop(len(candidates)-1)

def removing(macs, mems, gus):
   newList = []
   for i in xrange(0, len(candidates)):
      macsTemp, memsTemp = comparer(candidates[i],gus)
      if macs == str(macsTemp) and mems == str(memsTemp):
         newList.append(candidates[i])
   return newList

candidates=[]
fullCandidates()
guess=0
turn = 0
report = ""
while turn < 8:
   guess = candidates[ randint(0,len(candidates)-1) ]
   print "The Guess is", guess
   report = raw_input("Enter Report> ")
   macs = str(report[0])
   mems = str(report[2])
   
   if not macs == "4" and len(candidates) > 1:
      candidates = removing(macs, mems, guess)
      print "Remaining possibilities:", len(candidates)
      print "Turn  Guess  Matches Members"
      print "{}.    {}   {}       {}".format(turn+1, guess, macs, mems)
      if len(candidates) <= 1:
         turn = 10
   else:
      turn = 10 #Use turn like key. This will end the loop end turn will be 11
   turn+=1

if len(candidates) == 0 :
   print "You did something wrong :("
elif turn == 11 :
   print "I win ! (Your number :", candidates[0],")"
else :
   print "I lose :("
