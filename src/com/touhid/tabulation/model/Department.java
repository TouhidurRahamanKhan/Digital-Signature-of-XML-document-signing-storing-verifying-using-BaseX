package com.touhid.tabulation.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement()
@XmlType(propOrder={"serialNo","educationYear","examineeName","fatherName","classRoll","examRoll","hallName","semester"})
public class Department {

	private String serialNo;
	private String educationYear;
	private String examineeName;
	private String fatherName;
	private String classRoll;
	private String examRoll;
	private String hallName;
	
	
	private Semester semester;
	
	public Department() {
		
	}

	public Department(String serialNo, String educationYear, String examineeName, String fatherName, String classRoll,
			String examRoll, String hallName, Semester semeter) {
		super();
		this.serialNo = serialNo;
		this.educationYear = educationYear;
		this.examineeName = examineeName;
		this.fatherName = fatherName;
		this.classRoll = classRoll;
		this.examRoll = examRoll;
		this.hallName = hallName;
		this.semester = semeter;
	}

	public String getSerialNo() {
		return serialNo;
	}

	@XmlElement(name="serial_no")
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getEducationYear() {
		return educationYear;
	}
	@XmlElement(name="Education_year")
	public void setEducationYear(String educationYear) {
		this.educationYear = educationYear;
	}

	public String getExamineeName() {
		return examineeName;
	}
	@XmlElement(name="Name_of_the_examinee")
	public void setExamineeName(String examineeName) {
		this.examineeName = examineeName;
	}

	public String getFatherName() {
		return fatherName;
	}
	@XmlElement(name="Father_name")
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getClassRoll() {
		return classRoll;
	}
	@XmlElement(name="class_roll")
	public void setClassRoll(String classRoll) {
		this.classRoll = classRoll;
	}

	public String getExamRoll() {
		return examRoll;
	}
	@XmlElement(name="Exam_roll")
	public void setExamRoll(String examRoll) {
		this.examRoll = examRoll;
	}

	public String getHallName() {
		return hallName;
	}
	@XmlElement(name="Hall_name")
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semeter) {
		this.semester = semeter;
	}

	@Override
	public String toString() {
		return "Department [serialNo=" + serialNo + ", educationYear=" + educationYear + ", examineeName="
				+ examineeName + ", fatherName=" + fatherName + ", classRoll=" + classRoll + ", examRoll=" + examRoll
				+ ", hallName=" + hallName + ", semester=" + semester + "]";
	}

	
	
	
}
