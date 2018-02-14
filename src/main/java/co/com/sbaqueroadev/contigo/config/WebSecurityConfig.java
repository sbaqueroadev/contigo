package co.com.sbaqueroadev.contigo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import co.com.sbaqueroadev.contigo.model.implementation.Privilege.Privileges;
import co.com.sbaqueroadev.contigo.security.CustomAuthenticationFailureHandler;
import co.com.sbaqueroadev.contigo.security.CustomLoginSuccessHandler;
import co.com.sbaqueroadev.contigo.security.JWTAuthenticationFilter;
import co.com.sbaqueroadev.contigo.security.JWTAuthorizationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private CustomAuthenticationFailureHandler authenticationFailureHandler = 
			new CustomAuthenticationFailureHandler("/users/access?error=100");
	private CustomLoginSuccessHandler authenticationSuccessHandler = 
			new CustomLoginSuccessHandler("/board/home");

	public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET,"/users/access","/users/create","/error"
				,"/resources/**","/webjars/**").permitAll()
		.antMatchers(HttpMethod.POST,"/users/sign-up").permitAll()
		.antMatchers(HttpMethod.GET,"/class/**").hasAnyAuthority(
				Privileges.TEACH.getValue().getName(),
				Privileges.CLASS_VIEWER.getValue().getName())
		.antMatchers(HttpMethod.POST,"/class/**").hasAnyAuthority(
				Privileges.TEACH.getValue().getName(),
				Privileges.CLASS_VIEWER.getValue().getName())
		.anyRequest().hasAnyAuthority(Privileges.READ.getValue().getName(),
				Privileges.WRITE.getValue().getName())
		.antMatchers(HttpMethod.GET,"/teacher/**").hasAnyAuthority(
				Privileges.TEACH.getValue().getName())
		.antMatchers(HttpMethod.GET,"/student/**").hasAnyAuthority(
				Privileges.STUDENT.getValue().getName())
		.and()
		.formLogin()
		.loginPage("/users/access")
		.permitAll()
		.loginProcessingUrl("/login")
		.permitAll()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))//,UsernamePasswordAuthenticationFilter.class)
		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		// this disables session creation on Spring Security
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
    .passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}