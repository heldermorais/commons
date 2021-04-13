package app.only.springsec

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class SpsecdbService {

    SpringSecurityService springSecurityService

    void execute() {
        log.debug "Inicializando DB de Segurança ..."
        SecRole roleAdmin = new SecRole(authority: "ROLE_ADMIN")
        roleAdmin.save(flush: true, failOnError: true)

        SecRole roleUser = new SecRole(authority: "ROLE_USER")
        roleUser.save(flush: true, failOnError: true)

        SecGroup users = new SecGroup(name: "GROUP_USERS")
        users.save(flush: true, failOnError: true)

        SecGroup admins = new SecGroup(name: "GROUP_ADMINS")
        admins.save(flush: true, failOnError: true)

        SecGroupSecRole.create(users,roleUser,true)

        SecGroupSecRole.create(admins,roleAdmin,true)
        SecGroupSecRole.create(admins,roleUser,true)

        SecUser admin = new SecUser( username: 'admin', password: 'abc123')
        //abc123 => e99a18c428cb38d5f260853678922e03
        admin.save(flush: true, failOnError: true)

        SecUserSecGroup.create(admin,admins,true)
        //SecUsuarioSecPerfil.create(admin,roleAdmin,true)

        SecUser user = new SecUser( username: 'user', password: 'abc123')
        user.save(flush: true, failOnError: true)

        SecUserSecGroup.create(user,users,true)
        //SecUsuarioSecPerfil.create(user,roleUser,true)

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
