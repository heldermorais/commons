package app.keycloak

import org.keycloak.KeycloakPrincipal
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.authorization.client.AuthzClient
import org.keycloak.representations.idm.authorization.AuthorizationRequest
import org.keycloak.representations.idm.authorization.AuthorizationResponse

import java.security.Principal

class SignDocumentController {

    def index() {

        // create a new instance based on the configuration defined in keycloak.json
        AuthzClient authzClient = AuthzClient.create();

        // create an authorization request
        AuthorizationRequest request = new AuthorizationRequest();

        // send the entitlement request to the server in order to
        // obtain an RPT with all permissions granted to the user
        AuthorizationResponse response = authzClient.authorization("grails.user", "abc123").authorize()
        String rpt = response.getToken();


        System.out.println("You got an RPT: " + rpt);

render view: 'index'

    }

    def doSign(){

        Principal principal = request.getUserPrincipal()
        String useName = principal.getName()

        if (principal instanceof KeycloakAuthenticationToken) {
            KeycloakPrincipal kp = ((KeycloakAuthenticationToken)  principal).getPrincipal()

        }




        System.out.println("You got an RPT: " + rpt);
    }
}
