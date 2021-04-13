package commons.springsec

import commons.security.SecGroup
import commons.security.SecGroupSecRole
import commons.security.SecRequestmap
import commons.security.SecRole
import commons.security.SecUser
import commons.security.SecUserSecGroup
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import groovy.util.logging.Slf4j

@Transactional
@Slf4j
class InitSpringSecDBService {

    SpringSecurityService springSecurityService
    InitSpringSecDBService(SpringSecurityService springSecurityService){
       this.springSecurityService = springSecurityService
    }

    void execute() {
        log.debug "Inicializando DB de Segurança ..."
        SecRole roleAdmin = new SecRole(authority: "ROLE_ADMIN")
        roleAdmin.save(flush: true, failOnError: true)

        SecRole roleUser = new SecRole(authority: "ROLE_USER")
        roleUser.save(flush: true, failOnError: true)



        SecGroup admins = new SecGroup( name: 'GROUP_ADMIN' )
        admins.save(flush: true, failOnError: true)

        SecGroup users = new SecGroup( name: 'GROUP_USERS' )
        users.save(flush: true, failOnError: true)

        SecGroupSecRole.create(admins,roleAdmin,true)
        SecGroupSecRole.create(admins,roleUser ,true)

        SecGroupSecRole.create(users,roleUser,true)


        SecUser admin = new SecUser( username: 'admin', password: 'abc123')
        admin.save(flush: true, failOnError: true)

        SecUserSecGroup.create(admin,admins,true)

        SecUser user = new SecUser( username: 'user', password: 'abc123')
        user.save(flush: true, failOnError: true)
        SecUserSecGroup.create(user,users,true)


        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*',
                '/h2-console/**']) {
            new SecRequestmap(url: url, configAttribute: 'permitAll').save()
        }


        new SecRequestmap(url: '/hello/**', configAttribute: 'ROLE_ADMIN').save()

        springSecurityService.clearCachedRequestmaps()
        log.debug "DB de Segurança OK."

    }

    void saveAsUser( user ){
        user.save(flush: true, failOnError: true)
        SecGroup grp = SecGroup.findByName("GROUP_USERS")
        if (grp){
            SecUserSecGroup.create(user,grp,true)
        }
    }

    void saveAsAdmin( user ){
        user.save(flush: true, failOnError: true)
        SecGroup grp = SecGroup.findByName("GROUP_ADMIN")
        if (grp){
            SecUserSecGroup.create(user,grp,true)
        }
    }

}
