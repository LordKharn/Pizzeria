package application;
	
import Controller.KundenController;
import Dao.DAOFactory;
import Models.Kunde;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;


public class Main extends Application { 
	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(Main.class.getResource("../View/view.fxml"));
			TabPane view = (TabPane) loader.load();
			KundenController controller = loader.getController();
			controller.setMainApp(this);
			Scene scene = new Scene(view);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);

	}
	
	public ObservableList<Kunde> getKunden() {
		return FXCollections.observableArrayList(DAOFactory.getKundeDAO().selectAllKunden());
	}
	
	public ObservableList<Kunde> getKunde(int id) {
		return FXCollections.observableArrayList(DAOFactory.getKundeDAO().findKunde(id));
	}
	
	public ObservableList<Kunde> getKunde(String name) {
		return FXCollections.observableArrayList(DAOFactory.getKundeDAO().findKunde(name));
	}
}
