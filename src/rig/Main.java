package rig;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import rig.controller.CustomerViewController;
import rig.controller.ManagerViewController;

public class Main extends Application {

	public static Stage mainWindow;
	public static AnchorPane mainLayout;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.mainWindow= primaryStage;
		Main.mainWindow.setTitle("Hotel Hazare");
		showLoginView();
	}

	public static void showLoginView() throws IOException{

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/rig/view/Login.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		mainWindow.setScene(scene);
		mainWindow.show();
	}

	public static void goToSignUpView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/rig/view/SignUpPage.fxml"));
		AnchorPane signUpLayout = loader.load();
		mainLayout.getChildren().add(signUpLayout);
	}

	public static void goCustomerView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/rig/view/CustomerView.fxml"));
		BorderPane customerLayout = loader.load();
		CustomerViewController cuscon = loader.getController();
		cuscon.setCusname();
		Scene scene = new Scene(customerLayout);
		mainWindow.setScene(scene);
		mainWindow.show();
	}

	public static void goToManagerView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/rig/view/ManagerView.fxml"));
		BorderPane managerLayout = loader.load();
		ManagerViewController mgrCon = loader.getController();
		mgrCon.setManagerName();
		Scene scene = new Scene(managerLayout);
		mainWindow.setScene(scene);
		mainWindow.show();
	}
}
