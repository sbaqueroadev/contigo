package co.com.sbaqueroadev.contigo.config;



import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.com.sbaqueroadev.contigo.dao.ApplicationUserRepository;
import co.com.sbaqueroadev.contigo.dao.ClassRepository;
import co.com.sbaqueroadev.contigo.dao.PrivilegeRepository;
import co.com.sbaqueroadev.contigo.dao.RoleRepository;
import co.com.sbaqueroadev.contigo.dao.StudentRepository;
import co.com.sbaqueroadev.contigo.dao.TeacherRepository;
import co.com.sbaqueroadev.contigo.model.ContigoClass;
import co.com.sbaqueroadev.contigo.model.Student;
import co.com.sbaqueroadev.contigo.model.Teacher;
import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.contigo.model.implementation.Privilege;
import co.com.sbaqueroadev.contigo.model.implementation.Role;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		Privilege readPrivilege
		= createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege
		= createPrivilegeIfNotFound("WRITE_PRIVILEGE");
		Privilege teachClassPrivilege
		= createPrivilegeIfNotFound("TEACH_CLASS_PRIVILEGE");
		Privilege viewClassPrivilege
		= createPrivilegeIfNotFound("VIEW_CLASS_PRIVILEGE");

		List<Privilege> adminPrivileges = Arrays.asList(
				readPrivilege, writePrivilege);
		List<Privilege> teacherPrivileges = Arrays.asList(
				readPrivilege, writePrivilege, teachClassPrivilege);        
		List<Privilege> studentPrivileges = Arrays.asList(
				readPrivilege, writePrivilege, viewClassPrivilege);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_TEACHER", teacherPrivileges);
		createRoleIfNotFound("ROLE_STUDENT", studentPrivileges);
		createRoleIfNotFound("ROLE_USER", (Collection<Privilege>) Arrays.asList(readPrivilege));

		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		Role userRole = roleRepository.findByName("ROLE_USER");
		Role teacherRole = roleRepository.findByName("ROLE_TEACHER");
		Role studentRole = roleRepository.findByName("ROLE_STUDENT");
		ApplicationUser user = new ApplicationUser();
		user.setPassword(bCryptPasswordEncoder.encode("teacher"));
		user.setUsername("teacher");
		user.setEmail("teacher@test.com");
		user.setRole(teacherRole);
		user = createUserIfNotFound(user);
		ContigoClass cClass = new ContigoClass();
		cClass.setDate(new Date());
		cClass.setDuration(4);
		cClass.setSubject("Math");
		cClass.setStatus(ContigoClass.Status.ACTIVE.getName());
		Teacher teacher = new Teacher();
		teacher.setName("Camilo");
		teacher.setUserId(user.getId());
		teacher = createTeacherIfNotFound(teacher);
		cClass.setTeacherId(teacher.getId());
		cClass = createClassIfNotFound(cClass);
		teacher.addClass(cClass);
		teacherRepository.save(teacher);
		ApplicationUser user2 = new ApplicationUser();
		user2.setPassword(bCryptPasswordEncoder.encode("student"));
		user2.setUsername("student");
		user2.setEmail("student@test.com");
		user2.setRole(studentRole);
		user2 = createUserIfNotFound(user2);
		Student student = new Student();
		student.setName("Sergio");
		student.addClass(cClass);
		student.setUserId(user2.getId());
		student = createStudentIfNotFound(student);
		alreadySetup = true;
	}

	/**
	 * @param student
	 * @return
	 */
	private Student createStudentIfNotFound(Student student) {
		Student std = studentRepository.findByUserId(student.getUserId());
		if (std == null) {
			std = student;
			studentRepository.save(std);
		}
		return std;
	}

	/**
	 * @param cClass
	 * @return
	 */
	private ContigoClass createClassIfNotFound(ContigoClass cClass) {
		ContigoClass cls = null;
		if(cClass.getId()!=null){
			//cls = classRepository.findById(cClass.getId()).get();
			cls = classRepository.findBySubject(cClass.getSubject());
		}
		if (cls == null) {
			cls = cClass;
			classRepository.save(cls);
		}
		return cls;
	}

	/**
	 * @param teacher
	 */
	private Teacher createTeacherIfNotFound(Teacher teacher) {
		Teacher tea = teacherRepository.findByUserId(teacher.getUserId());
		if (tea == null) {
			tea = teacher;
			teacherRepository.save(tea);
		}
		return tea;
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Role createRoleIfNotFound(
			String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

	@Transactional
	private ApplicationUser createUserIfNotFound(
			ApplicationUser appUser) {

		ApplicationUser user = applicationUserRepository.findByUsername(appUser.getUsername());
		if (user == null) {
			user = appUser;
			applicationUserRepository.save(user);
		}
		return user;
	}
}