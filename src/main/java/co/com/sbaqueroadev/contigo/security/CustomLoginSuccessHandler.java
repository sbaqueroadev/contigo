
/* Archivo: MyCustomLoginSuccessHandler.java
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

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* MyCustomLoginSuccessHandler:  
*/
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  public CustomLoginSuccessHandler(String defaultTargetUrl) {
      setDefaultTargetUrl(defaultTargetUrl);
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
      super.onAuthenticationSuccess(request, response, authentication);
  }
}
