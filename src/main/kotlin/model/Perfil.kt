package prog2425.dam1.seguros.model

enum class Perfil {
    ADMIN, GESTION, CONSULTA;

    companion object {
        fun getPerfil(valor: String): Perfil {
            when (valor) {
                "ADMIN" -> return ADMIN
                "GESTION" -> return GESTION
                else -> return CONSULTA
            }
        }
    }
}