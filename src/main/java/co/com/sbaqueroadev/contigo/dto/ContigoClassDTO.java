
/* Archivo: ContigoClassDTO.java
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
	
package co.com.sbaqueroadev.contigo.dto;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* ContigoClass:  
*/
public class ContigoClassDTO extends SpaceDTO{
	
	public static enum Status{
		ACTIVE("active"), ASKED("asked");
		
		private String name;

		Status(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
	}
	
	private SubjectDTO subject;
	private String topic;
	private StudentDTO student;
	
	public ContigoClassDTO() {
		super();
	}

	public SubjectDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
}
