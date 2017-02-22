package rig.controller;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import rig.dao.HotelDao;
import rig.model.Order;

public class ViewOrderController implements Initializable {

	//Start of ViewOrderTable
	@FXML
	private TableView<Order> fxviewOrder;

	@FXML
	private TableColumn<Order,Long> VOOrderIdColumn;

	@FXML
	private TableColumn<Order,Integer> VOItemIdColumn;

	@FXML
	private TableColumn<Order,String> VOItemNameColumn;

	@FXML
	private TableColumn<Order,Integer> VOQuantityColumn;

	@FXML
	private TableColumn<Order,Double> VOCostColumn;
	//End of ViewOrderTable

	@FXML
	private Label billMsg;

	@FXML
	private Label billMsg1;

	@FXML
	private Label fxBillAmt;

	@FXML
	private Label customerDisName;

	ObservableList<Order> orderedItems;

	@FXML
	public void dispCusname() {
		customerDisName.setText("Welcome " + LoginController.customerName +" !");
	}

	// This method is automatically invoked when the FXML is loaded and all the Customer Orders are displayed.
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		fxviewOrder.setEditable(true);

		//The values in the quotes must have a respective <value>Property() method in class FoodItem example: orderIdProperty()  
		VOOrderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		VOItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		VOItemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		VOQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
		VOCostColumn.setCellValueFactory(new PropertyValueFactory<>("itemTotalCost"));

		String customerId = LoginController.dbUserName;
		orderedItems = new HotelDao().viewOrder(customerId);
		fxviewOrder.setItems(orderedItems);	

	}

	// This method generates the bill for the orders made by he/she.
	@FXML
	public void generateBill(){

		String billStatus = "unbilled";
		
		if(!fxviewOrder.getItems().isEmpty()){
			Double finalBill = 0.0;
			for(Order order : orderedItems ){
				finalBill += order.getItemTotalCost();
			}

			String transactionMsg = "";
			long transactionId = System.currentTimeMillis();
			String customerId = LoginController.dbUserName;

			// Inserts transaction details in Bill Table
			int status1 = HotelDao.storeBillDetails(transactionId, customerId, finalBill, billStatus);
			
			// Updates transaction ID to Order Table
			int status2 = 0; 
			for(Order orderedItems : fxviewOrder.getItems()){			
				status2 = HotelDao.updateOrderTableWithBillDetails(orderedItems, transactionId);
				if(status2 == 1){
					continue;
				}else{
					break;
				}
			}
			
			if((status1 == 1) && (status2 == 1)){
				transactionMsg = "**Dear Customer, your Bill is generated successfully !**";
			}else{
				transactionMsg = "**Bill generation failed**";
			}
			System.out.println(transactionMsg);

			this.billMsg.setText("Dear Customer, your Bill is generated successfully !");
			this.billMsg1.setText("Please pay the below amount to the manager by Cash/Card. Thank You. Visit us again :)");
			this.fxBillAmt.setText("Your Bill Amount is :: $ " + finalBill + " !");
		}
		else{
			this.billMsg.setText("**There are no orders placed to generate bill**");
			this.billMsg.setTextFill(Color.web("#e60000"));
		}
	}


	//This method closes the view order scene and returns to the main customer scene 
	@FXML
	public void goBack(){
		CustomerViewController.updateCus.close();
	}
}
