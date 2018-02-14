package co.com.sbaqueroadev.contigo.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.sbaqueroadev.contigo.exceptions.UsernameExistsException;
import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.contigo.model.implementation.Role;
import co.com.sbaqueroadev.contigo.model.implementation.Student;
import co.com.sbaqueroadev.contigo.model.implementation.Teacher;
import co.com.sbaqueroadev.contigo.model.implementation.Role.Roles;
import co.com.sbaqueroadev.contigo.services.ApplicationUserServiceImpl;
import co.com.sbaqueroadev.contigo.services.RoleServiceImpl;
import co.com.sbaqueroadev.contigo.services.StudentServiceImpl;
import co.com.sbaqueroadev.contigo.services.TeacherServiceImpl;

@Controller
public class UserController {

	@Autowired
	private ApplicationUserServiceImpl applicationUserServiceImpl;

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@Autowired
	private TeacherServiceImpl teacherServiceImpl;



	@RequestMapping(value = "/users/sign-up", method = RequestMethod.POST)
	public String signUp(@RequestParam("user") String userS
			,@RequestParam("pass") String pass
			,@RequestParam("role") String role) throws UsernameExistsException {
		ApplicationUser user = new ApplicationUser();
		user.setUsername(userS);
		user.setPassword(pass);
		user.setRole(roleServiceImpl.findByName(Role.PREFIX+role.toUpperCase()));
		user = applicationUserServiceImpl.registerNewUserAccount(user);
		createPersonByRole(user,Role.PREFIX+role.toUpperCase());
		return "redirect:access";
	}

	/**
	 * @param user
	 * @param string
	 */
	private void createPersonByRole(ApplicationUser user, String role) {
		if(role.equals(Roles.STUDENT.getValue())){
			Student student = new Student();
			student.setName("Nombre");
			student.setUserId(user.getId());
			studentServiceImpl.save(student);
		}else{
			if(role.equals(Roles.TEACHER.getValue())){
				Teacher teacher = new Teacher();
				teacher.setName("NombreT");
				teacher.setUserId(user.getId());
				teacherServiceImpl.save(teacher);
			}
		}
	}

	/**
	 * @return Home View represented by a JSP file.
	 */
	@RequestMapping(value = "/users/access", method = RequestMethod.GET)
	public String home() {
		return "users/login";
	}

	/**
	 * @return Home View represented by a JSP file.
	 */
	@RequestMapping(value = "/users/create", method = RequestMethod.GET)
	public String create() {
		return "users/signUp";
	}
}
