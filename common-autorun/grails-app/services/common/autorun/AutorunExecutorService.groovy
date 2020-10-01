package common.autorun

import grails.gorm.transactions.Transactional
import org.apache.commons.chain.impl.ChainBase
import org.apache.commons.chain.impl.ContextBase
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.Ordered

@Transactional
class AutorunExecutorService implements ApplicationContextAware{

    ApplicationContext applicationContext

    void execute() {
        log.debug "Autorun - Loading autorunBeans"

        String[] autorunBeansNames = this.applicationContext.getBeanNamesForAnnotation(Autorun.class)
        //Object[] arbeans = this.applicationContext.getBeansOfType(GenericAutorunService)
        //String[] autorunBeansNames = this.applicationContext.getBeanNamesForType(GenericAutorunService)
        ArrayList<GenericAutorunService> autorunBeans = new ArrayList<GenericAutorunService>()

        for(String beanName in autorunBeansNames){
            Object bean = this.applicationContext.getBean(beanName)
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
        //chainContext.put('grailsApplication', grailsApplication)

        chain.execute(chainContext)

        log.debug "Autorun - Execution of Autobeans has finished"

    }

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.applicationContext = applicationContext
    }
}
