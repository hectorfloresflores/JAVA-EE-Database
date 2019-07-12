package com.hectorflores;

public class Student {

	public Student(String name, String lastName, String email) {
		
		this.name = name;
		this.lastName = lastName;
		this.email = email;
	}

	private String name;
	private String lastName;
	private String email;
	private int id;
	
	public Student(String name, String lastName, String email, int id){
		
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", lastName=" + lastName + ", email=" + email + ", id=" + id + "]";
	}
	
	
}
