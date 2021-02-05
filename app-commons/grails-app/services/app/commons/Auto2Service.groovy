package app.commons


import common.autorun.OnApplicationReady
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.chain.Command
import org.apache.commons.chain.Context
import org.springframework.core.PriorityOrdered

@Transactional
@Slf4j
@CompileStatic
@OnApplicationReady
class Auto2Service implements Command, PriorityOrdered {


    @Override
    boolean execute(Context context) throws Exception {
        boolean resultado = false
        log.info "Auto2Service.execute()"

        return resultado
    }



    @Override
    int getOrder() {
        return HIGHEST_PRECEDENCE
    }

}
