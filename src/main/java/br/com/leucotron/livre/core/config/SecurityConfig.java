package br.com.leucotron.livre.core.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.leucotron.livre.core.security.AuthenticationFilter;
import br.com.leucotron.livre.core.security.LoginFilter;

/**
 * Security Configuration.
 * 
 * @author Virtus
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
	        .antMatchers(HttpMethod.POST, "/refresh-access").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .addFilterBefore(new LoginFilter("/login"), UsernamePasswordAuthenticationFilter.class)
////            .addFilterBefore(new JJWTRefreshAuthenticationFilter("/refresh-access"), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}