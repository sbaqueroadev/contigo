package co.com.sbaqueroadev.contigo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);

		public ApplicationUser save(ApplicationUser user);
}