package app.commons

//import grails.plugin.springsecurity.SpringSecurityService

class HelloController {

    //SpringSecurityService springSecurityService

//    HelloController( SpringSecurityService springSecurityService ){
//        //this.springSecurityService = springSecurityService
//    }

    def index() {

        String resultado = null


        //def recipient = grailsApplication.config.getProperty('foo.bar.hello')
        def recipient2 = grailsApplication.config.getProperty('module.sidecar.hello')

        def recipient = grailsApplication.config.getProperty('grails.plugin.springsecurity.userLookup.userDomainClassName')

        resultado = "Hello [${recipient}], [${recipient2}]"



        render resultado

    }

}
