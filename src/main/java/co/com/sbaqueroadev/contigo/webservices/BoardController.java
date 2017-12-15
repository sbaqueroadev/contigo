package co.com.sbaqueroadev.contigo.webservices;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	@RequestMapping(value = "/board/home", method = RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("panel");
		Collection<GrantedAuthority> authorities = 
				(Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		if( isTeacher(authorities) ){
			mv.addObject("role", "teacher");
		}else {
			if( isStudent(authorities) ){
				mv.addObject("role", "student");
			}
		}
			
		return mv;
	}
	
	private boolean isStudent(Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority a : authorities){
			if(a.getAuthority().equals("VIEW_CLASS_PRIVILEGE")){
				return true;
			}
		}
		return false;
	}

	private boolean isTeacher(Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority a : authorities){
			if(a.getAuthority().equals("TEACH_CLASS_PRIVILEGE")){
				return true;
			}
		}
		return false;
	}

	@MessageMapping("/boardUpdate")
	@SendTo("/topic/updatingBoard")
	public String gettingData(String data) throws Exception{
		return data;
	}
	
}
