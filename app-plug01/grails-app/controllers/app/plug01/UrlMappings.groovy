package app.plug01

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }



        "/dessert.page"       (controller: 'dessertPage', action:"index")

        "/dessert.api/"       (controller: 'dessertApi' , action: "index")
        "/dessert.api/list"   (controller: 'dessertApi' , action: "list")
        "/dessert.api/get"    (controller: 'dessertApi' , action: "get")
        "/dessert.api/update" (controller: 'dessertApi' , action: "update")
        "/dessert.api/create" (controller: 'dessertApi' , action: "create")
        "/dessert.api/delete" (controller: 'dessertApi' , action: "delete")




        "/"   (view:"/index")
        "500" (view:'/error')
        "404" (view:'/notFound')
    }
}
