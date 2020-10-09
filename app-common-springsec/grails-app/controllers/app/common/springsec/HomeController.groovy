package app.common.springsec

import java.security.Principal

class HomeController {

    def index() {

        render view: 'index', model: [authenticatedUser: authenticatedUser]

    }
}
