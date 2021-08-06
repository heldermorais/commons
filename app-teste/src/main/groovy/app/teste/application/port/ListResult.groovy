package app.teste.application.port

class ListResult<T> {

    List<T> lista
    Long count

    ListResult(List<T> lista, Long count) {
        this.lista = lista
        this.count = count
    }

}
