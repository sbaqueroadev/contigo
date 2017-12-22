package co.com.sbaqueroadev.contigo.model.implementation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.sbaqueroadev.contigo.model.implementation.Privilege.Privileges;
import co.com.sbaqueroadev.contigo.model.implementation.Role.Roles;


@Document(collection="role")
public class Role {
	
	public static enum Roles{
		ADMIN("ROLE_ADMIN"),
		TEACHER("ROLE_TEACHER"),
		STUDENT("ROLE_STUDENT"),
		USER("ROLE_USER");
		
		public String rol;
		
		Roles(String name){
			this.rol = name;
		}
		
		public String getValue(){
			return this.rol;
		}
		
	}

	public static final String PREFIX = "ROLE_";

	@Id
	private String id;
	private String name;
	@DBRef
	private Collection<ApplicationUser> users;
	@DBRef
	private Collection<Privilege> privileges;

	public String getId() {
		return id;
	}

	/**
	 * @param name
	 * @param asList
	 */
	public Role(String name, List<Privilege> privileges) {
		this.name = name;
		this.privileges = privileges;
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
