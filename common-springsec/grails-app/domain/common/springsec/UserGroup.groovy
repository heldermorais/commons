package common.springsec

import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class UserGroup implements Serializable {

	private static final long serialVersionUID = 1

	User user
	Group group

	@Override
	boolean equals(other) {
		if (other instanceof UserGroup) {
			other.userId == user?.id && other.groupId == group?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (user) {
            hashCode = HashCodeHelper.updateHash(hashCode, user.id)
		}
		if (group) {
		    hashCode = HashCodeHelper.updateHash(hashCode, group.id)
		}
		hashCode
	}
	
	static UserGroup get(long userId, long groupId) {
		criteriaFor(userId, groupId).get()
	}

	static boolean exists(long userId, long groupId) {
		criteriaFor(userId, groupId).count()
	}

	private static DetachedCriteria criteriaFor(long userId, long groupId) {
		UserGroup.where {
			user == User.load(userId) &&
			group == Group.load(groupId)
		}
	}

	static UserGroup create(User user, Group group, boolean flush = false) {
		def instance = new UserGroup(user: user, group: group)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(User u, Group rg) {
		if (u != null && rg != null) {
			UserGroup.where { user == u && group == rg }.deleteAll()
		}
	}

	static int removeAll(User u) {
		u == null ? 0 : UserGroup.where { user == u }.deleteAll() as int
	}

	static int removeAll(Group rg) {
		rg == null ? 0 : UserGroup.where { group == rg }.deleteAll() as int
	}

	static constraints = {
	    group nullable: false
		user nullable: false, validator: { User u, UserGroup ug ->
			if (ug.group?.id) {
				if (UserGroup.exists(u.id, ug.group.id)) {
					return ['userGroup.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['group', 'user']
		version false
	}
}
