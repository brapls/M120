package rcas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import rcas.model.MagicFormulaTireModel;
import rcas.model.RaceCar;
import rcas.model.RaceCarSelectionItem;
import rcas.model.TireModel;
import rcas.util.DataUtil;

import javax.xml.crypto.Data;

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
    @FXML
    public Button btnCancel;



    private RaceCar raceCar;

    @FXML
    public void initialize() {
        btnSave.setOnAction(e -> save());
        btnCancel.setOnAction(e -> ((Stage) btnCancel.getScene().getWindow()).close());
    }

    void SetCarForEdit(RaceCar car) {
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

    void SetValuesIntoFields() {
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

    private void save() {
        String errorText = "";

        if (CarName.getText().equals("")) {
            errorText += "Please enter a name\n";
        }

        double cwfl = 0;
        String cwflErrorText = "Enter for corner weight front left a number between 50 and 1000\n";
        try {
            cwfl = Double.valueOf(CwFrontLeft.getText());
            if(cwfl <= 50 || cwfl >= 1000) {
                errorText += cwflErrorText;
            }
        } catch (Exception ex) {
            errorText += cwflErrorText;
        }

        double cwfr = 0;
        String cwfrErrorText = "Enter for corner weight front right a number between 50 and 1000\n";
        try {
            cwfr = Double.valueOf(CwFrontRight.getText());
            if(cwfr <= 50 || cwfr >= 1000) {
                errorText += cwfrErrorText;
            }
        } catch (Exception ex) {
            errorText += cwfrErrorText;
        }

        double cwrl = 0;
        String cwrlErrorText = "Enter for corner weight rear left a number between 50 and 1000\n";
        try {
            cwrl = Double.valueOf(CwRearLeft.getText());
            if(cwrl <= 50 || cwrl >= 1000) {
                errorText += cwrlErrorText;
            }
        } catch (Exception ex) {
            errorText += cwrlErrorText;
        }

        double cwrr = 0;
        String cwrrErrorText = "Enter for corner weight rear right a number between 50 and 1000\n";
        try {
            cwrr = Double.valueOf(CwRearRight.getText());
            if(cwrr <= 50 || cwrr >= 1000) {
                errorText += cwrrErrorText;
            }
        } catch (Exception ex) {
            errorText += cwrrErrorText;
        }

        double trackF = 0;
        String trackFErrorText = "Enter for front track a number between 0.1 and 5\n";
        try {
            trackF = Double.valueOf(FrontTrack.getText());
            if(trackF <= 0.1 || trackF >= 5) {
                errorText += trackFErrorText;
            }
        } catch (Exception ex) {
            errorText += trackFErrorText;
        }

        double trackR = 0;
        String trackRErrorText = "Enter for rear track a number between 0.1 and 5\n";
        try {
            trackR = Double.valueOf(RearTrack.getText());
            if(trackR <= 0.1 || trackR >= 5) {
                errorText += trackRErrorText;
            }
        } catch (Exception ex) {
            errorText += trackRErrorText;
        }

        double wheelbase = 0;
        String wheelbaseErrorText = "Enter for wheelbase a number between 1 and 15\n";
        try {
            wheelbase = Double.valueOf(Wheelbase.getText());
            if(wheelbase <= 1 || wheelbase >= 15) {
                errorText += wheelbaseErrorText;
            }
        } catch (Exception ex) {
            errorText += wheelbaseErrorText;
        }

        double cogHeight = 0;
        String cogHeightErrorText = "Enter for cogHeight a number between 0.01 and 2\n";
        try {
            cogHeight = Double.valueOf(CogHeight.getText());
            if(cogHeight <= 0.01 || cogHeight >= 2) {
                errorText += cogHeightErrorText;
            }
        } catch (Exception ex) {
            errorText += cogHeightErrorText;
        }


        double frd = 0;
        String frdErrorText = "Enter for front roll dist a number between 0.2 and 0.8\n";
        try {
            frd = Double.valueOf(FrontRollDist.getText());
            if(frd <= 0.01 || frd >= 2) {
                errorText += frdErrorText;
            }
        } catch (Exception ex) {
            errorText += frdErrorText;
        }

        if (!errorText.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText(errorText);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }

        ObservableList<RaceCar> rcs = DataUtil.GetAllRaceCars();
        rcs.remove(raceCar);

        raceCar.setName(CarName.getText());

        MagicFormulaTireModel tiresFront = new MagicFormulaTireModel();
        tiresFront.setName(TireFront.getSelectionModel().getSelectedItem());
        raceCar.setFrontAxleTireModel(tiresFront);

        MagicFormulaTireModel tiresRear = new MagicFormulaTireModel();
        tiresRear.setName(TireRear.getSelectionModel().getSelectedItem());
        raceCar.setRearAxleTireModel(tiresRear);

        raceCar.setCornerWeightFL(cwfl);
        raceCar.setCornerWeightFR(cwfr);
        raceCar.setCornerWeightRL(cwrl);
        raceCar.setCornerWeightRR(cwrr);

        raceCar.setFrontTrack(trackF);
        raceCar.setRearTrack(trackR);
        raceCar.setWheelbase(wheelbase);
        raceCar.setCogHeight(cogHeight);
        raceCar.setFrontRollDist(frd);

        DataUtil.AddRaceCar(raceCar);
        DataUtil.AddRaceCarSelectionItem(new RaceCarSelectionItem(raceCar));

        ((Stage) btnCancel.getScene().getWindow()).close();
    }
}
