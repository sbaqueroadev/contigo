package co.com.sbaqueroadev.contigo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.sbaqueroadev.contigo.model.implementation.Role;

public interface RoleRepository extends MongoRepository<Role, Long> {
    	Role findByName(String name);
}