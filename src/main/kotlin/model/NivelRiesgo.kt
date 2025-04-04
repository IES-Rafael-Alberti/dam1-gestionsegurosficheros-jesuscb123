package prog2425.dam1.seguros.model

enum class NivelRiesgo {
    BAJO, MEDIO, ALTO;

    val interesAplicado = arrayOf(2.0, 5.0, 10.0)

    companion object {
        fun getRiesgo(valor: String): NivelRiesgo {
            when (valor) {
                "bajo" -> return BAJO
                "alto" -> return ALTO
                else -> return MEDIO
            }
        }
    }
}