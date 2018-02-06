package UpSystem.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "log")

// Class that deals with the Report log events, including
// organization, structure, and storage of report logs
@XmlType(propOrder = {"name", "time", "hadUp", "notes"})
public class Report {
	private String name;
    private String time;
    private boolean hadUp;
    private String notes;

    // Method gets name from report
    //@XmlElement(name = "title")
    public String getName() {
        return name;
    }

    // Method sets name for report
    public void setName(String name) {
        this.name = name;
    }

    // Method gets time from report
	public String getTime() {
		return time;
	}

	// Method sets time for report
	public void setTime(String time) {
		this.time = time;
	}

	// Method gets up-status of employee report
	public boolean getHadUp() {
		return hadUp;
	}

	// Method sets up-status in report
	public void setHadUp(boolean hadUp) {
		this.hadUp = hadUp;
	}

	// Method gets Note information from report
	public String getNotes() {
		return notes;
	}

	// Method sets Note information for report
	public void setNotes(String notes) {
		this.notes = notes;
	}
}

