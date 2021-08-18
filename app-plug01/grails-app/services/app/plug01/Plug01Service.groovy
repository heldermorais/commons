package app.plug01

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j



@Transactional
@Slf4j
class Plug01Service {

    def execute() {
        log.debug("Plug01Service.execute  - BEGIN")
        Thread.sleep(2000)
        log.debug("Plug01Service.execute  - END")
    }

}
