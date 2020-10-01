package common.autorun

import org.apache.commons.chain.Command
import org.apache.commons.chain.Context
import org.springframework.core.Ordered
import org.springframework.util.ReflectionUtils

import java.lang.reflect.Method

class AutorunBeanAdapter extends GenericAutorunService {



    AutorunBeanAdapter( Object bean , String beanName, String methodName = 'execute', Integer executionOrder = Ordered.LOWEST_PRECEDENCE ){
        super()

        this.autorunBean    = bean
        this.executionOrder = executionOrder
        this.methodName     = methodName
        this.beanName       = beanName
    }

    @Override
    boolean execute(Context context) throws Exception {

        boolean applyParameters = true
        boolean result = false

        if(this.autorunBean instanceof Command){
            result = ((Command) this.autorunBean).execute(context)
        }else{
            Method method2Run = ReflectionUtils.findMethod(this.autorunBean.getClass(), this.methodName, Context.class )
            if (!method2Run){
                applyParameters = false
                method2Run = ReflectionUtils.findMethod(this.autorunBean.getClass(), this.methodName )
            }

            if (method2Run){
                if(applyParameters){
                    ReflectionUtils.invokeMethod(method2Run, this.autorunBean, context)
                }else{
                    ReflectionUtils.invokeMethod(method2Run, this.autorunBean)
                }
            }else{
                throw new NoSuchMethodError("Error while trying to Autorun method ${methodName} on ${this.autorunBean}.")
            }
        }

        return result
    }

    @Override
    int getOrder() {
        if(this.autorunBean instanceof Ordered){
            return ((Ordered) this.autorunBean).getOrder()
        }else{
            return this.executionOrder
        }
    }



}
