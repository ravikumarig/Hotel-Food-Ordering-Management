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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import rig.dao.HotelDao;
import rig.model.FoodItem;
import rig.model.Transaction;

public class PlaceOrderController implements Initializable, Transaction  {


	// TableView1 and its columns for Food Menu
	@FXML
	private TableView<FoodItem> foodMenu;

	@FXML
	private TableColumn<FoodItem,Integer> itemIdColumn;

	@FXML
	private TableColumn<FoodItem,String> itemNameColumn;

	@FXML
	private TableColumn<FoodItem,Double> itemCostColumn;

	@FXML
	private TableColumn<FoodItem,String> itemTypeColumn;
	//End of TableView1


	// TableView2 and its columns for Cart Selection
	@FXML
	private TableView<FoodItem> cartTable;

	@FXML
	private TableColumn<FoodItem,Integer> cartIdColumn;

	@FXML
	private TableColumn<FoodItem,String> cartNameColumn;

	@FXML
	private TableColumn<FoodItem,Integer> cartQtyColumn;

	@FXML
	private TableColumn<FoodItem,Double> cartCostColumn;
	//End of TableView2

	@FXML
	private TextField fxquantity;

	//Message field
	@FXML
	private Label cartmsg;

	//CustomerName display field
	@FXML
	private Label customerDisName;

	ObservableList<FoodItem> itemSelected;

	ObservableList<FoodItem> cartList = FXCollections.observableArrayList(); 

	@FXML
	public void dispCusname() {
		customerDisName.setText("Welcome " + LoginController.customerName +" !");
	}

	//**This method is responsible to load all the data in the FoodMenu table automatically 
	//	once the scene is launched.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		foodMenu.setEditable(true);
		//The values in the quotes must have a respective <value>Property() method in class FoodItem example: itemNameProperty()
		itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		itemCostColumn.setCellValueFactory(new PropertyValueFactory<>("itemCost"));
		itemTypeColumn.setCellValueFactory(new PropertyValueFactory<>("itemType"));

		cartIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		cartNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		cartQtyColumn.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
		cartCostColumn.setCellValueFactory(new PropertyValueFactory<>("itemTotalCost"));

		itemSelected = new HotelDao().loadFoodItems();
		foodMenu.setItems(itemSelected);
		cartTable.setItems(cartList);

	}

	@FXML
	public void addToCart() {

		if (foodMenu.getSelectionModel().isEmpty()) {
			this.cartmsg.setText("**Please select any of the existing items from Food Menu Table**");

		}else if(fxquantity.getText().isEmpty()){

			this.cartmsg.setText("**Please enter a valid Quantity value**");
		}	
		else {
			FoodItem fi = new FoodItem();
			fi.itemId.set(itemSelected.get(foodMenu.getSelectionModel().getSelectedIndex()).getItemId());
			fi.itemName.set(itemSelected.get(foodMenu.getSelectionModel().getSelectedIndex()).getItemName());
			fi.itemQuantity.set(Integer.parseInt(fxquantity.getText()));
			fi.itemTotalCost.set(itemSelected.get(foodMenu.getSelectionModel().getSelectedIndex()).getItemCost() * (Double.parseDouble(fxquantity.getText())));
			cartList.add(fi);

		}

	}

	@FXML
	public void clearEachItem() {

		if (cartTable.getSelectionModel().isEmpty()) {
			this.cartmsg.setText("**Please select an item in your selection list to clear**");

		}	
		else {

			cartList.remove(cartTable.getSelectionModel().getSelectedIndex());
			this.cartmsg.setText("");

		}
	}

	@FXML
	public void clearAllItems() {
		
		cartList.clear();
		this.cartmsg.setText("");
	}

	@FXML
	public void goToMainMenu(){

		CustomerViewController.updateCus.close();
	}

	@FXML
	public void confirmOrder(){
		String orderMsg = "";
		String customerId = LoginController.dbUserName;
		String orderStatus = "active";
		
		if(!cartTable.getItems().isEmpty()){
			for(FoodItem orderedItems : cartTable.getItems()){
				long orderId = System.currentTimeMillis();				
				int status = HotelDao.storeOrderDetails(orderedItems, orderId, customerId, orderStatus);
				if(status == 1){
					orderMsg = "**Order registered successfully**";
				}else{
					orderMsg = "**Order registration failed**";
					break;
				}
				System.out.println(orderMsg);
				this.cartmsg.setText(orderMsg);
			}
		}else{
			this.cartmsg.setText("**There are no items in the selection list to place order**");
		}
	}
}
