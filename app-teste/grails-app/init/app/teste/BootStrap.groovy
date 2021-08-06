package app.teste

import app.teste.pessoa.application.service.PessoaService

class BootStrap {


    PessoaService pessoaService

    def init = { servletContext ->
        pessoaService.initDAO()
    }
    def destroy = {
    }
}
