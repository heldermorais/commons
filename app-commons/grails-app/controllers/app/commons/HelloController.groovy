package app.commons

class HelloController {

    def index() {

        String resultado = null



        def recipient2 = grailsApplication.config.flatten() //.getProperty('autoconfig.plugins.gui.vuetify.message')



        resultado = "Hello [${recipient2}]"



        render view: 'index'

    }


    def apiHello(){
        log.warn("Params: ${request.JSON}, ${params}")
        String nome = (params.nome != null) ? params.nome : "stranger"
        Thread.sleep(3000)
        render view: 'apiHello', model:[messageStr:  "Hello, ${nome} !"]
    }

}
