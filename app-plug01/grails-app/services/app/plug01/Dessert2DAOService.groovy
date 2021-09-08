package app.plug01


import commons.database.utils.dao.IGenericDAO
import grails.gorm.transactions.Transactional

@Transactional
class Dessert2DAOService implements IGenericDAO<Dessert> {

    protected DessertHelperService dessertHelperService

    Dessert2DAOService ( DessertHelperService dessertHelperService ){
        this.dessertHelperService = dessertHelperService
    }

    Dessert findDessert(String name){
        return this.dessertHelperService.findDessert(name)
    }


    Dessert findLikeDessert(String name){
        String nameLike = "%${name}%"
        return Dessert.where{
            name =~ nameLike
        }.get()
    }

}
