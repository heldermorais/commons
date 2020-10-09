package app.common.keycloak

import common.security.keycloak.KeycloakAppConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity

@Configuration
class SecurityConfig extends KeycloakAppConfiguration {

    @Override
    void configure(HttpSecurity http) throws Exception {
        super.configure(http)

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
        //.antMatchers("/signDocument").permitAll()
                .antMatchers("/home/**").hasAuthority('ROLE_KC_USER')
                .antMatchers("/other/**").hasAuthority('ROLE_KC_ADMIN')

    }
}
