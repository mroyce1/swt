package application;

public class Settings {
    public static final String citiesDBPedia =  "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"+
            "SELECT ?cityName WHERE {\n" +
            "   ?s foaf:name ?cityName.\n" +
            "   ?s a dbo:City.\n" +
            "  filter( regex(str(?cityName), \"^B\" ))\n" +
            "}\n" +
            "LIMIT 100\n";

    public static final String countriesDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"+
            "SELECT ?companyName WHERE {\n" +
            " ?c foaf:name ?companyName.\n" +
            " ?c a dbo:Company.\n" +
            " FILTER( regex(str(?companyName) , \"^B\"))\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String animalsDBPedia = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "SELECT DISTINCT ?animalName WHERE {\n" +
            " ?an rdfs:label ?animalName.\n" +
            " ?an a dbo:Animal.\n" +
            " FILTER( regex(str(?animalName) , \"^B\"))\n" +
            " FILTER (lang(?animalName) = 'en')\n" +
            " }\n" +
            " LIMIT 100\n";

    public static final String doesCompanyExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "ASK {\n" +
            " ?c foaf:name \"Adidas\"@en.\n" +
            " ?c a dbo:Company.\n" +
            " }\n";

    public static final String doesAnimalExistQuery = "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "ASK {\n" +
            " ?animal rdfs:label \"Tiger\"@en.\n" +
            " }\n";

    //public static final String countries = "bla";
    
    //dbpedia query of River



    		//widata query of River
    		public static final String Rivers="PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
			"PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
			"PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
			"PREFIX wd: <http://www.wikidata.org/entity/>\n" +
			"SELECT DISTINCT ?riverLabel\n" +
			"WHERE \n" +
			"{\n" +
			"  {?river wdt:P30 wd:Q46.}\n" +
			"  UNION {?river wdt:P30 wd:Q48.}\n" +
			"  UNION {?river wdt:P30 wd:Q15.}\n" +
			"  UNION {?river wdt:P30 wd:Q18.}\n" +
			"  UNION {?river wdt:P30 wd:Q49.}\n" +
			"  UNION {?river wdt:P30 wd:Q538}\n" +
			"  UNION {?river wdt:P30 wd:Q51.}\n" +
			"  ?river wdt:P31 wd:Q4022;\n" +
			"  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }\n" +
			"}\n";	


    public static final String dbpediaEndpoint = "http://dbpedia.org/sparql";
    public static final String wikidataEndpoint="https://query.wikidata.org/sparql";
}
