package model

enum class Cobertura {
    TERCEROS,
    TERCEROS_AMPLIADO,
    FRANQUICIA_200,
    FRANQUICIA_300,
    FRANQUICIA_400,
    FRANQUICIA_500,
    TODO_RIESGO;
  //  val desc =
    companion object{
        fun getCobertura(valor: String): Cobertura{
            when (valor){
                "terceros_ampliado" -> return TERCEROS_AMPLIADO
                "franquicia_200" -> return FRANQUICIA_200
                "franquicia_300" -> return FRANQUICIA_300
                "franquicia_400" -> return FRANQUICIA_400
                "franquicia_500" -> return FRANQUICIA_500
                "todo_riesgo" -> return TODO_RIESGO
                else -> return TERCEROS
            }
        }
    }
}