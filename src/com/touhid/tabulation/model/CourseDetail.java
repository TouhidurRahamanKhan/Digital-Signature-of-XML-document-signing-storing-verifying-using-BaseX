package com.touhid.tabulation.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="courseDetails")

public class CourseDetail {

	private ArrayList<Course> courses;
	
	
	public CourseDetail() {
		
	}


	public CourseDetail(ArrayList<Course> courses) {
		super();
		this.courses = courses;
	}


	public ArrayList<Course> getCourses() {
		return courses;
	}


	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}


	@Override
	public String toString() {
		return "CourseDetail [courses=" + courses + "]";
	}
	
	
}
