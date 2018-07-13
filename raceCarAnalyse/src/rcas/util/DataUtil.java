package rcas.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rcas.model.MagicFormulaTireModel;
import rcas.model.RaceCar;
import rcas.model.RaceCarSelectionItem;
import rcas.model.TireModel;

import java.io.*;
import java.util.ArrayList;

public class DataUtil {

    private static Boolean isInitialised = false;
    private static ObservableList<RaceCar> raceCars = FXCollections.observableArrayList();
    private static ObservableList<RaceCarSelectionItem> raceCarSelectionItems =FXCollections.observableArrayList();
    private static void Initialise(){
        raceCars = FXCollections.observableArrayList();

        RaceCar myRaceCar_1 = new RaceCar(420, 420, 370, 370);
        TireModel myTireModel_1 = new MagicFormulaTireModel();
        myRaceCar_1.setFrontRollDist(0.55);
        myRaceCar_1.setFrontAxleTireModel(myTireModel_1);
        myRaceCar_1.setRearAxleTireModel(myTireModel_1);
        myRaceCar_1.setName("Car STD (blue)");
        raceCars.add(myRaceCar_1);

        RaceCar myRaceCar_2 = new RaceCar(420, 420, 370, 370);
        TireModel myTireModel_2_Fr = new MagicFormulaTireModel(1.3, 15.2, -1.6, 1.6, 0.000075);
        TireModel myTireModel_2_Rr = new MagicFormulaTireModel(1.3, 15.2, -1.6, 1.8, 0.000075);
        myRaceCar_2.setFrontRollDist(0.55);
        myRaceCar_2.setFrontAxleTireModel(myTireModel_2_Fr);
        myRaceCar_2.setRearAxleTireModel(myTireModel_2_Rr);
        myRaceCar_2.setName("Car MOD (red)");
        raceCars.add(myRaceCar_2);

        raceCarSelectionItems = FXCollections.observableArrayList();
        raceCarSelectionItems.add(new RaceCarSelectionItem(myRaceCar_1,true , "BLUE"));
        raceCarSelectionItems.add(new RaceCarSelectionItem(myRaceCar_2, true, "RED"));
        isInitialised = true;
    }

    public static ObservableList<RaceCar> GetAllRaceCars(){
        if(!isInitialised)
            Initialise();
        return raceCars;
    }
    public static void AddRaceCar(RaceCar raceCar){
        if(!isInitialised)
            Initialise();
        raceCars.add(raceCar);
    }
    public static ObservableList<RaceCarSelectionItem> GetAllRaceCarSelectionItems(){
        if(!isInitialised)
            Initialise();
        return raceCarSelectionItems;
    }
    public static void AddRaceCarSelectionItem(RaceCarSelectionItem raceCarSelectionItem){
        if(!isInitialised)
            Initialise();
        raceCarSelectionItems.add(raceCarSelectionItem);
    }
    public static void removeCarSelectionItem(RaceCarSelectionItem raceCarSelectionItem){
        raceCarSelectionItems.remove(raceCarSelectionItem);
    }
}