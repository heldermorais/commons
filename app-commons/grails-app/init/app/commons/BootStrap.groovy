package app.commons

//import commons.springsec.InitSpringSecDBService
import grails.core.GrailsApplication
import grails.util.Environment
import groovy.util.logging.Slf4j

//import commons.springsec.InitSpringSecDBService

@Slf4j
class BootStrap {

   // InitSpringSecDBService initSpringSecDBService
    def init = { servletContext ->

//        log.debug "bootstrap APP..."
//        initSpringSecDBService.execute()
//
//        AppSecUser user1 = new AppSecUser(username: 'user1', password: 'abc123', fullName: 'User 1')
//        initSpringSecDBService.saveAsAdmin(user1)
//
//        AppSecUser user2 = new AppSecUser(username: 'user2', password: 'abc123', fullName: 'User 2')
//        initSpringSecDBService.saveAsUser(user2)
    }


    def destroy = {
    }
}
