package app.plug01

import app.plug01.defa.Dessert3DAOService
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

            Random aleatorio = new Random();


            Dessert dess1 = new Dessert(
                        name     : "Dessert ${i}",
                        calories : aleatorio.nextInt(100),
                        fat      : aleatorio.nextInt(2),
                        carbs    : aleatorio.nextInt(5),
                        protein  : aleatorio.nextDouble(),
                        iron     : aleatorio.nextDouble()
            )
            dess1.save()
            log.debug " ..... dessert: ${dess1}"
        }
        log.debug "${this.getOrder()  - HIGHEST_PRECEDENCE}. AutorunDessertService.run - END"
    }

    @Override
    int getOrder() {
        return APP_LEVEL + 10
    }

}