package common.autoconfig

import grails.compiler.GrailsCompileStatic
import grails.core.GrailsApplication
import grails.util.Environment
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j
import org.apache.commons.chain.Command
import org.apache.commons.chain.Context
import org.grails.config.PropertySourcesConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringApplicationRunListener
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternResolver


@CompileStatic
@Slf4j
class AutoconfigRunListener implements SpringApplicationRunListener {

    protected ResourceLoader defaultResourceLoader = new DefaultResourceLoader()

    AutoconfigRunListener(SpringApplication application, String[] args) {

        log.debug "AutoconfigRunListener - CREATED"

    }


    GrailsApplication grailsApplication


    @Override
    void starting() {

    }

    @Override
    void environmentPrepared(ConfigurableEnvironment environment) {

        log.debug "AutoconfigRunListener - environmentPrepared - BEGIN"

        //List locations = environment.getProperty('grails.config.locations', List, [])
        // See if grails.config.locations is defined in an environments block like 'development' or 'test'
        //String environmentString = "environments.${Environment.current.name}.grails.config.locations"
        //locations = environment.getProperty(environmentString, List, locations)

        String encoding = environment.getProperty('grails.config.encoding', String, 'UTF-8')

        MapPropertySource propertySource = null
        Map currentProperties = getCurrentConfig(environment)


        ClassLoader cl = this.getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        Resource[] foundResources = resolver.getResources("classpath*:autoconfig/**/*.groovy")

        log.debug "AutoconfigRunListener - Searching autoconfigFiles like {classpath*:autoconfig/**/*.groovy} ..."

        foundResources = foundResources.sort {
                    it.getFilename()
                }

        for (Resource res: foundResources){

            log.debug(">>> Found autoconfig file: [${res.getFilename()}, ${res.getURL()}]");
            //Resource resource = defaultResourceLoader.getResource("classpath:/auto/config/CustomSecurityConfig.groovy")
            propertySource = loadGroovyConfig(res, encoding, currentProperties)

            if (propertySource?.getSource() && !propertySource.getSource().isEmpty()) {
                environment.propertySources.addFirst(propertySource)
            }

        }

        log.debug "AutoconfigRunListener - environmentPrepared - END"

    }

    @Override
    void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    void started(ConfigurableApplicationContext context) {

//        this.grailsApplication = context.getBean('grailsApplication')
//        log.debug ("Grails Application : ${grailsApplication}")
//        String[] autorunBeanNames = context.getBeanNamesForAnnotation(Autorun)
//
//        Context chainContext = new ContextBase();
//        chainContext.put("grailsContext", context)
//        chainContext.put("grailsApplication", this.grailsApplication)
//
//        for (String autorunBeanName in autorunBeanNames){
//           this.executeAutorunOn(autorunBeanName, context.getBean(autorunBeanName), chainContext)
//        }

    }

    protected executeAutorunOn ( String autorunBeanName, Object autorunBean , Context chainContext ){

        //log.debug "AutoRun ${autorunBeanName} - BEGIN"

        if (autorunBean instanceof Command){
            Command cmd = (Command) autorunBean
            cmd.execute(chainContext)
        }

        //log.debug "AutoRun ${autorunBeanName} - END"

    }

    @Override
    void running(ConfigurableApplicationContext context) {

    }

    @Override
    void failed(ConfigurableApplicationContext context, Throwable exception) {

    }




    @CompileStatic(TypeCheckingMode.SKIP)
    private MapPropertySource loadGroovyConfig(Resource resource, String encoding, Map currentConfig) {

        //log.info("    Loading groovy config file properties {}", resource.URI)
        String configText           = resource.inputStream.getText(encoding)
        ConfigSlurper slurper       = new ConfigSlurper(Environment.current.name)
        WriteFilteringMap filterMap = new WriteFilteringMap(currentConfig)
        slurper.binding = filterMap
        ConfigObject configObject = slurper.parse(configText)
        Map properties = configText ? configObject?.flatten() : [:]
        Map writtenValues = filterMap.getWrittenValues()
        properties.putAll(writtenValues)


        return new MapPropertySource(resource.filename.toString(), properties)

    }



    static Map getCurrentConfig(ConfigurableEnvironment environment) {
        return new PropertySourcesConfig(environment.propertySources)
    }


}
