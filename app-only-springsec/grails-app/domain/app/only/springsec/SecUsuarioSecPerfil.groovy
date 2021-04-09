package app.only.springsec

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class SecUsuarioSecPerfil implements Serializable {

	private static final long serialVersionUID = 1

	SecUsuario secUsuario
	SecPerfil secPerfil

	@Override
	boolean equals(other) {
		if (other instanceof SecUsuarioSecPerfil) {
			other.secUsuarioId == secUsuario?.id && other.secPerfilId == secPerfil?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (secUsuario) {
            hashCode = HashCodeHelper.updateHash(hashCode, secUsuario.id)
		}
		if (secPerfil) {
		    hashCode = HashCodeHelper.updateHash(hashCode, secPerfil.id)
		}
		hashCode
	}

	static SecUsuarioSecPerfil get(long secUsuarioId, long secPerfilId) {
		criteriaFor(secUsuarioId, secPerfilId).get()
	}

	static boolean exists(long secUsuarioId, long secPerfilId) {
		criteriaFor(secUsuarioId, secPerfilId).count()
	}

	private static DetachedCriteria criteriaFor(long secUsuarioId, long secPerfilId) {
		SecUsuarioSecPerfil.where {
			secUsuario == SecUsuario.load(secUsuarioId) &&
			secPerfil == SecPerfil.load(secPerfilId)
		}
	}

	static SecUsuarioSecPerfil create(SecUsuario secUsuario, SecPerfil secPerfil, boolean flush = false) {
		def instance = new SecUsuarioSecPerfil(secUsuario: secUsuario, secPerfil: secPerfil)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(SecUsuario u, SecPerfil r) {
		if (u != null && r != null) {
			SecUsuarioSecPerfil.where { secUsuario == u && secPerfil == r }.deleteAll()
		}
	}

	static int removeAll(SecUsuario u) {
		u == null ? 0 : SecUsuarioSecPerfil.where { secUsuario == u }.deleteAll() as int
	}

	static int removeAll(SecPerfil r) {
		r == null ? 0 : SecUsuarioSecPerfil.where { secPerfil == r }.deleteAll() as int
	}

	static constraints = {
	    secUsuario nullable: false
		secPerfil nullable: false, validator: { SecPerfil r, SecUsuarioSecPerfil ur ->
			if (ur.secUsuario?.id) {
				if (SecUsuarioSecPerfil.exists(ur.secUsuario.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['secUsuario', 'secPerfil']
		version false
	}
}
