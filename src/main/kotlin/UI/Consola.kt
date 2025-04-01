package prog2425.dam1.seguros.UI

class Consola : IEntradaSalida {
    override fun mostrar(msj: String, salto: Boolean, pausa: Boolean) {
        println(msj)
        if (salto) println()
        if (pausa) pausar()
    }

    override fun mostrarError(msj: String, pausa: Boolean) {
        if (msj.contains("ERROR -")) println() else println(msj)
        if (pausa) pausar()
    }

    override fun pedirInfo(msj: String): String {
        if (msj.isNotEmpty()){
            println(msj)
            return readln().trim()
        }else{
            return ""
        }
    }

    override fun pedirInfo(msj: String, error: String, debeCumplir: (String) -> Boolean): String {
        println(msj)
        val respuestaUsuario = readln().trim()
        require(debeCumplir(respuestaUsuario)) {mostrarError("error")}
        return respuestaUsuario
    }

    override fun pedirDouble(
        prompt: String,
        error: String,
        errorConv: String,
        debeCumplir: (Double) -> Boolean
    ): Double {
        TODO("Not yet implemented")
    }

    override fun pedirEntero(prompt: String, error: String, errorConv: String, debeCumplir: (Int) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun pedirInfoOculta(prompt: String): String {
        TODO("Not yet implemented")
    }

    override fun pausar(msj: String) {
        TODO("Not yet implemented")
    }

    override fun limpiarPantalla(numSaltos: Int) {
        TODO("Not yet implemented")
    }

    override fun preguntar(mensaje: String): Boolean {
        TODO("Not yet implemented")
    }


}