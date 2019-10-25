package application;

import domain.Category;
import org.apache.jena.query.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class SparqlController {


    /*
    gets an endpoint (DBPedia, WikiData...), the initial Char and the Category as input
    1. Gets query string from Settings.java
    2. Get endpoint for category
    3. replaces placeholder in query string with initial char
    4. retrieves results
    5. etc.
     */

    public static void queryList(Category category, char initialChar) {
        String queryString = String.format(category.getListQuery(), initialChar);
        String endpoint = category.getEndpoint();
        Query query = QueryFactory.create(queryString);
        QueryExecution qExe = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
    }


    public static Boolean validateAnswer(Category category, String answer) {
        String queryString = String.format(category.getValidateQuery(), answer);
        String endpoint = category.getEndpoint();
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        return qexec.execAsk();
    }


    public static void queryWikidata(Category category) {
        String urlString = "https://query.wikidata.org/sparql?query=SELECT ?country ?countryLabel ?article WHERE {" +
                "?country wdt:P31 wd:Q3624078 ." +
                "?article schema:about ?country ." +
                "?article schema:isPartOf <https://en.wikipedia.org/>." +
                "SERVICE wikibase:label {" +
                "bd:serviceParam wikibase:language \"en\"" +
                "}" +
                "}";
        if (urlString.contains(" "))
            urlString = urlString.replace(" ", "%20");
        BufferedReader bufR = null;
        String line = null;
        StringBuffer responseContent = new StringBuffer();
//        urlString = "https://query.wikidata.org/sparql?query=SELECT%20?dob%20WHERE%20{wd:Q42%20wdt:P569%20?dob.}";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = null;
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
            } else {
                bufR = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = bufR.readLine()) != null) {
                    responseContent.append(line);
                    responseContent.append("\n");
                }
                bufR.close();
            }
            System.out.println(responseContent.toString());
            System.out.println(status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        queryWikidata(Category.COUNTRY);
//        queryList(Category.CITY, 'A');
//        boolean answer = validateAnswer(Category.CITY, "Berlin");
//        System.out.println(answer);
    }
}

