package app.common.keycloak

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/accessDenied"(view:"/error")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
