package app.commons.autorun

import commons.autorun.AbstractAutorunService
import commons.autoconfig.AutoConfigRunnerService
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.springframework.boot.ApplicationArguments
import org.springframework.stereotype.Component

@Component
@Transactional
@Slf4j
class MyAppRunnerService extends AbstractAutorunService {

    @Override
    void run(ApplicationArguments args) throws Exception {
        log.debug "${this.getOrder()  - HIGHEST_PRECEDENCE}. MyAppRunnerService.run"
    }

    @Override
    int getOrder() {
        return APP_LEVEL + 1
    }

}
