package application;

public class Settings {
    protected static final String citiesDBPedia =  "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"+
            "SELECT ?cityName WHERE {\n" +
            "   ?s foaf:name ?cityName.\n" +
            "   ?s a dbo:City.\n" +
            "  filter( regex(str(?cityName), \"^B\" ))\n" +
            "}\n" +
            "LIMIT 100\n";


    protected static final String dbpediaEndpoint = "http://dbpedia.org/sparql";
}
