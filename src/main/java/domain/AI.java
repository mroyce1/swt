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
        return (new Random().nextInt(10) + upperBound) * 1000;
    }

        /*
    Returns a list of answers for each category and starting with the respective character.
     */
    @Override
    public List<Answer> getListOfAnswers(List<Category> categories, char initialChar) {
        List<Answer> answers = new ArrayList<Answer>();
        Random random = new Random();
        for (Category c : categories) {
            List<String> results = SparqlController.queryList(c, initialChar);
            if (results.isEmpty()){
                answers.add(new Answer(null, c, this.getRandomMoveTime()));
            }else{
                int randomInt = random.nextInt(results.size());
                answers.add(new Answer(results.get(randomInt), c, this.getRandomMoveTime()));
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
}
