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

