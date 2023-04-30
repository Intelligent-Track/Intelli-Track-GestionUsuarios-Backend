package com.architechz.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

	@NotNull
	private Long document;
	
	@NotNull
	private Long phone;

	@NotBlank
	private String job;

	@NotBlank
	private String location;

	@Size(max = 50)
	@Email
	@NotBlank
	private String managerUsername;

	@NotBlank
	private String vehiclePlate;

	@Lob
	private byte[] license;

	@Lob
	private byte[] mecReview;

	@NotNull
	private boolean external;

	public Driver() {
	}

	public Driver(@NotBlank String name, @NotBlank @Size(max = 50) @Email String username, @NotNull Long document,
			@NotNull Long phone, @NotBlank String job, @NotBlank String location,
			@Size(max = 50) @Email @NotBlank String managerUsername, @NotBlank String vehiclePlate, byte[] license,
			byte[] mecReview, @NotNull boolean external) {
		this.name = name;
		this.username = username;
		this.document = document;
		this.phone = phone;
		this.job = job;
		this.location = location;
		this.managerUsername = managerUsername;
		this.vehiclePlate = vehiclePlate;
		this.license = license;
		this.mecReview = mecReview;
		this.external = external;
	}

	public Driver(Long id, @NotBlank String name, @NotBlank @Size(max = 50) @Email String username,
			@NotBlank Long document, @NotBlank Long phone, @NotBlank String job, @NotBlank String location,
			@Size(max = 50) @Email @NotBlank String managerUsername, @NotBlank String vehiclePlate, byte[] license,
			byte[] mecReview, @NotNull boolean external) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.document = document;
		this.phone = phone;
		this.job = job;
		this.location = location;
		this.managerUsername = managerUsername;
		this.vehiclePlate = vehiclePlate;
		this.license = license;
		this.mecReview = mecReview;
		this.external = external;
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

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public byte[] getLicense() {
		return license;
	}

	public void setLicense(byte[] license) {
		this.license = license;
	}

	public byte[] getMecReview() {
		return mecReview;
	}

	public void setMecReview(byte[] mecReview) {
		this.mecReview = mecReview;
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}
	
}
