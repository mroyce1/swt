package domain;

import application.SparqlController;

import java.util.List;

public class AI extends Player {
    private SparqlController sparqlController;
    private Difficulty difficulty;

    public AI(String name, int points, Difficulty difficulty) {
        super(name, points, PlayerType.AI);
        this.sparqlController = new SparqlController();
        this.difficulty = difficulty;
    }

    public String getMove(char c, List<Category> categories){
        return null;
    }
}
