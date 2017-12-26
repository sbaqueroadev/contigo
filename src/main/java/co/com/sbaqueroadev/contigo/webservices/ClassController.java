package co.com.sbaqueroadev.contigo.webservices;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.sbaqueroadev.contigo.model.ContigoClass;
import co.com.sbaqueroadev.contigo.security.StudentSecurity;
import co.com.sbaqueroadev.contigo.security.TeacherSecurity;
import co.com.sbaqueroadev.contigo.services.ContigoClassServiceImpl;

@Controller
public class ClassController {

	@Autowired
	ContigoClassServiceImpl classServiceImpl;

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
	
	/*@RequestMapping(value = "/class/available/list", method = RequestMethod.GET)
	@ResponseBody
	public String accept(@RequestBody ContigoClass cClass) {
			if(studentSecurity.isStudentRole()!=null){
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
		}*/

}
