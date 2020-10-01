package common.autorun


import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {

    AutorunExecutorService autorunExecutorService
    def init = { servletContext ->
        autorunExecutorService.execute()
    }


    def destroy = {

    }


}
