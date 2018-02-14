
/* Archivo: Space.java
* Fecha: 14/02/2018
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
* Space:  
*/
@Document(collection = "space")
public class Space {
	
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
	
	@Id
	private String id;
	//@DBRef
	private Date date;
	private int duration;
	private String status;
	//@DBRef
	@JsonBackReference
	private Teacher teacher;
	
	public Space() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/**
	 * @param spaces
	 * @return
	 */
	public static JSONArray toJSON(List<Space> spaces) {
		JSONArray data = new JSONArray();
		for(Space space:spaces){
			data.put(space.toJSON());
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