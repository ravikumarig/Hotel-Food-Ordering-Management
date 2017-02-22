package rig.model;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FoodItem {
	
	public IntegerProperty itemId = new SimpleIntegerProperty();
	public StringProperty itemName = new SimpleStringProperty();
	public DoubleProperty itemCost = new SimpleDoubleProperty();
	public StringProperty itemType = new SimpleStringProperty();
	
	public IntegerProperty itemQuantity = new SimpleIntegerProperty();
	public DoubleProperty itemTotalCost = new SimpleDoubleProperty();
	
	//Default constructor
	public FoodItem(){
		super();
	}
	//Constructor with arguments
	public FoodItem(Integer itemId, String itemName, Double itemCost, String itemType){
		this.itemId = new SimpleIntegerProperty(itemId);
		this.itemName = new SimpleStringProperty(itemName);
		this.itemCost = new SimpleDoubleProperty(itemCost);
		this.itemType = new SimpleStringProperty(itemType);		
	}
	
	//getters
	public Integer getItemId(){
		return itemId.get();
	}
	public String getItemName(){
		return itemName.get();
	}
	public Double getItemCost(){
		return itemCost.get();
	}
	public String getItemType(){
		return itemType.get();
	}
	public Integer getItemQuantity(){
		return itemQuantity.get();
	}
	public Double getItemTotalCost(){
		return itemTotalCost.get();
	}
	
	
	//setters
	public void setItemId(Integer value){
		itemId.set(value);
	}
	public void setItemName(String value){
		itemName.set(value);
	}
	public void setItemCost(Double value){
		itemCost.set(value);
	}
	public void setItemType(String value){
		itemType.set(value);
	}
	public void setItemQuantity(Integer value){
		itemQuantity.set(value);
	}
	public void seItemTotalCost(Double value){
		itemTotalCost.set(value);
	}
	
	//Property values
	public IntegerProperty itemIdProperty(){
		return itemId;
	}
	public StringProperty itemNameProperty(){
		return itemName;
	}
	public DoubleProperty itemCostProperty(){
		return itemCost;
	}
	public StringProperty itemTypeProperty(){
		return itemType;
	}
	public IntegerProperty itemQuantityProperty(){
		return itemQuantity;
	}
	public DoubleProperty itemTotalCostProperty(){
		return itemTotalCost;
	}
	
	public String toString(){
		return getItemId() + " " + getItemName() + " " + getItemCost() + " " + getItemType() + " " + getItemQuantity() + " " + getItemTotalCost();          
	}
}
