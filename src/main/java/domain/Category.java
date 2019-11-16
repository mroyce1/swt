package domain;

import application.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum Category {
    COUNTRY, RIVER, CITY, ANIMAL, COMPANY, SOCCERPLAYER, SCIENTIST, DRUG, SOFTWARE;

    public String getListQuery() {
        switch (this) {
            case COUNTRY:
                return Settings.countriesWikiData;
            case CITY:
                return Settings.citiesDBPedia;
            case ANIMAL:
                return Settings.animalsDBPedia;
            case COMPANY:
                return Settings.companiesDBPedia;
            case SOCCERPLAYER:
                return Settings.soccerplayersDBPedia;
            case SCIENTIST:
                return Settings.scientistDBPedia;
            case DRUG:
                return Settings.drugDBPedia;
            case SOFTWARE:
                return Settings.softwareDBPedia;
        }
        return Settings.riversDBPedia;
    }

    public String getValidateQuery() {
        switch (this) {
            case COUNTRY:
                return Settings.citiesDBPedia;
            case CITY:
                return Settings.doesCityExistQuery;
            case ANIMAL:
                return Settings.doesAnimalExistQuery;
            case COMPANY:
                return Settings.doesCompanyExistQuery;
            case SOCCERPLAYER:
                return Settings.doesSoccerPlayerExistQuery;
            case SCIENTIST:
                return Settings.doesScientistExistQuery;
            case DRUG:
                return Settings.doesDrugExistQuery;
            case SOFTWARE:
                return Settings.doesSoftwareExistQuery;
        }
        return Settings.doesRiverExistQuery;
    }

    public List<String> getSuffixes() {
        switch (this) {
            case COUNTRY:
                return new ArrayList<String>() {{
                    add(" Country");
                    add(" country");
                    add(" (Country)");
                    add(" (country)");
                }};
            case CITY:
                return new ArrayList<String>() {{
                    add(" City");
                    add(" city");
                    add(" (City)");
                    add(" (city)");
                }};
            case ANIMAL:
                return new ArrayList<String>() {{
                    add(" Animal");
                    add(" animal");
                    add(" (Animal)");
                    add(" (animal)");
                }};
            case COMPANY:
                return new ArrayList<String>() {{
                    add(" Company");
                    add(" company");
                    add(" (Company)");
                    add(" (company)");
                }};
            case SOCCERPLAYER:
                return new ArrayList<String>() {{
                    add(" SoccerPlayer");
                    add(" soccerplayer");
                    add(" (SoccerPlayer)");
                    add(" (soccerplayer)");
                }};
            case SCIENTIST:
                return new ArrayList<String>() {{
                    add(" Scientist");
                    add(" scientist");
                    add(" (Scientist)");
                    add(" (scientist)");
                }};
            case DRUG:
                return new ArrayList<String>() {{
                    add(" Drug");
                    add(" drug");
                    add(" (Drug)");
                    add(" (drug)");
                }};
            case SOFTWARE:
                return new ArrayList<String>() {{
                    add(" Software");
                    add(" software");
                    add(" (Software)");
                    add(" (software)");
                }};
        }
        return new ArrayList<String>() {{
            add(" River");
            add(" river");
            add(" (River)");
            add(" (river)");
        }};
    }

    public String getEndpoint() {
        if (this == COUNTRY) {
            return Settings.wikiDataEndpoint;
        }
        return Settings.dbpediaEndpoint;
    }
}
