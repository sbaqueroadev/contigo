package co.com.sbaqueroadev.contigo.model.implementation;

import java.util.Collection;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="privilege")
public class Privilege {
	
	public static enum Privileges{
		READ("READ_PRIVILEGE"),
		WRITE("WRITE_PRIVILEGE"),
		TEACH("TEACH_CLASS_PRIVILEGE"),
		CLASS_VIEWER("VIEW_CLASS_PRIVILEGE"), 
		MANAGE_USERS("MANAGE_USERS"), 
		STUDENT("STUDENT_PRIVILEGE");
		
		private Privilege value;
		
		private Privileges(String value) {
			this.value = new Privilege(value);
		}

		/**
		 * @return
		 */
		public Privilege getValue() {
			return this.value;
		}
	}

	@Id
	private String id;

	private String name;
	@DBRef
	private Collection<Role> roles;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Privilege() {
		super();
	}

	public Privilege(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


}
