package common.springsec

import common.autorun.GenericAutorunService
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.apache.commons.chain.Context

@Transactional
@Slf4j
class T1Service extends GenericAutorunService{

    @Override
    boolean execute(Context context) throws Exception {
        log.info "Heeeyyyyy"

        return false
    }

    @Override
    int getOrder() {
        return 0
    }
}
