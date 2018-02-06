package UpSystem.model;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import UpSystem.views.Main_Screen;

import java.util.Timer;
import java.util.TimerTask;

//Class that sets, starts, resets, and displays the timeClock for the Uptime System
public class TimeClock {
	int secondsPassed = 0;
	int clockStartValueInSeconds;
	public boolean clockStarted;
	public int timeRemaining;
	private Main_Screen theView;
	private model_UpSystem theModel;
	
	// Constructor that sets the initialized time clock and takes parameters of
	// initial time and the View model
	public TimeClock(int timeAmntInMins, Main_Screen view) {
		this.clockStartValueInSeconds = timeAmntInMins*60;
		this.timeRemaining = clockStartValueInSeconds;
		this.theView = view;
		clockStarted = false;
		theView.timeTextField.setText(convertToDisplayTime(timeRemaining));
	}
	
	// Creates the Timer, and establishes a new Task
	Timer myTimer = new Timer();
	TimerTask task = new TimerTask() {
	
	public void run() {
			secondsPassed++;
			timeRemaining -= 1;
			
			theView.timeTextField.setText(convertToDisplayTime(timeRemaining));
			if(secondsPassed >= clockStartValueInSeconds) {
				theModel.rotateEmployees();
				resetTime();
			}
		}
	};
	
	// Method to start the TimeClock
	public void start() {
		myTimer.scheduleAtFixedRate(task, 0, 1000);
		clockStarted = true;
	}
	
	// Method adds the Model to the TimeClock data variables
	public void addModel(model_UpSystem theModel) {
		this.theModel = theModel;
	}
	
	// Method to stop and reset the TimeClock
	public void stop() {
		task.cancel();
		clockStarted = false;
	}
	
	// Method alters the setTime for the time clock
	// the new time will be applied based on rounded minutes
	public void setTime(int newTime) {
		newTime = newTime *60;
		secondsPassed = clockStartValueInSeconds - newTime;
		timeRemaining = newTime;
		theView.timeTextField.setText(convertToDisplayTime(timeRemaining));
	}
	
	// Method resets Clock time to standard interval value
	public void resetTime() {
		secondsPassed = 0;
		timeRemaining = clockStartValueInSeconds;
	}
	
	// Method converts System time to a displayable time clock
	// to track uptime
	public String convertToDisplayTime(int toConvert) {
		int mins = toConvert/60;
		int seconds = toConvert%60;
		String formattedTime = "";
		
		
		if(mins < 10)
			formattedTime = "0" + mins + ":";
		else
			formattedTime = "" + mins + ":";
		
		if(seconds < 10)
			formattedTime += "0" + seconds;
		else
			formattedTime += seconds;
		
		
		return formattedTime;
	}
}


