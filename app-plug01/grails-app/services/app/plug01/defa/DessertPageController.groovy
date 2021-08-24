package app.plug01.defa

import groovy.util.logging.Slf4j


@Slf4j
class DessertPageController {


    protected Plug01Service plug01Service

    DessertPageController(Plug01Service plug01Service) {
        this.plug01Service = plug01Service
    }


    def index() {

        //use renderVueComponent para renderizar um único componente na página ( sem props nem nada )
        HashMap model = new HashMap()
        model.put("Slasse", "Plugin01Controller")

        renderVueComponent("plug01-homepage", "vue/plug01/plug01_HomePage.js", model)

    }


}
