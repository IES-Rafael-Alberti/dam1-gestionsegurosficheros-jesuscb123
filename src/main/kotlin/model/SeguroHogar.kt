package model

class SeguroHogar private constructor(dniTitular: String,
                  importe: Double,
                  val metrosCuadrados: Int,
                  val valorContenido: Double,
                  val direccion: String,
                  val anioConstruccion: Int) : Seguro(dniTitular, importe) {


    //constructor(dniTitular: String, importe) Implementar constructor secundario.

    companion object{
      private val numPolizaAuto: Int = 100000
        fun crearSeguro(datos: List<String>): SeguroHogar{
            val dniTitular = datos[0]
            val importe = datos[1].toDouble()
            val metrosCuadrados = datos[2].toInt()
            val valorContenido = datos[3].toDouble()
            val direccion = datos[4].toString()
            val anioConstruccion = datos[5].toInt()
            return SeguroHogar(dniTitular, importe, metrosCuadrados, valorContenido,direccion, anioConstruccion)
        }
    }

    override fun serializar(): String {
        return "$;${obtenerDNI()};${obtenerImporte()};$metrosCuadrados;$valorContenido;$direccion;$anioConstruccion;${tipoSeguro()}"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return obtenerImporte() + (obtenerImporte() * interes) // implementar el método de la guía.
    }

}