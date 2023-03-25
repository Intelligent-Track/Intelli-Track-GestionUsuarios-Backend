package com.architechz.project.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
    
    @NotBlank
    private String name;

    @NotBlank
	@Size(max = 50)
	@Email
	private String username;

    @NotBlank
	@Size(max = 120,min = 10)
	private String password;

  private Set<String> role;

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

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}