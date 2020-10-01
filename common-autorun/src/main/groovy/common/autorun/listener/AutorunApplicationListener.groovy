package common.autorun.listener

import common.autorun.Autorun
import groovy.util.logging.Slf4j
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener

@Slf4j
class AutorunApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    void onApplicationEvent(ApplicationReadyEvent event) {
        String[] autorunBeansNames = event.applicationContext.getBeanNamesForAnnotation(Autorun.class)

        for(String beanName in autorunBeansNames){
            log.info "FoundBean -> ${beanName}"
            Object bean = event.applicationContext.getBean(beanName)
            Autorun ann = (Autorun) bean.getClass().getAnnotation(Autorun.class)
            String methodName = ann.executeMethod()

            //autorunBeans.add( new AutorunBeanAdapter(bean, beanName, methodName) )
        }
    }

}
