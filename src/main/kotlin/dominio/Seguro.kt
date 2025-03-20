package dominio

abstract class Seguro(val dniTitutar: String, private val importe: Double) {

   abstract fun calcularImporteAnioSiguiente(interes: Double): Double


     fun tipoSeguro(): String{
         return (this::class.simpleName).toString()
     }

   abstract fun serializar(): String

    protected fun obtenerImporte(): Double{
        return importe
    }

    abstract fun generarNumPoliza()


}