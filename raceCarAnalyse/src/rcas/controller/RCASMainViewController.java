package rcas.controller;

import com.sun.javafx.collections.MappingChange;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import rcas.model.MagicFormulaTireModel;
import rcas.model.RaceCar;
import rcas.model.RaceCarSelectionItem;
import rcas.model.TireModel;
import rcas.util.CorneringAnalyserUtil;
import rcas.util.DataUtil;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class RCASMainViewController {

	@FXML
	public TableView carsTableView;

	ObservableList<RaceCarSelectionItem> tableViewData;
	ObservableList<String> cbValues = FXCollections.observableArrayList("RED", "BROWN", "GREEN", "BLUE");
    @FXML
	private GridPane mainPane;
	@FXML
	private LineChart<Number, Number> mainChart;
	@FXML
	private ResourceBundle resources;
	@FXML
	private Button btnAddNew;
	@FXML
	private Button btnChange;
	@FXML
	private Button btnDelete;

	@FXML
	public void initialize() {

		carsTableView.setEditable(true); // Makes the table view Editable

		TableColumn colSelection = new TableColumn("Selection");
		TableColumn colCarName = new TableColumn("Car Name");


		tableViewData = FXCollections.observableArrayList();
		for(RaceCarSelectionItem rcsi : DataUtil.GetAllRaceCarSelectionItems()){
			tableViewData.add(rcsi);
		}
		//START Selection-Checkboxcolumn
		colSelection.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RaceCarSelectionItem, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<RaceCarSelectionItem, Boolean> param) {
				RaceCarSelectionItem raceCarSelectionItem = param.getValue();
				SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(raceCarSelectionItem.getIsSelected());
				booleanProperty.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
						raceCarSelectionItem.setIsSelected(newValue);
						setDiagramForAllSelectedCars();
					}
				});
				return booleanProperty;
			}
		});
		colSelection.setCellFactory(new Callback<TableColumn<RaceCarSelectionItem, Boolean>,
			TableCell<RaceCarSelectionItem, Boolean>>() {
				@Override
				public TableCell<RaceCarSelectionItem, Boolean> call(TableColumn<RaceCarSelectionItem, Boolean> p) {
					CheckBoxTableCell<RaceCarSelectionItem, Boolean> cell = new CheckBoxTableCell<RaceCarSelectionItem, Boolean>();
					cell.setAlignment(Pos.CENTER);
					return cell;
				}
		});
		//END Selection-Checkboxcolumn
		//START Carname-column
		colCarName.setCellValueFactory(new PropertyValueFactory<>("raceCarName"));
		//END Carname-column

		//START RCASColor-column0

        TableColumn colColour = new TableColumn<>("Color");
		colColour.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RaceCarSelectionItem, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<RaceCarSelectionItem, String> param) {
				RaceCarSelectionItem raceCarSelectionItem = param.getValue();
				SimpleStringProperty stringProperty = new SimpleStringProperty(raceCarSelectionItem.getGridColor());
				stringProperty.addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						raceCarSelectionItem.setGridColor(newValue);
						setDiagramForAllSelectedCars();
					}
				});
				return stringProperty;
			}
		});
        //colColour.setCellValueFactory(new PropertyValueFactory<>("gridColor"));
        colColour.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), cbValues));

		//END RCASColor-column


        carsTableView.getColumns().addAll(colSelection, colCarName, colColour);
		carsTableView.setItems(tableViewData);
/*
		// create race cars and calculate a chart.
		RaceCar myRaceCar_1 = new RaceCar(420, 420, 370, 370);
		TireModel myTireModel_1 = new MagicFormulaTireModel();
		myRaceCar_1.setFrontRollDist(0.55);
		myRaceCar_1.setFrontAxleTireModel(myTireModel_1);
		myRaceCar_1.setRearAxleTireModel(myTireModel_1);
		myRaceCar_1.setName("Car STD (blue)");

		RaceCar myRaceCar_2 = new RaceCar(420, 420, 370, 370);
		TireModel myTireModel_2_Fr = new MagicFormulaTireModel(1.3, 15.2, -1.6, 1.6, 0.000075);
		TireModel myTireModel_2_Rr = new MagicFormulaTireModel(1.3, 15.2, -1.6, 1.8, 0.000075);
		myRaceCar_2.setFrontRollDist(0.55);
		myRaceCar_2.setFrontAxleTireModel(myTireModel_2_Fr);
		myRaceCar_2.setRearAxleTireModel(myTireModel_2_Rr);
		myRaceCar_2.setName("Car MOD (red)");

		CorneringAnalyserUtil corneringUtil = new CorneringAnalyserUtil();

		// show what the toString() methods print out.
		System.out.println(myRaceCar_1.toString());
		System.out.println(myRaceCar_2.toString());
		// show balance, grip, control and stability values of the cars.
		this.printRaceCarCorneringValues(myRaceCar_1, corneringUtil);
		this.printRaceCarCorneringValues(myRaceCar_2, corneringUtil);

		ObservableList<Series<Number, Number>> dataList_1 = corneringUtil.generateMMMChartData(myRaceCar_1);
		mainChart.getData().addAll(dataList_1);
		// Set the style of the series after adding the data to the chart.
		// Otherwise no there is no reference "Node" which leads to a
		// NullPointerException.
		this.setSeriesStyle(dataList_1, ".chart-series-line", "-fx-stroke: blue; -fx-stroke-width: 1px;");

		ObservableList<Series<Number, Number>> dataList_2 = corneringUtil.generateMMMChartData(myRaceCar_2);
		mainChart.getData().addAll(dataList_2);
		this.setSeriesStyle(dataList_2, ".chart-series-line", "-fx-stroke: red; -fx-stroke-width: 1px;");*/
		setDiagramForAllSelectedCars();


	}

	private void setDiagramForAllSelectedCars(){
		mainChart.getData().removeAll();
		mainChart.getData().clear();
		for(RaceCarSelectionItem rcsi : tableViewData){
			if(rcsi.getIsSelected()){
				CorneringAnalyserUtil corneringUtil = new CorneringAnalyserUtil();
				ObservableList<Series<Number, Number>> dataList = corneringUtil.generateMMMChartData(rcsi.getRaceCar());
				mainChart.getData().addAll(dataList);
				this.setSeriesStyle(dataList, ".chart-series-line", "-fx-stroke: "+rcsi.getGridColor() +"; -fx-stroke-width: 1px;");
			}
		}
	}
	private void setSeriesStyle(ObservableList<Series<Number, Number>> dataList_1, String styleSelector,
			String lineStyle) {
		for (Series<Number, Number> curve : dataList_1) {
			curve.getNode().lookup(styleSelector).setStyle(lineStyle);
		}
	}

	private void printRaceCarCorneringValues(RaceCar raceCar, CorneringAnalyserUtil util) {
		System.out.println(String.format(
				"%s: Grip = %.2f G\tBalance = %.2f Nm\tControl(entry) = %.2f Nm/deg\t"
						+ "Control(middle) = %.2f Nm/deg\tStability(entry) = %.2f Nm/deg\t"
						+ "Stability(middle) = %.2f Nm/deg",
				raceCar.getName(), util.getMMMGripValue(raceCar) / 9.81, util.getMMMBalanceValue(raceCar),
				util.getMMMControlValue(raceCar, 0.0, 0.0, 10.0), util.getMMMControlValue(raceCar, -5.0, 20.0, 30.0),
				util.getMMMStabilityValue(raceCar, 0.0, 0.0, 1.0),
				util.getMMMStabilityValue(raceCar, 20.0, -5.0, -4.0)));
	}
}
