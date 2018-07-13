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
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(RCASMain.class.getResource("rcas/view/RCASMainView.fxml"));
		ResourceBundle resourceBundle = ResourceBundle.getBundle("RCASResources");
		fxmlLoader.setResources(resourceBundle);
		GridPane mainPane = fxmlLoader.load();
		Scene mainScene = new Scene(mainPane, 1000, 600);
		primaryStage.centerOnScreen();
		primaryStage.setTitle(resourceBundle.getString("applicationTitle"));
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(RCASMain.class, args);
	}
}
