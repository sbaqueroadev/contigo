
/* Archivo: Subject.java
* Fecha: 22/12/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva de Sergio Baquero Ariza
* Su uso, alteracion, reproduccion o modificacion sin la debida
* consentimiento por escrito de Sergio Baquero Ariza.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dará lugar a las sanciones
* previstas en la Ley.
*/
	
package co.com.sbaqueroadev.contigo.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* Subject:  
*/
@Document(collection = "subject")
public class Subject {

	@Id
	private String id;
	private String name;
	
	public Subject() {
		super();
	}

	public Subject(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
