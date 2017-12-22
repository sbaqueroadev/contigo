package co.com.sbaqueroadev.contigo.webservices;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.sbaqueroadev.contigo.model.Student;
import co.com.sbaqueroadev.contigo.model.Teacher;
import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.contigo.model.implementation.Privilege.Privileges;
import co.com.sbaqueroadev.contigo.services.ApplicationUserServiceImpl;
import co.com.sbaqueroadev.contigo.services.StudentServiceImpl;
import co.com.sbaqueroadev.contigo.services.TeacherServiceImpl;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private TeacherServiceImpl teacherService;
	private ApplicationUserServiceImpl applicationUserService;

	@Autowired
	private StudentServiceImpl studentService;

	public BoardController(TeacherServiceImpl teacherService,
			ApplicationUserServiceImpl applicationUserService) {
		this.teacherService = teacherService;
		this.applicationUserService = applicationUserService;
	}

	@RequestMapping(value = "/board/home", method = RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("panel");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ApplicationUser user = 
				applicationUserService.findByUserName((String) authentication.getPrincipal());
		Collection<GrantedAuthority> authorities = 
				(Collection<GrantedAuthority>) authentication.getAuthorities();
		if( isTeacher(authorities) ){
			mv.addObject("role", "teacher");
			Teacher teacher = teacherService.findByUserId(user.getId());
			logger.debug(teacher.toString());
			if(teacherService.getCurrentClass(teacher)!=null){
				mv.addObject("classId", teacherService.getCurrentClass(teacher).getId());
			}else{
				mv = new ModelAndView("redirect:/class/home");
			}
		}else {
			if( isStudent(authorities) ){
				mv.addObject("role", "student");
				Student student = studentService.findByUserId(user.getId());
				logger.debug(student.toString());
				if(studentService.getCurrentClass(student)!=null){
					mv.addObject("classId", studentService.getCurrentClass(student).getId());
				}else{
					mv = new ModelAndView("redirect:/class/home");
				}
			}
		}

		return mv;
	}

	private boolean isStudent(Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority a : authorities){
			if(a.getAuthority().equals(Privileges.CLASS_VIEWER.getValue().getName())){
				return true;
			}
		}
		return false;
	}

	private boolean isTeacher(Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority a : authorities){
			if(a.getAuthority().equals(Privileges.TEACH.getValue().getName())){
				return true;
			}
		}
		return false;
	}

	@MessageMapping("/boardUpdate/{classId}")
	@SendTo("/topic/updatingBoard/{classId}")
	public String gettingData(@DestinationVariable String classId,String data) throws Exception{
		return data;
	}

}
