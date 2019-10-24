package application;

import org.apache.jena.query.*;


public class SparqlController {

    public SparqlController(){

    }

    public void query(String endpoint, String queryString){
        Query query = QueryFactory.create(queryString);
        QueryExecution qExe = QueryExecutionFactory.sparqlService( endpoint, query);
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
    }


    public Boolean validateAnswer(String endpoint, String queryString){
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        return qexec.execAsk();
        }
    }

