package co.com.sbaqueroadev.contigo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.sbaqueroadev.contigo.model.implementation.Privilege;

public interface PrivilegeRepository extends MongoRepository<Privilege, Long> {
    Privilege findByName(String name);
}