package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${ldap.urls}")
	private String ldapUrls;
	
	@Value("${ldap.base.dn}")
	private String ldapBaseDn;
	
	@Value("${ldap.username}")
	private String ldapSecurityPrincipal;
	
	@Value("${ldap.password}")
	private String ldapPrincipalPassword;
	
	@Value("${ldap.user.dn.pattern}")
	private String ldapUserDnPattern;
	
	@Value("${ldap.enabled}")
	private String ldapEnabled;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login*").permitAll()
		.antMatchers("/signup").permitAll()
		.anyRequest()
		.fullyAuthenticated()
		.and()
		.formLogin();
	}
	public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		if (Boolean.parseBoolean(ldapEnabled)) {
			authBuilder
			.ldapAuthentication()
			.contextSource()
			.url(ldapUrls + ldapBaseDn)
			.managerDn(ldapSecurityPrincipal)
			.and()
			.userDnPatterns(ldapUserDnPattern);
		} else {
			authBuilder
			.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER")
			.and()
			.withUser("admin").password("{noop}admin").roles("ADMIN");
		}
	}
	

}
