package co.com.sbaqueroadev.contigo.webservices;

import java.util.Collection;

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
import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.contigo.services.ApplicationUserServiceImpl;
import co.com.sbaqueroadev.contigo.services.StudentServiceImpl;
import co.com.sbaqueroadev.contigo.services.TeacherServiceImpl;

@Controller
public class StudentController {

	@Autowired
	private TeacherServiceImpl teacherService;
	
	@Autowired
	private ApplicationUserServiceImpl applicationUserService;

	@Autowired
	private StudentServiceImpl studentService;
	
	@RequestMapping(value = "/student/home", method = RequestMethod.GET)
	public ModelAndView init(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ApplicationUser user = 
				applicationUserService.findByUserName((String) authentication.getPrincipal());
		Collection<GrantedAuthority> authorities = 
				(Collection<GrantedAuthority>) authentication.getAuthorities();
		ModelAndView mv = new ModelAndView("student/home");
		if(studentService.isStudent(authorities)){
			Student student = studentService.findByUserId(user.getId());
			mv.addObject("student", student);
			ContigoClass current = studentService.getCurrentClass(student);
			if(current!=null)
				mv.addObject("currentClass", current);
		}else{
			if( teacherService.isTeacher(authorities) ){
				mv = new ModelAndView("redirect:/teacher/home");
			}else{
				mv = new ModelAndView("/home");
			}
		}
		return mv;
	}

}
