package app.keycloak2

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class KeycloakOnRequestInterceptorSpec extends Specification implements InterceptorUnitTest<KeycloakOnRequestInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test keycloakOnRequest interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"keycloakOnRequest")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
