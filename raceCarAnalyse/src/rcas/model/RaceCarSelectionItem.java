package rcas.model;

import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

/**
 * Created by brian on 06.07.2018.
 */
//Is used for the table of the RaceCar in the MainView
public class RaceCarSelectionItem {
    private Boolean isSelected = false;
    private RaceCar raceCar = null;
    private String gridColor = "GREEN";
    private String raceCarName;
    public ComboBox<Color> colorComboBox;

    public RaceCarSelectionItem(RaceCar raceCar){
        this.raceCar = raceCar;
        this.raceCarName = raceCar.getName();
        this.isSelected = false;
    }
    public RaceCarSelectionItem(RaceCar raceCar, boolean isSelected, String gridColor){
        this.raceCar = raceCar;
        this.raceCarName = raceCar.getName();
        this.isSelected = isSelected;
        this.gridColor = gridColor;
    }
    public boolean getIsSelected(){
        return this.isSelected;
    }
    public RaceCar getRaceCar(){
        return this.raceCar;
    }
    public String getRaceCarName(){
        return this.raceCar.getName();
    }
    public String getGridColor(){
        return this.gridColor;
    }
    public void setGridColor(String color){
        this.gridColor = color;
    }
    public void setIsSelected(Boolean isSelected){
        this.isSelected = isSelected;
    }
}
