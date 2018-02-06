package UpSystem.model;


// SalesStaff is an inheriting class from Employee
public class SalesStaff extends Employee{

	// includes new data member: available to indicate their availability to take a client
	private boolean available;
	
	// constructor for SalesStaff
	public SalesStaff(String firstName, String lastName, int idnumber, String password, String phone)
	{
		// calls Employee(String firstName, StringlastName, int idnumber, String password, String phone) 
		// constructor from parent class
		super(firstName, lastName, idnumber, password, phone);
		available = true;  // at constructor, staff is set to available
	}
	
	// Method to determine SalesStaff availability
	public boolean IsAvailable()
	{
		return available;
	}
	
	// Method to change availability
	public void SetAvailable(boolean available)
	{
		this.available = available;
	}
}
