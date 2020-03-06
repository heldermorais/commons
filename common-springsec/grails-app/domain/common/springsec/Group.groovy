package common.springsec

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='name')
@ToString(includes='name', includeNames=true, includePackage=false)
class Group implements Serializable {

	private static final long serialVersionUID = 1

	String name

	Set<Role> getAuthorities() {
		(GroupRole.findAllByGroup(this) as List<GroupRole>)*.role as Set<Role>
	}

	static constraints = {
		name nullable: false, blank: false, unique: true
	}

	static mapping = {
		cache true
	}
}
