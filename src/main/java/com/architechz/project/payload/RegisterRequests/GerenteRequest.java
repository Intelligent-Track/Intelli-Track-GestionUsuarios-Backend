package com.architechz.project.payload.RegisterRequests;
import javax.validation.constraints.*;

public class GerenteRequest {
    
    

	@Size(max = 120,min = 10)
	private String password;

    @NotBlank
    private String name;

	@NotBlank
	@Size(max = 50)
	@Email
	private String username;

    
	private Long document;

	
	private Long phone;

	
	private String job;

	@NotBlank
	private String location;

	private Boolean GerenteGeneral;

	@Size(max = 50)
	@Email
	private String managerUsername;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getGerenteGeneral() {
        return GerenteGeneral;
    }

    public void setGerenteGeneral(Boolean gerenteGeneral) {
        GerenteGeneral = gerenteGeneral;
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