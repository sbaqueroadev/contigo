
/* Archivo: TeacherServiceImpl.java
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
	
package co.com.sbaqueroadev.contigo.services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.contigo.dao.ClassRepository;
import co.com.sbaqueroadev.contigo.dao.TeacherRepository;
import co.com.sbaqueroadev.contigo.model.ContigoClass;
import co.com.sbaqueroadev.contigo.model.Teacher;
import co.com.sbaqueroadev.contigo.model.TeacherInterface;
import co.com.sbaqueroadev.contigo.model.implementation.Privilege.Privileges;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* TeacherServiceImpl:  
*/
@Service
public class TeacherServiceImpl implements TeacherInterface {

	private static final int HOUR_IN_MILISECONDS = 3600000;
private TeacherRepository teacherRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.TeacherInterface#findByUsername(java.lang.String)
	 */
	@Override
	public Teacher findByUserId(String userId) {
		return teacherRepository.findByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.TeacherInterface#getCurrentClass()
	 */
	@Override
	public ContigoClass getCurrentClass(Teacher teacher) {
		ContigoClass currentClass = null;
		Date now = new Date();
		for(ContigoClass c:teacher.getClasses()){
			String id = c.getId();
			ContigoClass cClass = classRepository.findById(id).get();
			Date from = new Date(cClass.getDate().getTime());
			Date to = new Date(cClass.getDate().getTime());
			to.setTime(cClass.getDate().getTime()+cClass.getDuration()*HOUR_IN_MILISECONDS);
			if(from.before(now) && now.before(to)){
					currentClass = cClass;
					break;
			}
		};
		return currentClass;
	}

	/**
	 * @param teacher
	 */
	@Override
	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);		
	}

	public boolean isTeacher(Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority a : authorities){
			if(a.getAuthority().equals(Privileges.TEACH.getValue().getName())){
				return true;
			}
		}
		return false;
	}
	
}
