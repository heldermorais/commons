package app.commons

import common.autoconfig.Autorun

//import common.aop.interceptors.CheckBeforeExecution
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import org.apache.commons.chain.Command
import org.apache.commons.chain.Context

@Transactional
@Autorun
class HomeService implements Command {


    GrailsApplication grailsApplication

    //@CheckBeforeExecution(checker='checker1Service')
    def hello( String someoneName) {

        log.debug "     executing HomeService.hello(${someoneName})"
        //grailsApplication.mainContext.getBeansWithAnnotation()

    }

    //@CheckBeforeExecution(checker='checker1Service')
    def hello2( String someoneElse ) {

        log.debug "     executing HomeService.hello(${someoneName})"
        //grailsApplication.mainContext.getBeansWithAnnotation()

    }


    def helloUntraced() {

        log.debug "     executing HomeService.helloUntraced()"
        //grailsApplication.mainContext.getBeansWithAnnotation()

    }


    @Override
    boolean execute(Context context) throws Exception {
        log.info "Hello !!! On AutoRun"
        return false
    }

}
