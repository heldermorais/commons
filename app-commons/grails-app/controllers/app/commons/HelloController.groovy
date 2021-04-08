package app.commons

class HelloController {

    def index() {

        def recipient = grailsApplication.config.getProperty('foo.bar.hello')
        def recipient2 = grailsApplication.config.getProperty('module.sidecar.hello')
        render "Hello [${recipient}], [${recipient2}]"

    }
}
