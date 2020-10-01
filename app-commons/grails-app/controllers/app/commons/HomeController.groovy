package app.commons

import common.autorun.Autorun
import common.autorun.GenericAutorunService
import grails.core.GrailsApplication


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



        String[] autorunBeansNames = grailsApplication.mainContext.getBeanNamesForType(GenericAutorunService.class)

        for(String beanName in autorunBeansNames){
            log.info "FoundBean -> ${beanName}"
            Object bean = grailsApplication.mainContext.getBean(beanName)
            Autorun ann = (Autorun) bean.getClass().getAnnotation(Autorun.class)
            String methodName = ann.executeMethod()

            //autorunBeans.add( new AutorunBeanAdapter(bean, beanName, methodName) )
        }


       render view: '/index.gsp'
    }

}
