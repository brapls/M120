package rcas.model;

import javafx.scene.paint.Color;

/**
 * Created by brian on 06.07.2018.
 */
//Is used for the table of the RaceCar in the MainView
public class RaceCarSelectionItem {
    private Boolean isSelected = false;
    private RaceCar raceCar = null;
    private Color gridColor = Color.BLACK;
    private String raceCarName;

    public RaceCarSelectionItem(RaceCar raceCar){
        this.raceCar = raceCar;
        this.raceCarName = raceCar.getName();
        this.isSelected = false;
    }
    public RaceCarSelectionItem(RaceCar raceCar, boolean isSelected){
        this.raceCar = raceCar;
        this.raceCarName = raceCar.getName();
        this.isSelected = isSelected;
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
    public Color getGridColor(){
        return this.gridColor;
    }
    public void setGridColor(Color color){
        this.gridColor = color;
    }
    public void setIsSelected(Boolean isSelected){
        this.isSelected = isSelected;
    }
}
