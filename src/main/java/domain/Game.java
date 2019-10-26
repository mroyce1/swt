package domain;

import org.apache.jena.base.Sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static String answer1 = null;
    private static String answer2 = null;
    private Player player1;
    private Player player2;
    private int maxRounds;
    private int maxTime;
    private List<Category> categories;
    private int rounds;
    private List<Character> listOfChars;

    public Game(Player player1, Player player2, int maxRounds, Difficulty difficulty, List<Category> categories) {
        this.player1 = player1;
        this.player2 = player2;
        this.maxRounds = maxRounds;
        this.categories = categories;
        this.maxTime = difficulty.getVal();
        this.rounds = 0;
        this.listOfChars = new ArrayList<Character>();
        for (int i = 0; i < 26; i++) {
            this.listOfChars.add((char) ('A' + i));
        }
    }

    public Player start() {
        while (this.rounds < this.maxRounds) {
            this.playRoundCommandLine();
        }
        return null;
    }

    private char getRandomChar() {
        Random r = new Random();
        int randomInt = r.nextInt(this.listOfChars.size());
        return this.listOfChars.remove(randomInt);
    }

    public void playRound() {
        char initialChar = this.getRandomChar();
        for (char c : this.listOfChars) {
            System.out.print(c);
        }

//        Thread t1 = new Thread() {
//            public void run() {
//                answer1 = player1.getListOfMoves(categories, initialChar);
//            }
//        };
//        Thread t2 = new Thread() {
//            public void run() {
//                answer2 = player2.getListOfMoves(categories, initialChar);
//            }
////        };
//        t1.start();
//        t2.start();
//        long start = System.currentTimeMillis();
//        while ((System.currentTimeMillis() - start) / 1000 < this.maxTime) {
//            //null = exit from game
//            if (answer1 == null || answer2 == null) {
//                t1.interrupt();
//                t2.interrupt();
//                return;
//            }
//        }
    }

    public void playRoundCommandLine() {
        char initialChar = this.getRandomChar();
        System.out.println("Starting Character is:  " + initialChar);

        for (Category category : this.categories) {
            Thread t1 = new Thread() {
                public void run() {
                    answer1 = player1.getMove(category, initialChar);
                }
            };
            Thread t2 = new Thread() {
                public void run() {
                    answer2 = player2.getMove(category, initialChar);
                }
            };
            t1.start();
            t2.start();
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) / 1000 < this.maxTime) {
                //null = exit from game
                if (answer1 != null){
                    t1.interrupt();
                    t2.interrupt();
                    long duration = (System.currentTimeMillis() - start)/1000;
                    System.out.println("AI answered "+ answer1+ " in " +duration + "s.");
                    this.player1.incrementPoints(10);
                    System.out.println("AI receives 10 points.");
                    System.out.println("Current score:");
                    System.out.println("AI: " + this.player1.getPoints() + " | You:" + this.player2.getPoints());
                    answer1 = null;
                    answer2 = null;
                    this.rounds++;
                }
                if (answer2 != null){
                    t1.interrupt();
                    t2.interrupt();
                    long duration = (System.currentTimeMillis() - start)/1000;
                    System.out.println("You answered "+ answer2+ " in " +duration + "s.");
                    this.player2.incrementPoints(10);
                    System.out.println("You receive 10 points.");
                    System.out.println("Current score:");
                    System.out.println("AI: " + this.player1.getPoints() + " | You:" + this.player2.getPoints());
                    answer1 = null;
                    answer2 = null;
                    this.rounds++;
                }

//                if (answer1 != null || answer2 != null) {
//                    t1.interrupt();
//                    t2.interrupt();
//                    return;
//                }
            }
        }
    }
}

