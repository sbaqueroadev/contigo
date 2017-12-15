package co.com.sbaqueroadev.contigo.model;

import co.com.sbaqueroadev.contigo.exceptions.UsernameExistsException;
import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;

public interface ApplicationUserInterface {

	public ApplicationUser registerNewUserAccount(ApplicationUser applicationUser) throws UsernameExistsException;
}
