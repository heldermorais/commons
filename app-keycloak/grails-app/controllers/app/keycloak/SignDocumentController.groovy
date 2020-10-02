package app.keycloak

import org.keycloak.KeycloakPrincipal
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.authorization.client.AuthzClient
import org.keycloak.representations.idm.authorization.AuthorizationRequest
import org.keycloak.representations.idm.authorization.AuthorizationResponse

import java.security.Principal

class SignDocumentController {

    def index() {

    }

    def doSign(){

        Principal principal = request.getUserPrincipal()
        String useName = principal.getName()

        if (principal instanceof KeycloakAuthenticationToken) {
            KeycloakPrincipal kp = ((KeycloakAuthenticationToken)  principal).getPrincipal()

            // this is how to get the real userName (or rather the login name)
            //useName = kp.getKeycloakSecurityContext().authorizationContext.getIdToken().getPreferredUsername()
        }

//
//        Keycloak keycloak = KeycloakBuilder.builder()
//                .serverUrl("https://sso.example.com/auth")
//                .realm("realm")
//                .username("user")
//                .password("pass")
//                .clientId("client")
//                .clientSecret("secret")
//                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(20).build())
//                .build();
//
        System.out.println("You got an RPT: " + rpt);
    }
}
