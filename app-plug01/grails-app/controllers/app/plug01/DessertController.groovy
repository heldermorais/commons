package app.plug01

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DessertController {

    DessertService dessertService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dessertService.list(params), model:[dessertCount: dessertService.count()]
    }

    def show(Long id) {
        respond dessertService.get(id)
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
            dessertService.save(dessert)
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
        respond dessertService.get(id)
    }

    def update(Dessert dessert) {
        if (dessert == null) {
            notFound()
            return
        }

        try {
            dessertService.save(dessert)
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

        dessertService.delete(id)

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
