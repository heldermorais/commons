package app.commons

import app.commons.aop.checkers.Checker1Service
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class Checker1ServiceSpec extends Specification implements ServiceUnitTest<Checker1Service>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
