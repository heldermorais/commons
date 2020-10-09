package app.common.springsec

class LogoutController {

    def index() {
        request.logout()

        redirect uri: '/'
    }

}
