package rig.model;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order extends FoodItem {

	public LongProperty orderId = new SimpleLongProperty();
	public StringProperty orderStatus = new SimpleStringProperty();
	public DoubleProperty billAmount = new SimpleDoubleProperty();


	//getters
	public Long getOrderId(){
		return orderId.get();
	}
	public String getOrderStatus(){
		return orderStatus.get();
	}
	public Double getBillAmount(){
		return billAmount.get();
	}

	//setters
	public void setOrderId(Long value){
		orderId.set(value);
	}
	public void setOrderStatus(String value){
		orderStatus.set(value);
	}
	public void setBillAmount(Double value){
		billAmount.set(value);
	}

	//Property values
	public LongProperty orderIdProperty(){
		return orderId;
	}
	public StringProperty orderStatusProperty(){
		return orderStatus;
	}
	public DoubleProperty billAmountProperty(){
		return billAmount;
	}
}

