package rig.controller;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rig.Main;

public class CustomerViewController {

	public static Stage updateCus;

	@FXML 
	private Label fxwelcomeCus;

	@FXML
	private Label miscMsg;

	@FXML
	private void customerLogout() throws IOException{
		Main.showLoginView();
	}

	@FXML
	public void setCusname() {
		fxwelcomeCus.setText("Welcome " + LoginController.customerName +" !");
	}

	@FXML
	private void updateCustomer(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/rig/view/UpdateCustomerProfile.fxml"));
			BorderPane updCusLayout = loader.load();
			UpdateCustomerProfile updateProfile = loader.getController();
			updateProfile.autoPopulateField();
			updateCus = new Stage();
			updateCus.setTitle("Update Customer Details");
			updateCus.initModality(Modality.APPLICATION_MODAL);
			updateCus.initOwner(Main.mainWindow);
			Scene scene = new Scene(updCusLayout);
			updateCus.setScene(scene);
			updateCus.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void placeOrder(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/rig/view/PlaceOrder.fxml"));
			AnchorPane plcOrdrLayout;
			plcOrdrLayout = loader.load();
			PlaceOrderController placeOdr = loader.getController();
			placeOdr.dispCusname();
			updateCus = new Stage();
			updateCus.setTitle("Place Order");
			updateCus.initModality(Modality.APPLICATION_MODAL); 
			updateCus.initOwner(Main.mainWindow);
			Scene scene = new Scene(plcOrdrLayout);
			updateCus.setScene(scene);
			updateCus.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void viewOrder(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/rig/view/ViewOrder.fxml"));
			AnchorPane viewOrdrLayout;
			viewOrdrLayout = loader.load();
			ViewOrderController viewOdr = loader.getController();
			viewOdr.dispCusname();
			updateCus = new Stage();
			updateCus.setTitle("View Order");
			updateCus.initModality(Modality.APPLICATION_MODAL); 
			updateCus.initOwner(Main.mainWindow);
			Scene scene = new Scene(viewOrdrLayout);
			updateCus.setScene(scene);
			updateCus.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
