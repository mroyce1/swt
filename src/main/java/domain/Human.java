package domain;

import java.util.List;

public class Human extends Player {
    public Human(String name, int points) {
        super(name, points, PlayerType.HUMAN);
    }

    public String getMove(char c, List<Category> categories) {
//        return "human blabla";
        return null;
    }
}
