package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Human extends Player {
    public Human(String name, int points) {
        super(name, points, PlayerType.HUMAN);
    }

    public List<String> getListOfMoves(char c, List<Category> categories) {
//        return "human blabla";
        return new ArrayList<String>();
    }


    public String getMove(Category category, char initialChar) {
        String answer = this.readAnswer(category, initialChar);
        System.out.println("answer is " + answer);
        return answer;
    }



    private String readAnswer(Category category, char initialChar){
        BufferedReader bufR = new BufferedReader(new InputStreamReader(System.in));
        String answer = null;
        String matcher = "^"+initialChar;
        try{
            while(answer == null) {
                System.out.println(String.format("Please enter a %s beginning with %c: ", category, initialChar));
                answer = bufR.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        return answer;
    }
}
