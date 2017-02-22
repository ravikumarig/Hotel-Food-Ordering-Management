package rig.controller;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rig.dao.HotelDao;
import rig.model.Customer;
import rig.model.CustomerPropertyClass;

public class UpdateCustomerByManagerController {

	@FXML
	private TextField fxUpdatename;

	@FXML
	private TextField fxUpdateEmail;

	@FXML
	private PasswordField fxUpdatePassword;

	public void autoPopulateField(CustomerPropertyClass cs){

		fxUpdateEmail.setText(cs.getUserIdProperty());
		fxUpdatename.setText(cs.getUserNameProperty());
		fxUpdatePassword.setText(cs.getUserPassProperty());
	}

	@FXML
	public void goBack() throws IOException{
		ManageCustomerController.addCusWindow.close();
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
			ManageCustomerController.addCusWindow.close();

		}
		catch(Exception e ){
			e.getMessage();

		}
	}

}
