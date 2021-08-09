package app.commons

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")

        "/vue/apiHello.json"(controller: 'hello', action:"apiHello" )

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
