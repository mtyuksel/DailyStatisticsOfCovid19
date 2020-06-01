package sample;

import edu.erciyes.helpers.DataHelpers;
import edu.erciyes.models.CountryDataModel;
import edu.erciyes.models.RecordModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class DataTableController {
    ArrayList<RecordModel> allRecords;

    @FXML
    private TableView<CountryDataModel> tableData;
    @FXML
    private TableColumn<CountryDataModel, String> CountryColumn;
    @FXML
    private TableColumn<CountryDataModel, Integer> PopulationColumn, NewDeathsColumn, TotalDeathsColumn, NewCasesColumn, TotalCasesColumn;
    @FXML
    private TableColumn<CountryDataModel, Float> AttackRateColumn, MortalityColumn;


    public void setData(ArrayList<RecordModel> records){
        allRecords = records;
        fillTableView();
    }

    public void fillTableView(){
        ArrayList<CountryDataModel> totalDataList = new ArrayList<CountryDataModel>();
        totalDataList = DataHelpers.getCountryData(allRecords);
        ObservableList<CountryDataModel> list = FXCollections.observableList(totalDataList);

        CountryColumn.setCellValueFactory(new PropertyValueFactory<>("countriesAndTerritories"));
        TotalCasesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCases"));
        NewCasesColumn.setCellValueFactory(new PropertyValueFactory<>("newCases"));
        TotalDeathsColumn.setCellValueFactory(new PropertyValueFactory<>("totalDeaths"));
        NewDeathsColumn.setCellValueFactory(new PropertyValueFactory<>("newDeaths"));
        PopulationColumn.setCellValueFactory(new PropertyValueFactory<>("popData2018"));
        MortalityColumn.setCellValueFactory(new PropertyValueFactory<>("mortalityRate"));
        AttackRateColumn.setCellValueFactory(new PropertyValueFactory<>("attackRate"));

        tableData.setItems(list);
    }
}
