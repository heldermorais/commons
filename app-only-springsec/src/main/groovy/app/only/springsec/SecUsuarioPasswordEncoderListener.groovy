package app.only.springsec

import grails.plugin.springsecurity.SpringSecurityService
import groovy.util.logging.Slf4j
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.PreInsertEvent
import org.grails.datastore.mapping.engine.event.PreUpdateEvent
import org.springframework.beans.factory.annotation.Autowired
import grails.events.annotation.gorm.Listener
import groovy.transform.CompileStatic
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder

import javax.xml.bind.DatatypeConverter
import java.security.MessageDigest

@CompileStatic
@Slf4j
class SecUsuarioPasswordEncoderListener {


    @Autowired
    SpringSecurityService springSecurityService

    @Listener(SecUsuario)
    void onPreInsertEvent(PreInsertEvent event) {
        encodePasswordForEvent(event)
    }

    @Listener(SecUsuario)
    void onPreUpdateEvent(PreUpdateEvent event) {
        encodePasswordForEvent(event)
    }

    private void encodePasswordForEvent(AbstractPersistenceEvent event) {
        if (event.entityObject instanceof SecUsuario) {
            SecUsuario u = event.entityObject as SecUsuario
            if (u.password && ((event instanceof  PreInsertEvent) || (event instanceof PreUpdateEvent && u.isDirty('password')))) {
                event.getEntityAccess().setProperty('password', encodePassword(u.password))
            }
        }
    }

    private String encodePassword(String password) {
        String ePassword = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
        return ePassword
    }
}
