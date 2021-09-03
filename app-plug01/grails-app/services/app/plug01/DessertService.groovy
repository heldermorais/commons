package app.plug01

import grails.gorm.services.Service



@Service(Dessert)
interface DessertService {

    Dessert get(Serializable id)

    List<Dessert> list(Map args)

    Long count()

    void delete(Serializable id)

    Dessert save(Dessert dessert)



}