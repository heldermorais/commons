package app.commons

class HomeController {


    HomeService homeService

    def index() {

       homeService.hello()

       render view: '/index.gsp'
    }

}
