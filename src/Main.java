import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Template.fxml"));
		primaryStage.setTitle("Identicon");
		primaryStage.setScene(new Scene(root, 250, 300));
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
