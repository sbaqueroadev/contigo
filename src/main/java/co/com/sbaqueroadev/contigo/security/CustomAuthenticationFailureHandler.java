
/* Archivo: CustomAuthenticationFailureHandler.java
* Fecha: 21/12/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva de Sergio Baquero Ariza
* Su uso, alteracion, reproduccion o modificacion sin la debida
* consentimiento por escrito de Sergio Baquero Ariza.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dará lugar a las sanciones
* previstas en la Ley.
*/
	
package co.com.sbaqueroadev.contigo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* CustomAuthenticationFailureHandler:  
*/

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	public CustomAuthenticationFailureHandler(String failureUrl) {
		super(failureUrl);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	    AuthenticationException exception) throws IOException, ServletException {
			//response.sendError(403, "Login error");
			super.onAuthenticationFailure(request, response, exception);
			//((Response) response).setAppCommitted(true);
	}

}
