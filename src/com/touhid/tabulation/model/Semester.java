package com.touhid.tabulation.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="semester")
@XmlType(propOrder={"courseDetail","gpa","previousGPA","cGPA"})
public class Semester {

	private CourseDetail courseDetail;
	private String gpa;
	private String previousGPA;
	private String cGPA;
	
	public Semester() {
		
	}

	public Semester(CourseDetail courseDetail, String gpa, String previousGPA, String cGPA) {
		super();
		this.courseDetail = courseDetail;
		this.gpa = gpa;
		this.previousGPA = previousGPA;
		this.cGPA = cGPA;
	}

	public CourseDetail getCourseDetail() {
		return courseDetail;
	}

	public void setCourseDetail(CourseDetail courseDetail) {
		this.courseDetail = courseDetail;
	}

	public String getGpa() {
		return gpa;
	}

	@XmlElement(name="GPA")
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public String getPreviousGPA() {
		return previousGPA;
	}
	@XmlElement(name="previous_GPA")
	public void setPreviousGPA(String previousGPA) {
		this.previousGPA = previousGPA;
	}

	public String getcGPA() {
		return cGPA;
	}
	@XmlElement(name="CGPA")
	public void setcGPA(String cGPA) {
		this.cGPA = cGPA;
	}

	@Override
	public String toString() {
		return "Semester [courseDetail=" + courseDetail + ", gpa=" + gpa + ", previousGPA=" + previousGPA + ", cGPA="
				+ cGPA + "]";
	}
	
	
	
}
