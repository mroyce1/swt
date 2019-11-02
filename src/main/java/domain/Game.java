package domain;

import org.apache.jena.base.Sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private static volatile Answer aiAnswer = null;
    private static volatile Answer humanAnswer = null;
    private static volatile List<Answer> aiAnswers = null;
    private static volatile List<Answer> humanAnswers = null;
    private Player aiPlayer;
    private Player humanPlayer;
    private int maxRounds;
    private int maxTime;
    private List<Category> categories;
    private int rounds;
    private List<Character> listOfChars;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public Game(Player player1, Player player2, int maxRounds, Difficulty difficulty, List<Category> categories) {
        if (player1.getPlayerType() == PlayerType.HUMAN) {
            this.humanPlayer = player1;
            this.aiPlayer = player2;
        } else {
            this.humanPlayer = player2;
            this.aiPlayer = player1;
        }
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


    }

    private void evaluateAnswers() {
        /*
        Do validate queries here. If answers are valid, proceed
         */
        int p1Points = 0;
        int p2Points = 0;
        for (int i = 0; i < humanAnswers.size(); i++) {
            if (humanAnswers.get(i).getMoveTime() < aiAnswers.get(i).getMoveTime()) {
                p1Points += 10;
            } else if (humanAnswers.get(i).getMoveTime() > aiAnswers.get(i).getMoveTime()) {
                p2Points += 10;
            } else {
                p1Points += 5;
                p2Points += 5;
            }
        }
        System.out.println("Your answers: " + humanAnswers);
        System.out.println("Ai answers: " + aiAnswers);
        System.out.println("You receive " + p1Points + " points.");
        System.out.println("AI receives " + p2Points + " points");
        this.humanPlayer.incrementPoints(p1Points);
        this.aiPlayer.incrementPoints(p2Points);
        humanAnswers = null;
        aiAnswers = null;
        this.rounds++;
    }

    public void playRoundCommandLine() {
        char initialChar = this.getRandomChar();
        System.out.println("Starting round no. " + (this.rounds + 1) + " of " + this.maxRounds);
        System.out.println("Starting Character is:  " + initialChar);
        Thread t1 = new Thread() {
            public void run() {
                humanAnswers = humanPlayer.getListOfMoves(categories, initialChar);
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                aiAnswers = aiPlayer.getListOfMoves(categories, initialChar);
            }
        };
        t1.start();
        t2.start();
        while (aiAnswers == null || humanAnswers == null) {

        }
        this.evaluateAnswers();
        System.out.println("######################\n");
    }


    public void playRoundCommandLine2() {
        char initialChar = this.getRandomChar();
        System.out.println("######################\n");
        System.out.println("Starting round no. " + (this.rounds + 1));
        System.out.println("Starting Character is:  " + initialChar);

        for (Category category : this.categories) {
            running.set(true);
            Thread t1 = new Thread() {
                public void run() {
                    while (running.get() && humanAnswer == null) {
                        humanAnswer = humanPlayer.getMove(category, initialChar);
                    }
                }
            };
            Thread t2 = new Thread() {
                public void run() {
                    while (running.get() && aiAnswer == null) {
                        aiAnswer = aiPlayer.getMove(category, initialChar);
                    }
                }
            };
            t1.start();
            t2.start();
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) / 1000 < this.maxTime) {
                if (humanAnswer != null) {
//                    try {
//                        t1.join(1);
//                        t2.join(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    t1.interrupt();
                    t2.interrupt();
                    running.set(false);
                    long duration = (System.currentTimeMillis() - start) / 1000;
                    System.out.println("You answered " + humanAnswer + " in " + duration + "s.");
                    this.humanPlayer.incrementPoints(10);
                    System.out.println("You receive 10 points.");
                    System.out.println("Current score:");
                    System.out.println("AI: " + this.aiPlayer.getPoints() + " | You: " + this.humanPlayer.getPoints());
                    humanAnswer = null;
                    aiAnswer = null;

                }
                //null = exit from game
                if (aiAnswer != null) {
//                    try {
//                        t1.join(1);
//                        t2.join(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    t1.interrupt();
                    t2.interrupt();
                    running.set(false);
                    long duration = (System.currentTimeMillis() - start) / 1000;
                    System.out.println("AI answered " + aiAnswer + " in " + duration + "s.");
                    this.aiPlayer.incrementPoints(10);
                    System.out.println("AI receives 10 points.");
                    System.out.println("Current score:");
                    System.out.println("AI: " + this.aiPlayer.getPoints() + " | You: " + this.humanPlayer.getPoints());
                    humanAnswer = null;
                    aiAnswer = null;
                }

//                if (answer1 != null || answer2 != null) {
//                    t1.interrupt();
//                    t2.interrupt();
//                    return;
//                }
            }
        }
        this.rounds++;
    }
}

