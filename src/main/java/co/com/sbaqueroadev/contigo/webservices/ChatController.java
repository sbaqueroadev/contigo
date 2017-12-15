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
public class ChatController {
	
	@MessageMapping("/write")
	@SendTo("/topic/reading")
	public String gettingData(String data) throws Exception{
		return data;
	}
	
}
