package app.plug01.defa

import app.plug01.Dessert
import commons.gui.vuetify.renderer.VueDatatableRenderer
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class DessertApiController implements VueDatatableRenderer {


    def index() {
        redirect action: "list"
    }



    def list() {
       def lista = Dessert.list()
       respond Dessert.list()
    }



    def get(Long id){
        Dessert d = Dessert.get(id)
        respond d
    }


    def save(Dessert dessert) {
        if (dessert == null) {
            notFound()
            return
        }

        try {
            dessert.save()
        } catch (ValidationException e) {
            respond dessert.errors, [status: NOT_ACCEPTABLE]
            return
        }

        respond dessert, [status: CREATED]

    }

    def update(Dessert dessert) {
        if (dessert == null) {
            notFound()
            return
        }

        try {
            dessert.save()
        } catch (ValidationException e) {
            respond dessert.errors, [status: NOT_ACCEPTABLE]
            return
        }

        respond dessert, [status: OK]

    }


    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }


        try {
            Dessert dessert = Dessert.get(id)

            if(dessert != null){
                dessert.delete(id)
            }else{
                throw new RuntimeException("Operação não completada.")
            }
        } catch (ValidationException e) {
            respond dessert.errors, [status: NOT_ACCEPTABLE]
            return
        } catch (RuntimeException e) {
            respond e.message, [status: NOT_ACCEPTABLE]
            return
        }

        render status: OK

    }

    protected void notFound() {
        render status: NOT_FOUND
    }
}
