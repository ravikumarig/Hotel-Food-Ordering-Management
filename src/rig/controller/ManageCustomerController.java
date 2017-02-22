package rig.controller;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.io.IOException;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rig.Main;
import rig.dao.HotelDao;
import rig.model.CustomerPropertyClass;
import rig.model.Transaction;

public class ManageCustomerController implements Initializable, Transaction {

	public static Stage addCusWindow;

	//Start of ManageCustomerTable1
	@FXML
	private TableView<CustomerPropertyClass> fxActiveCustomer;

	@FXML
	private TableColumn<CustomerPropertyClass,String> MCUserID;

	@FXML
	private TableColumn<CustomerPropertyClass,String> MCUserName;

	@FXML
	private TableColumn<CustomerPropertyClass,String> MCUserPwd;
	//End of ManageCustomerTable1

	//Start of ManageCustomerTable2
	@FXML
	private TableView<CustomerPropertyClass> fxActiveCustomer1;

	@FXML
	private TableColumn<CustomerPropertyClass,String> MCUserID1;

	@FXML
	private TableColumn<CustomerPropertyClass,String> MCUserName1;

	@FXML
	private TableColumn<CustomerPropertyClass,String> MCUserPwd1;
	//End of ManageCustomerTable2

	@FXML
	private Label cusManageMsg;

	@FXML
	private Label adminDisplayName;

	ObservableList<CustomerPropertyClass> activeCustomers;

	ObservableList<CustomerPropertyClass> custList = FXCollections.observableArrayList();



	@FXML
	public void displayManagerName() {
		adminDisplayName.setText("Welcome " + LoginController.managerName +" !");
	}

	@Override
	@FXML
	public void goToMainMenu() {
		// TODO Auto-generated method stub

		ManagerViewController.manageMainWindow.close();
	}

	@Override
	@FXML
	public void clearAllItems() {
		// TODO Auto-generated method stub

		custList.clear();
		this.cusManageMsg.setText("");
	}

	@Override
	@FXML
	public void clearEachItem() {
		// TODO Auto-generated method stub

		if (fxActiveCustomer1.getSelectionModel().isEmpty()) {
			this.cusManageMsg.setText("**Please select a customer in the list to clear**");

		}	
		else {
			custList.remove(fxActiveCustomer1.getSelectionModel().getSelectedIndex());
			this.cusManageMsg.setText("");

		}
	}

	@Override
	@FXML
	public void addToCart() {
		// TODO Auto-generated method stub

		if (fxActiveCustomer.getSelectionModel().isEmpty()) {
			this.cusManageMsg.setText("**Please select any of the active customers from Active Customer Table**");

		}
		else {
			CustomerPropertyClass cs = new CustomerPropertyClass();
			cs.userIdProperty.set(activeCustomers.get(fxActiveCustomer.getSelectionModel().getSelectedIndex()).getUserIdProperty());
			cs.userNameProperty.set(activeCustomers.get(fxActiveCustomer.getSelectionModel().getSelectedIndex()).getUserNameProperty());
			cs.userPassProperty.set(activeCustomers.get(fxActiveCustomer.getSelectionModel().getSelectedIndex()).getUserPassProperty());
			custList.add(cs);

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		fxActiveCustomer.setEditable(true);

		//The values in the quotes must have a respective <value>Property() method in class Bill example: transIdProperty()  
		MCUserID.setCellValueFactory(new PropertyValueFactory<>("userIdProperty"));
		MCUserName.setCellValueFactory(new PropertyValueFactory<>("userNameProperty"));
		MCUserPwd.setCellValueFactory(new PropertyValueFactory<>("userPassProperty"));

		MCUserID1.setCellValueFactory(new PropertyValueFactory<>("userIdProperty"));
		MCUserName1.setCellValueFactory(new PropertyValueFactory<>("userNameProperty"));
		MCUserPwd1.setCellValueFactory(new PropertyValueFactory<>("userPassProperty"));

		activeCustomers = new HotelDao().getActiveCustomer();
		fxActiveCustomer.setItems(activeCustomers);	
		fxActiveCustomer1.setItems(custList);

	}

	@FXML
	public void refreshMenu(){

		this.initialize(null, null);
		this.cusManageMsg.setText("** Customer Table is refreshed **");
	}


	@FXML
	private void deleteCustomer(){

		int msg = 1;
		String status = "";

		if(!fxActiveCustomer1.getItems().isEmpty()){
			for(CustomerPropertyClass c : fxActiveCustomer1.getItems()){
				status = HotelDao.deleteCustomer(c);
				if(status == "success"){
					msg = 0;
				}
				else{
					msg = 1;
					break;
				}

			}

			if(msg == 0){

				System.out.println("** Selected Customers are successfully deleted from the system **");
				this.cusManageMsg.setText("** Selected Customers are successfully deleted from the system **");	
			}else{

				System.out.println(status);
				this.cusManageMsg.setText(status);	
				this.cusManageMsg.setTextFill(Color.web("#e60000"));
			}

			this.initialize(null, null);


		}else{
			this.cusManageMsg.setText("** There are no customers in the list to delete **");
		}
	}

	@FXML
	private void createCustomer(){

		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/rig/view/AddNewCustomer.fxml"));
			AnchorPane addCusLayout = loader.load();
			addCusWindow = new Stage();
			addCusWindow.setTitle("Add New Customers");
			addCusWindow.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(addCusLayout);
			addCusWindow.setScene(scene);
			addCusWindow.showAndWait();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

	}


	@FXML
	private void updateCustomer(){

		if (fxActiveCustomer.getSelectionModel().isEmpty()) {
			this.cusManageMsg.setText("**Please select any of the active customers from Active Customer Table**");

		}
		else {
			CustomerPropertyClass cs = new CustomerPropertyClass();
			cs.userIdProperty.set(activeCustomers.get(fxActiveCustomer.getSelectionModel().getSelectedIndex()).getUserIdProperty());
			cs.userNameProperty.set(activeCustomers.get(fxActiveCustomer.getSelectionModel().getSelectedIndex()).getUserNameProperty());
			cs.userPassProperty.set(activeCustomers.get(fxActiveCustomer.getSelectionModel().getSelectedIndex()).getUserPassProperty());

			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("/rig/view/UpdateCustomerByManager.fxml"));
				BorderPane updCusByMangLayout = loader.load();
				UpdateCustomerByManagerController updateProf = loader.getController();
				updateProf.autoPopulateField(cs);
				addCusWindow = new Stage();
				addCusWindow.setTitle("Update Customer Details");
				addCusWindow.initModality(Modality.APPLICATION_MODAL);
				addCusWindow.initOwner(Main.mainWindow);
				Scene scene = new Scene(updCusByMangLayout);
				addCusWindow.setScene(scene);
				addCusWindow.showAndWait();
				
				this.initialize(null, null);
				this.cusManageMsg.setText("** Selected Customer details updated **");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
