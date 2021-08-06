package app.teste.pessoa.adapter.web

import app.teste.application.port.ListResult
import app.teste.pessoa.adapter.persist.IPessoaDAOService
import app.teste.pessoa.application.service.PessoaService
import app.teste.pessoa.domain.Pessoa
import grails.validation.ValidationException
import grails.web.Controller

import static org.springframework.http.HttpStatus.*


@Controller
class PessoaController {

    PessoaService pessoaService



    PessoaController(PessoaService pessoaService) {
        this.pessoaService    = pessoaService
    }



    @UseCase("Pessoa.list")
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        ListResult<Pessoa> resultado = this.pessoaService.loadAll(params)

        List<Pessoa> pessoaList = resultado.getLista()

        //respond pessoaDAOService.list(params), model:[pessoaCount: pessoaDAOService.count()]
        respond pessoaList, model:[pessoaCount: resultado.getCount()]
    }



    @UseCase("Pessoa.list")
    def show(Long id) {
        respond pessoaService.findById(id)
    }

    @UseCase("Pessoa.list")
    def edit(Long id) {
        respond pessoaService.findById(id)
    }







    def create() {
        respond new Pessoa(params)
    }



    def save(Pessoa pessoa) {
        if (pessoa == null) {
            notFound()
            return
        }

        try {
            pessoaService.save(pessoa)
        } catch (ValidationException e) {
            respond pessoa.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoa.id])
                redirect pessoa
            }
            '*' { respond pessoa, [status: CREATED] }
        }
    }


    def update(Pessoa pessoa) {
        if (pessoa == null) {
            notFound()
            return
        }

        try {
            pessoaService.save(pessoa)
        } catch (ValidationException e) {
            respond pessoa.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoa.id])
                redirect pessoa
            }
            '*'{ respond pessoa, [status: OK] }
        }
    }




    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pessoaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }






    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


}
