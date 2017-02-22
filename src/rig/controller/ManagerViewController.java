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
import javafx.stage.Modality;
import javafx.stage.Stage;
import rig.Main;

public class ManagerViewController {

	public static Stage manageMainWindow;

	@FXML 
	private Label fxwelcomeMangr;

	@FXML
	private void managerLogout() throws IOException{
		Main.showLoginView();
	}

	@FXML
	public void setManagerName() {
		fxwelcomeMangr.setText("Welcome " + LoginController.managerName +" !");
	}


	@FXML
	public void manageFoodItems(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/rig/view/ManageFoodItem.fxml"));
			AnchorPane manageMenu;
			manageMenu = loader.load();
			ManageFoodItemController manageFoodItem = loader.getController();
			manageFoodItem.displayManagerName();
			manageMainWindow = new Stage();
			manageMainWindow.setTitle("Manage Menu");
			manageMainWindow.initModality(Modality.APPLICATION_MODAL); 
			manageMainWindow.initOwner(Main.mainWindow);
			Scene scene = new Scene(manageMenu);
			manageMainWindow.setScene(scene);
			manageMainWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void manageBill(){

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/rig/view/ManageBill.fxml"));
			AnchorPane manageBill;
			manageBill = loader.load();
			ManageBillController manageActBill = loader.getController();
			manageActBill.displayManagerName();
			manageMainWindow = new Stage();
			manageMainWindow.setTitle("Manage Bill and Order");
			manageMainWindow.initModality(Modality.APPLICATION_MODAL); 
			manageMainWindow.initOwner(Main.mainWindow);
			Scene scene = new Scene(manageBill);
			manageMainWindow.setScene(scene);
			manageMainWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	@FXML
	private void manageCustomer(){

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/rig/view/ManageCustomer.fxml"));
			AnchorPane manageCusLyt;
			manageCusLyt = loader.load();
			ManageCustomerController manageCus = loader.getController();
			manageCus.displayManagerName();
			manageMainWindow = new Stage();
			manageMainWindow.setTitle("Manage Customer");
			manageMainWindow.initModality(Modality.APPLICATION_MODAL); 
			manageMainWindow.initOwner(Main.mainWindow);
			Scene scene = new Scene(manageCusLyt);
			manageMainWindow.setScene(scene);
			manageMainWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
