package model

abstract class Seguro(val numPoliza: Int, private val dniTitutar: String, protected val importe: Double) : IExportable {


    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    fun tipoSeguro(): String{
         return (this::class.simpleName).toString() ?: "desconocido"
     }

    override fun hashCode(): Int {
        return numPoliza
    }

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (other is Seguro){
            return other.numPoliza == numPoliza
        }
        return false
    }

    override fun serializar(separador: String): String{
        return "$numPoliza$separador$dniTitutar$separador$importe"
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

