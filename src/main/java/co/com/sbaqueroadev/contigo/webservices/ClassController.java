package co.com.sbaqueroadev.contigo.webservices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClassController {

	@RequestMapping(value = "/class/home", method = RequestMethod.GET)
	public String init(){
		return "classtest";
	}

}
