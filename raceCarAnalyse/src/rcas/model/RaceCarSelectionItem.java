package rcas.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;

/**
 * Created by brian on 06.07.2018.
 */
//Is used for the table of the RaceCar in the MainView
public class RaceCarSelectionItem {
    private CheckBox isSelectedCheckBox = new CheckBox();
    private RaceCar raceCar = null;
    private Color gridColor = Color.BLACK;

    public RaceCarSelectionItem(RaceCar raceCar){
        this.raceCar = raceCar;
        this.isSelectedCheckBox = new CheckBox();
    }
    public boolean GetIsSelected(){
        return this.isSelectedCheckBox.isSelected();
    }
    public RaceCar GetRaceCar(){
        return this.raceCar;
    }
    public String GetRaceCarName(){
        return this.raceCar.getName();
    }
    public Color GetGridColor(){
        return this.gridColor;
    }
    public void SetGridColor(Color color){
        this.gridColor = color;
    }
    public void SetIsSelected(Boolean isSelected){
        this.isSelectedCheckBox.selectedProperty().set(isSelected);
    }
}
