package commons.springsec

import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class SecUser implements Serializable {

	private static final long serialVersionUID = 1

	//SpringSecurityService springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<SecRole> getAuthorities() {
		(SecUserSecRole.findAllBySecUser(this) as List<SecUserSecRole>)*.secRole as Set<SecRole>
	}


	static transients = ['springSecurityService']

	static constraints = {
		password nullable: false, blank: false, password: true
		username nullable: false, blank: false, unique: true
	}

	static mapping = {
		password column: 'passwd'
	}
}


/*
 *    private static final long serialVersionUID = 1

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    String fullName
    String cpf
    String email
    String urlUnicad

    String uniqueId

    Set<SecGroup> getAuthorities() {
        (SecUserSecGroup.findAllBySecUser(this) as List<SecUserSecGroup>)*.secGroup as Set<SecGroup>
    }

    def getDomainClass(){
        return this.class
    }

    static constraints = {

        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true

        fullName   nullable: true, blank: true
        cpf       nullable: true, blank: true
        email     nullable: true, blank: true
        urlUnicad nullable: true, blank: true

        uniqueId nullable: true, blank: true

    }

    static mapping = {
        //datasource 'unicad'
        table '[unicad].[SecUser]'
	    password column: '`password`'

        fullName  column: 'full_name'
        cpf       column: 'cpf'
        email     column: 'email'
        urlUnicad column: 'url_unicad'

        uniqueId column: 'unique_id'

        cache false
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("password", password)
                .append("enabled", enabled)
                .append("accountExpired", accountExpired)
                .append("accountLocked", accountLocked)
                .append("passwordExpired", passwordExpired)
                .append("fullName", fullName)
                .append("cpf", cpf)
                .append("email", email)
                .append("urlUnicad", urlUnicad)
                .toString();
    }

 */