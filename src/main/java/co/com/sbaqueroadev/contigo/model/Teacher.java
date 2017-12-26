
/* Archivo: Teacher.java
* Fecha: 21/12/2017
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
	
package co.com.sbaqueroadev.contigo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* Teacher:  
*/
@Document(collection = "teacher")
public class Teacher {
	
	@Id
	private String id;
	private String name;
	private String userId;
	@DBRef
	@JsonManagedReference
	private List<ContigoClass> classes = new ArrayList<>();
	@DBRef
	private List<Subject> subjects = new ArrayList<>();
	
	public Teacher() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<ContigoClass> getClasses() {
		return classes;
	}
	public void setClasses(List<ContigoClass> classes) {
		this.classes = classes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public String toString(){
		return "[Teacher: { id: "+this.id+", name: "+this.name
				+", classes: "+this.classes.size()+"}]";
		
	}

	/**
	 * @param cClass
	 */
	public void addClassIfNotFound(ContigoClass cClass) {
		if(this.getClasses().size()>0){
			for(ContigoClass cls:this.getClasses()){
				if(cls.getId().equals(cClass.getId())){
					return;
				}
			}
		}
	this.classes.add(cClass);
	}
	
	/**
	 * @param subject
	 */
	public boolean addSubjectIfNotFound(Subject subject) {
		if(this.getSubjects().size()>0){
			for(Subject sbj:this.getSubjects()){
				if(sbj.getId().equals(subject.getId())){
					return false;
				}
			}
		}
	this.subjects.add(subject);
	return true;
	}

	/**
	 * @param subject
	 * @return
	 */
	public boolean removeSubjectIfFound(Subject subject) {
		Subject found = null;
		if(this.getSubjects().size()>0){
			for(Subject sbj:this.getSubjects()){
				if(sbj.getId().equals(subject.getId())){
					found = sbj;
					break;
				}
			}
		}
		if(found!=null){
			this.getSubjects().remove(found);
			return true;	
		}
		return false;
	}
	
}
