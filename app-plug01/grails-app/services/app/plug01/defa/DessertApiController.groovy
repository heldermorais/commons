package app.plug01.defa

import app.plug01.Dessert
import app.plug01.defa.usecases.DessertDAOService
import grails.validation.ValidationException
import org.springframework.http.HttpStatus


import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT



/**
 *
 */
class DessertApiController {

    /**
     *
     */
    protected DessertDAOService dessertDAOService


    /**
     *
     * @param dessertDAOService
     */
    public DessertApiController( DessertDAOService dessertDAOService ){
        this.dessertDAOService = dessertDAOService
    }


    /**
     *
     * @return
     */
    def index() {

        //dessertDAOService.teste()

        forward action: "list"
    }


    /**
     *
     * @return
     */
    def list() {

       params.offset =  0  // Forçar a carregar a lista do início ( paginação no cliente )
       params.max    = -1  // Forçar a carregar a lista COMPLETA ( paginação no cliente )

       dessertDAOService.teste()

       List<Dessert> lista = dessertDAOService.list( params )
       Long count          = dessertDAOService.count()

       Thread.sleep(2000)

       respond lista, model:[count: count]

    }


    /**
     *
     * @param id
     * @return
     */
    def get(Long id){
        Dessert d = dessertDAOService.get(id)
        respond d
    }


    /**
     *
     * @param dessert
     * @return
     */
    def save(Dessert dessert) {

        if (dessert == null) {
            notFound()
            return
        }

        insertOrUpdate(dessert, HttpStatus.CREATED, HttpStatus.NOT_ACCEPTABLE)

    }

    /**
     *
     * @param dessert
     * @return
     */
    def update(Dessert dessert) {

        if (dessert == null) {
            notFound()
            return
        }

        insertOrUpdate(dessert, HttpStatus.OK, HttpStatus.NOT_ACCEPTABLE)

    }

    /**
     *
     * @param dessert
     * @param statusOk
     * @param statusError
     */
    protected void insertOrUpdate(Dessert dessert, HttpStatus statusOk, HttpStatus statusError) {

        try {
            dessertDAOService.save(dessert)
        } catch (ValidationException e) {
            respond e.errors, [status: statusError]
            return
        }

        respond dessert, [status: statusOk]

    }


    /**
     *
     * @param id
     * @return
     */
    def delete(Long id) {

        if (id == null) {
            notFound()
            return
        }


        try {

            dessertDAOService.delete(id)

        } catch (ValidationException e) {
            respond e.errors, [status: NOT_ACCEPTABLE]
            return
        }


        render status: NO_CONTENT

    }


    /**
     *
     */
    protected void notFound() {
        render status: NOT_FOUND
    }



}
