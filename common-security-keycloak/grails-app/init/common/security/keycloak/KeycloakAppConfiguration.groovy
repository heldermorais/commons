package common.security.keycloak

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy

@Configuration
class KeycloakAppConfiguration extends KeycloakWebSecurityConfigurerAdapter {


    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }


    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        //return new NullAuthenticatedSessionStrategy();
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl())
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        return keycloakAuthenticationProvider();
    }


    @Bean
    public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
            KeycloakAuthenticationProcessingFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(KeycloakPreAuthActionsFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http
                .authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/h2-console/login.do/**").permitAll()


//        [pattern: '/',               access: ['permitAll']],
//        [pattern: '/error',          access: ['permitAll']],
//        [pattern: '/index',          access: ['permitAll']],
//        [pattern: '/home/**',           access: ['permitAll']],
//        [pattern: '/db-console/**',     access: ['permitAll']],
//        [pattern: '/h2-console/**',     access: ['permitAll']],
//        [pattern: '/index.gsp',      access: ['permitAll']],
//        [pattern: '/shutdown',       access: ['permitAll']],

//        [pattern: '/assets/**',      access: ['permitAll']],
//        [pattern: '/**/js/**',       access: ['permitAll']],
//        [pattern: '/**/css/**',      access: ['permitAll']],
//        [pattern: '/**/images/**',   access: ['permitAll']],
//        [pattern: '/**/favicon.ico', access: ['permitAll']]

                //.antMatchers("/signDocument").permitAll()
                //.antMatchers("/home/**").hasAuthority('ROLE_KC_USER')

//                .antMatchers("/api/user-info").hasAuthority(USER)
//                .antMatchers("/api/products").hasAuthority(USER)
//                .antMatchers("/api/**").authenticated()
//                .antMatchers("/management/health").permitAll()
//                .antMatchers("/management/**").hasAuthority(ADMIN)
//                .antMatchers("/swagger-resources/configuration/ui").permitAll()

    }

}

