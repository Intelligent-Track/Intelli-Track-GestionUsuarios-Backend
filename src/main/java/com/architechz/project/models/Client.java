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
@Table(	name = "cliente")
public class Client {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
    private String name;

	@NotBlank
	@Size(max = 50)
	@Email
	private String username;

	private Long document;

	private Long phone;

	@NotBlank
	private String job;

	@NotBlank
	private String location;

	private String nit;

	private String companyName;
	
	private Boolean adm;

	@Size(max = 50)
	@Email
	@NotBlank
	private String managerUsername;

	public Client() {
	}

	public Client( @NotBlank String name, @NotBlank @Size(max = 50) @Email String username,
			@NotBlank Long document, @NotBlank Long phone, @NotBlank String job, @NotBlank String location, String nit,
			String companyName, Boolean adm, @Size(max = 50) @Email @NotBlank String managerUsername) {
		
		this.name = name;
		this.username = username;
		this.document = document;
		this.phone = phone;
		this.job = job;
		this.location = location;
		this.nit = nit;
		this.companyName = companyName;
		this.adm = adm;
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

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Boolean getAdm() {
		return adm;
	}

	public void setAdm(Boolean adm) {
		this.adm = adm;
	}

	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}



	public Long getDocument() {
		return document;
	}



	public void setDocument(Long document) {
		this.document = document;
	}

	
}
