package prog2425.dam1.seguros.UI

class Consola : IEntradaSalida {
    override fun mostrar(msj: String, salto: Boolean, pausa: Boolean) {
        println(msj)
        if (salto) println()
        if (pausa) pausar()
    }

    override fun mostrarError(msj: String, pausa: Boolean) {
        if (msj.startsWith("ERROR -")) println() else println(msj)
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
        val respuestaUsuario = pedirInfo(msj)
        require(debeCumplir(respuestaUsuario)) {mostrarError("error")}
        return respuestaUsuario
    }

    override fun pedirDouble(
        prompt: String,
        error: String,
        errorConv: String,
        debeCumplir: (Double) -> Boolean
    ): Double {

    }

    override fun pedirEntero(prompt: String, error: String, errorConv: String, debeCumplir: (Int) -> Boolean): Int {

    }

    override fun pedirInfoOculta(prompt: String): String {
        TODO("Not yet implemented")
    }

    override fun pausar(msj: String) {
        TODO("Not yet implemented")
    }

    override fun limpiarPantalla(numSaltos: Int) {
        for (i in 1..numSaltos) {
            println()
        }
    }

    override fun preguntar(mensaje: String): Boolean {

    }


}