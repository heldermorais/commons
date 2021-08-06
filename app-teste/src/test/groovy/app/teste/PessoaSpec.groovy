package app.teste

import app.teste.pessoa.domain.Pessoa
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PessoaSpec extends Specification implements DomainUnitTest<Pessoa> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
