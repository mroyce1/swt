package application;

import domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Main class that is called when the application starts.
 *
 */

public class Main {

    /**
     * main method that is called when the application starts.
     * Reads necessary input from user and starts a game.
     * @param args not needed
     */

    public static void main(String[] args){
//        String playerName = readName();
//        Difficulty difficulty = readDifficulty();
//        int maxRounds = readMaxRounds();
        int maxRounds = 10;
        Difficulty difficulty = Difficulty.HARD;
        List<Category> categories = new ArrayList<Category>();
        categories.add(Category.CITY);
        categories.add(Category.COUNTRY);
        categories.add(Category.RIVER);
//        categories.add(Category.SOFTWARE);
//        categories.add(Category.ANIMAL);
        categories.add(Category.DRUG);


        Player p1 = new Human("human", 0);
        Player p2 = new AI("bla AI", 0, difficulty);
        Game g  = new Game(p1, p2, 10, difficulty, categories);
        Player winner = g.start();
        System.out.println("Winner is: " + winner);
    }

    /**
     * Reads the player's name from the command line and returns it.
     * @return String
     */

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

    /**
     * Reads the desired difficulty from the command line and returns it.
     * @return Difficulty
     */

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

    /**
     * Reads the desired categories from the command line and returns them as a list.
     * @return List<Category>
     */

    private static List<Category> readCategories(){
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        String categories = null;
        String catString = "";
        int i=0;
        List<Category> catList = Arrays.asList(Category.values());
        for(Category c : catList){
            catString += c+"(" + (i) + "),";
            i++;
        }
        try{
            while(categories == null) {
                System.out.println("Please select the categories' numbers separated by comma:");
                System.out.println(catString);
                categories = bufR.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        List<Category> returnList = new ArrayList<Category>();
        for(String s : categories.split(",")){
            returnList.add(catList.get(Integer.parseInt(s)));
        }
        System.out.println(returnList);
        return returnList;
    }


    /**
     * Reads the desired number of maximum rounds from the command line and returns the value.
     * @return int
     */

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
