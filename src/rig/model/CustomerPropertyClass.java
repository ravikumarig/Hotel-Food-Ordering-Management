package rig.model;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerPropertyClass {


	public StringProperty userIdProperty = new SimpleStringProperty();
	public StringProperty userNameProperty = new SimpleStringProperty();
	public StringProperty userPassProperty = new SimpleStringProperty();


	//getters
	public String getUserIdProperty(){
		return userIdProperty.get();
	}
	public String getUserNameProperty(){
		return userNameProperty.get();
	}
	public String getUserPassProperty(){
		return userPassProperty.get();
	}

	//setters
	public void setUserIdProperty(String value){
		userIdProperty.set(value);
	}
	public void setUserNameProperty(String value){
		userNameProperty.set(value);
	}
	public void setUserPassProperty(String value){
		userPassProperty.set(value);
	}

	//Property values
	public StringProperty userIdPropertyProperty(){
		return userIdProperty;
	}
	public StringProperty userNamePropertyProperty(){
		return userNameProperty;
	}
	public StringProperty userPassPropertyProperty(){
		return userPassProperty;
	}	

}
