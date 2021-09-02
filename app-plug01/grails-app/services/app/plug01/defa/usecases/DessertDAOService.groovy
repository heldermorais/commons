package app.plug01.defa.usecases

import app.plug01.Dessert
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional


@Transactional
@Service(Dessert)
abstract class DessertDAOService implements IDessertDAO {

}
