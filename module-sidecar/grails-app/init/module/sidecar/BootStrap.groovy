package module.sidecar

import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {

    def init = { servletContext ->

        log.debug "bootstrap SIDECAR..."

    }
    def destroy = {
    }
}
