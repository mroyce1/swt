package domain;

import application.SparqlController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Subclass of abstract class Player representing an AI player
 */
public class AI extends Player {
    /**
     * The AI's difficulty level
     */
    private Difficulty difficulty;

    /**
     * Constructor of AI class
     * @param name AI's name
     * @param points AI's points
     * @param difficulty the AI's difficulty
     */
    public AI(String name, int points, Difficulty difficulty) {
        super(name, points, PlayerType.AI);
        this.difficulty = difficulty;
    }

    /**
     * Generates a random move time for the AI
     * @return int
     */
    private int getRandomMoveTime() {
        int upperBound = this.difficulty.getMoveTimeUpperBound();
        return (new Random(System.currentTimeMillis()).nextInt(10) + upperBound) * 500;
    }

    /**
     * Generates a random String to be taken as an answer
     * @return String
     */
    private String generateRandomAnswer(){
        int rdm = this.getRandomNumberInRange(3, 8);
        String str = "";
        for (int i=0; i < rdm; i++){
            str += (char) ('a' + this.getRandomNumberInRange(0, 26));
        }
        return str;
    }

    /**
     *     Returns a list of answers for each category and starting with the respective character.
     * @param categories List of categories
     * @param initialChar starting char
     * @return List<Answer>
     */
    @Override
    public List<Answer> getListOfAnswers(List<Category> categories, char initialChar) {
        //thresholdFailure should depend on difficulty
        int thresholdFailure = 50;
        if (this.difficulty == Difficulty.MEDIUM) {
            thresholdFailure = 70;
        } else if (this.difficulty == Difficulty.HARD) {
            thresholdFailure = 90;
        }
        List<Answer> answers = new ArrayList<Answer>();
        Random random = new Random(System.currentTimeMillis());
        for (Category c : categories) {
            List<String> results = SparqlController.queryList(c, initialChar);
            if (results.isEmpty()){
                answers.add(new Answer(null, c, initialChar, this.getRandomMoveTime()));
            }else{
                int randomInt = random.nextInt(results.size());
                int randomIntForAiFailure = getRandomNumberInRange(1, 100);
                if (randomIntForAiFailure >= thresholdFailure) {
                    answers.add(new Answer(this.generateRandomAnswer(), c, initialChar, this.getRandomMoveTime()));
                }else {
                    answers.add(new Answer(results.get(randomInt), c, initialChar, this.getRandomMoveTime(), true));
//                    answers.add(new Answer(results.get(randomInt), c, initialChar, this.getRandomMoveTime()));
                }
            }
        }
        return answers;
    }

    /**
     * Returns a single answer for each category and starting with the respective character.
     * @param category Single category
     * @param initialChar starting char
     * @return Answer
     */
    @Override
    public Answer getAnswer(Category category, char initialChar) {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) / 1000 < this.difficulty.getMoveTimeUpperBound() / 2) {

        }
        List<String> results = SparqlController.queryList(category, initialChar);
        Random random = new Random();
        int randomInt = random.nextInt(results.size());
        return new Answer(results.get(randomInt), category, initialChar, this.getRandomMoveTime());
    }

    /**
     * Generated a random integer within a given range
     * @param min lower bound
     * @param max upper bound
     * @return int
     */
    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
