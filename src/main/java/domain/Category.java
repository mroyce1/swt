package domain;

import application.Settings;

import java.util.Set;

public enum Category {
    COUNTRY, RIVER, CITY, ANIMAL, COMPANY;

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
        }
        return Settings.doesRiverExistQuery;
    }

    public String getEndpoint() {
        if (this == COUNTRY || this == RIVER) {
            return Settings.wikiDataEndpoint;
        }
        return Settings.dbpediaEndpoint;
    }
}
