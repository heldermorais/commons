package app.plug01

import groovy.util.logging.Slf4j


@Slf4j
class Plug01Controller {


    protected Plug01Service plug01Service

    Plug01Controller(Plug01Service plug01Service) {
        this.plug01Service = plug01Service
    }




    def index() { }


}
