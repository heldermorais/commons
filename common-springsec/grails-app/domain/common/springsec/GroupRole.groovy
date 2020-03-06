package common.springsec

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class GroupRole implements Serializable {

	private static final long serialVersionUID = 1

	Group group
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof GroupRole) {
			other.roleId == role?.id && other.groupId == group?.id
		}
	}

	@Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (group) {
            hashCode = HashCodeHelper.updateHash(hashCode, group.id)
		}
		if (role) {
		    hashCode = HashCodeHelper.updateHash(hashCode, role.id)
		}
		hashCode
	}

	static GroupRole get(long groupId, long roleId) {
		criteriaFor(groupId, roleId).get()
	}

	static boolean exists(long groupId, long roleId) {
		criteriaFor(groupId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long groupId, long roleId) {
		GroupRole.where {
			group == Group.load(groupId) &&
			role == Role.load(roleId)
		}
	}

	static GroupRole create(Group group, Role role, boolean flush = false) {
		def instance = new GroupRole(group: group, role: role)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(Group rg, Role r) {
		if (rg != null && r != null) {
			GroupRole.where { group == rg && role == r }.deleteAll()
		}
	}

	static int removeAll(Role r) {
		r == null ? 0 : GroupRole.where { role == r }.deleteAll() as int
	}

	static int removeAll(Group rg) {
		rg == null ? 0 : GroupRole.where { group == rg }.deleteAll() as int
	}

	static constraints = {
	    group nullable: false
		role nullable: false, validator: { Role r, GroupRole rg ->
			if (rg.group?.id) {
				if (GroupRole.exists(rg.group.id, r.id)) {
				    return ['roleGroup.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['group', 'role']
		version false
	}
}
