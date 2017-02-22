package rig.controller;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rig.dao.HotelDao;
import rig.model.FoodItem;

public class AddNewFoodItemController implements Initializable {
	
	@FXML
	private TextField fxAddItemId;
	
	@FXML
	private TextField fxAddItemName;
	
	@FXML
	private TextField fxAddItemCost;
	
	@FXML
	private Label additionMsg;

	@FXML
	private ChoiceBox<String> itemTypeDropDown;

	ObservableList<String> itemTypeList = FXCollections.observableArrayList("Starters","Soup","Burgers","Salads");


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		itemTypeDropDown.setValue("Starters");
		itemTypeDropDown.setItems(itemTypeList);

	}
	
	@FXML
	private void cancelAddition(){
		ManageFoodItemController.addItemWindow.close();
	}

	
	@FXML
	private void addNewItem(){
		
		FoodItem newItem = new FoodItem();

		newItem.setItemId(Integer.parseInt(fxAddItemId.getText()));
		newItem.setItemName(fxAddItemName.getText());
		newItem.setItemCost(Double.parseDouble(fxAddItemCost.getText()));
		newItem.setItemType(itemTypeDropDown.getValue());

		String insertMsg = HotelDao.insertNewFoodItem(newItem);		
		this.additionMsg.setText(insertMsg);
	}
	
}
