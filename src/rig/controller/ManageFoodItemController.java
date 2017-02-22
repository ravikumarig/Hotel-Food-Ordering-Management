package rig.controller;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rig.Main;
import rig.dao.HotelDao;
import rig.model.FoodItem;

public class ManageFoodItemController implements Initializable{

	// TableView1 and its columns for Existing Menu under ManageFoodItems
	@FXML
	private TableView<FoodItem> existingMenu;

	@FXML
	private TableColumn<FoodItem,Integer> itemIdColumnManage;

	@FXML
	private TableColumn<FoodItem,String> itemNameColumnManage;

	@FXML
	private TableColumn<FoodItem,Double> itemCostColumnManage;

	@FXML
	private TableColumn<FoodItem,String> itemTypeColumnManage;
	//End of TableView1


	// TableView2 and its columns for Delete Table
	@FXML
	private TableView<FoodItem> deleteTable;

	@FXML
	private TableColumn<FoodItem,Integer> deleteIdColumn;

	@FXML
	private TableColumn<FoodItem,String> deleteNameColumn;

	@FXML
	private TableColumn<FoodItem,Integer> deleteCostColumn;

	@FXML
	private TableColumn<FoodItem,Double> deleteTypeColumn;
	//End of TableView2	

	ObservableList<FoodItem> deleteList = FXCollections.observableArrayList();

	ObservableList<FoodItem> markForDelete;

	@FXML
	private Label adminDisplayName;

	@FXML
	public Label manageMenuMsg;	
	
	public static Stage addItemWindow;
	
	@FXML
	public void displayManagerName() {
		adminDisplayName.setText("Welcome " + LoginController.managerName +" !");
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		existingMenu.setEditable(true);
		//The values in the quotes must have a respective <value>Property() method in class FoodItem example: itemNameProperty()
		itemIdColumnManage.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		itemNameColumnManage.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		itemCostColumnManage.setCellValueFactory(new PropertyValueFactory<>("itemCost"));
		itemTypeColumnManage.setCellValueFactory(new PropertyValueFactory<>("itemType"));

		deleteIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		deleteNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		deleteCostColumn.setCellValueFactory(new PropertyValueFactory<>("itemCost"));
		deleteTypeColumn.setCellValueFactory(new PropertyValueFactory<>("itemType"));

		markForDelete = new HotelDao().loadFoodItems();
		existingMenu.setItems(markForDelete);
		deleteTable.setItems(deleteList);

	}

	@FXML
	private void addToDeleteTable() {

		if (existingMenu.getSelectionModel().isEmpty()) {
			this.manageMenuMsg.setText("**Please select any of the existing items from Existing Menu Table**");

		}
		else {
			FoodItem foodItem = new FoodItem();
			foodItem.itemId.set(markForDelete.get(existingMenu.getSelectionModel().getSelectedIndex()).getItemId());
			foodItem.itemName.set(markForDelete.get(existingMenu.getSelectionModel().getSelectedIndex()).getItemName());
			foodItem.itemCost.set(markForDelete.get(existingMenu.getSelectionModel().getSelectedIndex()).getItemCost());
			foodItem.itemType.set(markForDelete.get(existingMenu.getSelectionModel().getSelectedIndex()).getItemType());
			deleteList.add(foodItem);

		}

	}


	@FXML
	private void clearEachItemFromDeleteTable() {

		if (deleteTable.getSelectionModel().isEmpty()) {
			this.manageMenuMsg.setText("**Please select an item in your delete table to clear**");

		}	
		else {

			deleteList.remove(deleteTable.getSelectionModel().getSelectedIndex());
			this.manageMenuMsg.setText("");

		}
	}

	@FXML
	private void clearAllItemsFromDeleteTable() {

		deleteList.clear();
		this.manageMenuMsg.setText("");
	}

	@FXML
	private void goToManagerMainWindow(){

		ManagerViewController.manageMainWindow.close();
	}
	
	@FXML
	private void deleteItemFromItemDB(){
		String deleteMsg = "";
		
		if(!deleteTable.getItems().isEmpty()){
			for(FoodItem orderedItems : deleteTable.getItems()){
				int status = HotelDao.deleteItems(orderedItems);
				if(status == 1){
					deleteMsg = "**Selected item/items deleted successfully**";
				}else{
					deleteMsg = "**Item/Items cannot be deleted as it may be still actively used by a customer**";
					break;
				}
			}

			this.initialize(null, null);
			System.out.println(deleteMsg);
			this.manageMenuMsg.setText(deleteMsg);
		}else{
			this.manageMenuMsg.setText("**There are no items in the delete table to remove permanently from the system**");
		}
	}
	
	@FXML
	private void addNewFoodItem(){
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/rig/view/AddNewFoodItem.fxml"));
		AnchorPane addItemLayout = loader.load();
		addItemWindow = new Stage();
		addItemWindow.setTitle("Add Food Items");
		addItemWindow.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(addItemLayout);
		addItemWindow.setScene(scene);
		addItemWindow.showAndWait();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}	
	
	public void refreshMenu(){
		
		this.initialize(null, null);
		this.manageMenuMsg.setText("** Menu Table is refreshed **");
	}
	
}
