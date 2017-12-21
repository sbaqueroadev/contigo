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

import org.springframework.data.mongodb.core.mapping.Document;

import co.com.sbaqueroadev.contigo.model.implementation.Privilege.Privileges;


@Document(collection="role")
public class Role {
	
	public static enum Roles{
		ADMIN("ROLE_ADMIN",Arrays.asList(
				Privileges.READ.getValue(), Privileges.WRITE.getValue(), Privileges.MANAGE_USERS.getValue())),
		TEACHER("ROLE_TEACHER",Arrays.asList(
				Privileges.READ.getValue(), Privileges.WRITE.getValue(), Privileges.TEACH.getValue())),
		STUDENT("ROLE_STUDENT",Arrays.asList(
				Privileges.READ.getValue(), Privileges.WRITE.getValue(), Privileges.CLASS_VIEWER.getValue())),
		USER("ROLE_USER",Arrays.asList(Privileges.READ.getValue()));
		
		public Role rol;
		
		Roles(String name,List<Privilege> privileges){
			this.rol = new Role(name);
			this.rol.setPrivileges(privileges);
		}
		
		public Role getValue(){
			return this.rol;
		}
		
	}

	@Id
	private String id;
	private String name;
	private Collection<ApplicationUser> users;

	private Collection<Privilege> privileges;

	public String getId() {
		return id;
	}

	/**
	 * @param string
	 * @param asList
	 */
	private void ADMIN(String string, List<Privilege> asList) {
		// TODO Auto-generated method stub
		
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
