package app.plug01

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/plug01.page"(controller: 'plug01',action:"index")
        "/plug01.api" (controller: 'plug01',action:"list")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
