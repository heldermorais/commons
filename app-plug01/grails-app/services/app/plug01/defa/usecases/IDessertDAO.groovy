package app.plug01.defa.usecases

import app.plug01.Dessert


/**
 *
 */
interface IDessertDAO {

    Dessert get(Serializable id)

    List<Dessert> list(Map args)

    Long count()

    void delete(Serializable id)

    Dessert save(Dessert dessert)

}