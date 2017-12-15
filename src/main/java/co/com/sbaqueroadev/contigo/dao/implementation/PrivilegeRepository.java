package co.com.sbaqueroadev.contigo.dao.implementation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sbaqueroadev.contigo.model.implementation.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}