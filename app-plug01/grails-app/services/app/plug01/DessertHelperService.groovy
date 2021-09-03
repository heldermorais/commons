package app.plug01

import grails.gorm.services.Service


@Service(Dessert)
interface DessertHelperService {
    Dessert findDessert(String name)
}