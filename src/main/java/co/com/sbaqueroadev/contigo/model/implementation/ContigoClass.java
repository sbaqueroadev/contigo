
/* Archivo: ContigoClass.java
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
	
package co.com.sbaqueroadev.contigo.model.implementation;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* ContigoClass:  
*/

@Document(collection = "cClass")
public class ContigoClass extends Space{
	
	private Subject subject;
	private String topic;
	@JsonBackReference
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ContigoClass() {
		super();
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @param cClasses
	 * @return
	 */
	public static JSONArray toJSONL(List<ContigoClass> cClasses) {
		JSONArray data = new JSONArray();
		for(ContigoClass cC:cClasses){
			data.put(cC.toJSON());
		}
		return data;
	}

	/**
	 * @return
	 */
	private Object toJSON() {
		String data = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			data = objectMapper.writeValueAsString(this);
			JSONObject jObject = new JSONObject(data);
			return jObject;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new JSONObject();
		
	}
	
}
