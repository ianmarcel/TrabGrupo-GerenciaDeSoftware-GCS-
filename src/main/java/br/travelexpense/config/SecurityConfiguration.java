package br.travelexpense.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.travelexpense.utils.CookieHelper;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private static final long VADLID_TOKEN_TIME = 604800L; //7 dias

	private final UserDetailsServiceImpl userDetailsService;

	public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			//.and().authorizeHttpRequests().anyRequest().permitAll()
			//.httpBasic()
			.and()
			.authorizeHttpRequests()
				//.antMatchers("/auth/**").permitAll()
			//.antMatchers("/empresa*/**").hasAnyRole("GESTOR", "DONO")
			.anyRequest().permitAll()
			.and()
			.cors().disable()
			.csrf().disable()
			.formLogin().disable()
			.rememberMe().alwaysRemember(true).key("session_key").tokenValiditySeconds(VADLID_TOKEN_TIME).rememberMeCookieName("LOGADO")
			.and()
			.logout().deleteCookies("JSESSIONID", CookieHelper.ID_EMPRESA_COOKIE)
			.and();
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and().build();
	}


}
