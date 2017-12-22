package co.com.sbaqueroadev.contigo.security;

import static co.com.sbaqueroadev.contigo.security.SecurityConstants.EXPIRATION_TIME;
import static co.com.sbaqueroadev.contigo.security.SecurityConstants.HEADER_STRING;
import static co.com.sbaqueroadev.contigo.security.SecurityConstants.SECRET;
import static co.com.sbaqueroadev.contigo.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private CustomAuthenticationFailureHandler authenticationFailureHandler = 
			new CustomAuthenticationFailureHandler("/users/access?error=100");
	private CustomLoginSuccessHandler authenticationSuccessHandler = 
			new CustomLoginSuccessHandler("/student/home");

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.setAuthenticationFailureHandler(authenticationFailureHandler);
		this.setAuthenticationSuccessHandler(authenticationSuccessHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException {
		ApplicationUser creds = new ApplicationUser();
		//if(req.getParameter("username")==null || req.getParameter("password")==null)
		//throw new RuntimeException();
		//ApplicationUser creds;
		UsernamePasswordAuthenticationToken user;
		
		
		try {
			creds = /*new ObjectMapper()
					.readValue(req.getInputStream(), ApplicationUser.class);*/
					new ApplicationUser();
			creds.setPassword(req.getParameter("password"));
			creds.setUsername(req.getParameter("username"));
			user = new UsernamePasswordAuthenticationToken(
					creds.getUsername(),
					creds.getPassword());
		} catch (NullPointerException e) {
			user = new UsernamePasswordAuthenticationToken(null, null);
		}
		return authenticationManager.authenticate(user);
		/*creds.setUsername(req.getParameter("username"));
		creds.setPassword(req.getParameter("password"));*/

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		//super.successfulAuthentication(req, res, chain, auth);
		JSONArray authorities = new JSONArray();
		for (GrantedAuthority sa : auth.getAuthorities()){
			authorities.put(sa.getAuthority());
		}
		Map<String,Object> map = new HashMap<>();
		map.put("Authorities", authorities.toString());
		String token = Jwts.builder()
				.setClaims(map)
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		Cookie authcoo = new Cookie(HEADER_STRING, URLEncoder.encode(TOKEN_PREFIX+ token,"UTF-8"));
		authcoo.setSecure(false);
		authcoo.setMaxAge((int) EXPIRATION_TIME);
		res.addCookie(authcoo);
		SecurityContextHolder.getContext().setAuthentication(auth);
		super.getSuccessHandler().onAuthenticationSuccess(req, res, auth);
		//chain.doFilter(req, res);
	}

}