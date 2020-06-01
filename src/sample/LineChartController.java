package sample;

import edu.erciyes.helpers.DataHelpers;
import edu.erciyes.models.CountryDataModel;
import edu.erciyes.models.RecordModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class LineChartController {
    ArrayList<RecordModel> allRecords;

    @FXML
    private LineChart caseChart, deathChart;
    @FXML
    private ListView<String> countryList;

    public void setData(ArrayList<RecordModel> records){
        allRecords = records;

        ArrayList<CountryDataModel> countries = DataHelpers.getCountryData(allRecords);
        fillList(countries);
    }

    public void fillList(ArrayList<CountryDataModel> countries){
        for (CountryDataModel country :
                countries) {
            countryList.getItems().add(country.getCountriesAndTerritories().toString());
        }
        countryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void fillCharts(ObservableList<String> selectedCountries){
        XYChart.Series seriesCase = new XYChart.Series();
        XYChart.Series seriesDeath = new XYChart.Series();

        caseChart.setCreateSymbols(false);
        caseChart.setStyle("-fx-background-color: lightgray");
        deathChart.setCreateSymbols(false);
        deathChart.setStyle("-fx-background-color: lightgray");

        boolean isDifferentCountry = true;
        int dailyCaseSum = 0, dailyDeathsSum = 0;

        Collections.reverse(allRecords);

        for (int i = 0; i < allRecords.size(); i++)
        {
            RecordModel dailyCase = allRecords.get(i);
            RecordModel tomorrowCase;

            if (!(i == allRecords.size() - 1))
                tomorrowCase = allRecords.get(i + 1);
            else
                tomorrowCase = allRecords.get(allRecords.size() - 1);

            if (isDifferentCountry)
            {
                seriesCase = new XYChart.Series();
                seriesDeath = new XYChart.Series();
                isDifferentCountry = false;
            }

            if (selectedCountries.contains(dailyCase.countriesAndTerritories))
            {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

                dailyCaseSum += dailyCase.cases;
                dailyDeathsSum += dailyCase.deaths;

                seriesCase.getData().add(new XYChart.Data(formatter.format(dailyCase.dateRep), dailyCaseSum));
                seriesDeath.getData().add(new XYChart.Data(formatter.format(dailyCase.dateRep), dailyDeathsSum));

                if (!dailyCase.geoId.matches(tomorrowCase.geoId))
                {
                    seriesCase.setName(dailyCase.countriesAndTerritories);
                    seriesDeath.setName(dailyCase.countriesAndTerritories);

                    caseChart.getData().addAll(seriesCase);
                    deathChart.getData().addAll(seriesDeath);

                    dailyCaseSum = 0;
                    dailyDeathsSum = 0;
                    isDifferentCountry = true;
                }
            }
        }//for
    }

    @FXML
    public void submitCountry(){
        caseChart.getData().clear();
        deathChart.getData().clear();
        ObservableList<String> selectedList = countryList.getSelectionModel().getSelectedItems();
        fillCharts(selectedList);
    }
}
