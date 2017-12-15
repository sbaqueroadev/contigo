package co.com.sbaqueroadev.contigo.security;

import static co.com.sbaqueroadev.contigo.security.SecurityConstants.HEADER_STRING;
import static co.com.sbaqueroadev.contigo.security.SecurityConstants.SECRET;
import static co.com.sbaqueroadev.contigo.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		String cookie = getCookieValue(req, HEADER_STRING); 
		//String resHeader = res.getHeader(HEADER_STRING);

		if ((header == null || !header.startsWith(TOKEN_PREFIX))/* &&
				(resHeader == null || !resHeader.startsWith(TOKEN_PREFIX)) */&&
					(cookie == null || !cookie.startsWith(TOKEN_PREFIX))
				){
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
		//System.out.println("c"+res.getStatus());
	}

	private String getCookieValue(HttpServletRequest req, String headerString) throws UnsupportedEncodingException {
		if(req.getCookies()!=null)
			for (Cookie cook: req.getCookies()) {
				if(cook.getName().equals(HEADER_STRING))
					return URLDecoder.decode(cook.getValue(),"UTF-8");
			}
		return null;
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws UnsupportedEncodingException {
		String token = request.getHeader(HEADER_STRING);
		if(token == null) 
			token = getCookieValue(request, HEADER_STRING);
		/*if(token == null) 
			token = response.getHeader(HEADER_STRING);*/
		if (token != null) {
			// parse the token.
			Claims claims = Jwts.parser()
					.setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			String user = claims
					.getSubject();
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			JSONArray jwtAuthorities;
			try {
				jwtAuthorities = new JSONArray((String) claims.get("Authorities"));
				for( int i = 0 ; i< jwtAuthorities.length(); i++ ){
					authorities.add(new SimpleGrantedAuthority(jwtAuthorities.getString(i)));
				}
			} catch (ParseException e) {
				throw new UnsupportedEncodingException("Bad credentials in Token");
			}
			 

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, authorities);
			}
			return null;
		}
		return null;
	}
}