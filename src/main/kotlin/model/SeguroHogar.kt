package prog2425.dam1.seguros.model

import java.util.function.DoubleBinaryOperator

class SeguroHogar : Seguro {
    private val metrosCuadrados: Int
    private val valorContenido: Double
    private val direccion: String
    private val anioConstruccion: Int


    companion object{
     private var numPolizaHogar: Int = 100000
        const val PORCENTAJE_INCREMENTO_ANIOS = 0.02
        const val CICLO_ANIOS_INCREMENTO = 5
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

    constructor(dniTitular: String, //constructor primario que suma numPoliza
                importe: Double,
                metrosCuadrados: Int,
                valorContenido: Double,
                direccion: String,
                anioConstruccion: Int) : super(numPolizaHogar++, dniTitular, importe){
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
        this.anioConstruccion = anioConstruccion
    }

    constructor(numPoliza: Int, // constructor secundario
                dniTitular: String,
                importe: Double,
                metrosCuadrados: Int,
                valorContenido: Double,
                direccion: String,
                anioConstruccion: Int) : super(numPoliza, dniTitular, importe){
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
        this.anioConstruccion = anioConstruccion
    }


    override fun serializar(separador: String): String {
        return "$;${obtenerDNI()}$separador${obtenerImporte()}$separador$metrosCuadrados$separador $valorContenido;$direccion$separador$anioConstruccion$separador$importe"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return obtenerImporte() + (obtenerImporte() * interes) // implementar el método de la guía.
    }

    override fun toString(): String {
        return "Seguro Hogar(numPoliza = $numPolizaHogar, dniTitular=${obtenerDNI()}, importe=$importe, metrosCuadrados=$metrosCuadrados, valorContenido=$valorContenido, direccion=$direccion, anioContrusccion=$anioConstruccion)"
    }
}