package domain;

import application.SparqlController;
import org.apache.jena.query.QueryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Class representing a single game between AI and Human.
 */

public class Game {
    private static volatile Answer aiAnswer = null;
    private static volatile Answer humanAnswer = null;
    /**
     * Static list of AI answers
     */
    private static volatile List<Answer> aiAnswers = null;
    /**
     * Static list of human answers
     */
    private static volatile List<Answer> humanAnswers = null;
    /**
     * AI Player instance
     */
    private Player aiPlayer;
    /**
     * Human Player instance
     */
    private Player humanPlayer;
    /**
     * Maximum number of rounds to be played
     */
    private int maxRounds;
    /**
     * List of categories to be played
     */
    private List<Category> categories;
    /**
     * Counter for rounds that have been played so far. Incremented with each round
     */
    private int rounds;
    /**
     * List of starting characters, reduced with each round
     */
    private List<Character> listOfChars;
    private final AtomicBoolean running = new AtomicBoolean(true);

    /**
     * Constructor for Game class.
     *
     * @param player1    first player
     * @param player2    second player
     * @param maxRounds  maximum number of rounds to be played (upper bound of 26)
     * @param difficulty desired Difficulty level
     * @param categories desired categories
     */

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
//        this.maxTime = difficulty.getMoveTimeUpperBound();
        this.rounds = 0;
        this.listOfChars = new ArrayList<Character>();
        for (int i = 0; i < 26; i++) {
            this.listOfChars.add((char) ('A' + i));
        }
    }

    /**
     * Runs the game until the maximum number of rounds has been played (26 at most).
     * Returns player who is leading in points as winner.
     *
     * @return Player winner after the game has finished
     */

    public Player start() {
        while (this.rounds < this.maxRounds) {
            this.playRoundCommandLine();
        }
        return this.humanPlayer.getPoints() > this.aiPlayer.getPoints() ? this.humanPlayer : this.aiPlayer;
    }


    /**
     * Generates a random starting character
     *
     * @return char returns the character
     */
    private char getRandomChar() {
        Random r = new Random(System.currentTimeMillis());
        int randomInt = r.nextInt(this.listOfChars.size());
        return this.listOfChars.remove(randomInt);
    }


    /**
     * Method to validate and evaluate (in terms of move time) human and AI answers and award points.
     * Resets global variables.
     */

    private void evaluateAnswers() {
        int p1Points = 0;
        int p2Points = 0;
        List<Boolean> answers = new ArrayList<>();
        for (int i = 0; i < humanAnswers.size(); i++) {
            Boolean answerCorrect = false;
            try {
                answerCorrect = SparqlController.validateAnswer(humanAnswers.get(i));
                humanAnswers.get(i).setCorrect(answerCorrect);
//                System.out.println(humanAnswers.get(i).getAnswerText() + ":  " + answerCorrect);
            } catch (QueryException ignored) {
            }
            try {
                if (!aiAnswers.get(i).isCorrect()) {
                    answerCorrect = SparqlController.validateAnswer(aiAnswers.get(i));
                    aiAnswers.get(i).setCorrect(answerCorrect);
                }
//                System.out.println(humanAnswers.get(i).getAnswerText() + ":  " + answerCorrect);
            } catch (QueryException ignored) {
            }
        }
        Answer answer;
        for (int i = 0; i < humanAnswers.size(); i++) {
            answer = humanAnswers.get(i);
            if (answer.isCorrect() && answer.getMoveTime() < aiAnswers.get(i).getMoveTime()) {
                p1Points += 10; //human has correct answer and is faster than AI -> Human receives points
            } else if (answer.isCorrect() && answer.getMoveTime() > aiAnswers.get(i).getMoveTime() && aiAnswers.get(i) != null) {
                p2Points += 10; //human has correct answer but is slower than AI -> AI receives points
            } else if (!answer.isCorrect() && aiAnswers.get(i).getAnswerText() != null) {
                p2Points += 10; //human has no correct answer -> AI receives points
            }
            System.out.println("For " + answer.getCategory() + ":");
            System.out.println("You answered: " + humanAnswers.get(i));
            System.out.println("The AI answered: " + aiAnswers.get(i));
        }

        this.humanPlayer.incrementPoints(p1Points);
        this.aiPlayer.incrementPoints(p2Points);
        System.out.println("You receive " + p1Points + " points and now have " + this.humanPlayer.getPoints() + " points.");
        System.out.println("AI receives " + p2Points + " points and now has " + this.aiPlayer.getPoints() + " points.");
        humanAnswers = null;
        aiAnswers = null;
        this.rounds++;
        System.out.println("Press return to start the next round.");
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (bufR.readLine() == null) {
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * Carries out a single round (one starting character) on the command line. Spawns two threads, one for the AI, one for the human player.
     * Round finished when both players have entered their answers.
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

