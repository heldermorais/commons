package app.commons.aop.checkers

import common.aop.interceptors.GenericMethodExecutionChecker
import grails.gorm.transactions.Transactional
import org.apache.commons.chain.Context

@Transactional
class Checker1Service extends GenericMethodExecutionChecker {

    @Override
    boolean execute(Context context) throws Exception {
        context.keySet().each {String key ->
            log.info ("Checando param ${key} = ${context.get(key)}")

            if(((String)context.get(key)).equalsIgnoreCase("ERRO")){
                throw new RuntimeException("Erro ao checar NOME!!!")
            }

        }
        return super.execute(context)
    }
}
