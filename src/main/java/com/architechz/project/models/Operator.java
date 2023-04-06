package com.architechz.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(	name = "operadores")
public class Operator {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
    private String name;

	
	@Size(max = 50)
	@Email
	private String username;

	
	private Long document;

	
	private Long phone;

	
	private String job;

	
	private String location;

	@Size(max = 50)
	@Email
	private String managerUsername;

	public Operator() {
	}

	public Operator( String name,  @Size(max = 50) @Email String username,
			 Long document,  Long phone,  String job,  String location,
			@Size(max = 50) @Email  String managerUsername) {
		
		this.name = name;
		this.username = username;
		this.document = document;
		this.phone = phone;
		this.job = job;
		this.location = location;
		this.managerUsername = managerUsername;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getDocument() {
		return document;
	}

	public void setDocument(Long document) {
		this.document = document;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}

	
























}
