
/* Archivo: OwnProperties.java
* Fecha: 22/12/2017
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
	
package co.com.sbaqueroadev.contigo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* OwnProperties:  
*/
@Component
@ConfigurationProperties(prefix = "contigo")
public class OwnProperties {

			private boolean heroku;

			public boolean isHeroku() {
				return heroku;
			}

			public void setHeroku(boolean heroku) {
				this.heroku = heroku;
			}
}
