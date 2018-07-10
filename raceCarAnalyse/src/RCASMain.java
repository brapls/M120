import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rcas.model.MagicFormulaTireModel;
import rcas.model.RaceCar;
import rcas.model.RaceCarSelectionItem;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import rcas.model.TireModel;
import rcas.util.DataUtil;


public class RCASMain extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		RaceCar myRaceCar_2 = new RaceCar(420, 420, 370, 370);
		TireModel myTireModel_2_Fr = new MagicFormulaTireModel(1.3, 15.2, -1.6, 1.6, 0.000075);
		TireModel myTireModel_2_Rr = new MagicFormulaTireModel(1.3, 15.2, -1.6, 1.8, 0.000075);
		myRaceCar_2.setFrontRollDist(0.55);
		myRaceCar_2.setFrontAxleTireModel(myTireModel_2_Fr);
		myRaceCar_2.setRearAxleTireModel(myTireModel_2_Rr);
		myRaceCar_2.setName("Car MOD (red)");
		DataUtil.AddRaceCar(myRaceCar_2);

		ArrayList<RaceCar> raceCars = DataUtil.GetAllRaceCars();
		for(RaceCar rc : raceCars){
			System.out.println(rc.getName());
		}
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(RCASMain.class.getResource("rcas/view/RCASMainView.fxml"));
		ResourceBundle resourceBundle = ResourceBundle.getBundle("RCASResources");
		fxmlLoader.setResources(resourceBundle);
		GridPane mainPane = (GridPane) fxmlLoader.load();
		Scene mainScene = new Scene(mainPane, 800, 600);
		primaryStage.centerOnScreen();
		primaryStage.setTitle(resourceBundle.getString("applicationTitle"));
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(RCASMain.class, args);
	}
}
