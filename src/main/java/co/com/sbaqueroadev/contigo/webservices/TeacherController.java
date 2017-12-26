package co.com.sbaqueroadev.contigo.webservices;

import static co.com.sbaqueroadev.contigo.utils.LayerCommunicationUtils.mapAsJSONString;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.com.sbaqueroadev.contigo.model.ContigoClass;
import co.com.sbaqueroadev.contigo.model.Subject;
import co.com.sbaqueroadev.contigo.model.Teacher;
import co.com.sbaqueroadev.contigo.security.StudentSecurity;
import co.com.sbaqueroadev.contigo.security.TeacherSecurity;
import co.com.sbaqueroadev.contigo.services.SubjectServiceImpl;
import co.com.sbaqueroadev.contigo.services.TeacherServiceImpl;

@Controller
public class TeacherController {
	
	@Autowired
	private TeacherServiceImpl teacherService;
	
	@Autowired
	private SubjectServiceImpl subjectServiceImpl;

	@Autowired
	private TeacherSecurity teacherSecurity;
	
	@Autowired
	private StudentSecurity studentSecurity;

	@RequestMapping(value = "/teacher/home", method = RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView("teacher/home");
		Teacher teacher = teacherSecurity.isTeacherRole();
		if( teacher != null ){
			mv.addObject("teacher",mapAsJSONString(teacher));
			ContigoClass current = teacherService.getCurrentClass(teacher);
			if(current!=null){
				mv.addObject("currentClass", current);
			}
			List<Subject> subjects = subjectServiceImpl.findAll();
			if(!subjects.isEmpty()){
				mv.addObject("subjects",mapAsJSONString(subjects));
			}
		}else{
			if(studentSecurity.isStudentRole()!=null){
				mv = new ModelAndView("redirect:/student/home");
			}else{
				mv = new ModelAndView("/home");
			}
		}
		return mv;
	}
	
	@RequestMapping(value = "/teacher/subject/add", method = RequestMethod.POST)
	@ResponseBody
	public String addSubject(@RequestBody Subject subject){
		Teacher teacher = teacherSecurity.isTeacherRole();
		if(teacher!=null){
			Subject aux = subjectServiceImpl.findById(subject.getId());
			if(aux!=null){
				if(!teacherService.addSubject(teacher, aux)){
					return new JSONObject().put("type","error")
							.put("msg","Subject Error").toString();
				}
			}else{
				aux = subjectServiceImpl.save(subject);
				if(aux!=null){
					if(!teacherService.addSubject(teacher, aux)){
						return new JSONObject().put("type","error")
								.put("msg","Subject Error").toString();
					}
				}else{
					return new JSONObject().put("type","error")
							.put("msg","Subject Error").toString();
				}
			}
		}else{
			return new JSONObject().put("type","error")
					.put("msg","Teacher Error").toString();
		}
		return new JSONObject().put("type","OK")
				.put("msg","Subject Added successfully").toString();
	}
	

	@RequestMapping(value = "/teacher/subject/remove", method = RequestMethod.POST)
	@ResponseBody
	public String removeSubject(@RequestBody Subject subject){
		Teacher teacher = teacherSecurity.isTeacherRole();
		if(teacher!=null){
			Subject aux = subjectServiceImpl.findById(subject.getId());
			if(aux!=null){
				if(!teacherService.removeSubject(teacher, aux)){
					return new JSONObject().put("type","error")
							.put("msg","Subject Error").toString();
				}
			}else{
				return new JSONObject().put("type","error")
					.put("msg","Subject Error").toString();
			}	
		}else{
			return new JSONObject().put("type","error")
					.put("msg","Teacher Error").toString();
		}
		return new JSONObject().put("type","OK")
				.put("msg","Subject Removed successfully").toString();
	}

}
