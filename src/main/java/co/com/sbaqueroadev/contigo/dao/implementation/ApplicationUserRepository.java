package co.com.sbaqueroadev.contigo.dao.implementation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}