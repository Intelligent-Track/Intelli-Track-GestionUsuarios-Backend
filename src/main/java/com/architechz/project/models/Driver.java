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
@Table(	name = "Driver")
public class Driver {
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

	private Boolean directo;

	@Size(max = 50)
	@Email
	@NotBlank
	private String managerUsername;

	private String placaVehiculo;

	@NotBlank
	private String licencia;

	private String revisionAutoMec;

	public Driver() {
	}

	public Driver(@NotBlank String name, @NotBlank @Size(max = 50) @Email String username,
			@NotBlank Long document, @NotBlank Long phone, @NotBlank String job, @NotBlank String location,
			@NotBlank Boolean directo, @Size(max = 50) @Email @NotBlank String managerUsername, String placaVehiculo,
			@NotBlank String licencia, String revisionAutoMec) {
		
		this.name = name;
		this.username = username;
		this.document = document;
		this.phone = phone;
		this.job = job;
		this.location = location;
		this.directo = directo;
		this.managerUsername = managerUsername;
		this.placaVehiculo = placaVehiculo;
		this.licencia = licencia;
		this.revisionAutoMec = revisionAutoMec;
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


	public Boolean getDirecto() {
		return directo;
	}


	public void setDirecto(Boolean directo) {
		this.directo = directo;
	}


	public String getManagerUsername() {
		return managerUsername;
	}


	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}


	public String getPlacaVehiculo() {
		return placaVehiculo;
	}


	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}


	public String getLicencia() {
		return licencia;
	}


	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}


	public String getRevisionAutoMec() {
		return revisionAutoMec;
	}


	public void setRevisionAutoMec(String revisionAutoMec) {
		this.revisionAutoMec = revisionAutoMec;
	}




	public Long getDocument() {
		return document;
	}




	public void setDocument(Long document) {
		this.document = document;
	}

	
}
