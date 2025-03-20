package dominio

abstract class Seguro(val dniTitutar: String, private val importe: Double) {

     abstract val numPoliza: Int

    companion object{
        var contadorNumPolizaHogar = 100000
        var contadorNumPolizaAuto = 400000
    }
    abstract fun calcularImporteAnioSiguiente(interes: Double): Double


     fun tipoSeguro(): String{
         return (this::class.simpleName).toString()
     }

   abstract fun serializar(): String

    protected fun obtenerImporte(): Double{
        return importe
    }

     fun generarNumPoliza(seguro: String): Int{
         when(seguro){
             "SeguroHogar" -> return ++contadorNumPolizaHogar
             else -> return ++contadorNumPolizaAuto
         }
     }
}