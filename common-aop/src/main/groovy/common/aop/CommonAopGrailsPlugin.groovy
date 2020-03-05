package common.aop

import common.aop.interceptors.CheckBeforeExecution
import grails.plugins.*
import org.codehaus.groovy.reflection.CachedMethod
import org.springframework.core.annotation.AnnotationUtils

import java.lang.reflect.Method

class CommonAopGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "4.0.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Common Aop" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/common-aop"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    Closure doWithSpring() { {->
            // TODO Implement runtime spring config (optional)
        }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)

        grailsApplication.serviceClasses.each { serviceClass ->


            if(AnnotationUtils.findAnnotation(serviceClass.clazz, CheckBeforeExecution.class) != null) {

                serviceClass.metaClass.invokeMethod = { name, args ->

                    def metaMethod = delegate.metaClass.getMetaMethod(name, args)
                    try {

                        delegate.log.debug "Invoking $name in ${delegate.class.name}"

                        if (metaMethod instanceof CachedMethod) {

                            Method meth1 = ((CachedMethod) metaMethod).getCachedMethod();

                            def ann = meth1.getAnnotation(CheckBeforeExecution)
                            if (ann != null) {
                                delegate.log.debug "   ... Found CheckBefore Annotation ${ann.checker()}"
                            }

                        }

                        def result = metaMethod.doMethodInvoke(delegate, args)

                        delegate.log.info "Execution completed for method $name with result [$result]"

                        return result

                    } catch (Exception ex) {
                        delegate.log.error "Exception occurred during execution of $name: ${ex.message}"
                        throw ex
                    }
                }

            }


        }

    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
