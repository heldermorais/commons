package app.plug01

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class Dessert2Controller {

    protected Dessert2DAOService dessert2DAOService

    Dessert2Controller(Dessert2DAOService dessert2DAOService) {
        this.dessert2DAOService = dessert2DAOService
    }


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        redirect action: 'list'
    }


    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<Dessert> lista = dessert2DAOService.list(params)

        render view: 'index' ,model:[dessertCount: 200, dessertList: lista ]
    }

    def show(Long id) {
        respond dessert2DAOService.getById(id)
    }




    def create() {
        respond new Dessert(params)
    }

    def save(Dessert dessert) {
        if (dessert == null) {
            notFound()
            return
        }

        try {
            dessert2DAOService.save(dessert)
        } catch (ValidationException e) {
            respond dessert.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dessert.label', default: 'Dessert'), dessert.id])
                redirect dessert
            }
            '*' { respond dessert, [status: CREATED] }
        }
    }




    def edit(Long id) {
        respond dessert2DAOService.getById(id)
    }

    def update(Dessert dessert) {
        if (dessert == null) {
            notFound()
            return
        }

        try {
            dessert2DAOService.save(dessert)
        } catch (ValidationException e) {
            respond dessert.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dessert.label', default: 'Dessert'), dessert.id])
                redirect dessert
            }
            '*'{ respond dessert, [status: OK] }
        }
    }




    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dessert2DAOService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dessert.label', default: 'Dessert'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }




    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dessert.label', default: 'Dessert'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }



}
