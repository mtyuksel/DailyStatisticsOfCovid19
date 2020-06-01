package edu.erciyes.models;

public class CountryDataModel {
    private String countriesAndTerritories, geoId, countryterritoryCode, continentExp;
    private int totalDeaths, totalCases,newCases, newDeaths, popData2018;
    private float mortalityRate, attackRate;

    public CountryDataModel(String countriesAndTerritories, String geoId, String countryterritoryCode, String continentExp, int totalDeaths, int totalCases, int newCases, int newDeaths, int popData2018, float mortalityRate, float attackRate) {
        this.countriesAndTerritories = countriesAndTerritories;
        this.geoId = geoId;
        this.countryterritoryCode = countryterritoryCode;
        this.continentExp = continentExp;
        this.totalDeaths = totalDeaths;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
        this.popData2018 = popData2018;
        this.mortalityRate = mortalityRate;
        this.attackRate = attackRate;
    }

    public String getCountriesAndTerritories() {
        return countriesAndTerritories;
    }

    public void setCountriesAndTerritories(String countriesAndTerritories) {
        this.countriesAndTerritories = countriesAndTerritories;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public String getCountryterritoryCode() {
        return countryterritoryCode;
    }

    public void setCountryterritoryCode(String countryterritoryCode) {
        this.countryterritoryCode = countryterritoryCode;
    }

    public String getContinentExp() {
        return continentExp;
    }

    public void setContinentExp(String continentExp) {
        this.continentExp = continentExp;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getPopData2018() {
        return popData2018;
    }

    public void setPopData2018(int popData2018) {
        this.popData2018 = popData2018;
    }

    public float getMortalityRate() {
        return mortalityRate;
    }

    public void setMortalityRate(float mortalityRate) {
        this.mortalityRate = mortalityRate;
    }

    public float getAttackRate() {
        return attackRate;
    }

    public void setAttackRate(float attackRate) {
        this.attackRate = attackRate;
    }
}
