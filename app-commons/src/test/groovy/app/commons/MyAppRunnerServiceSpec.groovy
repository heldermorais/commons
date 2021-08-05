package app.commons

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MyAppRunnerServiceSpec extends Specification implements ServiceUnitTest<MyAppRunnerService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
