package common.springsec

import common.autoconfig.Autorun
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import org.apache.commons.chain.Command
import org.apache.commons.chain.Context

@Transactional
class SpringsecCommonConfigService implements Command {


    SpringSecurityService springSecurityService
    GrailsApplication grailsApplication


    @Override
    boolean execute(Context context) throws Exception {
        log.debug("Bootstrap.init - BEGIN")

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


//        new RequestMap(url: '/profile/**',    configAttribute: 'ROLE_USER').save()
//        new RequestMap(url: '/admin/**',      configAttribute: 'ROLE_ADMIN').save()
//        new RequestMap(url: '/admin/role/**', configAttribute: 'ROLE_SUPERVISOR').save()
//        new RequestMap(url: '/admin/user/**',
//                configAttribute: 'ROLE_ADMIN,ROLE_SUPERVISOR').save()
//        new RequestMap(url: '/login/impersonate',
//                configAttribute: 'ROLE_SWITCH_USER,IS_AUTHENTICATED_FULLY').save()


        springSecurityService.clearCachedRequestmaps()

        log.debug("Bootstrap.init - END")

        return false
    }
}
