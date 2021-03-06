package common.springsec

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='name')
@ToString(includes='name', includeNames=true, includePackage=false)
class SecGroup implements Serializable {

	private static final long serialVersionUID = 1

	String name

	Set<SecRole> getAuthorities() {
		(SecGroupSecRole.findAllBySecGroup(this) as List<SecGroupSecRole>)*.secRole as Set<SecRole>
	}

	static constraints = {
		name nullable: false, blank: false, unique: true
	}

	static mapping = {
		cache true
	}
}
