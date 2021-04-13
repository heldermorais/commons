package commons.autoconfig


import grails.core.GrailsApplication
import grails.util.Environment
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j
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
        log.debug "starting ..."
    }

    @Override
    void environmentPrepared(ConfigurableEnvironment environment) {

        log.debug "AutoconfigRunListener - environmentPrepared - BEGIN"

        String encoding = environment.getProperty('grails.config.encoding', String, 'UTF-8')

        MapPropertySource propertySource = null
        Map currentProperties = getCurrentConfig(environment)


        ClassLoader cl = this.getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        Resource[] foundResources = resolver.getResources("classpath*:*Autoconfig.groovy")
                                            .sort { Resource a, Resource b -> a.getFilename() < b.getFilename() ? -1 : 1  }

        log.debug "AutoconfigRunListener - Searching autoconfigFiles like {classpath*:*Autoconfig.groovy} ..."

        foundResources = foundResources.sort {
            it.getFilename()
        }

        for (Resource res: foundResources){

            log.debug(">>> Found autoconfig file: [${res.getFilename()}, ${res.getURL()}]");
            propertySource = loadGroovyConfig(res, encoding, currentProperties)

            if (propertySource?.getSource() && !propertySource.getSource().isEmpty()) {
                environment.propertySources.addFirst(propertySource)
            }

        }

        log.debug "AutoconfigRunListener - environmentPrepared - END"

    }

    @Override
    void contextPrepared(ConfigurableApplicationContext context) {
        log.debug "contextPrepared ..."
    }

    @Override
    void contextLoaded(ConfigurableApplicationContext context) {
        log.debug "contextLoaded ..."
    }

    @Override
    void started(ConfigurableApplicationContext context) {

        log.debug "started ..."

    }

    @Override
    void running(ConfigurableApplicationContext context) {
        log.debug "running ..."
    }

    @Override
    void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.debug "failed ..."
    }


    @CompileStatic(TypeCheckingMode.SKIP)
    protected MapPropertySource loadGroovyConfig(Resource resource, String encoding, Map currentConfig) {

        log.debug("    Loading groovy config file properties...")
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
