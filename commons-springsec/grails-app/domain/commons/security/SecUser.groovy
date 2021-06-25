package commons.security


import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Table

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class SecUser implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<SecGroup> getAuthorities() {
        (SecUserSecGroup.findAllBySecUser(this) as List<SecUserSecGroup>)*.secGroup as Set<SecGroup>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
        tablePerHierarchy false
	    password column: '`password`'
    }
}