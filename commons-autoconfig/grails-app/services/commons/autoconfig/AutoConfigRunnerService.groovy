package commons.autoconfig

import commons.autorun.AbstractAutorunService
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.springframework.boot.ApplicationArguments




@Transactional
@Slf4j
class AutoConfigRunnerService extends AbstractAutorunService {

    @Override
    void run(ApplicationArguments args) throws Exception {
       log.debug "${this.getOrder() - HIGHEST_PRECEDENCE}. AutoConfigRunnerService.run"
    }

    @Override
    int getOrder() {
        return LOW_LEVEL
    }

}
