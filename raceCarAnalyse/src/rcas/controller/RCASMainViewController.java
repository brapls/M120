package rcas.controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import rcas.model.RaceCar;
import rcas.model.RaceCarSelectionItem;
import rcas.util.CorneringAnalyserUtil;
import rcas.util.DataUtil;

import java.io.IOException;
import java.util.ResourceBundle;

public class RCASMainViewController {

	@FXML
	public TableView carsTableView;

	private ObservableList<RaceCarSelectionItem> tableViewData;
	private ObservableList<String> cbValues = FXCollections.observableArrayList("RED", "BROWN", "GREEN", "BLUE");
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
		tableViewData.addAll(DataUtil.GetAllRaceCarSelectionItems());
		//START Selection-Checkboxcolumn
		colSelection.setCellValueFactory((Callback<TableColumn.CellDataFeatures<RaceCarSelectionItem, Boolean>, ObservableValue<Boolean>>) param -> {
			RaceCarSelectionItem raceCarSelectionItem = param.getValue();
			SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(raceCarSelectionItem.getIsSelected());
			booleanProperty.addListener((observable, oldValue, newValue) -> {
				raceCarSelectionItem.setIsSelected(newValue);
				setDiagramForAllSelectedCars();
			});
			return booleanProperty;
		});
		colSelection.setCellFactory((Callback<TableColumn<RaceCarSelectionItem, Boolean>, TableCell<RaceCarSelectionItem, Boolean>>) p -> {
			CheckBoxTableCell<RaceCarSelectionItem, Boolean> cell = new CheckBoxTableCell<>();
			cell.setAlignment(Pos.CENTER);
			return cell;
		});
		//END Selection-Checkboxcolumn
		//START Carname-column
		colCarName.setCellValueFactory(new PropertyValueFactory<>("raceCarName"));
		//END Carname-column

		//START RCASColor-column0

        TableColumn colColour = new TableColumn<>("Color");
		colColour.setCellValueFactory((Callback<TableColumn.CellDataFeatures<RaceCarSelectionItem, String>, ObservableValue<String>>) param -> {
			RaceCarSelectionItem raceCarSelectionItem = param.getValue();
			SimpleStringProperty stringProperty = new SimpleStringProperty(raceCarSelectionItem.getGridColor());
			stringProperty.addListener((observable, oldValue, newValue) -> {
				raceCarSelectionItem.setGridColor(newValue);
				setDiagramForAllSelectedCars();
			});
			return stringProperty;
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

		btnAddNew.setOnAction(e -> openCarEdit(new RaceCar("New Car")));
		btnChange.setOnAction(e -> {
			RaceCarSelectionItem rcsi = (RaceCarSelectionItem)carsTableView.getSelectionModel().getSelectedItem();
			RaceCar rc = rcsi.getRaceCar();
			openCarEdit(rc);
		});
		btnDelete.setOnAction(event -> tableViewData.remove(carsTableView.getSelectionModel().getSelectedItem()));
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

	private void openCarEdit(RaceCar car) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/rcas/view/RCASCarEdit.fxml"));
		GridPane root;
		try {
			root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Car Details");
			stage.setScene(new Scene(root, 725, 530));

			RCASCarEdit controller = fxmlLoader.getController();
			controller.SetCarForEdit(car);
            controller.SetValuesIntoFields();

			Scene scene;
			if (root.getScene() == null) {
				scene = new Scene(root);
				stage.setScene(scene);
			} else {
				stage.setScene(root.getScene());
			}
			stage.show();
		}
		catch (IOException error) {
			error.printStackTrace();
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
