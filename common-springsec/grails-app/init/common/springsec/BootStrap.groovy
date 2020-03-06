package common.springsec

import grails.core.GrailsApplication
import grails.plugin.springsecurity.SpringSecurityService

class BootStrap {


    SpringSecurityService springSecurityService
    GrailsApplication grailsApplication

    def init = { servletContext ->

        log.debug("Bootstrap.init - BEGIN")


        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            new RequestMap(url: url, configAttribute: 'permitAll').save()
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
    }

    def destroy = {
    }
}
