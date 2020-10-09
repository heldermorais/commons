package autoconfig

// Added by the Spring Security Core plugin:
keycloak.realm = "grails-realm"
keycloak."auth-server-url" = "http://localhost:58080/auth"
keycloak."ssl-required" = "external"
keycloak.resource = "app-common-keycloak"
keycloak.credentials.secret = "7c71991c-ac9f-4a22-8588-96cb8fe182d7"

//keycloak.'policy-enforcer-config'.'online-introspection' = true


/*
{
  "realm": "grails-realm",
  "auth-server-url": "http://localhost:58080/auth/",
  "ssl-required": "external",
  "resource": "app-common-keycloak",
  "verify-token-audience": true,
  "credentials": {
    "secret": "7c71991c-ac9f-4a22-8588-96cb8fe182d7"
  },
  "confidential-port": 0,
  "policy-enforcer": {}
}

 */