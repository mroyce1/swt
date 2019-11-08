package application;

import domain.*;
import ui.InitUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args){
//        javafx.application.Application.launch(InitUI.class);

//        String playerName = readName();
        Difficulty difficulty = readDifficulty();
        int maxRounds = readMaxRounds();
        List<Category> categories = new ArrayList<Category>();
        categories.add(Category.CITY);
        categories.add(Category.COUNTRY);
//        categories.add(Category.RIVER);

        Player p1 = new Human("human", 0);
        Player p2 = new AI("bla AI", 0, difficulty);
        Game g  = new Game(p1, p2, 10, difficulty, categories);
        Player winner = g.start();
        System.out.println("Winner is: " + winner);
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
