package app.commons

import common.aop.interceptors.CheckBeforeExecution
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class HomeService {


    GrailsApplication grailsApplication

    @CheckBeforeExecution(checker='checker1Service')
    def hello( String someoneName) {

        log.debug "     executing HomeService.hello(${someoneName})"
        //grailsApplication.mainContext.getBeansWithAnnotation()

    }

    @CheckBeforeExecution(checker='checker1Service')
    def hello2( String someoneElse) {

        log.debug "     executing HomeService.hello(${someoneName})"
        //grailsApplication.mainContext.getBeansWithAnnotation()

    }


    def helloUntraced() {
        log.debug "     executing HomeService.helloUntraced()"
        //grailsApplication.mainContext.getBeansWithAnnotation()
    }


}
