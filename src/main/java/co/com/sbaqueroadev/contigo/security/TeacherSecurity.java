
/* Archivo: TeacherSecurity.java
* Fecha: 26/12/2017
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
	
package co.com.sbaqueroadev.contigo.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.contigo.model.implementation.Teacher;
import co.com.sbaqueroadev.contigo.services.ApplicationUserServiceImpl;
import co.com.sbaqueroadev.contigo.services.TeacherServiceImpl;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* TeacherSecurity:  
*/

@Component
public class TeacherSecurity {
	
	@Autowired
	private TeacherServiceImpl teacherService;
	
	@Autowired
	private ApplicationUserServiceImpl applicationUserService;

	
	/**
	 * @return
	 */
	public Teacher isTeacherRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ApplicationUser user = 
				applicationUserService.findByUserName((String) authentication.getPrincipal());
		Collection<GrantedAuthority> authorities = 
				(Collection<GrantedAuthority>) authentication.getAuthorities();
		if( teacherService.isTeacher(authorities) ){
			Teacher teacher = teacherService.findByUserId(user.getId());
			return teacher;
		}else{
			return null;
		}
	} 
}
