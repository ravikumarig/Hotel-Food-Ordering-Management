package rig.model;

//RAVI KUMAR INDIRA GANGARAM
//12/02/2016
//ITMD_510_04_Final_Project

// This interface is being used by both Order and Bill Controllers
public interface Transaction {
	
	public void goToMainMenu();
	public void clearAllItems();
	public void clearEachItem();
	public void addToCart();

}
