
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

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.contigo.dao.ClassRepository;
import co.com.sbaqueroadev.contigo.dao.StudentRepository;
import co.com.sbaqueroadev.contigo.model.ContigoClass;
import co.com.sbaqueroadev.contigo.model.Student;
import co.com.sbaqueroadev.contigo.model.StudentInterface;
import co.com.sbaqueroadev.contigo.model.Teacher;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * TeacherServiceImpl:  
 */
@Service
public class StudentServiceImpl implements StudentInterface {

	private static final int HOUR_IN_MILISECONDS = 3600000;
	private StudentRepository studentRepository;

	@Autowired
	private ClassRepository classRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.TeacherInterface#findByUsername(java.lang.String)
	 */
	@Override
	public Student findByUserId(String userId) {
		return studentRepository.findByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.TeacherInterface#getCurrentClass()
	 */
	@Override
	public ContigoClass getCurrentClass(Student student) {
		ContigoClass currentClass = null;
		Date now = new Date();
		for(String id:student.getClasses()){
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
	 * @param student
	 */
	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

}
