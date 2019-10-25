package domain;

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

    public Game(Player player1, Player player2, int maxRounds, Difficulty difficulty, List<Category> categories) {
        this.player1 = player1;
        this.player2 = player2;
        this.maxRounds = maxRounds;
        this.categories = categories;
        this.maxTime = difficulty.getVal();
    }

    private char getRandomChar() {
        Random r = new Random();
        return (char) (r.nextInt(26) + 'A');
    }

    public void playRound() {
        char initialChar = this.getRandomChar();

        Thread t1 = new Thread() {
            public void run() {
                answer1 = player1.getMove(initialChar, categories);
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                answer2 = player2.getMove(initialChar, categories);
            }
        };
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) / 1000 < this.maxTime) {
            System.out.println("bla");
            //null = exit from game
            if (answer1 == null || answer2 == null) {
                t1.interrupt();
                t2.interrupt();
                return;
            }
        }
    }
}

