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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import rig.dao.HotelDao;
import rig.model.Bill;
import rig.model.Transaction;

public class ManageBillController implements Initializable, Transaction {

	//Start of ManageBillTable1
	@FXML
	private TableView<Bill> fxActiveBill;

	@FXML
	private TableColumn<Bill,Long> MBTransID;

	@FXML
	private TableColumn<Bill,String> MBUserID;

	@FXML
	private TableColumn<Bill,Double> MBBillAmt;
	//End of ManageBillTable1

	//Start of ManageBillTable2
	@FXML
	private TableView<Bill> fxUpdateBill;

	@FXML
	private TableColumn<Bill,Long> MBTransID1;

	@FXML
	private TableColumn<Bill,String> MBUserID1;

	@FXML
	private TableColumn<Bill,Double> MBBillAmt1;
	//End of ManageBillTable2

	@FXML
	private Label billMsg;

	@FXML
	private Label adminDisplayName;

	ObservableList<Bill> activeBills;

	ObservableList<Bill> updateList = FXCollections.observableArrayList();

	@FXML
	public void displayManagerName() {
		adminDisplayName.setText("Welcome " + LoginController.managerName +" !");
	}

	// This method is automatically invoked when the FXML is loaded and all the active Bill transactions are displayed.
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		fxActiveBill.setEditable(true);

		//The values in the quotes must have a respective <value>Property() method in class Bill example: transIdProperty()  
		MBTransID.setCellValueFactory(new PropertyValueFactory<>("transId"));
		MBUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
		MBBillAmt.setCellValueFactory(new PropertyValueFactory<>("finalBillAmount"));

		MBTransID1.setCellValueFactory(new PropertyValueFactory<>("transId"));
		MBUserID1.setCellValueFactory(new PropertyValueFactory<>("userId"));
		MBBillAmt1.setCellValueFactory(new PropertyValueFactory<>("finalBillAmount"));

		activeBills = new HotelDao().getActiveBills();
		fxActiveBill.setItems(activeBills);	
		fxUpdateBill.setItems(updateList);

	}

	public void refreshMenu(){

		this.initialize(null, null);
		//this.billMsg.setText("** Menu Table is refreshed **");
	}


	@FXML
	public void addToCart() {

		if (fxActiveBill.getSelectionModel().isEmpty()) {
			this.billMsg.setText("**Please select any of the active bills from Active Bill Table**");

		}
		else {
			Bill bil = new Bill();
			bil.transId.set(activeBills.get(fxActiveBill.getSelectionModel().getSelectedIndex()).getTransId());
			bil.userId.set(activeBills.get(fxActiveBill.getSelectionModel().getSelectedIndex()).getUserId());
			bil.finalBillAmount.set(activeBills.get(fxActiveBill.getSelectionModel().getSelectedIndex()).getFinalBillAmount());
			updateList.add(bil);

		}

	}

	@FXML
	public void clearEachItem() {

		if (fxUpdateBill.getSelectionModel().isEmpty()) {
			this.billMsg.setText("**Please select an bill item in the list to clear**");

		}	
		else {
			updateList.remove(fxUpdateBill.getSelectionModel().getSelectedIndex());
			this.billMsg.setText("");

		}
	}

	@FXML
	public void clearAllItems() {

		updateList.clear();
		this.billMsg.setText("");
	}


	//This method closes the manage bill scene and returns to the main manager scene 
	@FXML
	public void goToMainMenu(){
		ManagerViewController.manageMainWindow.close();
	}

	@FXML
	private void updateBillAndOrder(){

		String msg = "";

		if(!fxUpdateBill.getItems().isEmpty()){
			for(Bill bill : fxUpdateBill.getItems()){
				int status = HotelDao.updateBillAndOrderTable(bill);
				if(status == 0){
					msg = "**failure**";
					break;
				}else if(status == -1){
					msg = "failure";
					break;
				}
				else{
					msg = "success";
				}

			}

			if(msg == "success"){

				System.out.println("** Selected Transactions are now complete and marked as billed **");
				this.billMsg.setText("** Selected Transactions are now complete and marked as billed **");	
			}else{

				System.out.println("** Selected Transactions cannot be updated **");
				this.billMsg.setText("** Selected Transactions cannot be updated **");	
				this.billMsg.setTextFill(Color.web("#e60000"));
			}
			
			this.refreshMenu();


		}else{
			this.billMsg.setText("**There are no transactions in the list to update bill and order**");
		}
	}

}
