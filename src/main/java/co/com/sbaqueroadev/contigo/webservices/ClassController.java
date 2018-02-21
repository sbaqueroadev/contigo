package co.com.sbaqueroadev.contigo.webservices;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.sbaqueroadev.contigo.model.implementation.ContigoClass;
import co.com.sbaqueroadev.contigo.security.StudentSecurity;
import co.com.sbaqueroadev.contigo.security.TeacherSecurity;
import co.com.sbaqueroadev.contigo.services.ContigoClassServiceImpl;
import co.com.sbaqueroadev.contigo.services.StudentServiceImpl;
import co.com.sbaqueroadev.contigo.services.SubjectServiceImpl;
import co.com.sbaqueroadev.contigo.services.TeacherServiceImpl;

@Controller
public class ClassController {

	@Autowired
	ContigoClassServiceImpl classServiceImpl;
	
	@Autowired
	SubjectServiceImpl subjectServiceImpl;
	
	@Autowired
	TeacherServiceImpl teacherServiceImpl;
	
	@Autowired
	StudentServiceImpl studentServiceImpl;

	@Autowired
	private TeacherSecurity teacherSecurity;

	@Autowired
	private StudentSecurity studentSecurity;

	@RequestMapping(value = "/class/home", method = RequestMethod.GET)
	public String init(){
		return "classtest";
	}

	@RequestMapping(value = "/class/accept/{classId}", method = RequestMethod.POST)
	@ResponseBody
	public String accept(@PathVariable("classId") String id) {
		if(id!=null){
			if(teacherSecurity.isTeacherRole()!=null){
				ContigoClass cClass = classServiceImpl.findById(id);
				cClass.setStatus(ContigoClass.Status.ACTIVE.getName());
				classServiceImpl.save(cClass);
				return new JSONObject().put("type","OK")
						.put("msg","Class accepted successfully").toString();
			}else{
				return new JSONObject().put("type","error")
						.put("msg","Authorization error").toString();
			}
		}else{
			return new JSONObject().put("type","error")
					.put("msg","Class error").toString();
		}
	}

	@RequestMapping(value = "/class/available/list", method = RequestMethod.GET)
	@ResponseBody
	public String list(@RequestParam("subject") String subjectId,HttpServletResponse response) {
		if(studentSecurity.isStudentRole()!=null){
			ContigoClass cClass = new ContigoClass();
			cClass.setSubject(subjectServiceImpl.findById(subjectId));
			List<ContigoClass> cClasses = classServiceImpl.findBySubject(cClass);
			if(cClasses.size()>0){
				return new JSONObject().put("type","OK")
						.put("msg","Classes found")
						.put("data",ContigoClass.toJSONL(cClasses)).toString();
			}else{
				response.setStatus(HttpStatus.NOT_FOUND.value(), "No data");
				return new JSONObject().put("type","error")
						.put("msg","No DATA").toString();
			}
		}else{
			return new JSONObject().put("type","error")
					.put("msg","Authorization error").toString();
		}
	}
	
	@RequestMapping(value = "/class/teacher", method = RequestMethod.GET)
	@ResponseBody
	public String toTeach(@RequestParam("id") String teacherId, HttpServletResponse response) {
		//if(teacherSecurity.isTeacherRole()!=null){
			ContigoClass cClass = new ContigoClass();
			cClass.setTeacher(teacherServiceImpl.findById(teacherId));
			List<ContigoClass> cClasses = classServiceImpl.findByTeacherAndDate(cClass, new Date());
			if(cClasses.size()>0){
				return new JSONObject().put("type","OK")
						.put("msg","Classes found")
						.put("data",ContigoClass.toJSONL(cClasses)).toString();
			}else{
				response.setStatus(HttpStatus.NOT_FOUND.value(), "No data");
				return new JSONObject().put("type","error")
						.put("msg","No DATA").toString();
			}
		/*}else{
			return new JSONObject().put("type","error")
					.put("msg","Authorization error").toString();
		}*/
	}
	
	@RequestMapping(value = "/class/student", method = RequestMethod.GET)
	@ResponseBody
	public String toLearn(@RequestParam("id") String studentId, HttpServletResponse response) {
		//if(teacherSecurity.isTeacherRole()!=null){
			ContigoClass cClass = new ContigoClass();
			cClass.setStudent(studentServiceImpl.findById(studentId));
			List<ContigoClass> cClasses = classServiceImpl.findByStudentAndDate(cClass, new Date());
			if(cClasses.size()>0){
				return new JSONObject().put("type","OK")
						.put("msg","Classes found")
						.put("data",ContigoClass.toJSONL(cClasses)).toString();
			}else{
				response.setStatus(HttpStatus.NOT_FOUND.value(), "No data");
				return new JSONObject().put("type","error")
						.put("msg","No DATA").toString();
			}
		/*}else{
			return new JSONObject().put("type","error")
					.put("msg","Authorization error").toString();
		}*/
	}
}
