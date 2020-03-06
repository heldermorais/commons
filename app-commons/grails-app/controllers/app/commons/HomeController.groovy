package app.commons

class HomeController {


    HomeService homeService

    def index() {

       homeService.hello("Helder")
       homeService.helloUntraced()

        try{
            homeService.hello("ERRO")
        }catch (Exception e){
            log.error("Ih deu Erro...${e.message}")
        }


       render view: '/index.gsp'
    }

}
