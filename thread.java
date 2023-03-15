//MULTI THREADING USING INTERFACE

public class multithreading_inter {
    public static void main(String args[]){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    System.out.println("Thread 1 :"+i);
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    System.out.println("Thread 2 :"+i);
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}

//MULTI THREADING USING ABSTRACT CLASS

class MyThread extends Thread {
    String name = "";

    public MyThread(String name){
        this.name = name;
    }

    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(this.name+" : "+i);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
public class multithreading_abstract_class {
    public static void main(String args[]){
        MyThread t1 = new MyThread("Thread 1");
        MyThread t2 = new MyThread("Thread 2");

        t1.start();
        t2.start();
    }
}

//MAIN THREAD 

class CurrentThreadDemo {
	public static void main(String args[]) {
		Thread t = Thread.currentThread();
		System.out.println("Current thread: " + t);
		t.setName("My Thread");
		System.out.println("After name change: " + t);
		try {
			for(int n = 5; n > 0; n--) {
				System.out.println(n);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted");
		}
	}
}

//RUNNABLE INTERFACE

class NewThread implements Runnable {
	Thread t;
	NewThread() {
		t = new Thread(this, "Demo Thread");
		System.out.println("Child thread: " + t);
		t.start(); 
	}
	public void run() {
		try {
			for(int i = 5; i > 0; i--) {
				System.out.println("Child Thread: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread.");
	}
}

public class ThreadDemo {
	public static void main(String args[ ] ) {
		new NewThread(); 
		try {
			for(int i = 5; i > 0; i--) {
				System.out.println("Main Thread: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
	}
}

//THREAD CLASS

class NewThread extends Thread {
	NewThread() {
		super("Demo Thread");
		System.out.println("Child thread: " + this);
		start(); 
	}
	public void run() {
		try {
			for(int i = 5; i > 0; i--) {
				System.out.println("Child Thread: " + i);
				Thread.sleep(500);
			}	
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread.");
	}
}

public class ExtendThread {
	public static void main(String args[]) {
		new NewThread(); 
		try {
			for(int i = 5; i > 0; i--) {
				System.out.println("Main Thread: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
	}
}

//THREAD PRIORITY

class ThreadDemo extends Thread { 
	public static void main(String[] args) { 
		ThreadDemo t1 = new ThreadDemo(); 
		ThreadDemo t2 = new ThreadDemo(); 
		ThreadDemo t3 = new ThreadDemo(); 
		System.out.println("t1 thread priority: "+ t1.getPriority());  
        		System.out.println("t2 thread priority: "+ t2.getPriority());  
		System.out.println("t3 thread priority: "+ t3.getPriority());  
  
      		t1.setPriority(2); 
		t2.setPriority(5); 
        	t3.setPriority(8); 
		
		System.out.println("t1 thread priority: "+ t1.getPriority());  
        		System.out.println("t2 thread priority: "+ t2.getPriority());  
       		System.out.println("t3 thread priority: "+ t3.getPriority());  
  
		System.out.println("Currently Executing Thread : "
            			+ Thread.currentThread().getName()); 
               		System.out.println("Main thread priority : "
            			+ Thread.currentThread().getPriority()); 
	
		Thread.currentThread().setPriority(10); 
		System.out.println( "Main thread priority : "
            			+ Thread.currentThread().getPriority()); 
    } 
}

//USING SYNCHRONIZED METHODS: WITHOUT SYNCHRONIZATION

class First{
	public void display(String msg) {
	       System.out.print ("["+msg);
	       try {
	             Thread.sleep(1000);
                  }
	       catch(InterruptedException e){
	             e.printStackTrace();
	       }
                 System.out.println ("]");
         }
}

class Second extends Thread{
	String msg;
	First fobj;
	Second (First fp,String str){
		fobj = fp;
		msg = str;
		start();
	}
	public void run(){
		fobj.display(msg);
	}
}

public class WithoutSynMethod{
	public static void main (String[] args){
		First fnew = new First();
		Second ss = new Second(fnew, "welcome");
		Second ss1= new Second(fnew,"new");
		Second ss2 = new Second(fnew, "programmer");
	}
}

//USING SYNCHRONIZED STATEMENT

class Second extends Thread{
	String msg;
	First fobj;
	Second (First fp,String str){
		fobj = fp;
		msg = str;
		start();
	}
	public void run(){
		synchronized(fobj){
			fobj.display(msg);
		}
	}
}

//INTER THREAD COMMUNICATION EX 1

class Chat {
   boolean flag = false;
   public synchronized void question(String msg) {
      if (flag) {
         try {
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println(msg);
      flag = true;
      notify();
   }

public synchronized void answer(String msg) {
      if (!flag) {
         try {
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println(msg);
      flag = false;
      notify();
   }
}

//INTER THREAD COMMUNICATION EX 2

class T1 implements Runnable {
   Chat m;
   String[] s1 = { "Hi", "How are you ?", "I am also doing 				fine!" };
   public T1(Chat m1) {
      this.m = m1;
      new Thread(this, "Question").start();
   }
   public void run() {
      for (int i = 0; i < s1.length; i++) {
         m.question(s1[i]);
      }
   }
}

class T2 implements Runnable {
   Chat m;
   String[] s2 = { "Hi", "I am good, what about you?", "Great!" };

   public T2(Chat m2) {
      this.m = m2;
      new Thread(this, "Answer").start();
   }

   public void run() {
      for (int i = 0; i < s2.length; i++) {
         m.answer(s2[i]);
      }
   }
}

public class TestThread {
   public static void main(String[] args) {
      Chat m = new Chat();
      new T1(m);
      new T2(m);
   }
}

