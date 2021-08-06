package app.teste.pessoa.adapter.persist

import app.teste.application.port.ListResult
import app.teste.pessoa.domain.Pessoa
import grails.gorm.CriteriaBuilder
import grails.gorm.DetachedCriteria
import grails.gorm.services.Service



@Service(Pessoa)
abstract class PessoaDAOService implements IPessoaDAOService {


    ListResult<Pessoa> list(Map args){

        return list(args, Pessoa.where({}))

    }

    ListResult<Pessoa> list(Map args, DetachedCriteria<Pessoa> criteria){

        def query = criteria

        ListResult<Pessoa> result = new ListResult<Pessoa>( query.list(args), query.count(args))


        return result

    }


}
