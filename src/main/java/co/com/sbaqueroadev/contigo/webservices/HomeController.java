package co.com.sbaqueroadev.contigo.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Redirects to /home/ as the home page.
	 * 
	 * @return View represented by a JSP file.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init() {
		logger.info("Getting home view");
		return "redirect:/teacher/home";
	}
	
	/**
	 * @return Home View represented by a JSP file.
	 */
	@RequestMapping(value = "/home/", method = RequestMethod.GET)
	public String home() {
		return "classtest";
	}

}
