package prog2425.dam1.seguros.model

enum class Cobertura(val desc: String) {
    TERCEROS("Terceros"),
    TERCEROS_AMPLIADO("terceros+"),
    FRANQUICIA_200("franquicia200"),
    FRANQUICIA_300("franquicia300"),
    FRANQUICIA_400("franquicia400"),
    FRANQUICIA_500("franquicia500"),
    TODO_RIESGO("TodoRiesgo");
    companion object{
        fun getCobertura(valor: String): Cobertura{
            when (valor){
                "terceros+" -> return TERCEROS_AMPLIADO
                "franquicia22" -> return FRANQUICIA_200
                "franquicia300" -> return FRANQUICIA_300
                "franquicia400" -> return FRANQUICIA_400
                "franquicia500" -> return FRANQUICIA_500
                "todo_riesgo" -> return TODO_RIESGO
                else -> return TERCEROS
            }
        }
    }
}