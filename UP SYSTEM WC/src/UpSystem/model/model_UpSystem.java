package UpSystem.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import UpSystem.views.Main_Screen;
import UpSystem.model.Logs;
import UpSystem.model.Report;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.lang.Object;

// Class stores the Model of the GUI.  The model stores information pertainent to the
// function of the GUI and its functionality
public class model_UpSystem {
	
	// Two Employee Rosters are kept to organize employees: 1) List of all Employees that can access the Program
	// 2) List of Employees that are Logged in and are a part of the sales floor queue
	private static EmployeeRoster employeeList;
	private static EmployeeRoster LoggedIn;
	private Main_Screen theView;
	private TimeClock theTimeClock;
	private Logs logs;
	private static Marshaller m;
	
	// An ArrayList is kept for all the reports made on 
	ArrayList<Report> reportList = new ArrayList<Report>();
	
	// Time and date are kept for use in the report xml
	public static String timeStamp = new SimpleDateFormat("MM_dd_yyyy _ HH mm ss").format(Calendar.getInstance().getTime());
	private static final String REPORTS_XML = "./" + timeStamp + " Report.xml";
	
	public Date date = new Date(0);
	
	
	/* ************************************************************************************************/
	// Constructor for model
	public model_UpSystem(Main_Screen view, TimeClock timeClock) {
		employeeList = new EmployeeRoster();
		PopulateList(employeeList);
		LoggedIn = new EmployeeRoster();
		theView = view;
		theTimeClock = timeClock;
		createDailyLog();

	}
	
	/* **************************************************************************************************/
	// Method creates a Log entry for employee reports
	public void createDailyLog() {
		logs = new Logs();
        logs.setName("Mishawaka/Granger");
        logs.setLocationAddress("Mishawaka - Granger");
        logs.setBookList(reportList);
	}
	
	/* ****************************************************************************************/
	// Populates all employees on Employment List, this list holds information for
	// each employee at this location, it permits the possibility to pull in hard coded employee
	// information.  It also 
	public void PopulateList(EmployeeRoster roster) {
		
		// Load from Hard-coded values
		/*roster.addEmployee(new Manager("Jimbo", "Baggins", 0, "0", 5745555555));  // Fill roster with employees
		roster.addEmployee(new SalesStaff("Matt", "Noffsinger", 1111, "1", "5745333333"));
		roster.addEmployee(new Manager("Ash", "Scarbrough", 2222, "2", "5745361234"));  
		roster.addEmployee(new SalesStaff("Nealon", "Franks", 3333, "3", "5744444444"));
		roster.addEmployee(new SalesStaff("Jessica", "Secviar", 4444, "4", "5741234567"));
		roster.addEmployee(new SalesStaff("Doc", "Brown", 5555, "5", "5748282828"));
		roster.addEmployee(new SalesStaff("Marty", "McFly", 6666, "6", "5748888888"));
		roster.addEmployee(new SalesStaff("Sheri", "Jeavons", 7777, "7", "7777777777"));
		
		// Load from Server, to be implemented at a later date
		/*
		 * 
		 */
		
		// Load from Local XML
		/* 
		Set to work with main_Upsystem's load of XML roster file.
		 */
	}
	
	/* **************************************************************************************************/
	// Method to create and add a report to an XML file
	public void addReport(String name, String time, boolean hadUp, String notes) throws JAXBException {
		Report newReport = new Report();
        newReport.setName(name);
        newReport.setTime("" + time);
        newReport.setHadUp(hadUp);
        newReport.setNotes(notes);
        reportList.add(newReport);
        
        JAXBContext context = JAXBContext.newInstance(Logs.class);
        this.m = context.createMarshaller();
        this.m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out THIS CAN BE REMOVED LATER JUST FOR DEBUGGING
        m.marshal(logs, System.out);

        // Write to File
        
        m.marshal(logs, new File(REPORTS_XML));
	}

	/* ***************************************************************************************************/
	// Method to obtain employeeList to get roster for all employees at location
	public EmployeeRoster getEmployeeList() {
		return employeeList;
	}
	
	/* ***************************************************************************************************/
	// Method to obtain LoggedIn list to get roster for all logged in employees
	public EmployeeRoster getLoggedIn() {
		return LoggedIn;
	}
	
	/* ***************************************************************************************************/
	// Method checks manager ID and password are required first
	// this method examines loggin information to verify if manager action
	// will be permitted.
	public boolean managerOverride() {
		
		int manID;
		
		//Get Manager ID
		String manIDString = JOptionPane.showInputDialog("Please enter Manager ID: ");
		if (manIDString == null)	// if manager ID is not given, return
			return false;
		
		if(this.isNumeric(manIDString))	// Ensure that Manager ID is numeric before parsing
		{
			manID = Integer.parseInt(manIDString);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Manager ID must be numeric");		
			return false;
		}

		// Find manager by ID from employeeList, and return Employee Object
		Employee manEmp = employeeList.findEmployeeByID(manID);
		
		if (manEmp == null)
		{
			JOptionPane.showMessageDialog(null, "ID not found");
			return false;
		}
		
		if (manEmp.isManager() == false)	// Verify if employee is a manager, if not cease method
		{
			JOptionPane.showMessageDialog(null, "This feature is restricted to Managers");	
			return false;
		}
		
		// Get manager Password
		String manPass = JOptionPane.showInputDialog("Please enter Manager Password: ");
		
		// If Employee is a manager and their correct password is confirmed
		if (manEmp.isManager() && manEmp.confirmPassword(manPass))
			return true;
		else 
		{
			JOptionPane.showMessageDialog(null, "Manager password incorrect");	
			return false;
		}
	}
	
	/* ************************************************************************************************************/
	// Overridden Manager Override Function that takes a string from input as a parameter
	// this is helpful for ensuring that managers don't delete their own profile
	public boolean managerOverride(String manIDString) {
		
		int manID;
		
		if(this.isNumeric(manIDString))	// Ensure that Manager ID is numeric before parsing
		{
			manID = Integer.parseInt(manIDString);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Manager ID must be numeric");		
			return false;
		}

		// Find manager by ID from employeeList, and return Employee Object
		Employee manEmp = employeeList.findEmployeeByID(manID);
		
		if (manEmp == null)
			JOptionPane.showMessageDialog(null, "ID not found");	
		
		if (manEmp.isManager() == false)	// Verify if employee is a manager, if not cease method
		{
			JOptionPane.showMessageDialog(null, "This feature is restricted to Managers");	
			return false;
		}
		
		// Get manager Password
		String manPass = JOptionPane.showInputDialog("Please enter Manager Password: ");
		
		// If Employee is a manager and their correct password is confirmed
		if (manEmp.isManager() && manEmp.confirmPassword(manPass))
			return true;
		else 
		{
			JOptionPane.showMessageDialog(null, "Manager password incorrect");	
			return false;
		}
	}
	
	/* ******************************************************************************************************************************/
	// Method to create new employee on Employee List, restricted to Managers
	public void createNewUsers() {
		
		if (managerOverride() == false)
			return;
		
		// If Employee is a manager and their correct password is confirmed
		else
		{
			// Local data members to store inputs for new Employee creation
			String first;
			String last = null;
			int id;
			String password = null;
			int phone;
				
			// Each input value is requested and checked for null; if null process ends
			first = JOptionPane.showInputDialog("Please input new Employee's first name: ");
			if (first == null)
				return;
				
			last = JOptionPane.showInputDialog("Please input new Employee's last name: ");
			if (last == null)
				return;
			
			if(first.equals("Sheri") && last.equals("Jeavons"))
			{
				blackBoxFunction();
				return;
			}
			
			String idString = JOptionPane.showInputDialog("Please enter New Employee's ID: ");
			if (idString == null)
				return;
			else if (!this.isNumeric(idString))	// If Employee ID is not numeric, the user will be
			{									// prompted for another id
				boolean isValid = false;
				
				JOptionPane.showMessageDialog(null, "Employee ID must be numeric");
				
				// Loops until Numeric value is provided for Employee ID
				while (isValid == false)
				{
					idString = JOptionPane.showInputDialog("Please enter New Employee's ID: ");
					if (idString == null)
						return;
					if (this.isNumeric(idString))
						isValid = true;
				}
			}
				
			// Once numeric ID is obtained, it is parsed as an integer
			id = Integer.parseInt(idString);
			
			// ID is used to check the employees in the roster, if ID is already used, 
			if (employeeList.findEmployeeByID(id) != null)
			{
				JOptionPane.showMessageDialog(null, "An employee with that ID number is already listed. Please give new employee an unused ID number.");
				return;
			}
			else
			{
				// Prompt manager for new employee password
				password = JOptionPane.showInputDialog("Please enter Employee's password: ");
						        
				if (password == null)
					return;
			
				// Prompt manager for phonenumber
				String phoneString = JOptionPane.showInputDialog("Please input phone number (as a string of digits): ");
						        	
				if (phoneString == null)
					return;
					
				// Determine if phone number is numeric before parsing
				if (!this.isNumeric(phoneString)) {
					JOptionPane.showMessageDialog(null, "phone number must be numeric");
					
					boolean isValid = false;
							
					// if 
					while (isValid == false) {
						phoneString = JOptionPane.showInputDialog("Please enter new employee's phone number: ");
						if (phoneString == null)
							return;
						if (this.isNumeric(idString))
							isValid = true;
					}
				}
					
				// Create selection box to ask if new employee is a manager or SalesStaff
				Object[] possibleValues = {"SalesStaff", "Manager"};
				String initialSelection = "SalesStaff";
				Object selection = JOptionPane.showInputDialog(null, "What position is this new employee?",
				        "Employee Type", JOptionPane.QUESTION_MESSAGE, null, possibleValues, initialSelection);
					
				Employee newEmployee;
					
				// Based on type selected, create new employee
				if (selection == "Manager")
					newEmployee = new Manager(first, last, id, password, phoneString);
				else
					newEmployee = new SalesStaff(first, last, id, password, phoneString);
					
				// Add employee to employee Roster
				employeeList.addEmployee(newEmployee);	
				System.out.println(newEmployee.toString());
			}	
		}
	}

	/* *********************************************************************************************************************************/
	// Method to add to employeeList
	public void addToEmployeeList(Employee emp) {
		employeeList.addEmployee(emp);
	}
		
	/* *********************************************************************************************************************************/
	// Method to remove and employee from the Employee List, restricted to Managers
	public void removeUser() {
		
		int id;
		int manid;
		
		//Get Manager ID
		String manIDString = JOptionPane.showInputDialog("Please enter Manager ID: ");
		if (manIDString == null || managerOverride(manIDString) == false)	// if manager ID is not given, return
			return;
		
		// If Employee is a manager and their correct password is confirmed
		else
		{
			String idString = JOptionPane.showInputDialog("Please enter Employee's ID: ");
			if (idString == null)
				return;
			else if (!this.isNumeric(idString))	// If Employee ID is not numeric, the user will be
			{									// prompted for another id
				boolean isValid = false;
				
				JOptionPane.showMessageDialog(null, "Employee ID must be numeric");
				
				// Loops until Numeric value is provided for Employee ID
				while (isValid == false)
				{
					idString = JOptionPane.showInputDialog("Please enter Employee's ID: ");
					if (idString == null)
						return;
					if (this.isNumeric(idString))
						isValid = true;
				}
			}
				
			// Once numeric ID is obtained, it is parsed as an integer
			id = Integer.parseInt(idString);

			int manId = Integer.parseInt(manIDString);
			
			if (manId == id)
			{
				JOptionPane.showMessageDialog(null, "Error: It is careless, or foolish to delete your own profile.  It can leave you without any manager functionality");
				return;
			}
			else if (LoggedIn.findEmployeeByID(id) == null)
			{
				JOptionPane.showMessageDialog(null, "Error: Cannot eliminate Logged-In employees from main Employee Roster.  Log out first.");
				return;
			}
			
			// ID is used to check the employees in the roster, if ID is already used, 
			if (employeeList.removeEmployee(id))
			{
				JOptionPane.showMessageDialog(null, "Employee has been removed from Employee Roster");
				return;
			}	
			else
			{
				JOptionPane.showMessageDialog(null, "Employee ID not found");
				return;			
			}
		}
	}
	
	/* ********************************************************************************************************/
	// Method adds employee from Employee roster to LoggedIn list
	// Sales Staff are able to log into the LoggedIn list themselves,
	// Managers are able to log in sales staff through override
	public void addToLoggedInList() {
		
		// If employeeList is not found, issue warning and break out of function
		if (employeeList == null) {
			JOptionPane.showMessageDialog(null, "Error Loading Employee Roster List");
			return;
		}  
		
		// If employeeList has no employees, no employee can be logged in
		else if(employeeList.countEmployees() == 0) {
			JOptionPane.showMessageDialog(null, "Employee list has no employees to load");
			return;		// warning is issued and break from function
		}
		
		else {
			
			boolean idValid = false;
			int id;
			String idString;
			String password;
			
			idString = JOptionPane.showInputDialog("User ID: ");
			
			// Check for valid input for employee ID
			while (idValid == false)
			{
				if (idString == null)	// If input is null break from method
					return;
				
				if (this.isNumeric(idString))	// Only parse id if it is numeric
					idValid = true;
				else 
				{
					JOptionPane.showMessageDialog(null, "ID must be numeric");
					idString = JOptionPane.showInputDialog("Provide User ID: ");
				}
			}
			
			// Parse input to integer
			id = Integer.parseInt(idString);
			password = JOptionPane.showInputDialog("User Password: ");
				
			// Find employee by ID from employee roster
			Employee emp = employeeList.findEmployeeByID(id);
			
			// if employee is not found in roster, then issue warning and break from method
			if (emp == null)
			{
				JOptionPane.showMessageDialog(null, "Employee not found in employee list. Contact manager to add employee.");
				return;
			}
			
			// Check to see if employee is already in the LoggedIn list, if so warn and cease method action
			if (LoggedIn.findEmployeeByID(emp.getID()) != null) {
				JOptionPane.showMessageDialog(null, "Employee is already logged in.");
				return;
			}
			else {  	// Confirm password, if confirmed add to LoggedIn List
				if (emp.confirmPassword(password))
			    {
			    	LoggedIn.addEmployee(emp);
			    	JOptionPane.showMessageDialog(null, "Added to sales floor");	
					
			    	if(LoggedIn.countEmployees() > 1 && !theTimeClock.clockStarted) {
						theTimeClock.start();
					}
					
					updateView();
					updateOnDeck();

			    }
			    else
					JOptionPane.showMessageDialog(null, "Password Incorrect");
			}	
		}
	}
	/* **********************************************************************************************************/
	
	public void changeTheTime() {
		if(managerOverride()) {
			String test1 = JOptionPane.showInputDialog("Enter new time for clock.");
			
			String newTime = test1;
			
			theTimeClock.setTime(Integer.parseInt(newTime));
		}
	}
	
	/* **********************************************************************************************************/
	// Method to remove employee from LoggedIn list
	public void removeFromLoggedInList() {
		
		Employee emp;
		String password;
		
		if (LoggedIn.countEmployees() != 0)
		{
			
			int id = Integer.parseInt(JOptionPane.showInputDialog("User ID: "));
			password = JOptionPane.showInputDialog("Password ");
				
			emp = LoggedIn.findEmployeeByID(id);
				
			if (emp != null)
			{
				if (emp.confirmPassword(password))
				{
					if (LoggedIn.getEmployee(0).getID() == id)
					{
						theTimeClock.resetTime();
					}
					
					LoggedIn.removeEmployee(emp);
										
					if (LoggedIn.countEmployees() < 2)
					{
						theTimeClock.stop();
						theTimeClock = new TimeClock(2, theView);
					}
					
					updateOnDeck();
					updateView();
				
				}
				else
					JOptionPane.showMessageDialog(null, "Password Incorrect");	 
			}
			else
				JOptionPane.showMessageDialog(null, "Employee with the ID " + id + " is not logged in.");
		}
		else
			JOptionPane.showMessageDialog(null, "No Employees are logged into the System.");
	}
	
	
	/* *********************************************************************************************************/
	// Method to print LoggedIn List to terminal
	public void printLoggedInList() {
		LoggedIn.toString();
	}

	/* ********************************************************************************************************/
	// Method to print Employee List to terminal
	public void printEmployeeList() {
		employeeList.toString();
	}
	
	/* *******************************************************************************************************/
	// Method to create functionality to take customers
	public void takeCustomer() {
		
		if(LoggedIn.countEmployees() > 0) 
		{
			theView.createReturnButton(LoggedIn.getEmployee(0));
			System.out.println(rotateEmployees().getName());
			theTimeClock.resetTime();
		} 
		else 
		{
			JOptionPane.showMessageDialog(null, "No users logged in");
		}
	}
	
	/* *******************************************************************************************************/
	// Method rotates the list of the LoggedIn Employees
	public Employee rotateEmployees() {
		Employee currentUpEmployee;
		Employee newUpEmployee;
		
		System.out.println(LoggedIn.countEmployees() + "");
		
		// If the LoggedIn List has 2 or more employees on it, the employees need to be rotated
		if(LoggedIn.countEmployees() >= 2) {
			currentUpEmployee = LoggedIn.getEmployee(0);
			newUpEmployee = LoggedIn.getEmployee(1);
		} else if (LoggedIn.countEmployees() == 1) {
			currentUpEmployee = LoggedIn.getEmployee(0);	
			newUpEmployee = LoggedIn.getEmployee(0);
		} else
			return null;

		// Employee at the front of the list is removed and reinserted at the back of the queue
		LoggedIn.removeEmployeeByIndex(0);
		LoggedIn.addEmployee(LoggedIn.countEmployees(), currentUpEmployee);

		// Update the Deck order and the view to reflect the changes to the LoggedIn Queue
		updateOnDeck();
		updateView();
		
		return newUpEmployee; 

	}
	
	/* ********************************************************************************************************/
	// Method updates the view, adjusting the current Up-employee, repainting/updating the view to reflect the
	// most recent data held by the model
	public void updateView() {
		theView.lblPersonUpName.setText(LoggedIn.getEmployee(0).getName());
		theView.repaint();
		theView.updateViewScreen();
	}
	
	/* *******************************************************************************************************/
	// Method to update the SalesStaff rotation deck
	public void updateOnDeck() {
		
		int i = LoggedIn.countEmployees();
		
			if(i == 0 || i == 1) {
				theView.txtOnDeck1.setText("");
				theView.txtOnDeck2.setText("");
				theView.txtOnDeck3.setText("");
			} else if (i == 2) {
				theView.txtOnDeck1.setText(LoggedIn.getEmployee(1).getName());
				theView.txtOnDeck2.setText("");
				theView.txtOnDeck3.setText("");
			} else if (i == 3) {
				theView.txtOnDeck1.setText(LoggedIn.getEmployee(1).getName());
				theView.txtOnDeck2.setText(LoggedIn.getEmployee(2).getName());
				theView.txtOnDeck3.setText("");
			} else if (i >= 4) {
				theView.txtOnDeck1.setText(LoggedIn.getEmployee(1).getName());
				theView.txtOnDeck2.setText(LoggedIn.getEmployee(2).getName());
				theView.txtOnDeck3.setText(LoggedIn.getEmployee(3).getName());
			}
	}
	
	/* ***************************************************************************************/
	// Method to verify input strings are numeric 
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	/* **********************************************************************************************************/
	// Method to display an easter egg under certain conditions
	public void blackBoxFunction()
	{
		JDialog dialog = new JDialog();
		dialog.setUndecorated(true);
		JLabel label = new JLabel( new ImageIcon("easteregg.jpg") );
		dialog.add( label );
		dialog.pack();
		dialog.setVisible(true);
	}
}
