package co.com.sbaqueroadev.contigo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sbaqueroadev.contigo.model.implementation.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    	Role findByName(String name);
}