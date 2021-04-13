package commons.security

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.codehaus.groovy.util.HashCodeHelper

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class SecUserSecGroup implements Serializable {

	private static final long serialVersionUID = 1

	SecUser secUser
	SecGroup secGroup

	@Override
	boolean equals(other) {
		if (other instanceof SecUserSecGroup) {
			other.secUserId == secUser?.id && other.secGroupId == secGroup?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (secUser) {
            hashCode = HashCodeHelper.updateHash(hashCode, secUser.id)
		}
		if (secGroup) {
		    hashCode = HashCodeHelper.updateHash(hashCode, secGroup.id)
		}
		hashCode
	}
	
	static SecUserSecGroup get(long secUserId, long secGroupId) {
		criteriaFor(secUserId, secGroupId).get()
	}

	static boolean exists(long secUserId, long secGroupId) {
		criteriaFor(secUserId, secGroupId).count()
	}

	private static DetachedCriteria criteriaFor(long secUserId, long secGroupId) {
		SecUserSecGroup.where {
			secUser == SecUser.load(secUserId) &&
			secGroup == SecGroup.load(secGroupId)
		}
	}

	static SecUserSecGroup create(SecUser secUser, SecGroup secGroup, boolean flush = false) {
		def instance = new SecUserSecGroup(secUser: secUser, secGroup: secGroup)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(SecUser u, SecGroup rg) {
		if (u != null && rg != null) {
			SecUserSecGroup.where { secUser == u && secGroup == rg }.deleteAll()
		}
	}

	static int removeAll(SecUser u) {
		u == null ? 0 : SecUserSecGroup.where { secUser == u }.deleteAll() as int
	}

	static int removeAll(SecGroup rg) {
		rg == null ? 0 : SecUserSecGroup.where { secGroup == rg }.deleteAll() as int
	}

	static constraints = {
	    secGroup nullable: false
		secUser nullable: false, validator: { SecUser u, SecUserSecGroup ug ->
			if (ug.secGroup?.id) {
				if (SecUserSecGroup.exists(u.id, ug.secGroup.id)) {
					return ['userGroup.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['secGroup', 'secUser']
		version false
	}
}
