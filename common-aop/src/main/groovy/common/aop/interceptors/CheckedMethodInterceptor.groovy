package common.aop.interceptors

import grails.core.GrailsApplication
import groovy.util.logging.Slf4j
import org.apache.commons.chain.Chain
import org.apache.commons.chain.Context
import org.apache.commons.chain.impl.ContextBase
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

import javax.inject.Inject
import java.lang.reflect.Method

@Slf4j
@Aspect
class CheckedMethodInterceptor {

    @Inject
    GrailsApplication grailsApplication

    /**
     * expression to identify which method calls to intercept
     */
    @Pointcut("execution(@common.aop.interceptors.CheckBeforeExecution * *(..))")
    public void executeMethods() {}

    @Around("executeMethods()")
    def interceptJobExecuteMethod(ProceedingJoinPoint joinPoint) {
        //FlushMode flushMode = grailsApplication.mainContext.sessionFactory.currentSession.flushMode
        log.debug("Inside CheckedMethodInterceptor: Hope everything goes well.")

        //Modify default flush mode for current session
        //grailsApplication.mainContext.sessionFactory.currentSession.setFlushMode(FlushMode.COMMIT)

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        CheckBeforeExecution myAnnotation = method.getAnnotation(CheckBeforeExecution.class);

        Chain checker = grailsApplication.mainContext.getBean(myAnnotation.checker())
        Context executionContext = new ContextBase()
        executionContext.put("joinPoint", joinPoint)

        int i = 0
        signature.getParameterNames().each{ String parameterName ->
            executionContext.put(parameterName, joinPoint.getArgs()[i])
            i++
        }

        boolean resultado = checker.execute(executionContext)
        //Proceed with method execution
        joinPoint.proceed()

        log.debug("Inside CheckedMethodInterceptor: Done.")

    }

}
