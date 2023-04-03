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
///////////////////


QN:
one of the important task of ChessManager software is paring players. The general goal of a chess tournament is to select the winner and rank the others. The most important requirement of the paring is that everyone should play in each round of the tournament(except one player if the number of players is odd ). Thus , in mathematical terms the matching obtained should be(almost) complete. In the case of odd number of players one player will remain unmatched in every round, and gets a bye (i.e. 1 point) without colour. This cannot happen twice with any player during a tournamnet. the second common criterion is the Swiss tournament is that no pair of players can play twice. thus when considering a paring problem these pairs are not eligible . Finally, since playing a game with white or black can have significant effect on the result, tournament is considered fair if every player has played approximately the same number of times with white and black. these colour rules are a bit softer and used differently in the four variants, but a common strict rule is not to let any player to play with same colour three times in a row, and also not to lat any player to have a colour difference greater than two.


ANSWER 1:
import java.util.*;

public class ChessParing {
    private int numPlayers;
    private int numRounds;
    private List<Player> players;
    private List<Match> matches;
    private Map<Player, Integer> byes;
    private Map<Player, List<Player>> opponents;
    private Map<Player, Integer> whiteCount;
    private Map<Player, Integer> blackCount;

    public ChessParing(int numPlayers) {
        this.numPlayers = numPlayers;
        this.numRounds = (numPlayers % 2 == 0) ? numPlayers - 1 : numPlayers;
        this.players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i));
        }
        this.matches = new ArrayList<>();
        this.byes = new HashMap<>();
        this.opponents = new HashMap<>();
        this.whiteCount = new HashMap<>();
        this.blackCount = new HashMap<>();
        for (Player p : players) {
            byes.put(p, 0);
            opponents.put(p, new ArrayList<>());
            whiteCount.put(p, 0);
            blackCount.put(p, 0);
        }
    }

    public void runParing() {
        for (int round = 1; round <= numRounds; round++) {
            Collections.shuffle(players);
            Map<Player, Integer> colors = new HashMap<>();
            for (Player p : players) {
                colors.put(p, 0);
            }
            List<Player> roundOpponents = new ArrayList<>();
            for (int i = 0; i < numPlayers / 2; i++) {
                Player p1 = players.get(i * 2);
                Player p2 = players.get(i * 2 + 1);
                int c1 = colors.get(p1);
                int c2 = colors.get(p2);
                if (c1 == c2) {
                    c2 = 1 - c1;
                    colors.put(p2, c2);
                }
                Match match = new Match(round, p1, p2, c1 == 0 ? p1 : p2, c1 == 0 ? p2 : p1);
                matches.add(match);
                roundOpponents.add(p2);
                opponents.get(p1).add(p2);
                opponents.get(p2).add(p1);
                if (c1 == 0) {
                    whiteCount.put(p1, whiteCount.get(p1) + 1);
                    blackCount.put(p2, blackCount.get(p2) + 1);
                } else {
                    blackCount.put(p1, blackCount.get(p1) + 1);
                    whiteCount.put(p2, whiteCount.get(p2) + 1);
                }
            }
            if (numPlayers % 2 == 1) {
                Player byePlayer = players.get(numPlayers - 1);
                byes.put(byePlayer, byes.get(byePlayer) + 1);
            }
            for (Player p : players) {
                if (!roundOpponents.contains(p) && byes.get(p) == 0) {
                    byes.put(p, byes.get(p) + 1);
                }
            }
        }
    }

    public void printMatches() {
        for (Match match : matches) {
            System.out.println(match);
        }
    }

    public void printStats() {
        for (Player p : players) {
            System.out.println("Player " + p.getId() + ":");
            System.out.println("  Byes: " + byes.get(p));
            System.out.println("  Opponents: " + opponents.get(p));
           




ANSWER 2:

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private String name;
    private int points;
    private int numWhite;
    private int numBlack;
    private boolean hasBye;
    
    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.numWhite = 0;
        this.numBlack = 0;
        this.hasBye = false;
    }
    
    // getters and setters
    
    public String getName() {
        return name;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void addPoints(int points) {
        this.points += points;
    }
    
    public int getNumWhite() {
        return numWhite;
    }
    
    public void incrementNumWhite() {
        this.numWhite++;
    }
    
    public int getNumBlack() {
        return numBlack;
    }
    
    public void incrementNumBlack() {
        this.numBlack++;
    }
    
    public boolean hasBye() {
        return hasBye;
    }
    
    public void setBye(boolean hasBye) {
        this.hasBye = hasBye;
    }
    
    @Override
    public String toString() {
        return name;
    }
}

public class Match {
    private int roundNum;
    private Player whitePlayer;
    private Player blackPlayer;
    private int result;
    
    public Match(int roundNum, Player whitePlayer, Player blackPlayer) {
        this.roundNum = roundNum;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.result = -1; // -1 indicates no result yet
    }
    
    // getters and setters
    
    public int getRoundNum() {
        return roundNum;
    }
    
    public Player getWhitePlayer() {
        return whitePlayer;
    }
    
    public Player getBlackPlayer() {
        return blackPlayer;
    }
    
    public int getResult() {
        return result;
    }
    
    public void setResult(int result) {
        this.result = result;
    }
    
    @Override
    public String toString() {
        String resultStr;
        switch(result) {
            case 0:
                resultStr = "Draw";
                break;
            case 1:
                resultStr = whitePlayer.getName() + " wins";
                break;
            case -1:
            default:
                resultStr = "TBD";
                break;
        }
        return "Round " + roundNum + ": " + whitePlayer.getName() + " vs. " + blackPlayer.getName() + " (" + resultStr + ")";
    }
}

public class Tournament {
    private List<Player> players;
    private List<Match> matches;
    private int numRounds;
    
    public Tournament(List<Player> players) {
        this.players




ANSWER 3:

public class Player {
  int id;
  String name;
  String color;
  int score;

  public Player(int id, String name) {
    this.id = id;
    this.name = name;
    this.color = "";
    this.score = 0;
  }
}

public class Round {
  int id;
  Player player1;
  Player player2;
  String color1;
  String color2;

  public Round(int id, Player player1, Player player2, String color1, String color2) {
    this.id = id;
    this.player1 = player1;
    this.player2 = player2;
    this.color1 = color1;
    this.color2 = color2;
  }
}

public class Tournament {
  int id;
  String name;
  List<Player> players;
  List<Round> rounds;

  public Tournament(int id, String name, List<Player> players) {
    this.id = id;
    this.name = name;
    this.players = players;
    this.rounds = new ArrayList<Round>();
  }
}

public class ChessManager {
  public static void main(String[] args) {
    // create a tournament with 8 players
    List<Player> players = new ArrayList<Player>();
    for (int i = 1; i <= 8; i++) {
      players.add(new Player(i, "Player " + i));
    }
    Tournament tournament = new Tournament(1, "Chess Tournament", players);

    // generate all possible rounds
    List<Round> possibleRounds = generatePossibleRounds(tournament);

    // generate pairings for each round
    for (Round round : possibleRounds) {
      List<Round> possiblePairings = generatePossiblePairings(round, tournament);
      Round selectedPairing = selectBestPairing(possiblePairings);
      tournament.rounds.add(selectedPairing);
    }

    // calculate scores for each player
    for (Round round : tournament.rounds) {
      if (round.color1.equals("white")) {
        round.player1.score += 1;
      } else if (round.color1.equals("black")) {
        round.player2.score += 1;
      }
      if (round.color2.equals("white")) {
        round.player2.score += 1;
      } else if (round.color2.equals("black

