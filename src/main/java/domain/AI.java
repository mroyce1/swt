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

    public List<String> getListOfMoves(List<Category> categories, char initialChar) {
        List<String> answers = new ArrayList<String>();
        Random random = new Random();
        for (Category c : categories) {
            List<String> results = SparqlController.queryList(c, initialChar);
            int randomInt = random.nextInt(results.size());
            answers.add(results.get(randomInt));
        }
        return answers;
    }

    public String getMove(Category category, char initialChar) {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) / 1000 < this.difficulty.getVal() / 2) {

        }
        List<String> results = SparqlController.queryList(category, initialChar);
        Random random = new Random();
        int randomInt = random.nextInt(results.size());
        return results.get(randomInt);
    }
}
