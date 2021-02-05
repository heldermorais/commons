package sidecar


import common.autorun.OnApplicationReady
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.apache.commons.chain.Command
import org.apache.commons.chain.Context
import org.springframework.core.Ordered

@Transactional
@OnApplicationReady
@Slf4j
class Auto3000Service implements Command, Ordered {


    @Override
    boolean execute(Context context) throws Exception {

        log.info "Auto3000Service.execute()"
        Thread.sleep(2000)

        return false

    }

    @Override
    int getOrder() {
        return LOWEST_PRECEDENCE - 200
    }
}
