package app.common.keycloak

import java.security.Principal

class HomeController {

    def index() {
        Principal principal = request.getUserPrincipal()
        String authenticatedUser = principal ? principal.getName() : 'NOT LOGGED IN'

        render view: 'index', model: [authenticatedUser: authenticatedUser]
    }

}
