package UpSystem.main_function;
import java.util.*;

import java.awt.EventQueue;

import javax.swing.UIManager;

import UpSystem.model.Manager;
import UpSystem.model.TimeClock;
import UpSystem.model.SalesStaff;
import UpSystem.model.model_UpSystem;
import UpSystem.controller.controller_UpSystem;
import UpSystem.model.Employee;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Main Application Function - handles tries and catches
public class main_UpSystem {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {

					Main_Screen theView = new Main_Screen();
					
					//the int passed in is the amount of the timer to countdown
					TimeClock timeClock = new TimeClock(2, theView);
					model_UpSystem theModel = new model_UpSystem(theView, timeClock);
					timeClock.addModel(theModel);
					theView.addModel(theModel);
					controller_UpSystem theController = new controller_UpSystem(theView, theModel);
					

					
					// View is made visible to User
					theView.setVisible(true);
					
					// XML file input reader is created, and the Employee Roster file is established and parsed
					// as local document
					File fXmlFile = new File("roster.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);

					// Optional element, but normalization is recommended
					doc.getDocumentElement().normalize();

					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

					// Two lists established: 1) SalesStaff, 2) Managers
					NodeList sList = doc.getElementsByTagName("salesperson");
					NodeList mList = doc.getElementsByTagName("manager");
					
					// Display number of staff in definable positions
					System.out.println("Number of SalesStaff: " + sList.getLength());
					System.out.println("Number of Managers: " + mList.getLength());
					System.out.println("----------------------------");

					System.out.println("\n------------SalesStaff----------------");
					
					// Each SalesStaff Employee is read from the list and added to the EmployeeRoster in Model
					for (int temp = 0; temp < sList.getLength(); temp++) {
						Node nNode = sList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							
							//Add new employee (SalesStaff)
							Employee SalesPerson = new SalesStaff(eElement.getElementsByTagName("firstName").item(0).getTextContent(), 
									eElement.getElementsByTagName("lastName").item(0).getTextContent(), 
									Integer.parseInt(eElement.getAttribute("id")), 
									eElement.getElementsByTagName("password").item(0).getTextContent(),
									eElement.getElementsByTagName("phone").item(0).getTextContent());

							theModel.addToEmployeeList(SalesPerson);
							
							System.out.println(SalesPerson.toString());
						}
					}
					
					System.out.println("\n------------Management----------------");
					
					// Each Manager Employee is read from the list and added to the EmployeeRoster in Model
					for (int temp = 0; temp < mList.getLength(); temp++) {
						Node nNode = mList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							
							//add new employee (Manager)
							Manager newManager = new Manager(eElement.getElementsByTagName("firstName").item(0).getTextContent(),
									eElement.getElementsByTagName("lastName").item(0).getTextContent(), 
									Integer.parseInt(eElement.getAttribute("id")), 
									eElement.getElementsByTagName("password").item(0).getTextContent(),
									eElement.getElementsByTagName("phone").item(0).getTextContent());

							theModel.addToEmployeeList(newManager);
							
							System.out.println(newManager.toString());
							
						}
					}
					System.out.println("\n");
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
