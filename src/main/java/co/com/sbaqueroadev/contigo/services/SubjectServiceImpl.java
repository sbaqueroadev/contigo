
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
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.contigo.dao.ClassRepository;
import co.com.sbaqueroadev.contigo.dao.StudentRepository;
import co.com.sbaqueroadev.contigo.dao.SubjectRepository;
import co.com.sbaqueroadev.contigo.model.SubjectInterface;
import co.com.sbaqueroadev.contigo.model.implementation.ContigoClass;
import co.com.sbaqueroadev.contigo.model.implementation.Student;
import co.com.sbaqueroadev.contigo.model.implementation.Subject;
import co.com.sbaqueroadev.contigo.model.implementation.Privilege.Privileges;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * TeacherServiceImpl:  
 */
@Service
public class SubjectServiceImpl implements SubjectInterface {

	private static final int HOUR_IN_MILISECONDS = 3600000;
	
	@Autowired
	private SubjectRepository subjectRepository;

	public SubjectServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.SubjectInterface#findById(java.lang.String)
	 */
	@Override
	public Subject findById(String subjectId) {
		try{
			return subjectRepository.findById(subjectId).get();
		}catch (NoSuchElementException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.SubjectInterface#save(co.com.sbaqueroadev.contigo.model.Subject)
	 */
	@Override
	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}

	/**
	 * @return
	 */
	public List<Subject> findAll() {
		return subjectRepository.findAll();
	}

}
