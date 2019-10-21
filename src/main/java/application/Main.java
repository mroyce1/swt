package application;

import domain.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
//        SparqlController s = new SparqlController();
//        s.query(Settings.dbpediaEndpoint, Settings.citiesDBPedia);
        Player p1 = new Human("bla", 0);
        Player p2 = new AI("bla", 0);
        Game g  = new Game(p1, p2, 10, Difficulty.HARD, new ArrayList<String>());
        g.playRound();

    }
}
