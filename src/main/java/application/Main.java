package application;

import domain.*;
import ui.InitUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
//        javafx.application.Application.launch(InitUI.class);

//        String playerName = readName();
//        Difficulty difficulty = readDifficulty();
//        int maxRounds = readMaxRounds();
        List<Category> categories = new ArrayList<Category>();
        categories.add(Category.CITY);
        categories.add(Category.COUNTRY);
//        categories.add(Category.RIVER);

        Player p1 = new Human("human", 0);
        Player p2 = new AI("bla AI", 0, Difficulty.HARD);
        Game g  = new Game(p1, p2, 10, Difficulty.HARD, categories);
        g.start();
    }

    private static String readName(){
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        String name = null;
        try{
            while(name == null) {
                System.out.println("Please enter you name: ");
                name = bufR.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        return name;
    }

    private static Difficulty readDifficulty(){
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        String difficulty = "";
        try{
            while(!difficulty.matches("(?i)EASY|MEDIUM|HARD")) {
                System.out.println("Please specify a difficulty [EASY, MEDIUM, HARD]: ");
                difficulty = bufR.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        return Difficulty.getDifficulty(difficulty.toUpperCase());
    }

    private static String readCategories(){
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        String categories = null;
        try{
            while(categories == null) {
                System.out.println("Please enter a list of categories: ");
                categories = bufR.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        return categories;
    }

    private static int readMaxRounds(){
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        String rounds = "";
        try{
            while(!rounds.matches("\\d+")) {
                System.out.println("Please enter a number of rounds [1-26]: ");
                rounds = bufR.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        return Integer.parseInt(rounds);
    }
}
