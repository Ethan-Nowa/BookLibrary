package com.packt.booklibrary.controller.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;		// Deprecated
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.packt.booklibrary.model.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Autowired
	private AuthEntryPoint exceptionHandler;

	// Defines security configurations such as CSRF protection, session management, request authorization rules, exception handling, and the order of authentication filters.
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//http.csrf().disable().cors().and()
		//.authorizeRequests().anyRequest().permitAll();

		http.csrf().disable().cors().and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.requestMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated().and()
		.exceptionHandling()
		.authenticationEntryPoint(exceptionHandler).and()
		.addFilterBefore(authenticationFilter, 
				UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}	

	// Configures Cross-Origin Resource Sharing (CORS) policies to allow requests from specified origins, methods, and headers.
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(false);
		config.applyPermitDefaultValues();

		source.registerCorsConfiguration("/**", config);
		return source;
	}	

	// Configures a global instance of AuthenticationManagerBuilder to use the userDetailsService and a BCryptPasswordEncoder for password encoding.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception  {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	// Defines an AuthenticationManager bean using AuthenticationConfiguration. This is needed for authentication purposes.
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
}