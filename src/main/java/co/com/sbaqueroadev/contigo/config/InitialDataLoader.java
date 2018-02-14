package co.com.sbaqueroadev.contigo.config;



import java.util.Arrays;
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
import co.com.sbaqueroadev.contigo.dao.SubjectRepository;
import co.com.sbaqueroadev.contigo.dao.TeacherRepository;
import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.contigo.model.implementation.ContigoClass;
import co.com.sbaqueroadev.contigo.model.implementation.Privilege;
import co.com.sbaqueroadev.contigo.model.implementation.Privilege.Privileges;
import co.com.sbaqueroadev.contigo.model.implementation.Role;
import co.com.sbaqueroadev.contigo.model.implementation.Student;
import co.com.sbaqueroadev.contigo.model.implementation.Subject;
import co.com.sbaqueroadev.contigo.model.implementation.Teacher;
import co.com.sbaqueroadev.contigo.model.implementation.Role.Roles;

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

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		
		Privilege write = createPrivilegeIfNotFound(Privileges.WRITE.getValue());
		Privilege read = createPrivilegeIfNotFound(Privileges.READ.getValue());
		Privilege teach = createPrivilegeIfNotFound(Privileges.TEACH.getValue());
		Privilege studentP = createPrivilegeIfNotFound(Privileges.STUDENT.getValue());
		Privilege classViewer = createPrivilegeIfNotFound(Privileges.CLASS_VIEWER.getValue());
		Privilege manageUsers = createPrivilegeIfNotFound(Privileges.MANAGE_USERS.getValue());
		
		Role adminRole = createRoleIfNotFound(new Role(Roles.ADMIN.getValue(),Arrays.asList(read, write, manageUsers)));
		Role userRole = createRoleIfNotFound(new Role(Roles.USER.getValue(),Arrays.asList(read, write)));
		Role teacherRole = createRoleIfNotFound(new Role(Roles.TEACHER.getValue(),Arrays.asList(read, write, teach)));
		Role studentRole = createRoleIfNotFound(
				new Role(Roles.STUDENT.getValue(),Arrays.asList(read, write, classViewer,studentP)));
		
		ApplicationUser admin = new ApplicationUser();
		admin.setPassword(bCryptPasswordEncoder.encode("admin"));
		admin.setUsername("admin");
		admin.setEmail("admin@test.com");
		admin.setRole(adminRole);
		admin = createUserIfNotFound(admin);
		
		ApplicationUser user = new ApplicationUser();
		user.setPassword(bCryptPasswordEncoder.encode("teacher"));
		user.setUsername("teacher");
		user.setEmail("teacher@test.com");
		user.setRole(teacherRole);
		user = createUserIfNotFound(user);
		
		Subject subject = createSubjectIfNotFound(new Subject("Matem&aacute;ticas"));
		Subject subject2 = createSubjectIfNotFound(new Subject("F&iacute;sica"));
		Subject subject3 = createSubjectIfNotFound(new Subject("Qu&iacute;mica"));
		ContigoClass cClass = new ContigoClass();
		cClass.setDate(new Date());
		cClass.setDuration(4);
		cClass.setSubject(subject);
		cClass.setStatus(ContigoClass.Status.ACTIVE.getName());
		
		ContigoClass cClass2 = new ContigoClass();
		cClass2.setDate(new Date());
		cClass2.setDuration(4);
		cClass2.setSubject(subject2);
		cClass2.setStatus(ContigoClass.Status.ASKED.getName());
		
		Teacher teacher = new Teacher();
		teacher.setName("Camilo");
		teacher.setUserId(user.getId());
		teacher.addSubjectIfNotFound(subject);
		teacher = createTeacherIfNotFound(teacher);
		cClass.setTeacher(teacher);
		cClass2.setTeacher(teacher);
		cClass = createClassIfNotFound(cClass);
		cClass2 = createClassIfNotFound(cClass2);
		teacher.addClassIfNotFound(cClass);
		teacher.addClassIfNotFound(cClass2);
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
		student.addClass(cClass2);
		student.setUserId(user2.getId());
		student = createStudentIfNotFound(student);
		alreadySetup = true;
	}

	/**
	 * @param string
	 * @return
	 */
	private Subject createSubjectIfNotFound(Subject subject) {
		Subject sbj = subjectRepository.findByName(subject.getName());
		if (sbj == null) {
			sbj = subject;
			subjectRepository.save(sbj);
		}
		return sbj;
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
		List<ContigoClass> classes = classRepository.findBySubject(cClass.getSubject());
		ContigoClass cls = null;
		if(classes.size()>0)
			cls = classes.get(0);
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
	private Privilege createPrivilegeIfNotFound(Privilege priv) {

		Privilege privilege = privilegeRepository.findByName(priv.getName());
		if (privilege == null) {
			privilege = new Privilege(priv.getName());
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Role createRoleIfNotFound(Role rol) {

		Role role = roleRepository.findByName(rol.getName());
		if (role == null) {
			role = new Role(rol.getName());
			role.setPrivileges(rol.getPrivileges());
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