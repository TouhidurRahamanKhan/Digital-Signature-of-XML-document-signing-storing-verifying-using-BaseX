package com.touhid.tabulation.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Jahangirnagar_university")
public class University {

	private Department department;
	
	public University() {
		
	}

	public University(Department department) {
		super();
		this.department = department;
	}

	public Department getDepartment() {
		return department;
	}

	@XmlElement(name="Dept_of_computer_science_and_Engineering")
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "University [department=" + department + "]";
	}
	
	
}
