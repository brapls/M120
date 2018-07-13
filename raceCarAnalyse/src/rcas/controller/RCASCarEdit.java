package rcas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import rcas.model.RaceCar;
import rcas.model.TireModel;

public class RCASCarEdit {

    @FXML
    public TextField CarName;
    @FXML
    public ComboBox<String> TireFront;
    @FXML
    public ComboBox<String> TireRear;
    @FXML
    public TextField CwFrontLeft;
    @FXML
    public TextField CwFrontRight;
    @FXML
    public TextField CwRearLeft;
    @FXML
    public TextField CwRearRight;
    @FXML
    public TextField FrontTrack;
    @FXML
    public TextField RearTrack;
    @FXML
    public TextField Wheelbase;
    @FXML
    public TextField CogHeight;
    @FXML
    public TextField FrontRollDist;
    @FXML
    public Button btnSave;



    private RaceCar raceCar;

    @FXML
    public void initialize() {
    }

    public void SetCarForEdit(RaceCar car) {
        raceCar = car;
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

    public void SetValuesIntoFields() {
        String carName = raceCar.getName();
        if (carName == null) carName = "";
        CarName.setText(carName);

        ObservableList<String> tires = getTireNameList();
        TireFront.setItems(tires);
        TireModel fatm = raceCar.getFrontAxleTireModel();
        if (fatm != null)
        TireFront.getSelectionModel().select(fatm.getName());

        TireRear.setItems(tires);
        TireModel ratm = raceCar.getRearAxleTireModel();
        if (ratm != null)
        TireRear.getSelectionModel().select(ratm.getName());

        CwFrontLeft.setText(raceCar.cornerWeightFLProperty().getValue().toString());
        CwFrontRight.setText(raceCar.cornerWeightFRProperty().getValue().toString());
        CwRearLeft.setText(raceCar.cornerWeightRLProperty().getValue().toString());
        CwRearRight.setText(raceCar.cornerWeightRRProperty().getValue().toString());

        FrontTrack.setText(raceCar.getFrontTrack().toString());
        RearTrack.setText(raceCar.getRearTrack().toString());
        Wheelbase.setText(raceCar.getWheelbase().toString());
        CogHeight.setText(raceCar.getCogHeight().toString());
        FrontRollDist.setText(raceCar.getFrontRollDist().toString());
    }

    public save() {

    }
}
