'''
This code works!
I have no idea what is expected in assignment
it writes current state of philosopher and time to mongodb
you should have mongodb server running

follows similar solution : https://www.youtube.com/watch?v=_ruovgwXyYs

'''

import threading
import time
from pymongo import MongoClient

class Philosopher(threading.Thread):
    running = True;
    connection = MongoClient("localhost",27017);
    arr=[0,0,0,0,0];
 
    def __init__(self, i, xname, forkOnLeft, forkOnRight):
        threading.Thread.__init__(self);
        self.name = xname;
        self.index=i;
        self.forkOnLeft = forkOnLeft;
        self.forkOnRight = forkOnRight;
	Philosopher.connection.test.dini.drop();
 
    @staticmethod
    def sendToMongo(index, state ):
	collection = Philosopher.connection.test.dini;
	doc = { "philo": index, "state":state, "time":time.ctime() };
	collection.insert_one(doc);

    def run(self):
	while(self.running):
 		print("Philosopher " + str(self.name) + " is thinking.\n");

		time.sleep(5);

		self.forkOnLeft.acquire();
		self.forkOnRight.acquire();

 		print("Philosopher " + str(self.name) + " is eating.\n");

		Philosopher.arr[self.index]=1;
		Philosopher.sendToMongo(self.index,Philosopher.arr);

		time.sleep(5);
		Philosopher.arr[self.index]=0;

		self.forkOnLeft.release();
		self.forkOnRight.release();
 
        
def DiningPhilosophers():
    forks = [];

    for i in range(5):
	forks.append(threading.Lock());
    #print forks
    
    ps = [];
    for i in range(5):
	ps.append(Philosopher(i,"Philosopher No. " + str(i), forks[min(i,(i+1)%5)] , forks[max(i,(i+1)%5)] ));

    print ps
	
    for p in ps:
	p.start();

    time.sleep(60);
    Philosopher.running = False;
DiningPhilosophers()
