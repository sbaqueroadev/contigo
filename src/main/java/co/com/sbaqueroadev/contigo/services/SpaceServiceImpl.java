
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
import co.com.sbaqueroadev.contigo.dao.SpaceRepository;
import co.com.sbaqueroadev.contigo.dto.ContigoClassDTO;
import co.com.sbaqueroadev.contigo.model.ContigoClassInterface;
import co.com.sbaqueroadev.contigo.model.SpaceInterface;
import co.com.sbaqueroadev.contigo.model.implementation.ContigoClass;
import co.com.sbaqueroadev.contigo.model.implementation.Space;
import co.com.sbaqueroadev.contigo.model.implementation.Teacher;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * TeacherServiceImpl:  
 */
@Service
public class SpaceServiceImpl implements SpaceInterface {

	
	@Autowired
	private SpaceRepository spaceRepository;

	public SpaceServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.SpaceInterface#findById(java.lang.String)
	 */
	@Override
	public Space findById(String id) {
		return spaceRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.SpaceInterface#save(co.com.sbaqueroadev.contigo.model.implementation.Space)
	 */
	@Override
	public Space save(Space space) {
		return spaceRepository.save(space);
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.SpaceInterface#findByTeacher(co.com.sbaqueroadev.contigo.model.implementation.Teacher)
	 */
	@Override
	public List<Space> findByTeacher(Teacher teacher) {
		return spaceRepository.findByTeacher(teacher);
	}
	
}
