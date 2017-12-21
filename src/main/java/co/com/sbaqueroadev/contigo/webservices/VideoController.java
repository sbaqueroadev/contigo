package co.com.sbaqueroadev.contigo.webservices;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.webjars.RequireJS;

@Controller
public class VideoController {
	
	@ResponseBody
	@RequestMapping(value = "/webjarsjs", produces = "application/javascript")
	public String webjarjs() {
	    return RequireJS.getSetupJavaScript("/webjars/");
	}
	
	@RequestMapping(value = "/voice/home", method = RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("voice");
		return mv;
	}
	
	@RequestMapping(value = "/voice/home2", method = RequestMethod.GET)
	public ModelAndView init2(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("voice2");
		return mv;
	}
	
	@MessageMapping("/show/{classId}")
	@SendTo("/topic/view/{classId}")
	public Object gettingData(Object data) throws Exception{
		//System.out.println("Video data received"+data.toString());
		return data;
	}
	
}
