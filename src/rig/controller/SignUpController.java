package rig.controller;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rig.Main;
import rig.dao.HotelDao;
import rig.model.Customer;

public class SignUpController {

	@FXML
	private TextField fxname;

	@FXML
	private TextField fxemail;

	@FXML 
	private PasswordField fxpassword;

	@FXML
	private Label signUpMessage;

	@FXML
	private void backToLoginScreen() throws IOException{
		Main.showLoginView();
	}

	@FXML
	private void customerSignup(){

		Customer customer = new Customer();

		customer.setName(fxname.getText());

		String lowercase = fxemail.getText();
		customer.setEmail(lowercase.toLowerCase());

		customer.setPassword(fxpassword.getText());

		String successfulInsert = HotelDao.insertCustomer(customer);
		signUpMessage.setText(successfulInsert);

	}
}
