package co.com.sbaqueroadev.contigo;



import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.com.sbaqueroadev.contigo.dao.implementation.ApplicationUserRepository;
import co.com.sbaqueroadev.contigo.dao.implementation.PrivilegeRepository;
import co.com.sbaqueroadev.contigo.dao.implementation.RoleRepository;
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
        createUserIfNotFound(user);
        //applicationUserRepository.save(user);
        ApplicationUser user2 = new ApplicationUser();
        user2.setPassword(bCryptPasswordEncoder.encode("student"));
        user2.setUsername("student");
        user2.setEmail("student@test.com");
        user2.setRole(studentRole);
        createUserIfNotFound(user2);
 
        alreadySetup = true;
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