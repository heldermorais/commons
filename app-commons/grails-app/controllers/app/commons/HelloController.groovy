package app.commons

class HelloController {

    def index() {

        String resultado = null



        def recipient2 = grailsApplication.config.flatten() //.getProperty('autoconfig.plugins.gui.vuetify.message')



        resultado = "Hello [${recipient2}]"



        render view: 'index'

    }


}
