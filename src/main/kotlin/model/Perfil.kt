package prog2425.dam1.seguros.model

enum class Perfil {
    ADMIN, GESTION, CONSULTA;

    companion object {
        fun getPerfil(valor: String): Perfil {
            when (valor) {
                "admin" -> return ADMIN
                "gestion" -> return GESTION
                else -> return CONSULTA
            }
        }
    }
}