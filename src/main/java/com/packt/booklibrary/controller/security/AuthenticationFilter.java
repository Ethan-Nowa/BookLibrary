// Intercepts incoming HTTP requests, extracts authentication tokens, performs authentication checks, and sets up the security context.
package com.packt.booklibrary.controller.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.packt.booklibrary.model.security.JwtService;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal
			(HttpServletRequest request, // HTTP request being processed
			HttpServletResponse response, // HTTP response being sent
			FilterChain filterChain) // Allows the request to proceed to the next filter in the chain.
					throws ServletException, IOException {

		// Get token from Authorization header of the incoming HTTP request.
		String jws = request.getHeader(HttpHeaders.AUTHORIZATION);			

		if (jws != null) {
			// Delegate to JwtService to verify token and get user.
			String user = jwtService.getAuthUser(request, jws);

			// Create authentication using the user.
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(user, null, java.util.Collections.emptyList());

			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);
	}
}