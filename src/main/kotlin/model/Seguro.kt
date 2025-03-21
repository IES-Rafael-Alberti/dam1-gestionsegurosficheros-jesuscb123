package model

abstract class Seguro(private val dniTitutar: String, protected val importe: Double) : IExportable {

     private val numPoliza: Int = 0


    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    fun tipoSeguro(): String{
         return (this::class.simpleName).toString() ?: "desconocido"
     }
/*
    fun comprobarNumPoliza(numPoliza: Int): Boolean{

    }
    */


    override fun serializar(): String{
        return "$numPoliza;$dniTitutar;$importe"
    }

    protected fun obtenerImporte(): Double{
        return importe
    }

    protected fun obtenerDNI(): String{
        return dniTitutar
    }

    override fun toString(): String {
        return "Seguro(numPoliza=$numPoliza, dniTitular=$dniTitutar, importe=$importe)"
    }

}

