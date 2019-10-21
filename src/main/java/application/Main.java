package application;

public class Main {

    public static void main(String[] args){
        SparqlController s = new SparqlController();
        s.query(Settings.dbpediaEndpoint, Settings.citiesDBPedia);
    }
}
