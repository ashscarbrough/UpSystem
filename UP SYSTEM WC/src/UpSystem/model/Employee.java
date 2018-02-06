package UpSystem.model;

//Employee Class serves as the primary General class for the inheritance of the SalesStaff
//Class and the Manager Class.  All general functions needed for a general employee are included
public class Employee {

	// employee data members are all kept private, storing:
	// first & last names, id number, password, and phone number
	private String firstName;	
	private String lastName;
	private int idnumber;
	private String password;
	private String phone;
	protected boolean isManager = false;

	// constructor to initialize all data members
	public Employee(String firstName, String lastName, int idnumber, String password, String phone)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.idnumber = idnumber;
		this.password = password;
		this.phone = phone;
	}
	
	// Method to determine status of isManager for employee
	public boolean isManager()
	{
		return isManager;
	}
	
	// enable change first and last name in case of error or name change
	public void changeName (String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// get employee's first name
	public String getFirstName()
	{
			return this.firstName;
	}
	
	// get employee's last name
	public String getLastName()
	{
			return this.lastName;
	}
	
	// get employee's First name with shortened last name
	public String getName()
	{
		String workName;
		workName = firstName + " " + lastName.charAt(0) + ".";
		return workName;
	}
	
	// get employee id number
	public int getID()
	{
		return idnumber;
	}
	
	// method to confirm password with user input, 
	// by examining password character by character
	public boolean confirmPassword(String pass)
	{
		if (password.length() != pass.length())
			return false;
		else
		{ 
			for (int i = 0; i < pass.length(); i++)
			{
				if (password.charAt(i) != pass.charAt(i))
					return false;
			}
			return true;	
		}
	}
	
	// change/update employee phone number 
	public void changePhone (String phonenumber)
	{
		this.phone = phonenumber;
	}
	
	// get employee's phone number
	public String getPhone()
	{
		return phone;
	}
	
	// return boolean value to affirm/deny that object has equal data member values
	public boolean equals(Employee emp)
	{
		// if all data members are the same return true
		if ((this.firstName == emp.getFirstName()) 
				&& (this.lastName == emp.getLastName()) 
					&& (this.idnumber == emp.getID())
						&& (this.phone == emp.getPhone())
							&& (emp.confirmPassword(this.password)))
			return true;
		else
			return false;
	}
	
	// method to get a printable form of Employee information
	public String toString()
	{
		return (lastName + ", " + firstName + "\t" + idnumber + "\t" + phone); 
	}	
	
}
