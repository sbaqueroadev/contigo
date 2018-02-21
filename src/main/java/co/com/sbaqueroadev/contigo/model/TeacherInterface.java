
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

import co.com.sbaqueroadev.contigo.model.implementation.ContigoClass;
import co.com.sbaqueroadev.contigo.model.implementation.Teacher;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* TeacherInterface:  
*/
public interface TeacherInterface {
	public Teacher findByUserId(String userId);
	public ContigoClass getCurrentClass(Teacher teacher);
	/**
	 * @param teacher
	 * @return
	 */
	public Teacher save(Teacher teacher);
	/**
	 * @param id
	 * @return
	 */
	public Teacher findById(String id);
}
