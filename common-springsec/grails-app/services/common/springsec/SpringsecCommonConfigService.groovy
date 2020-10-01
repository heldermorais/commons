package common.springsec

import common.autorun.Autorun
import common.autorun.GenericAutorunService
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import groovy.util.logging.Slf4j
import org.apache.commons.chain.Context
import org.springframework.core.Ordered

@Transactional
@Autorun
@Slf4j
class SpringsecCommonConfigService extends GenericAutorunService {


    SpringSecurityService springSecurityService
    GrailsApplication grailsApplication


    @Override
    boolean execute(Context context) throws Exception {
        log.debug("SpringsecCommonConfigService - BEGIN")

        SecRole role_admin = SecRole.get(1)
        if(role_admin == null){
            role_admin = new SecRole(authority: 'ROLE_ADMIN')
            role_admin.save(flush: true, failOnError: true)
        }

        SecRole role_user = SecRole.get(2)
        if(role_user == null){
            role_user = new SecRole(authority: 'ROLE_USER')
            role_user.save(flush: true, failOnError: true)
        }

        SecGroup group_admin = SecGroup.get(1)
        if(group_admin == null){
            group_admin = new SecGroup(name: 'Admin Users')
            group_admin.save(flush: true, failOnError: true)

            SecGroupSecRole.create(group_admin, role_admin)
            SecGroupSecRole.create(group_admin, role_user)
        }

        SecGroup group_users = SecGroup.get(2)
        if(group_users == null){
            group_users = new SecGroup(name: 'Plain Users')
            group_users.save(flush: true, failOnError: true)

        }
        SecGroupSecRole.create(group_users, role_user)

        SecUser admin = SecUser.get(1)
        if(admin == null){
            admin = new SecUser(username: 'admin', password: 'admin')
            admin.save(flush: true, failOnError: true)
            SecUserSecGroup.create(admin, group_admin)
        }


        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*','/dbconsole/**','/h2-console/**',
                '/logout', '/logout.*', '/logout/*']) {
            new SecRequestmap(url: url, configAttribute: 'permitAll').save()
        }


        springSecurityService.clearCachedRequestmaps()

        log.debug("SpringsecCommonConfigService - END")

        return false
    }

    @Override
    int getOrder() {
        return Ordered.LOWEST_PRECEDENCE + 10000
    }
}
