
/* Archivo: SubjectRepository.java
* Fecha: 22/12/2017
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
	
package co.com.sbaqueroadev.contigo.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.sbaqueroadev.contigo.model.Subject;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* SubjectRepository:  
*/
public interface SubjectRepository extends MongoRepository<Subject, String>{

	/**
	 * @param subjectId
	 * @return
	 */
	public Optional<Subject> findById(String subjectId);
	public Subject save( Subject subject );
	/**
	 * @param name
	 * @return
	 */
	public Subject findByName(String name);	

}
