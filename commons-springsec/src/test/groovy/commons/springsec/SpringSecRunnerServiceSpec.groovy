package commons.springsec

import commons.springsec.autorun.SpringSecRunnerService
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class SpringSecRunnerServiceSpec extends Specification implements ServiceUnitTest<SpringSecRunnerService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
