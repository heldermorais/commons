package app.only.springsec

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class SpsecdbService {

    SpringSecurityService springSecurityService

    void execute() {
        log.debug "Inicializando DB de Segurança ..."
        SecPerfil roleAdmin = new SecPerfil(authority: "ROLE_ADMIN")
        roleAdmin.save(flush: true, failOnError: true)

        SecPerfil roleUser = new SecPerfil(authority: "ROLE_USER")
        roleUser.save(flush: true, failOnError: true)

        SecUsuario admin = new SecUsuario( username: 'admin', password: 'abc123')
        //abc123 => e99a18c428cb38d5f260853678922e03
        admin.save(flush: true, failOnError: true)

        SecUsuarioSecPerfil.create(admin,roleAdmin,true)

        SecUsuario user = new SecUsuario( username: 'user', password: 'abc123')
        user.save(flush: true, failOnError: true)

        SecUsuarioSecPerfil.create(user,roleUser,true)

        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*',
                '/h2-console/**', '/hello/**']) {
            new SecRequestmap(url: url, configAttribute: 'permitAll').save()
        }


        new SecRequestmap(url: '/hello/**', configAttribute: 'ROLE_ADMIN').save()

        springSecurityService.clearCachedRequestmaps()
        log.debug "DB de Segurança OK."

    }
}
