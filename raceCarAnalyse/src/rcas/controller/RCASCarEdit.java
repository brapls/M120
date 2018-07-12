package rcas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import rcas.model.RaceCar;

public class RCASCarEdit {

    @FXML
    public TextField CarName;

    @FXML
    public ChoiceBox TireFront;
    @FXML
    public ChoiceBox TireRear;



    private RaceCar raceCar;

    public void SetCarForEdit(RaceCar car) {
        raceCar = car;

        CarName.setText(car.getName());
        TireFront.setItems(getTireNameList());
        TireFront.selectionModelProperty().set(car.getFrontAxleTireModel().tireName());
    }

    private ObservableList<String> getTireNameList() {
        ObservableList<String> tireList = FXCollections.observableArrayList();
        tireList.add("BFGoodrich");
        tireList.add("Bridgestone");
        tireList.add("Continental");
        tireList.add("Cooper");
        tireList.add("Dunlop");
        tireList.add("Falken");
        tireList.add("Firestone");
        tireList.add("General");
        tireList.add("Goodyear");
        tireList.add("GT Radial");
        tireList.add("Hankook");
        tireList.add("Kumho");
        tireList.add("Michelin");
        tireList.add("Nexen");
        tireList.add("Nitto");
        tireList.add("Nokian");
        tireList.add("Pirelli");
        tireList.add("Sumitomo");
        tireList.add("Toyo");
        tireList.add("Uniroyal");
        tireList.add("Yokohama");

        return tireList;
    }
}
