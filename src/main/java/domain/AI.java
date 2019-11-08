package domain;

import application.SparqlController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI extends Player {
    private Difficulty difficulty;

    public AI(String name, int points, Difficulty difficulty) {
        super(name, points, PlayerType.AI);
        this.difficulty = difficulty;
    }


    private int getRandomMoveTime() {
        int upperBound = this.difficulty.getMoveTimeUpperBound();
        return (new Random(System.currentTimeMillis()).nextInt(10) + upperBound) * 500;
    }


    private String generateRandomAnswer(){
        int rdm = this.getRandomNumberInRange(3, 8);
        String str = "";
        for (int i=0; i < rdm; i++){
            str += (char) ('a' + this.getRandomNumberInRange(0, 26));
        }
        return str;
    }
        /*
    Returns a list of answers for each category and starting with the respective character.
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
                answers.add(new Answer(null, c, this.getRandomMoveTime()));
            }else{
                int randomInt = random.nextInt(results.size());
                int randomIntForAiFailure = getRandomNumberInRange(1, 100);
                if (randomIntForAiFailure >= thresholdFailure) {
                    answers.add(new Answer(this.generateRandomAnswer(), c, this.getRandomMoveTime()));
                }else {
                    answers.add(new Answer(results.get(randomInt), c, this.getRandomMoveTime()));
                }
            }
        }
        return answers;
    }

        /*
    Returns a single answer for each category and starting with the respective character.
    */
    @Override
    public Answer getAnswer(Category category, char initialChar) {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) / 1000 < this.difficulty.getMoveTimeUpperBound() / 2) {

        }
        List<String> results = SparqlController.queryList(category, initialChar);
        Random random = new Random();
        int randomInt = random.nextInt(results.size());
        return new Answer(results.get(randomInt), category, this.getRandomMoveTime());
    }

    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
