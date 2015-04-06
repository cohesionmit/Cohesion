package com.example.cohesion;

import java.util.ArrayList;
import java.util.Map;

public class Users {
	
	String firstname;
	String lastname;
	double location;
	Map<String, String> courses;
	
	public Users(String firstname, String lastname, double location, Map<String, String> courses) {
		this.firstname=firstname;
		this.lastname=lastname;
		this.location=location;
		this.courses=courses;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public double getLocation() {
		return location;
	}
	
	public Map<String,String> getCourses() {
		return courses;
	}
}
