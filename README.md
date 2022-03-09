# 29-Concurrency
Concurrency and Threads. Threads and Runnable. Synchronization and Lock
The roject have 7 packages  

**_1_create_tread**: &emsp; &emsp; create 4 threads: one class that extend Thread, one class that implement Runnable, and two nested classe.  
**_2_sleep_join_interrupt**: &emsp;&emsp; create 2 threads and work with .sleep(), join() and .interrupt()..  
**_3_variables_and_synchronization_counter**:   &emsp;&emsp; local variable, instance variable, synchronized method, synchronized block.  
**_4_wait_notify_message**:   
**_5_producer_consumer_with_synchronization**:   
**_6_producer_consumer_with_lock**:  
**_7_producer_consumer_tryLock**:  

#### Project Description and some theory:  

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


Prevent Thread Interference:
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
Two threads use this method and count down from 10 to 1.  
-  Case 1: we use a **local variable**, this is saved in the thread stack and the threads do not interfere.  
**No need to synchronize**.  First thread will execute from 10 to 1 and the second thread will execute from 10 to 1 but not in order,  will alternate the output 
-  Case 2: we use an **instance variable**, this is saved in heap and the threads shared  it, so there is interfering, race condition.
   -  Case 2.1 we **do not synchronize**, the numbers will be printed from 10 to 1 only once by both threads. (may be some duplicates).  
   -  Case 2.2 we synchronize:  
      - Case 2.2.1 **method synchronization**:  first thread from 10 to one, second thread from 10 to 1, in order.  
      Drawback - too much code is synchronized. Better to synchronize to an absolute minimum.  
      - Case 2.2.2 **block of code synchronization** :only the for loop within the method will be synchronized using countdown object as an intrinsic look.  
      We use countdown object because it is shared by both threads.  
      Best way because less code is synchronized.
      Result first thread from 10 to one, second thread from 10 to 1, in order.  



[BACK TO START PAGE](https://github.com/FlorescuAndrei/Start.git) 