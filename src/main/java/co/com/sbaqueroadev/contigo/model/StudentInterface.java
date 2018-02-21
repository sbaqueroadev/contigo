
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

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import co.com.sbaqueroadev.contigo.model.implementation.ContigoClass;
import co.com.sbaqueroadev.contigo.model.implementation.Student;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* TeacherInterface:  
*/
public interface StudentInterface {
	public Student findByUserId(String userId);
	
	public Student findById(String id);
	
	public ContigoClass getCurrentClass(Student teacher);
	/**
	 * @param student
	 * @return
	 */
	Student save(Student student);
	/**
	 * @param authorities
	 * @return
	 */
	boolean isStudent(Collection<GrantedAuthority> authorities);
}
