package com.architechz.project.models;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
		})
public class User {
////////////////////////////////////atributes USERS///////////////////////////////////////////////////////////////////////////////////////////
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank
    private String name;

    @NotBlank
	@Size(max = 50)
	@Email
	private String username;

    @NotBlank
	@Size(max = 120,min = 10)
	private String password;
    

//////////////////////////////////////RELATION TO ROLES///////////////////////////////////////////////////////////////////////////////////

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

//////////////////////////////////////////FF///////////////////////////////////////////////////////////////////////////////////////////////
public User() {
}

public User(String username, String password, String name) {
    this.username = username;
    this.password = password;
    this.name = name;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
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

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}


public Set<Role> getRoles() {
    return roles;
}

public void setRoles(Set<Role> roles) {
    this.roles = roles;
}












}
