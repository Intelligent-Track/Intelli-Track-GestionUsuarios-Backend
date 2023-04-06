package com.architechz.project.payload.RegisterRequests;
import javax.validation.constraints.*;

public class ClienteRequest {
    
    @NotBlank
    private String name;

	@NotBlank
	@Size(max = 50)
	@Email
	private String username;

    @NotBlank
	@Size(max = 120,min = 10)
	private String password;

   
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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