package app.commons

import commons.security.SecUser

class AppSecUser extends SecUser{

    String fullName

    @Override
    void setUsername(String username) {
        super.setUsername(username)
        this.fullName = "Full ${username}"
    }
    static constraints = {
        fullName blank: true, nullable: true
    }

}
