package app.common.keycloak.secured

import org.springframework.security.access.annotation.Secured

import java.security.Principal

class OtherController {

    @Secured('ROLE_KC_ADMIN')
    def index() {
        Principal principal = request.getUserPrincipal()
        String authenticatedUser = principal ? principal.getName() : 'NOT LOGGED IN'

        render view: 'index', model: [authenticatedUser: authenticatedUser]
    }

}
