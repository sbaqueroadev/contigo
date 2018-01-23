
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.contigo.dao.ClassRepository;
import co.com.sbaqueroadev.contigo.dto.ContigoClassDTO;
import co.com.sbaqueroadev.contigo.model.ContigoClass;
import co.com.sbaqueroadev.contigo.model.ContigoClassInterface;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * TeacherServiceImpl:  
 */
@Service
public class ContigoClassServiceImpl implements ContigoClassInterface {

	
	@Autowired
	private ClassRepository classRepository;

	public ContigoClassServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.TeacherInterface#findByUsername(java.lang.String)
	 */
	@Override
	public ContigoClass findById(String id) {
		return classRepository.findById(id).get();
	}

	
	/**
	 * @param student
	 */
	@Override
	public ContigoClass save(ContigoClass cClass) {
		return classRepository.save(cClass);
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.ContigoClassInterface#findBySubject(co.com.sbaqueroadev.contigo.model.ContigoClass)
	 */
	@Override
	public List<ContigoClass> findBySubject(ContigoClass cClass) {
		return classRepository.findBySubject(cClass.getSubject());
	}
	
	
	
}
