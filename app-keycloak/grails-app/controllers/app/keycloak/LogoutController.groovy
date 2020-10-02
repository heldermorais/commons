package app.keycloak

class LogoutController {

    def index() {
        request.logout()

        redirect url: "/"
    }
}
