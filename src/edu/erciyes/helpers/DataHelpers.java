package edu.erciyes.helpers;

import edu.erciyes.models.RecordModel;
import edu.erciyes.models.CountryDataModel;
import java.util.ArrayList;

public class DataHelpers {
    public static ArrayList<CountryDataModel> getCountryData(ArrayList<RecordModel> allRecords){
        int totalCase = 0, totalDeath = 0, counter = 0;
        boolean isLastDay = true;
        ArrayList<CountryDataModel> totalDataList = new ArrayList<CountryDataModel>();

        for (int i=0; i < allRecords.size(); i++)
        {
            RecordModel referansCountry = allRecords.get(counter);
            RecordModel currCountry = allRecords.get(i);

            if (currCountry.geoId.equals(referansCountry.geoId))
            {
                totalDeath += currCountry.deaths;
                totalCase += currCountry.cases;
            }
            else
            {
                RecordModel country = allRecords.get(i - 1);

                float mortalityRate = (float) totalDeath / totalCase;
                float attackRate = (float) totalCase / country.popData2018;

                CountryDataModel total = new CountryDataModel(country.countriesAndTerritories, country.geoId, country.countryterritoryCode, country.continentExp, totalDeath + referansCountry.deaths, totalCase + referansCountry.cases, referansCountry.cases, referansCountry.deaths, country.popData2018, mortalityRate, attackRate);
                totalDataList.add(total);

                counter = i;
                totalCase = 0;
                totalDeath = 0;
            }
        }
        return totalDataList;
    }
}
