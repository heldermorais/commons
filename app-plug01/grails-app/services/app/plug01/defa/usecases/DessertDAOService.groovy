package app.plug01.defa.usecases

import app.plug01.Dessert
import grails.gorm.DetachedCriteria
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import org.grails.datastore.mapping.query.Query


@Transactional
@Service(Dessert)
abstract class DessertDAOService implements IDessertDAO {


    void teste(){
        log.info("teste - BEGIN")

        //name ==~ 'Zzz'
        DetachedCriteria<Dessert> criteria = Dessert.where {

        }

        criteria.getCriteria().add( new Query.ILike("name","%2%") )

        def lista = criteria.list()

        log.debug(lista)
        log.info("teste - END")
    }

}
