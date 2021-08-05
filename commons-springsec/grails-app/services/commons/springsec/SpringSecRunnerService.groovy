package commons.springsec

import commons.autoconfig.AbstractAutorunService
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.Ordered


@Transactional
@Slf4j
class SpringSecRunnerService extends AbstractAutorunService {

    @Override
    void run(ApplicationArguments args) throws Exception {
        log.debug "${this.getOrder()  - HIGHEST_PRECEDENCE}. SpringSecRunnerService.run"
    }

    @Override
    int getOrder() {
        return MEDIUM_LEVEL
    }

}