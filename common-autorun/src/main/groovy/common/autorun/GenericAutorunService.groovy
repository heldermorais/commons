package common.autorun

import org.apache.commons.chain.Command
import org.apache.commons.chain.Context
import org.springframework.core.Ordered

abstract class GenericAutorunService implements Command, Ordered {


    protected Integer executionOrder
    protected Object  autorunBean
    protected String  methodName
    protected String  beanName


    @Override
    public String toString() {
        return "${this.beanName}.${this.methodName}"
    }

}
