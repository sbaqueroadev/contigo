package co.com.sbaqueroadev.contigo.webservices;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.sbaqueroadev.contigo.model.ContigoClass;
import co.com.sbaqueroadev.contigo.model.Student;
import co.com.sbaqueroadev.contigo.model.Subject;
import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.contigo.security.StudentSecurity;
import co.com.sbaqueroadev.contigo.security.TeacherSecurity;
import co.com.sbaqueroadev.contigo.services.ApplicationUserServiceImpl;
import co.com.sbaqueroadev.contigo.services.StudentServiceImpl;
import co.com.sbaqueroadev.contigo.services.SubjectServiceImpl;
import co.com.sbaqueroadev.contigo.services.TeacherServiceImpl;
import static co.com.sbaqueroadev.contigo.utils.LayerCommunicationUtils.mapAsJSONString;

@Controller
public class StudentController {

	@Autowired
	private StudentSecurity studentSecurity;
	
	@Autowired
	private TeacherServiceImpl teacherService;
	
	@Autowired
	private ApplicationUserServiceImpl applicationUserService;

	@Autowired
	private TeacherSecurity teacherSecurity;
	
	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private SubjectServiceImpl subjectServiceImpl;
	
	@RequestMapping(value = "/student/home", method = RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView("student/home");
		Student student = studentSecurity.isStudentRole();
		if(student != null){
			mv.addObject("student", mapAsJSONString(student));
			ContigoClass current = studentService.getCurrentClass(student);
			List<Subject> subjects = subjectServiceImpl.findAll();
			if(!subjects.isEmpty()){
				mv.addObject("subjects",mapAsJSONString(subjects));
			}
			if(current!=null)
				mv.addObject("currentClass", current);
		}else{
			if( teacherSecurity.isTeacherRole()!=null ){
				mv = new ModelAndView("redirect:/teacher/home");
			}else{
				mv = new ModelAndView("/home");
			}
		}
		return mv;
	}

}
