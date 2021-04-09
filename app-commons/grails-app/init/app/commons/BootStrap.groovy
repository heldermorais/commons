package app.commons

import commons.springsec.InitSpringSecDBService
import grails.core.GrailsApplication
import grails.util.Environment
import groovy.util.logging.Slf4j

//import commons.springsec.InitSpringSecDBService

@Slf4j
class BootStrap {

    InitSpringSecDBService initSpringSecDBService
    def init = { servletContext ->

        log.debug "bootstrap APP..."
        initSpringSecDBService.execute()

    }


    def destroy = {
    }
}
