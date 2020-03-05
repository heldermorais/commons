package app.commons

import common.aop.interceptors.CheckBeforeExecution
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class HomeService {


    GrailsApplication grailsApplication

    @CheckBeforeExecution(checker='Azzzz')
    def hello() {
        log.debug "     executing HomeService.hello()"
        grailsApplication.mainContext.getBeansWithAnnotation()
    }

}
