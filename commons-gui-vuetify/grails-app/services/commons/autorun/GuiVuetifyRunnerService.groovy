package commons.autorun


import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.springframework.boot.ApplicationArguments




@Transactional
@Slf4j
class GuiVuetifyRunnerService extends AbstractAutorunService {

    @Override
    void run(ApplicationArguments args) throws Exception {
        log.debug "${this.getOrder()  - HIGHEST_PRECEDENCE}. GuiVuetifyRunnerService.run"
    }

    @Override
    int getOrder() {
        return HIGH_LEVEL + 1
    }

}

