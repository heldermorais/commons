package commons.autoconfig

import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {

    def init = { servletContext ->

      log.debug "bootstrap AUTOCONFIG..."

    }
    def destroy = {
    }
}
