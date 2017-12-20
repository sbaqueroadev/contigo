package co.com.sbaqueroadev.contigo.model.implementation;

import java.util.Collection;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="privilege")
public class Privilege {

	@Id
	private String id;

	private String name;
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
