package app.commons

import commons.autoconfig.AbstractAutorunService
import grails.gorm.transactions.Transactional
import org.springframework.boot.ApplicationArguments

@Transactional
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
