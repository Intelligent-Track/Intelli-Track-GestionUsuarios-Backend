package com.architechz.project.models;
import javax.persistence.*;

@Entity
@Table(	name = "passwords")
public class PasswordRequests {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
    private String token;
    
    public PasswordRequests() {
    }

    public PasswordRequests(String username, String token) {
        this.username = username;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
