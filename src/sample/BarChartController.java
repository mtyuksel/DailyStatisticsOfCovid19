package sample;


import edu.erciyes.models.RecordModel;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BarChartController {
    ArrayList<RecordModel> allRecords;

    @FXML
    private StackedBarChart barCase, barDeath;
    @FXML
    private DatePicker startDate, endDate;

    @FXML
    public void btnFilterClick(){
        LocalDate start = startDate.getValue();
        Instant instantStart = Instant.from(start.atStartOfDay(ZoneId.systemDefault()));
        Date beginDate = Date.from(instantStart);

        LocalDate end = endDate.getValue();
        Instant instantEnd = Instant.from(end.atStartOfDay(ZoneId.systemDefault()));
        Date endDate = Date.from(instantEnd);

        barDeath.getData().clear();
        barCase.getData().clear();

        fillCaseChart(beginDate, endDate);
        fillDeathChart(beginDate, endDate);
    }

    public void setData(ArrayList<RecordModel> records){
        allRecords = records;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 04);
        calendar.set(Calendar.DATE, 03);
        Date date = calendar.getTime();
        Instant instant = Instant.now();
        Date dateNow = Date.from(instant);

        fillCaseChart(date, dateNow);
        fillDeathChart(date, dateNow);
    }

    public void fillCaseChart(Date startDate, Date endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        int sumOfAsia = 0, sumOfEurope = 0, sumOfAfrica = 0, sumOfAmerica = 0, sumOfOceania = 0;

        XYChart.Series<String, Number> seriesAsia =
                new XYChart.Series<String, Number>();
        seriesAsia.setName("Asia");
        XYChart.Series<String, Number> seriesEurope =
                new XYChart.Series<String, Number>();
        seriesEurope.setName("Europe");
        XYChart.Series<String, Number> seriesAfrica =
                new XYChart.Series<String, Number>();
        seriesAfrica.setName("Africa");
        XYChart.Series<String, Number> seriesAmerica =
                new XYChart.Series<String, Number>();
        seriesAmerica.setName("America");
        XYChart.Series<String, Number> seriesOceania =
                new XYChart.Series<String, Number>();
        seriesOceania.setName("Oceania");

        while(startDate.compareTo(endDate) <= 0)
        {
            for (int i = 0; i < allRecords.size(); i++ )
            {
                RecordModel dailyCase = allRecords.get(i);
                System.out.println(dailyCase.dateRep);
                System.out.println(dailyCase.geoId);
                System.out.println(formatter.format(startDate));
                System.out.println(formatter.format(dailyCase.dateRep));
                if (formatter.format(dailyCase.dateRep).matches(formatter.format(startDate)))
                {
                    switch (dailyCase.continentExp)
                    {
                        case "Asia": sumOfAsia += dailyCase.cases; break;
                        case "Europe": sumOfEurope += dailyCase.cases; break;
                        case "America": sumOfAmerica += dailyCase.cases; break;
                        case "Africa": sumOfAfrica += dailyCase.cases; break;
                        case "Oceania": sumOfOceania += dailyCase.cases; break;
                    }
                }
            }
            if (!(sumOfAfrica == 0 || sumOfAmerica == 0 || sumOfAsia == 0 || sumOfEurope == 0 || sumOfOceania == 0 ))
            {
                seriesAfrica.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfAfrica));
                seriesAmerica.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfAmerica));
                seriesAsia.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfAsia));
                seriesEurope.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfEurope));
                seriesOceania.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfOceania));
            }
            startDate = Date.from(startDate.toInstant().plus( 1 , ChronoUnit.DAYS ));
        }
        barCase.getData().addAll(seriesAfrica, seriesAmerica, seriesAsia, seriesEurope, seriesOceania);
    }

    public void fillDeathChart(Date startDate, Date endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        int sumOfAsia = 0, sumOfEurope = 0, sumOfAfrica = 0, sumOfAmerica = 0, sumOfOceania = 0;

        XYChart.Series<String, Number> seriesAsia =
                new XYChart.Series<String, Number>();
        seriesAsia.setName("Asia");
        XYChart.Series<String, Number> seriesEurope =
                new XYChart.Series<String, Number>();
        seriesEurope.setName("Europe");
        XYChart.Series<String, Number> seriesAfrica =
                new XYChart.Series<String, Number>();
        seriesAfrica.setName("Africa");
        XYChart.Series<String, Number> seriesAmerica =
                new XYChart.Series<String, Number>();
        seriesAmerica.setName("America");
        XYChart.Series<String, Number> seriesOceania =
                new XYChart.Series<String, Number>();
        seriesOceania.setName("Oceania");

        while(startDate.compareTo(endDate) <= 0)
        {
            for (int i = 0; i < allRecords.size(); i++ )
            {
                RecordModel dailyCase = allRecords.get(i);
                System.out.println(dailyCase.dateRep);
                System.out.println(dailyCase.geoId);
                System.out.println(formatter.format(startDate));
                System.out.println(formatter.format(dailyCase.dateRep));
                if (formatter.format(dailyCase.dateRep).matches(formatter.format(startDate)))
                {
                    switch (dailyCase.continentExp)
                    {
                        case "Asia": sumOfAsia += dailyCase.deaths; break;
                        case "Europe": sumOfEurope += dailyCase.deaths; break;
                        case "America": sumOfAmerica += dailyCase.deaths; break;
                        case "Africa": sumOfAfrica += dailyCase.deaths; break;
                        case "Oceania": sumOfOceania += dailyCase.deaths; break;
                    }
                }
            }
            if (!(sumOfAfrica == 0 || sumOfAmerica == 0 || sumOfAsia == 0 || sumOfEurope == 0 || sumOfOceania == 0 ))
            {
                seriesAfrica.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfAfrica));
                seriesAmerica.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfAmerica));
                seriesAsia.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfAsia));
                seriesEurope.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfEurope));
                seriesOceania.getData().add(new XYChart.Data<String, Number>(formatter.format(startDate), sumOfOceania));
            }
            startDate = Date.from(startDate.toInstant().plus( 1 , ChronoUnit.DAYS ));
        }
        barDeath.getData().addAll(seriesAfrica, seriesAmerica, seriesAsia, seriesEurope, seriesOceania);
    }
}
