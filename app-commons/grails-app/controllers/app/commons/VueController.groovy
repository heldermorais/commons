package app.commons

class VueController {

    def index() { }


    def apiHello(){
        log.warn("Params: ${request.JSON}, ${params}")
        String nome = (params.nome != null) ? params.nome : "stranger"
        Thread.sleep(3000)
        render view: 'apiHello', model:[messageStr:  "Hello, ${nome} !"]
    }


}
