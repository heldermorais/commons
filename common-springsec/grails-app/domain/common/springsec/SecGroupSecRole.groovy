package common.springsec

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class SecGroupSecRole implements Serializable {

	private static final long serialVersionUID = 1

	SecGroup secGroup
	SecRole secRole

	@Override
	boolean equals(other) {
		if (other instanceof SecGroupSecRole) {
			other.secRoleId == secRole?.id && other.secGroupId == secGroup?.id
		}
	}

	@Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (secGroup) {
            hashCode = HashCodeHelper.updateHash(hashCode, secGroup.id)
		}
		if (secRole) {
		    hashCode = HashCodeHelper.updateHash(hashCode, secRole.id)
		}
		hashCode
	}

	static SecGroupSecRole get(long secGroupId, long secRoleId) {
		criteriaFor(secGroupId, secRoleId).get()
	}

	static boolean exists(long secGroupId, long secRoleId) {
		criteriaFor(secGroupId, secRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long secGroupId, long secRoleId) {
		SecGroupSecRole.where {
			secGroup == SecGroup.load(secGroupId) &&
			secRole == SecRole.load(secRoleId)
		}
	}

	static SecGroupSecRole create(SecGroup secGroup, SecRole secRole, boolean flush = false) {
		def instance = new SecGroupSecRole(secGroup: secGroup, secRole: secRole)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(SecGroup rg, SecRole r) {
		if (rg != null && r != null) {
			SecGroupSecRole.where { secGroup == rg && secRole == r }.deleteAll()
		}
	}

	static int removeAll(SecRole r) {
		r == null ? 0 : SecGroupSecRole.where { secRole == r }.deleteAll() as int
	}

	static int removeAll(SecGroup rg) {
		rg == null ? 0 : SecGroupSecRole.where { secGroup == rg }.deleteAll() as int
	}

	static constraints = {
	    secGroup nullable: false
		secRole nullable: false, validator: { SecRole r, SecGroupSecRole rg ->
			if (rg.secGroup?.id) {
				if (SecGroupSecRole.exists(rg.secGroup.id, r.id)) {
				    return ['roleGroup.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['secGroup', 'secRole']
		version false
	}
}
