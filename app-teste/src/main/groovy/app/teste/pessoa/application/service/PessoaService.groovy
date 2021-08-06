package app.teste.pessoa.application.service

import app.teste.application.port.ListResult
import app.teste.pessoa.adapter.persist.IPessoaDAOService
import app.teste.pessoa.application.port.PessoaListUseCase
import app.teste.pessoa.domain.Pessoa
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

import grails.gorm.transactions.Transactional


/**
 * Serviço de Regras de Negócio para {@link Pessoa}.
 * @see Pessoa.
 */
@Component
@Transactional
@Slf4j
class PessoaService implements PessoaListUseCase<Pessoa> {


    /**
     * DAO de {@link Pessoa}
     */
    protected IPessoaDAOService pessoaDAOService


    /**
     *
     * @param pessoaDAOService
     */
    PessoaService(IPessoaDAOService pessoaDAOService) {
        this.pessoaDAOService = pessoaDAOService
    }




    /**
     *
     * @param pessoa
     * @return
     */
    Pessoa save(Pessoa pessoa) {
        return pessoaDAOService.save(pessoa)
    }


    /**
     *
     * @param pessoa
     */
    void delete(Pessoa pessoa) {
        pessoaDAOService.delete(pessoa.id)
    }

    /**
     * Excluir instância de @Pessoa, passando como parâmetro o seu ID/Primary Key
     * @param id Id ou Primary key da instância de Pessoa
     */
    void delete(Serializable id) {
        pessoaDAOService.delete(id)
    }





    void initDAO() {

        for (i in 0..1999) {

            Pessoa pessoa = new Pessoa()
            pessoa.setAge(i + 1);
            pessoa.setName("Usuario No.${i + 1}")
            pessoa.setLastVisit(new Date())

            this.pessoaDAOService.save(pessoa)

        }


    }



    @Override
    ListResult<Pessoa> loadAll(Map args) {
        return this.pessoaDAOService.list(args, Pessoa.where { age < 100 })
    }

    @Override
    Pessoa findById(Long id) {
        return pessoaDAOService.get(id)
    }
}
