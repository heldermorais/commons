package app.teste.pessoa.adapter.persist


import app.teste.application.port.ListResult
import app.teste.pessoa.domain.Pessoa
import grails.gorm.CriteriaBuilder
import grails.gorm.DetachedCriteria

interface IPessoaDAOService {


    Pessoa get(Serializable id)

    ListResult<Pessoa> list(Map args)

    ListResult<Pessoa> list(Map args, DetachedCriteria<Pessoa> criteria)

    Long count()

    void delete(Serializable id)

    Pessoa save(Pessoa pessoa)

}