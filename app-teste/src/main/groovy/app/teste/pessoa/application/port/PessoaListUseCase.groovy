package app.teste.pessoa.application.port

import app.teste.application.port.ListResult
import app.teste.pessoa.domain.Pessoa

interface PessoaListUseCase<T> {

    ListResult<T> loadAll(Map args)

    Pessoa findById(Long id)

}