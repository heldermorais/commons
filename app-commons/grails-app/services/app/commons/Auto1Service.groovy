package app.commons


import common.autorun.OnApplicationReady
import common.events.CustomSpringEventPublisher
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.chain.Command
import org.apache.commons.chain.Context

@Transactional
@CompileStatic
@Slf4j
@OnApplicationReady
class Auto1Service implements Command {

    CustomSpringEventPublisher customSpringEventPublisher


    @Override
    boolean execute(Context context) throws Exception {
        boolean resultado = false
        log.info "Auto1Service.execute()"
        customSpringEventPublisher.publishCustomEvent("Autorungit")
        return resultado
    }


}
