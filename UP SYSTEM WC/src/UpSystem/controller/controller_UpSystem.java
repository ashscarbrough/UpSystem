package UpSystem.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UpSystem.model.model_UpSystem;
import UpSystem.views.Main_Screen;

public class controller_UpSystem {
	model_UpSystem theModel;
	Main_Screen theView;
	
	// Constructor for Controller: takes parameters of view and model and establishes
	// Events with listeners
	public controller_UpSystem(Main_Screen view, model_UpSystem model) {
		
		this.theModel = model;
		this.theView = view;
		this.theView.createEvents(new upSystemListener());
	}
	
	//Creates action listener responders for buttons on View module
    class upSystemListener implements ActionListener{
    	
    	public void actionPerformed(ActionEvent e) {
    		
    		// if AddUser button is pressed, activate createNewUsters() method found in the model
    		if(e.getSource() == theView.btnAddUser) {
      			System.out.println("YOU PRESSED ADDUSER");
      			theModel.createNewUsers();
    		}
    		
    		// if CheckLog button is pressed
    		if(e.getSource() == theView.btnCheckLog) {
    			System.out.println("YOU PRESSED CHECKLOG");
    		}
    		
    		// if NameWithCustomer button is pressed, activate nameButton() method found in the model
    		if(e.getSource() == theView.btnNameWithCustomer) {
    			System.out.println("YOU PRESSED UP PERSON NAME");
    		}
    		
    		// if TakeCustomer button is pressed, activate TakeCustomer() method found in the model
    		if(e.getSource() == theView.btnTakeCustomer) {
    			System.out.println("YOU PRESSED TAKE CUSTOMER");
    			theModel.takeCustomer();
    			theView.repaint();
    		}
    		
    		// if LogIn button is pressed, activate addToLoggedInList() method found in the model
    		if(e.getSource() == theView.btnLogIn) {
    			System.out.println("YOU ADD TO LOGGEDIN LIST");
    			theModel.addToLoggedInList();
    		}
    		
    		// if LogOut button is pressed, activate removeFromLoggedInList() method found in the model
    		if(e.getSource() == theView.btnLogOut) {
    			System.out.println("YOU PRESSED REMOVE FROM LOGGEDIN LIST");
    			theModel.removeFromLoggedInList();
    		}
    		
    		//
    		if(e.getSource() == theView.btnChangeTime) {
    			theModel.changeTheTime();
    		}

    		
    		// if RemoveUser button is pressed, activate removeUser() method found in the model
    		if(e.getSource() == theView.btnRemoveUser) {
    			System.out.println("YOU PRESSED REMOVE USER");
    			theModel.removeUser();
    		}
    	}
    }
}
