package app.common.keycloak

class LogoutController {

    def index() {

        request.logout()
        redirect uri: '/'

    }

}
