package prog2425.dam1.seguros.model

enum class TipoAuto {
    COCHE,MOTO,CAMION;

    companion object{
        fun getTipoAuto(valor: String): TipoAuto{
            when(valor){
                "moto" -> return MOTO
                "camion" -> return CAMION
                else -> return COCHE
            }
        }
    }
}