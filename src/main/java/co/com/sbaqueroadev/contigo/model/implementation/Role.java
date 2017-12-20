package co.com.sbaqueroadev.contigo.model.implementation;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="role")
public class Role {

	@Id
	private String id;
	private String name;
	private Collection<ApplicationUser> users;

	private Collection<Privilege> privileges;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role() {
		super();
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ApplicationUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<ApplicationUser> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}



}
