package UpSystem.model;
import java.util.ArrayList;

// Class that keeps a list of Employees and provides list functionality
public class EmployeeRoster {

	// Data Member that keeps a list of employees at employment location
	ArrayList <Employee> employees;

	// Constructor to create a new employeeRoster
	public EmployeeRoster()
	{
		employees = new ArrayList <Employee> ();
	}
	
	// Method to add a new employee to roster
	public void addEmployee (Employee emp)
	{
		employees.add(emp);
	}
	
	// Method to add new employee to specified position
	public void addEmployee (int size, Employee emp)
	{
		employees.add(size, emp);
	}
	
	// Method that counts the number of employees assigned to roster
	public int countEmployees()
	{
		return employees.size();
	}
	
	// Method to remove employee from a list using key of ID
	// if employee with ID is found, it is removed and a bool of true is returned
	// to indicate successful discovery.  If not found false is returned
	public boolean removeEmployee(int id)
	{
		Employee emp;
		int i;
		
		// employee roster is examined from beginning to end
		for (i = 0; i < employees.size(); i++)
		{
			// if employee in roster has the same ID as that provided by user
			// that discovered employee is ascribed to a temporary employee declaration
			// and removed from the list.  Bool true is then returned
			if (employees.get(i).getID() == id)
			{
				emp = employees.get(i);
				employees.remove(emp);
				return true;
			}	
		}
		return false; // If object ID is not found, false is returned.
	}
	
	// Method to remove employee from a list using key of ID
	// if employee with ID is found, it is removed and a bool of true is returned
	// to indicate successful discovery.  If not found false is returned
	public boolean removeEmployeeByIndex(int index)
	{
		
		if (employees.remove(index) == null)
			return false;
		else
			return true;
	}
	
	// Method to remove employee from a list using ArrayList.remove(obj o)
	// if employee is found, it is removed and a bool of true is returned
	// to indicate successful discovery.  If not found, false is returned
	public boolean removeEmployee(Employee emp)
	{
		// Uses ArrayList predefined remove function
		return employees.remove(emp);
	}
	
	// Method finds and returns employee by index 
	public Employee getEmployee(int index)
	{
		return employees.get(index);
	}
	
	// Method to find and return employee object by ID found in roster
	public Employee findEmployeeByID (Integer id)
	{
		int i;
		
		// Examine list of employees from beginning to end
		// if employee Id matches user provided ID, that employee
		// will be returned
		for (i = 0; i < employees.size(); i++)
		{
			if (employees.get(i).getID() == id)  // if id 
			{
				return employees.get(i);
			}	
		}

		return null; // If employee ID match is not found, return null
	}
	
	// Method to find and return arrayList index from ID found in employee roster
	public int findIndexByID (Integer id)
	{
		int i;
		
		// Examine list of employees from beginning to end
		// if employee ID matches user provided ID, that employee
		// index will be returned
		for (i = 0; i < employees.size(); i++)
		{
			if (employees.get(i).getID() == id)
			{
				return i;
			}	
		}

		return -1;  // If employee is not found, return negative value
	}
	
	// Custom toString Method for easy printing
	public String toString()
	{
		int i;
		String s = null;
		
		// Traverse arrayList and use the Employee Class toString() method to print Employee information
		for (i = 0; i < employees.size(); i++)
		{
			if (i == 0)
				s = employees.get(i).toString() + "\n";
			else
				s = s + employees.get(i).toString() + "\n";
		}

		return s;
	}
}
