package sample;


import edu.erciyes.helpers.OperationHelpers;
import edu.erciyes.models.RecordModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Controller {
    String xml;
    ArrayList<RecordModel> allRecords;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button btnSubmit, btnDataTable, btnLineChart, btnBarChart ;
    @FXML
    private Label lblEnterXml;
    @FXML
    private Label lblError;
    @FXML
    private TextField txtXml;
    @FXML
    public void onSubmit(){
        xml = txtXml.getText();

        int totalCase = 0, totalDeath = 0, counter = 0;

        if (OperationHelpers.isXmlValid(xml))   // Check is user input an xml url.
        {
            InputStream response = OperationHelpers.getHttpResponse(xml);

            btnDataTable.setVisible(true);
            btnLineChart.setVisible(true);
            btnBarChart.setVisible(true);
            lblError.setTextFill(Color.GREEN);
            lblError.setText("Please click one of the buttons bellow to chose graph type.");
        }
        else
        {
            String errorMessage = "*Please, check your url format. It has to contain an xml file. E.g: (http://www.url.com/file.xml)";
            lblError.setText(errorMessage);
        }
    }
    @FXML
    public void btnDataTableClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("data_table.fxml"));
        Parent root = (Parent) loader.load();

        DataTableController tableController = loader.<DataTableController>getController();
        InputStream response = OperationHelpers.getHttpResponse(xml);
        allRecords = OperationHelpers.parseXmlString(response);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        tableController.setData(allRecords);
        stage.show();
    }
    @FXML
    public void btnLineChartClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("line_chart.fxml"));
        Parent root = (Parent) loader.load();

        LineChartController chartController = loader.<LineChartController>getController();
        InputStream response = OperationHelpers.getHttpResponse(xml);
        allRecords = OperationHelpers.parseXmlString(response);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        chartController.setData(allRecords);
        stage.show();
    }

    @FXML
    public void btnBarChartClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bar_chart.fxml"));
        Parent root = (Parent) loader.load();

        BarChartController chartController = loader.<BarChartController>getController();
        InputStream response = OperationHelpers.getHttpResponse(xml);
        allRecords = OperationHelpers.parseXmlString(response);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        chartController.setData(allRecords);
        stage.show();
    }
}
