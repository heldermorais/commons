package app.common.springsec.secured

import org.springframework.security.access.annotation.Secured


class OtherController {

    @Secured('ROLE_USER')
    def index() {

        render view: 'index', model: [authenticatedUser: authenticatedUser]
    }

    @Secured('ROLE_ADMIN')
    def admin() {

        render view: 'index', model: [authenticatedUser: authenticatedUser]

    }

}
