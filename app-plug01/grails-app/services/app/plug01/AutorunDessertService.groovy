package app.plug01

import commons.autorun.AbstractAutorunService
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.springframework.boot.ApplicationArguments

@Transactional
@Slf4j
class AutorunDessertService  extends AbstractAutorunService {

    @Override
    void run(ApplicationArguments args) throws Exception {
        log.debug "${this.getOrder()  - HIGHEST_PRECEDENCE}. AutorunDessertService.run - BEGIN"
        for (i in 0..200) {
            Dessert dess1 = new Dessert(
                    name: "Dessert ${i}",
                    calories: 100,
                    fat : 2,
                    carbs: 5,
                    protein: 3.5,
                    iron: 45.8
            )
            dess1.save()
        }
        log.debug "${this.getOrder()  - HIGHEST_PRECEDENCE}. AutorunDessertService.run - END"
    }

    @Override
    int getOrder() {
        return APP_LEVEL + 10
    }

}