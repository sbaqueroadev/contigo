
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
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.contigo.dao.ClassRepository;
import co.com.sbaqueroadev.contigo.model.ContigoClassInterface;
import co.com.sbaqueroadev.contigo.model.implementation.ContigoClass;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * TeacherServiceImpl:  
 */
@Service
public class ContigoClassServiceImpl implements ContigoClassInterface {

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

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

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.ContigoClassInterface#findBySubject(co.com.sbaqueroadev.contigo.model.ContigoClass)
	 */
	@Override
	public List<ContigoClass> findByTeacherAndDate(ContigoClass cClass, Date date) {
		date.setTime(date.getTime()-(60000*60*24*365));
		MatchOperation filterDate = new MatchOperation(new Criteria("date").gte(date)
				.and("teacher._id").is(new ObjectId(cClass.getTeacher().getId())));
		SortOperation sortByDate = new SortOperation(new Sort(Direction.ASC, "date"));

		Aggregation aggregation = Aggregation.newAggregation(
				filterDate, sortByDate);
		AggregationResults<ContigoClass> ar = mongoTemplate.aggregate(
				aggregation, ContigoClass.class, ContigoClass.class);
				return ar.getMappedResults();

	}
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.contigo.model.ContigoClassInterface#findBySubject(co.com.sbaqueroadev.contigo.model.ContigoClass)
	 */
	@Override
	public List<ContigoClass> findByStudentAndDate(ContigoClass cClass, Date date) {
		date.setTime(date.getTime()-(60000*60*24*365));
		MatchOperation filterDate = new MatchOperation(new Criteria("date").gte(date)
				.and("student._id").is(new ObjectId(cClass.getStudent().getId())));
		SortOperation sortByDate = new SortOperation(new Sort(Direction.ASC, "date"));

		Aggregation aggregation = Aggregation.newAggregation(
				filterDate, sortByDate);
		AggregationResults<ContigoClass> ar = mongoTemplate.aggregate(
				aggregation, ContigoClass.class, ContigoClass.class);
				return ar.getMappedResults();

	}

}
