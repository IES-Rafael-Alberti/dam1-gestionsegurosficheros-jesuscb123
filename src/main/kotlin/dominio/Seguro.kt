package dominio

abstract class Seguro(val numPoliza: Int, val dniTitutar: String, private val importe: Double) {

   abstract fun calcularImporteAnioSiguiente(interes: Double): Double

     fun tipoSeguro(): String{
         return (this::class.simpleName).toString()
     }

   open fun serializar(): String{
         return "$numPoliza;$dniTitutar;$importe"
     }

    protected fun obtenerImporte(): Double{
        return importe
    }
}