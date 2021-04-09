package commons.springsec

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

        SecUser admin = new SecUser( username: 'admin', password: 'abc123')
        admin.save(flush: true, failOnError: true)

        SecUserSecRole.create(admin,roleAdmin,true)

        SecUser user = new SecUser( username: 'user', password: 'abc123')
        user.save(flush: true, failOnError: true)

        SecUserSecRole.create(user,roleUser,true)

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
}
