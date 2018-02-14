
/* Archivo: TeacherInterface.java
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

import java.util.List;

import co.com.sbaqueroadev.contigo.model.implementation.Space;
import co.com.sbaqueroadev.contigo.model.implementation.Teacher;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* TeacherInterface:  
*/
public interface SpaceInterface {
	public Space findById(String id);
	/**
	 * @param cClass
	 * @return
	 */
	public Space save(Space space);
	
	/**
	 * 
	 * @param cClass
	 * @return
	 */
	public List<Space> findByTeacher(Teacher teacher);
}
