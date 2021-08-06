package app.teste.pessoa.domain

import grails.persistence.Entity

/**
 *  Entity: Pessoa
 */
@Entity
class Pessoa {

    /**
     * Nome
     */
    String name

    /**
     * Idade
     */
    Integer age

    /**
     * Data da última visita
     */
    Date lastVisit

}
