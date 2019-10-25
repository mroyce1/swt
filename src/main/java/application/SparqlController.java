package application;

import domain.Category;
import org.apache.jena.query.*;



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


    //for testing
    public static void main(String[] args){
//        queryList(Category.CITY, 'A');
//        boolean answer = validateAnswer(Category.CITY, "Berlin");
//        System.out.println(answer);
    }
}

