package common.autorun

import grails.gorm.transactions.Transactional
import groovy.transform.AnnotationCollector
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Autorun {
   String executeMethod() default "execute"
}