package co.com.sbaqueroadev.contigo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer{

	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//super.configureMessageBroker(registry);
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/video","/chat","/board");
	}
	
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/show").withSockJS();
		registry.addEndpoint("/write").withSockJS();
		registry.addEndpoint("/boardUpdate").withSockJS();
	}

}
