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
import rig.model.Manager;

public class LoginController {

	@FXML
	private TextField fxusername;

	@FXML
	private PasswordField fxlpassword;

	@FXML
	public Label fxloginerror;

	// Customer db data
	public static String customerName;
	public static String dbUserName;
	public static String dbPassword;

	// Manager db data
	public static String managerName;
	public static String dbManagerUserName;
	public static String dbManagerPassword;

	@FXML 
	private void signUp() throws IOException{
		Main.goToSignUpView();
	}

	@FXML
	public void customerLogin(){
		try{
			// User data from FXML
			String lUserName = fxusername.getText();
			lUserName = lUserName.toLowerCase();
			String lPassword = fxlpassword.getText();

			//User data from DB
			Customer c = HotelDao.getUsername(lUserName);
			dbUserName = c.getEmail();
			dbPassword = c.getPassword();
			customerName = c.getName();
			if (dbUserName.equals(lUserName) && dbPassword.equals(lPassword)){
				System.out.println("Customer Login Successful!");
				Main.goCustomerView();

			}
			else{
				fxloginerror.setText("Invalid Username/Password !!");
			}
		}
		catch(NullPointerException e)
		{
			fxloginerror.setText("Please enter valid Username/Password !!");
			System.out.println("LoginController: managerLogin()"+e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("LoginController: customerLogin()"+e.getMessage());
		}
	}

	@FXML
	private void managerLogin(){
		try{
			// User data from FXML
			String lManagerUserName = fxusername.getText();
			lManagerUserName = lManagerUserName.toLowerCase();
			String lManagerPassword = fxlpassword.getText();

			//User data from DB
			Manager m = HotelDao.getManagerUsername(lManagerUserName);
			dbManagerUserName = m.getEmail();
			dbManagerPassword = m.getPassword();
			managerName = m.getName();
			if (dbManagerUserName.equals(lManagerUserName) && dbManagerPassword.equals(lManagerPassword)){
				System.out.println("Manager Login Successful!");
				Main.goToManagerView();

			}
			else{
				fxloginerror.setText("Invalid Username/Password !!");
			}
		}
		catch(NullPointerException e)
		{
			fxloginerror.setText("Please enter valid Username/Password !!");
			System.out.println("LoginController: managerLogin()"+e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("LoginController: managerLogin()"+e.getMessage());
		}
	}
}
