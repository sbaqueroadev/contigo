package co.com.sbaqueroadev.contigo.model.implementation;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class ApplicationUser {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    @DBRef
    private Role role;

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    
    
}