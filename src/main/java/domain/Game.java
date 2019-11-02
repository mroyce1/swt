package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


/*
Class representing a single game between AI and Human. Contains all needed functionality.
 */

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
        this.maxTime = difficulty.getMoveTimeUpperBound();
        this.rounds = 0;
        this.listOfChars = new ArrayList<Character>();
        for (int i = 0; i < 26; i++) {
            this.listOfChars.add((char) ('A' + i));
        }
    }

    /*
    Runs the game until the maximum number of rounds has been played (26 at most).
    Returns player who is leading in points as winner.
     */

    public Player start() {
        while (this.rounds < this.maxRounds) {
            this.playRoundCommandLine();
        }
        return this.humanPlayer.getPoints() > this.aiPlayer.getPoints() ? this.humanPlayer : this.aiPlayer;
    }


    /*
    Generates a random starting character.
     */
    private char getRandomChar() {
        Random r = new Random();
        int randomInt = r.nextInt(this.listOfChars.size());
        return this.listOfChars.remove(randomInt);
    }


    /*
    Method to validate and evaluate (in terms of move time) human and AI answers and award points.
    Resets global variables.
     */

    private void evaluateAnswers() {
        //TODO: Do validate queries here. If answers are valid, proceed
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
        System.out.println("You receive " + p1Points + " points and now have " + this.humanPlayer.getPoints() + " points.");
        System.out.println("AI receives " + p2Points + " points and now has " + this.aiPlayer.getPoints() + " points.");
        this.humanPlayer.incrementPoints(p1Points);
        this.aiPlayer.incrementPoints(p2Points);
        humanAnswers = null;
        aiAnswers = null;
        this.rounds++;
    }

    /*
    Carries out a single round (one starting character) on the command line.
     */

    public void playRoundCommandLine() {
        char initialChar = this.getRandomChar();
        System.out.println("Starting round no. " + (this.rounds + 1) + " of " + this.maxRounds);
        System.out.println("Starting Character is:  " + initialChar);
        Thread t1 = new Thread() {
            public void run() {
                humanAnswers = humanPlayer.getListOfAnswers(categories, initialChar);
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                aiAnswers = aiPlayer.getListOfAnswers(categories, initialChar);
            }
        };
        t1.start();
        t2.start();
        while (aiAnswers == null || humanAnswers == null) {

        }
        this.evaluateAnswers();
        System.out.println("######################\n");
    }
}

