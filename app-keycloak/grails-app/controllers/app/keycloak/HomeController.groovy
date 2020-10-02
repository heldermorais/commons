package app.keycloak

import org.keycloak.KeycloakPrincipal
import org.keycloak.KeycloakSecurityContext
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.springframework.security.core.context.SecurityContext

import java.security.Principal

class HomeController {

    SecurityContext securityContext

    def index() {
    //def index() {

        // this will set the user id as userName
//        String userName = securityContext.getAuthentication()
//
//        if (securityContext.getAuthentication().getPrincipal() instanceof KeycloakPrincipal) {
//            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>)  securityContext.getAuthentication().getPrincipal()
//
//            // this is how to get the real userName (or rather the login name)
//            userName = kp.getKeycloakSecurityContext().getIdToken().getPreferredUsername()
//        }

        Principal principal = request.getUserPrincipal()
        String useName = principal.getName()

        if (principal instanceof KeycloakAuthenticationToken) {
            KeycloakPrincipal kp = ((KeycloakAuthenticationToken)  principal).getPrincipal()

            // this is how to get the real userName (or rather the login name)
            useName = kp.getKeycloakSecurityContext().getIdToken().getPreferredUsername()
        }

        //String useName = principal.getName()

        render view: 'index', model: [username: useName]

    }
}
