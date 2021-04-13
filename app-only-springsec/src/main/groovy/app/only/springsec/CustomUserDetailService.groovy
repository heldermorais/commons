package app.only.springsec

import grails.core.GrailsApplication
import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import groovy.util.logging.Slf4j
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException


@Slf4j
class CustomUserDetailService extends GormUserDetailsService {

    CustomUserDetailService(GrailsApplication grailsApplication){
        super()
        this.setGrailsApplication(grailsApplication)
    }



    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        log.debug "CustomUserDetailService.loadUserByUsername (${username})"
        return super.loadUserByUsername(username, loadRoles)
    }


}
