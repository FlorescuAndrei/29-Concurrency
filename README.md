# 29-Concurrency
Concurrency and Threads. Threads and Runnable. Synchronization and Lock  

### The project have 5 packages  

**_1_create_tread**:   
  -  create 4 threads: one class that extend Thread, one class that implement Runnable, and two nested classe.  

**_2_sleep_join_interrupt**: 
  -  create 2 threads and work with .sleep(), join() and .interrupt()..  

**_3_variables_and_synchronization_counter**:  
  -  local variable, instance variable, synchronized method, synchronized block.  

**_4_wait_notify_message**:    
  -  Class Message with two synchronized methods read and write. Threads will write(produce) a message and read (consume) that message. .   
**_5_producer_consumer_reentrant_lock**:  
  -  Producer adds an item to an Arraylist<Strings>, Consumer removes the item.  
   Three implementation:   
     -  with synchronization  	
     -  with Reentrant Look  
     -  with Reentrant Look using try finally block

    

[BACK TO START PAGE](https://github.com/FlorescuAndrei/Start.git) 

### Project Description and some theory:  

Concurrency and Threads  

**Concurrency** refers to an application doing more than one thing at a time. Not necessarily at the same time, but one task doesn’t have to complete before another one starts.  
A process (= an application) is a unit of execution that has its own memory space (heap). Terms process and application are used interchangeably.  
Different applications run on different memory spaces known as **Heap**.  


**A thread** is a unit of execution within a process.  
**Thread Stack** is the memory that only that tread can access.  

Every java application runs as a single process.  
Each process has multiple threads and a principal thread the main thread.  

Why use threads:
-  when we want to perform a task that requires a long time(query a database). We give this task to a thread and on the main thread, we can continue working with our app. The app is not frozen.
-  API requires to provide a thread. Sometimes we have to provide the code that will run when a method reaches a certain point.

Threads schedule is unpredictable.  

### _1_create_tread  

We create a thread that is going to run some code. So we tell the thread what code to run.  
Two ways to create a thread 
-  Create an instance of a Thread Class: 
   -  create a subclass of the Thread  class (class that extends Thread), 
      -  override the run() method: put the code you want to run.  
      -  start() the thread in main method  
   -  create an anonymous class(in the Main Class) if the thread run only once:  
      -  new Thread(run(….)).start().  
      
-  Implement the Runnable Interface.  
   -  create a class that implements Runnable( ex: MyRunnable) and override the run() method.  
      -  in Main Class create a new Thread and pass the previous class as a paramenter (ex: Thread myRunnableThread = new Thread(new MyRunnable());)  -  -  -  start() the thread  
   -   create a anonymous class(in the Main Class) that implements Runnable  
       -  create a new Thread and pass the anonymous class as paramenter (ex: Thread myRunnableThread = new Thread(newRunnable(run(…)));)  
       -  start()the thread  

Most often Threads are used with Runnable because is more flexible.  

### _2_sleep_join_interrupt  

.sleep()  
.interrupt() - wake from sleep()  
.join() – when we join thread1 with thread2, thread 1 will wait for thread2 to terminate and then will continue to execute. If thread1 never terminates, to avoid blocking we add a time for thread2 to start if time is elapsed.   

sleep(), interrupt() an join() are methods from the Thread class.  

Thread.sleep() – is called  inside the thread run() method (or on the Main calss if we want the main default thread to sleep()).  
threaName.join(), threadName.interupt() are called in the Main class.


### _3_variables_and_synchronization_counter  

Memory  
-  application memory = Heap. Every process has a heap.  
-  thread memory = Thread Stack. Every thread has a thread stack  

Variables   
-  local variables (from methods) are saved in thread stack, every thread has a copy of local variable => are thread safe, thread won’t interfere with each other;  
-  instance variables (from class) are saved in Heap, threads share the same variable. Thread interference. Race condition, more threads are writing a shared resource.  

The thread stack will contain only primitive values and object references.  

Critical Section – code that’s referencing a shared resource like a variable.  
Only one thread at a time should be able to execute a critical section   

Thread safe – class or method – all the critical sections within the code are synchronized, no thread interference.  

Race condition, more threads are writing a shared resource.

We can prevent interference or race condition by synchronizing critical sections of code.


**Prevent Thread Interference:**  
A.	using Synchronization  
B.	using Classes that implement java.util.concurrent.locks.lock interface  

**A. Synchronization** – schedules when the heap is access.  
When a method is synchronized only one thread can execute it at a time. The other threads will suspend until the thread running the method exit it.  

Two ways to synchronized a Race Condition:  
-  synchronize methods   
-  synchronize a block of statements rather than an entire method. Do not use local variables to synchronize.   

To synchronize methods we add the synchronized keyword in method declaration.  
To synchronize a block of statement we need an **Intrinsic Lock** (also called Monitor). We can synchronize a block of statements that work with an object by forcing threads to acquire the object’s lock before they execute the statement block.  
Primitive types don’t have an intrinsic lock. We can not synchronize on them.  

**Counter example**:  
We have a class Countdown with a method doCountdown() which has a for loop with a classic int i variable.   
We create a thread CountdownThread and make two instace of it t1 and t2  
These threads run the doCountdown() method and count down from 10 to 1.  
-  Case 1: we use a **local variable**, this is saved in the thread stack and the threads do not interfere.  
**No need to synchronize**.  First thread will execute from 10 to 1 and the second thread will execute from 10 to 1 but not in order,  will alternate the output 
-  Case 2: we use an **instance variable**, this is saved in heap and the threads shared  it, so there is interfering, race condition.
   -  Case 2.1 we do **not synchronize**, the numbers will be printed from 10 to 1 only once by both threads. (may be some duplicates).  
   -  Case 2.2 we synchronize:  
      - Case 2.2.1 **method synchronization**:  first thread from 10 to one, second thread from 10 to 1, in order.  
      Drawback - too much code is synchronized. Better to synchronize to an absolute minimum.  
      - Case 2.2.2 **block of code synchronization** :only the for loop within the method will be synchronized using countdown object as an intrinsic look.  
      We use countdown object because it is shared by both threads.  
      Best way because less code is synchronized.
      Result first thread from 10 to one, second thread from 10 to 1, in order.    

### _4_wait_notify_message  

This is a Producer-Consumer with messages example:    
Two threads  - one produce a message, one consume the message.   
Object class Message.   
To create these two threads we create two classes that implement Runnable Interface. MessageWriter, MessageReader. These threads shared the same object Message.  
Message object has two methods read() and write(). These methods are synchronized and use wait(), and notify() methods to suspend or wake the thread.    

Methods from Object class that can be called only in synchronized code:   
-  wait()  
-  notify()  
-  notifyAll()  
   
notify and notifyAll does the same thing. When there are many threads we use notify to notify the next available thread and not notifyAll for fewer resources to be used. We can not notify a specific thread.  
  
  
 ### _5_producer_consumer_reentrant_lock:
  
  
  
Producer Consumer Example   
We have an ArrayList<String> buffer and two threads Producer and Consumer. 
Producer adds an item to the list, Consumer removes the item.  

-  Booth Threads use the same buffer ArrayList so we synchronized on buffer.  
-  Instead of using synchronization, we can prevent thread interference using classes   that implement java.util.concurrentlocks.lock interface.   
  We use a Lock Object – an instance of ReentrantLock class  
  With synchronization look is released automatically but with reentrant lock we must release the look. This can be error-prone so we use a Try Finally block

Drawbacks to use synchronized blocks 
1.	Threads that are blocked, waiting to execute synchronized code can’t be interrupted. They are stuck waiting for the lock for the object the code is synchronizing on.
2.	The synchronized block must be within the same method.
3.	We can’t test if an object intrinsic lock is available. Also, we can’t time out after we waited for the look for a while. When we reach the beginning of a synchronized block, we can either get the lock and continue executing, or block at that line of code until we get the look.
4.	If multiple threads are waiting to get a lock, it’s not first come first serve, there isn’t a set order.   
  
    
**B) Use Classes that inmplement java.util.concurrent.locks.look interface**  
  
  Instead of using synchronization, we can prevent thread interference using classes that implement java.util.concurrentlocks.lock interface.   
  We use a Lock Object – an instance of ReentrantLock class.  
  With synchronization look is released automatically but with reentrant lock we must release the look. This can be error-prone so we use a Try Finally block  

Implementation:   
-  add a variable (parameter) ReentrantLook bufferLock to the class. Add it in the class constructor too. Instead of using a synchronized block to surround the code to be synchronized we use bufferLock.lock before and bufferLock.unlock after  (in addition we use a try finally block)   
  
  
More topics…:  
Thread Pools. Starvation. Locks (Deadlocks, Reentrant Lock, Unlock, Fair locks, Live locks).  
  
  

[BACK TO START PAGE](https://github.com/FlorescuAndrei/Start.git) 
