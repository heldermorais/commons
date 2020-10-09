package app.keycloak2

import grails.core.GrailsApplication

class HomeController {

    GrailsApplication grailsApplication

    def index() {

        String keycloakRealmName = grailsApplication.config.get("keycloak.realm")
        def keycloakConfigs = [
                [name: "keycloak.realm", value: grailsApplication.config.get("keycloak.realm")],
                [name: "keycloak.resource", value: grailsApplication.config.get("keycloak.resource")],
                [name: "keycloak.auth-server-url", value: grailsApplication.config.get("keycloak.auth-server-url")],
                [name: "keycloak.credentials.secret", value: grailsApplication.config.get("keycloak.credentials.secret")],
        ]

        render view: 'index', model: [keycloakConfigs: keycloakConfigs]
    }
}
