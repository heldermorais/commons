package app.plug01

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DessertServiceSpec extends Specification {

    DessertService dessertService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Dessert(...).save(flush: true, failOnError: true)
        //new Dessert(...).save(flush: true, failOnError: true)
        //Dessert dessert = new Dessert(...).save(flush: true, failOnError: true)
        //new Dessert(...).save(flush: true, failOnError: true)
        //new Dessert(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dessert.id
    }

    void "test get"() {
        setupData()

        expect:
        dessertService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Dessert> dessertList = dessertService.list(max: 2, offset: 2)

        then:
        dessertList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dessertService.count() == 5
    }

    void "test delete"() {
        Long dessertId = setupData()

        expect:
        dessertService.count() == 5

        when:
        dessertService.delete(dessertId)
        sessionFactory.currentSession.flush()

        then:
        dessertService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Dessert dessert = new Dessert()
        dessertService.save(dessert)

        then:
        dessert.id != null
    }
}
