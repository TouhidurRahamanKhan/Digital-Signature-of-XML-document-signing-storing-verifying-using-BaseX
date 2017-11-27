package com.touhid.tabulation.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="course")
@XmlType(propOrder={"courseNo","courseTitle","totalCredit","totalTutorialMark","obtainedTutorialMark","totalFinalMark","obtainedFinalMark","totalObtainedMark","gradeObtained","gpObtained"})
public class Course {

	private String courseNo;
	private String courseTitle;
	private double totalCredit;
	private double totalTutorialMark;
	private double obtainedTutorialMark;
	private double totalFinalMark;
	private double obtainedFinalMark;
	private double totalObtainedMark;
	private double gradeObtained;
	private double gpObtained;
	
	
	public Course() {
		
	}


	public Course(String courseNo, String courseTitle, double totalCredit, double totalTutorialMark,
			double obtainedTutorialMark, double totalFinalMark, double obtainedFinalMark, double totalObtainedMark,
			double gradeObtained, double gpObtained) {
		super();
		this.courseNo = courseNo;
		this.courseTitle = courseTitle;
		this.totalCredit = totalCredit;
		this.totalTutorialMark = totalTutorialMark;
		this.obtainedTutorialMark = obtainedTutorialMark;
		this.totalFinalMark = totalFinalMark;
		this.obtainedFinalMark = obtainedFinalMark;
		this.totalObtainedMark = totalObtainedMark;
		this.gradeObtained = gradeObtained;
		this.gpObtained = gpObtained;
	}


	public String getCourseNo() {
		return courseNo;
	}


	@XmlElement(name="course_no")
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}


	public String getCourseTitle() {
		return courseTitle;
	}

	@XmlElement(name="course_title")
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}


	public double getTotalCredit() {
		return totalCredit;
	}

	@XmlElement(name="total_credit")
	public void setTotalCredit(double totalCredit) {
		this.totalCredit = totalCredit;
	}


	public double getTotalTutorialMark() {
		return totalTutorialMark;
	}

	@XmlElement(name="tutorial_marks_total")
	public void setTotalTutorialMark(double totalTutorialMark) {
		this.totalTutorialMark = totalTutorialMark;
	}


	public double getObtainedTutorialMark() {
		return obtainedTutorialMark;
	}

	@XmlElement(name="tutorial_marks_obtained")
	public void setObtainedTutorialMark(double obtainedTutorialMark) {
		this.obtainedTutorialMark = obtainedTutorialMark;
	}


	public double getTotalFinalMark() {
		return totalFinalMark;
	}

	@XmlElement(name="Final_marks_total")
	public void setTotalFinalMark(double totalFinalMark) {
		this.totalFinalMark = totalFinalMark;
	}


	public double getObtainedFinalMark() {
		return obtainedFinalMark;
	}

	@XmlElement(name="Final_marks_obtained")
	public void setObtainedFinalMark(double obtainedFinalMark) {
		this.obtainedFinalMark = obtainedFinalMark;
	}


	public double getTotalObtainedMark() {
		return totalObtainedMark;
	}

	@XmlElement(name="total_marks_obtained")
	public void setTotalObtainedMark(double totalObtainedMark) {
		this.totalObtainedMark = totalObtainedMark;
	}


	public double getGradeObtained() {
		return gradeObtained;
	}

	@XmlElement(name="Grade_obtained")
	public void setGradeObtained(double gradeObtained) {
		this.gradeObtained = gradeObtained;
	}


	public double getGpObtained() {
		return gpObtained;
	}

	@XmlElement(name="GP_obtained")
	public void setGpObtained(double gpObtained) {
		this.gpObtained = gpObtained;
	}


	@Override
	public String toString() {
		return "Course [courseNo=" + courseNo + ", courseTitle=" + courseTitle + ", totalCredit=" + totalCredit
				+ ", totalTutorialMark=" + totalTutorialMark + ", obtainedTutorialMark=" + obtainedTutorialMark
				+ ", totalFinalMark=" + totalFinalMark + ", obtainedFinalMark=" + obtainedFinalMark
				+ ", totalObtainedMark=" + totalObtainedMark + ", gradeObtained=" + gradeObtained + ", gpObtained="
				+ gpObtained + "]";
	}
	
	
	
	
}
