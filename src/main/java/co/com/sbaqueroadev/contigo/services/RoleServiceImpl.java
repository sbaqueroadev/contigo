
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.contigo.dao.RoleRepository;
import co.com.sbaqueroadev.contigo.model.RoleInterface;
import co.com.sbaqueroadev.contigo.model.implementation.Role;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * TeacherServiceImpl:  
 */
@Service
public class RoleServiceImpl implements RoleInterface {

	@Autowired
	private RoleRepository roleRepository;

	public RoleServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.RoleInterface#findByName(java.lang.String)
	 */
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
