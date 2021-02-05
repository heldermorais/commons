package common.autorun

import common.autorun.adapter.AutorunBeanAdapter
import common.autorun.adapter.GenericAutorunService
import grails.core.GrailsApplication
import grails.core.support.GrailsApplicationAware
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.chain.impl.ChainBase
import org.apache.commons.chain.impl.ContextBase
import org.springframework.beans.BeansException
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationListener

@Slf4j
@CompileStatic
class AutorunApplicationListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware, GrailsApplicationAware {

    public AutorunApplicationListener() {
        super()
        log.debug "Created"
    }


    ApplicationContext appContext
    GrailsApplication  grailsApplication



    @Override
    void onApplicationEvent(ApplicationReadyEvent event) {
        log.debug "ApplicationReadyEvent - BEGIN"
        String[] autorunBeansNames = this.appContext.getBeanNamesForAnnotation(Autorun.class)
        ArrayList<GenericAutorunService> autorunBeans = new ArrayList<GenericAutorunService>()

        for(String beanName in autorunBeansNames){
            log.debug "FoundBean -> ${beanName}"
            Object bean = event.applicationContext.getBean(beanName)
            Autorun ann = (Autorun) bean.getClass().getAnnotation(Autorun.class)
            String methodName = ann.executeMethod()

            autorunBeans.add( new AutorunBeanAdapter(bean, beanName, methodName) )
        }

        def sortedbeans = autorunBeans.sort {
            it.getOrder()
        }

        ChainBase chain = new ChainBase()
        int contador = 1
        for(GenericAutorunService bean in sortedbeans){
            log.debug "Autorun - found bean : (${contador}) ${bean.toString()}"
            chain.addCommand(bean)
            contador++
        }

        ContextBase chainContext = new ContextBase()
        chainContext.put('grailsApplication', this.grailsApplication)
        chainContext.put('applicationContext', this.appContext)

        log.debug "ApplicationReadyEvent - Executing Autorun beans"

        try {
            chain.execute(chainContext)
        }catch(Exception e){
            log.error("Erro ao executar Autorun bean",e)
        }


        log.debug "ApplicationReadyEvent - END"
    }



    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext
    }

    @Override
    void setGrailsApplication(GrailsApplication grailsApplication) {
         this.grailsApplication = grailsApplication
    }


}
