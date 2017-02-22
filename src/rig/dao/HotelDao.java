package rig.dao;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rig.model.Bill;
import rig.model.Customer;
import rig.model.CustomerPropertyClass;
import rig.model.FoodItem;
import rig.model.Manager;
import rig.model.Order;

public class HotelDao {

	private static Connection connection = null;
	private static String DB_URL = "jdbc:mysql://www.papademas.net:3306/fp";
	private static String USER_NAME = "dbfp";
	private static String PASSWORD = "510";
	private ObservableList<FoodItem> menuList;
	private ObservableList<Order> orderedList;
	private ObservableList<Bill> activeBillList;
	private ObservableList<CustomerPropertyClass> activeCustomerList;


	public static String insertCustomer(Customer customer){
		String msg="";
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String insert = "INSERT INTO rigcustomer VALUES('"+customer.getEmail()+"','"+customer.getName()+"','"+customer.getPassword()+"')";
			stmt.executeUpdate(insert);
			msg="User added successfully";
		} 
		catch (MySQLIntegrityConstraintViolationException e){
			msg="User already exists !";
		}catch (SQLException e) {
			msg="Failed to add customer";
			e.printStackTrace();
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return msg;
	} 


	public static Customer getUsername(String lUserName){
		Customer c = new Customer();
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String select = "SELECT email,name,password FROM rigcustomer WHERE email='"+lUserName+"'";
			ResultSet rs = stmt.executeQuery(select);
			while(rs.next()){
				c.setEmail(rs.getString("email"));
				c.setName(rs.getString("name"));
				c.setPassword(rs.getString("password"));
			}
		} 
		catch (MySQLIntegrityConstraintViolationException e){
			System.out.println(e.getMessage());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return c;
	}

	public static String updateCustomer(Customer customer){
		String msg="";
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String update = "UPDATE rigcustomer SET name='"+customer.getName()+"', password='"+customer.getPassword()+"' WHERE email='"+customer.getEmail()+"'";                                     
			stmt.executeUpdate(update);
			msg="User Profile updated successfully ! Please Login again. ";
		} 
		catch (MySQLIntegrityConstraintViolationException e){
			e.printStackTrace();
		}catch (SQLException e) {
			msg="Failed to update User Profile";
			e.printStackTrace();
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return msg;
	} 


	public static Manager getManagerUsername(String lManagerUserName){
		Manager m = new Manager();
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String select = "SELECT email,name,password FROM rigmanager WHERE email='"+lManagerUserName+"'";
			ResultSet rs = stmt.executeQuery(select);
			while(rs.next()){
				m.setEmail(rs.getString("email"));
				m.setName(rs.getString("name"));
				m.setPassword(rs.getString("password"));
			}
		} 
		catch (MySQLIntegrityConstraintViolationException e){
			System.out.println(e.getMessage());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return m;
	}

	public ObservableList<FoodItem> loadFoodItems(){
		menuList = FXCollections.observableArrayList();
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String select = "SELECT * FROM rigfood_item";
			ResultSet rs = stmt.executeQuery(select);
			while(rs.next()){
				menuList.add(new FoodItem(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("cost"), rs.getString("category")));
			}
		} 
		catch (MySQLIntegrityConstraintViolationException e){
			System.out.println(e.getMessage());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return menuList;

	}


	public static int storeOrderDetails(FoodItem orderedItems, long orderId, String customerId, String orderStatus){
		int successCode = 0;
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();

			String insert = "INSERT INTO rigorder(order_id,email,item_id,item_name,quantity,cost,status) "
					+ "VALUES('" + orderId + "','" + customerId + "','" 
					+ orderedItems.getItemId() + "','" + orderedItems.getItemName() + "','" 
					+ orderedItems.getItemQuantity() + "','" + orderedItems.getItemTotalCost() + "','" 
					+ orderStatus + "')";

			stmt.executeUpdate(insert);
			successCode = 1;
		} 
		catch (SQLException e) {
			successCode = -1;
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return successCode;
	}


	public ObservableList<Order> viewOrder(String customerId){
		orderedList = FXCollections.observableArrayList();
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String select = "SELECT * FROM rigorder WHERE email='" + customerId + "' AND " +"status='active'";
			ResultSet rs = stmt.executeQuery(select);
			//Double tempTotalCost = 0.0;
			while(rs.next()){
				//	orderedList.add(new Order(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("cost"), rs.getString("category")));

				Order order = new Order();

				order.orderId.set(rs.getLong("order_id"));
				order.itemId.set(rs.getInt("item_id"));
				order.itemName.set(rs.getString("item_name"));
				order.itemQuantity.set(rs.getInt("quantity"));
				order.itemTotalCost.set(rs.getDouble("cost"));
				order.orderStatus.set(rs.getString("status"));
				//		order.billAmount.set(tempTotalCost += rs.getDouble("cost"));
				orderedList.add(order);

			}
		} 
		catch (SQLException e) {
			System.out.println("viewOrder: Error on Building Data");
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return orderedList;
	}


	public static int storeBillDetails(long transactionId, String customerId, Double finalBill, String billStatus){
		int successCode = 0;
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();

			String insert = "INSERT INTO rigbill VALUES('" + transactionId + "','" + customerId + 
					"','" + finalBill + "','" + billStatus +"')";

			stmt.executeUpdate(insert);
			successCode = 1;
		} 
		catch (SQLException e) {
			successCode = -1;
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return successCode;
	}


	public static int updateOrderTableWithBillDetails(Order orderedItems, long trans_id){
		int successCode = 0;
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();

			String update = "UPDATE rigorder SET trans_id=" + trans_id + " WHERE order_id=" + orderedItems.getOrderId() ;

			stmt.executeUpdate(update);
			successCode = 1;
		} 
		catch (SQLException e) {
			successCode = -1;
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return successCode;
	}

	//Delete Food Items from the Database (admin privilege)
	public static int deleteItems(FoodItem orderedItems){
		int successCode = 0;
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();


			String delete1 = "DELETE FROM rigorder WHERE item_id=" + orderedItems.getItemId() + " AND status='inactive'";
			stmt.executeUpdate(delete1);

			String delete2 = "DELETE FROM rigfood_item WHERE item_id=" + orderedItems.getItemId();
			stmt.executeUpdate(delete2);
			successCode = 1;
		} 
		catch (Exception e) {
			successCode = -1;
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return successCode;
	}

	public static String insertNewFoodItem(FoodItem newItem){
		String msg="";
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String insert = "INSERT INTO rigfood_item VALUES('" + newItem.getItemId() + "','" + newItem.getItemName() + "','" +newItem.getItemCost()+ "','" + newItem.getItemType() + "')";
			stmt.executeUpdate(insert);
			msg="Item added successfully !";
		} 
		catch (MySQLIntegrityConstraintViolationException e){
			msg="Item already exists !";
		}catch (SQLException e) {
			msg="Failed to add new Item";
			e.printStackTrace();
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return msg;
	} 


	public ObservableList<Bill> getActiveBills(){
		activeBillList = FXCollections.observableArrayList();
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String select = "SELECT * FROM rigbill WHERE status='unbilled'";
			ResultSet rs = stmt.executeQuery(select);

			while(rs.next()){

				Bill bill = new Bill();

				bill.transId.set(rs.getLong("trans_id"));
				bill.userId.set(rs.getString("email"));
				bill.finalBillAmount.set(rs.getDouble("final_cost"));
				bill.billStatus.set(rs.getString("status"));

				activeBillList.add(bill);

			}
		} 
		catch (SQLException e) {
			System.out.println("viewOrder: Error on Building Data");
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return activeBillList;
	}


	// This method will update the Bill and Order Table once the transaction is processed and billed.
	public static int updateBillAndOrderTable(Bill bill){
		int successCode = 0;
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();


			String update1 = "UPDATE rigbill SET status='billed' WHERE trans_id=" + bill.getTransId();
			successCode = stmt.executeUpdate(update1);


			if(successCode == 0){
				System.out.println("The Bill Table was not updated for Transaction Id: " + bill.getTransId());
				return successCode;
			}

			String delete2 = "UPDATE rigorder SET status='inactive' WHERE trans_id=" + bill.getTransId();
			successCode = stmt.executeUpdate(delete2);

			if(successCode == 0){
				System.out.println("The Order Table was not updated for Transaction Id: " + bill.getTransId());
				return successCode;
			}

		} 
		catch (Exception e) {
			successCode = -1;
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return successCode;
	}



	public ObservableList<CustomerPropertyClass> getActiveCustomer(){
		activeCustomerList = FXCollections.observableArrayList();
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();
			String select = "SELECT * FROM rigcustomer";
			ResultSet rs = stmt.executeQuery(select);

			while(rs.next()){

				CustomerPropertyClass c = new CustomerPropertyClass();

				c.userIdProperty.set(rs.getString("email"));
				c.userNameProperty.set(rs.getString("name"));
				c.userPassProperty.set(rs.getString("password"));

				activeCustomerList.add(c);

			}
		} 
		catch (SQLException e) {
			System.out.println("viewOrder: Error on Building Data");
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return activeCustomerList;
	}



	// This method will update the Bill and Order Table once the transaction is processed and billed.
	public static String deleteCustomer(CustomerPropertyClass c){
		String msg = "";
		
		try {
			connection = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			Statement stmt = connection.createStatement();


			String select1 = "SELECT * FROM rigbill WHERE email='" + c.getUserIdProperty() + "' AND status='unbilled'";
			ResultSet rs = stmt.executeQuery(select1);
			
			if(rs.next()){
				System.out.println("Bill:* Cannot delete ! Customer may have active transactions *");
				return "Bill:* Cannot delete ! Customer may have active transactions *";
			}

			String select2 = "SELECT * FROM rigorder WHERE email='" + c.getUserIdProperty() + "' AND status='active'";
			rs = stmt.executeQuery(select2);
			
			if(rs.next()){
				System.out.println("Order:* Cannot delete ! Customer may have active transactions *");
				return "Order:* Cannot delete ! Customer may have active transactions *";
			}

			String delete1 = "DELETE FROM rigorder WHERE email='" + c.getUserIdProperty() + "'";
			String delete2 = "DELETE FROM rigbill WHERE email='" + c.getUserIdProperty() + "'";
			String delete3 = "DELETE FROM rigcustomer WHERE email='" + c.getUserIdProperty() + "'";
			
			stmt.executeUpdate(delete1);
			stmt.executeUpdate(delete2);
			stmt.executeUpdate(delete3);
			msg = "success";

		} 
		catch (Exception e) {
			msg = "There was an exception encountered during the deletion. Please the logs.";
			System.out.println(e.getMessage());
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return msg;
	}
}
