package app.keycloak2

import org.keycloak.KeycloakPrincipal
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken

import java.security.Principal


class KeycloakOnRequestInterceptor {

    KeycloakOnRequestInterceptor(){
        matchAll()
                .excludes(controller:"login")
                .excludes(controller:"logout")
    }

    boolean before() {

        Principal principal = request.getUserPrincipal()
        String    useName   = principal?.getName()

        return true
    }

    boolean after() {

        true
    }

    void afterView() {
        // no-op
    }
}
