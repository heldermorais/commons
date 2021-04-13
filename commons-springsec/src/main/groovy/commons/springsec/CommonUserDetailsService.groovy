package commons.springsec

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import groovy.util.logging.Slf4j
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Slf4j
@Transactional
class CommonUserDetailsService extends GormUserDetailsService {

    CommonUserDetailsService(GrailsApplication grailsApplication){
        super()

        log.debug "CommonUserDetailsService created !"
        this.setGrailsApplication(grailsApplication)

    }



    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        log.debug "CommonUserDetailsService.loadUserByUsername (${username})"

        //def conf = SpringSecurityUtils.securityConfig
        def conf = grailsApplication.config.grails?.plugin?.springsecurity

        String userClassName = conf.userLookup.userDomainClassName
        def dc = grailsApplication.getDomainClass(userClassName)
        if (!dc) {
            throw new IllegalArgumentException("The specified user domain class '$userClassName' is not a domain class")
        }

        Class<?> User = dc.clazz

        def user = User.createCriteria().get {
            if(conf.userLookup.usernameIgnoreCase) {
                eq((conf.userLookup.usernamePropertyName), username, [ignoreCase: true])
            } else {
                eq((conf.userLookup.usernamePropertyName), username)
            }
        }

        if (!user) {
            log.warn 'User not found: {}', username
            throw new NoStackUsernameNotFoundException()
        }

        Collection<GrantedAuthority> authorities = loadAuthorities(user, username, loadRoles)
        return createUserDetails (user, authorities)
    }

    @Override
    protected Collection<GrantedAuthority> loadAuthorities(Object user, String username, boolean loadRoles) {
        //return super.loadAuthorities(user, username, loadRoles)

        if (!loadRoles) {
            return []
        }

        //def conf = SpringSecurityUtils.securityConfig
        def conf = grailsApplication.config.grails?.plugin?.springsecurity

        String authoritiesPropertyName = conf.userLookup.authoritiesPropertyName
        String authorityPropertyName = conf.authority.nameField

        boolean useGroups = conf.useRoleGroups
        String authorityGroupPropertyName = conf.authority.groupAuthorityNameField

        Collection<?> userAuthorities = user."$authoritiesPropertyName"
        def authorities

        if (useGroups) {
            if (authorityGroupPropertyName) {
                authorities = userAuthorities.collect { it."$authorityGroupPropertyName" }.flatten().unique().collect { new SimpleGrantedAuthority(it."$authorityPropertyName") }
            }
            else {
                log.warn 'Attempted to use group authorities, but the authority name field for the group class has not been defined.'
            }
        }
        else {
            authorities = userAuthorities.collect { new SimpleGrantedAuthority(it."$authorityPropertyName") }
        }

        return authorities ?: [NO_ROLE]
    }

    @Override
    protected UserDetails createUserDetails(Object user, Collection<GrantedAuthority> authorities) {
        log.debug "CommonUserDetailsService.createUserDetails (${user.toString()})"
        return super.createUserDetails(user, authorities)
    }

}
