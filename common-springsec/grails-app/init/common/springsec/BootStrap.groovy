package common.springsec

import grails.core.GrailsApplication
import grails.plugin.springsecurity.SpringSecurityService

class BootStrap {

    SpringsecCommonConfigService springsecCommonConfigService

    def init = { servletContext ->

        springsecCommonConfigService.execute()

    }

    def destroy = {
    }
}
