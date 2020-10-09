package app.common.keycloak

import app.common.keycloak.secured.OtherController
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class OtherControllerSpec extends Specification implements ControllerUnitTest<OtherController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
