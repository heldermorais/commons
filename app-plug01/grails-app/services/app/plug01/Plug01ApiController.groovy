package app.plug01

class Plug01ApiController {

    def list() {
       def lista = Dessert.list()
       respond Dessert.list()
    }

    def get(Long id){
        Dessert d = Dessert.get(id)
        respond d
    }

    def save(Dessert dessert){
        if (dessert.id){
          dessert.save()
        }else{

        }

        respond ok
    }
}
