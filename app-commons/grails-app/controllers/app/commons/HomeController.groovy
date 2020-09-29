package app.commons

import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured


class HomeController {


    GrailsApplication grailsApplication

    HomeService homeService

    def index() {


       boolean enabled = grailsApplication.config.common.autoconfig.enabled
       log.info ("autoconfig.enabled = ${enabled}")

       homeService.hello("Helder")
       homeService.helloUntraced()

        try{
            homeService.hello("ERRO")
        }catch (Exception e){
            log.error("Ih deu Erro...${e.message}")
        }


       render view: '/index.gsp'
    }

}
