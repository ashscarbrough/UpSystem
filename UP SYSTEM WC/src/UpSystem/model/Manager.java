package UpSystem.model;

public class Manager extends Employee {
	
	private boolean loggedInStatus = false;
	private boolean modstatus = false;
	private boolean userCreator = false;
	
	// constructor for Manager
	public Manager(String firstName, String lastName, int idnumber, String password, String phone)
	{
		// calls Employee(String firstName, StringlastName, int idnumber, String password, int phone) 
		// constructor from parent class
		super(firstName, lastName, idnumber, password, phone);
		super.isManager = true;
		loggedInStatus = true;  // at constructor, manager is logged in
		modstatus = false;
	}
	
	// Method returns MOD status of manager
	public boolean isMOD() {
		return modstatus;
	}
	
	// Method changes MOD status of manager
	public void changeMOD(boolean bool) {
		modstatus = bool;
	}
	
	// Manager Method that adds Employee to Roster
	public void addEmployeeToRoster(EmployeeRoster roster, Employee emp) {
		roster.addEmployee(emp);
	}
	
	// Method removes Employee from Roster
	public boolean removeEmployeeFromRoster(EmployeeRoster roster, Employee emp) {
		return roster.removeEmployee(emp);
	}
	
	public boolean removeEmployeeFromRoster(EmployeeRoster roster, int id) {
		return roster.removeEmployee(id);
	}
	
	// Method changes userCreator status;
	public void canCreateUsers(boolean bool) {
		userCreator = bool;
	}
	
	// Method checks if Manager has userCreator status
	public boolean CreateUserStatus() {
		return userCreator;
	}
	
	// Method to set loggedInStatus
	public void setLoggedStatus(boolean bool) {
		loggedInStatus = bool;
	}
	
	// Method to check loggedInStatus
	public boolean LoggedStatus() {
		return loggedInStatus;
	}
	
}
