package application;

import domain.Category;
import org.apache.jena.query.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class SparqlController {


    /*
    gets an endpoint (DBPedia, WikiData...), the initial Char and the Category as input
    1. Gets query string from Settings.java
    2. Get endpoint for category
    3. replaces placeholder in query string with initial char
    4. retrieves results
    5. etc.
     */

    public static List<String> queryList(Category category, char initialChar) {
        List<String> results;
        if (category.getEndpoint().equals(Settings.dbpediaEndpoint)) {
            results = queryDBPedia(category, initialChar);
        } else {
            results = queryWikidata(category, initialChar);
        }
        return results;
    }

    public static List<String> queryDBPedia(Category category, char initialChar) {
        String queryString = String.format(category.getListQuery(), initialChar);
        String endpoint = category.getEndpoint();
        Query query = QueryFactory.create(queryString);
        QueryExecution qExe = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet resultSet = qExe.execSelect();
        List<String> results = new ArrayList<String>();
        while (resultSet.hasNext()) {
            QuerySolution sol = resultSet.next();
            String s = sol.get(category.getQueryVariable()).toString();
//            String s = resultSet.next().toString();
//            int start = s.indexOf("\"") + 1;
//            int end = s.indexOf("\"@en ");
//            s = s.substring(start, end);
//            if(category == Category.RIVER){
//                s = s.replaceAll(" River", "");
//            }
            results.add(s);
        }
        return results;
    }

    public static Boolean validateAnswer(Category category, String answer) {
        if (answer == null || answer.equals("")){
            return false;
        }
        //workaround until ASK COUNTRY query works
        if (category == Category.COUNTRY){
            List<String> countries = queryWikidata(category, answer.charAt(0));
            return countries.contains(answer);
        }
        String queryString = String.format(category.getValidateQuery(), answer);
        String endpoint = category.getEndpoint();
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        return qexec.execAsk();
    }


    public static List<String> queryWikidata(Category category, char initialChar) {
        String urlString = Settings.wikiDataEndpoint + category.getListQuery();
        if (urlString.contains(" "))
            urlString = urlString.replace(" ", "%20");
        BufferedReader bufR = null;
        String line = null;
        StringBuffer responseContent = new StringBuffer();
        List<String> countries = new ArrayList<String>();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = null;
//            System.out.println(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(60000);
            int status = connection.getResponseCode();
            if (status != 200) {
                bufR = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = bufR.readLine()) != null) {
                    responseContent.append(line);
                    responseContent.append("\n");
                }
                bufR.close();
                System.out.println("Error: " + status);
                System.out.println(responseContent.toString());
                return null;
            } else {
                bufR = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = bufR.readLine()) != null) {
                    if (line.contains("https://en.wikipedia.org/wiki/")) {
                        int start = line.indexOf("wiki/") + 5;
                        int end = line.indexOf("</uri>");
                        String country = line.substring(start, end).replace("_", " ");
                        if (country.charAt(0) == initialChar){
                            countries.add(country);
                        }
                    }
                }
                bufR.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries;
    }


    public static void main(String[] args) {
        queryList(Category.CITY, 'A');
    }
}

