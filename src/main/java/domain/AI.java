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
        int upperBound = this.difficulty.getVal();
        return (new Random().nextInt(10) + upperBound) * 1000;
    }

    @Override
    public List<Answer> getListOfMoves(List<Category> categories, char initialChar) {
        List<Answer> answers = new ArrayList<Answer>();
        Random random = new Random();
        for (Category c : categories) {
            List<String> results = SparqlController.queryList(c, initialChar);
            if (results.isEmpty()){
                answers.add(new Answer(null, this.getRandomMoveTime()));
            }else{
                int randomInt = random.nextInt(results.size());
                answers.add(new Answer(results.get(randomInt), this.getRandomMoveTime()));
            }
        }
        return answers;
    }

    @Override
    public Answer getMove(Category category, char initialChar) {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) / 1000 < this.difficulty.getVal() / 2) {

        }
        List<String> results = SparqlController.queryList(category, initialChar);
        Random random = new Random();
        int randomInt = random.nextInt(results.size());
        return new Answer(results.get(randomInt), this.getRandomMoveTime());
    }
}
