package co.com.sbaqueroadev.contigo.webservices;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.sbaqueroadev.contigo.model.ContigoClass;
import co.com.sbaqueroadev.contigo.services.ContigoClassServiceImpl;

@Controller
public class ClassController {

	@Autowired
	ContigoClassServiceImpl classServiceImpl;
	
	@RequestMapping(value = "/class/home", method = RequestMethod.GET)
	public String init(){
		return "classtest";
	}
	
	@RequestMapping(value = "/class/accept/{classId}", method = RequestMethod.GET)
	public String accept(@PathVariable("classId") String id, HttpServletRequest request) {
    if(id!=null){
			ContigoClass cClass = classServiceImpl.findById(id);
			cClass.setStatus(ContigoClass.Status.ACTIVE.getName());
			classServiceImpl.save(cClass);
		}
    String referer = request.getHeader("Referer");
    return "redirect:"+ referer;
	}

}
