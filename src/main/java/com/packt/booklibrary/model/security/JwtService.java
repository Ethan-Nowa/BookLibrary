package com.packt.booklibrary.model.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtService {
	static final long EXPIRATIONTIME = 86400000; // Tokens expire in 1 day in ms.
	static final String PREFIX = "Bearer"; // Prefix used in the Authorization header for JWT tokens.
	// Generate secret key for signing tokens.
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	// Generate JWT token for username.
	public String getToken(String username) {
		String token = Jwts.builder()
			  .setSubject(username)
			  .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
			  .signWith(key)
			  .compact();
		return token;
  }

	// Get a token from request Authorization header, 
	// parse a token and get username
	public String getAuthUser(HttpServletRequest request, String token) {
		if (token != null) {
			// Extracts the username from the token.
			String user = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();
	    
			if (user != null)
				return user;
		}

		return null;
	}
}