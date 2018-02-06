package UpSystem.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "de.vogella.xml.jaxb.model")
public class Logs {
	
	// XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "reportList")
    
    // XmlElement sets the name of the entities
    @XmlElement(name = "report")
    private ArrayList<Report> reportList;
    private String locationName;
    private String locationAddress;

    // Method creates the List that we will use to sort input
    public void setBookList(ArrayList<Report> reportList) {
        this.reportList = reportList;
    }

    // Method returns the List of entities and their associated data
    public ArrayList<Report> getBooksList() {
        return this.reportList;
    }

    // Method returns the name location for the XML File
    public String getName() {
        return locationName;
    }

    // Method sets the name location for the XML File
    public void setName(String newName) {
        this.locationName = newName;
    }

    // Method gets the Location address
    public String getLocationAddress() {
        return this.locationAddress;
    }

    // Method sets the Location address
    public void setLocationAddress(String newAddress) {
        this.locationAddress = newAddress;
    }
}
