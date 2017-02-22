package rig.controller;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rig.Main;
import rig.dao.HotelDao;
import rig.model.Customer;

public class UpdateCustomerProfile {

	@FXML
	private TextField fxUpdatename;

	@FXML
	private TextField fxUpdateEmail;

	@FXML
	private PasswordField fxUpdatePassword;

	public void autoPopulateField(){

		fxUpdateEmail.setText(LoginController.dbUserName);
		fxUpdatename.setText(LoginController.customerName);
		fxUpdatePassword.setText(LoginController.dbPassword);
	}

	@FXML
	public void cancelUpdate() throws IOException{
		CustomerViewController.updateCus.close();
	}
	
	@FXML
	private void updateProfile(){
		try{
		Customer customer = new Customer();

		customer.setName(fxUpdatename.getText());

		String lowercase = fxUpdateEmail.getText();
		customer.setEmail(lowercase.toLowerCase());

		customer.setPassword(fxUpdatePassword.getText());

		String successfulUpdate = HotelDao.updateCustomer(customer);
		System.out.println(successfulUpdate);
		CustomerViewController.updateCus.close();
		
		//logouts out from the profile
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/rig/view/Login.fxml"));
		Main.mainLayout = loader.load();
		LoginController login = loader.getController();
		login.fxloginerror.setText(successfulUpdate);
		Scene scene = new Scene(Main.mainLayout);
		Main.mainWindow.setScene(scene);
		Main.mainWindow.show();
		}
		catch(Exception e ){
			e.getMessage();
			
		}
	}
}
