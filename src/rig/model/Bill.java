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

public class Bill {

	public LongProperty transId = new SimpleLongProperty();
	public StringProperty userId = new SimpleStringProperty();
	public DoubleProperty finalBillAmount = new SimpleDoubleProperty();
	public StringProperty billStatus = new SimpleStringProperty();


	//getters
	public Long getTransId(){
		return transId.get();
	}
	public String getUserId(){
		return userId.get();
	}
	public Double getFinalBillAmount(){
		return finalBillAmount.get();
	}
	public String getBillStatus(){
		return billStatus.get();
	}

	//setters
	public void setTransId(Long value){
		transId.set(value);
	}
	public void setUserId(String value){
		userId.set(value);
	}
	public void setFinalBillAmount(Double value){
		finalBillAmount.set(value);
	}
	public void setBillStatus(String value){
		billStatus.set(value);
	}

	//Property values
	public LongProperty transIdProperty(){
		return transId;
	}
	public StringProperty userIdProperty(){
		return userId;
	}
	public DoubleProperty finalBillAmountProperty(){
		return finalBillAmount;
	}
	public StringProperty billStatusProperty(){
		return billStatus;
	}

}
