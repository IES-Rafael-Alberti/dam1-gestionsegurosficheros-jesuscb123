package prog2425.dam1.seguros.model

import java.time.LocalDate
import java.time.Year


class SeguroHogar : Seguro {
    private val metrosCuadrados: Int
    private val valorContenido: Double
    private val direccion: String
    private val anioConstruccion: Int


    companion object{
        var numPolizaHogar: Int = 100000
        const val PORCENTAJE_INCREMENTO_ANIOS = 0.02
        const val CICLO_ANIOS_INCREMENTO = 5
        fun crearSeguro(datos: List<String>): SeguroHogar{
            val numPoliza = datos[0].toInt()
            val dniTitular = datos[1]
            val importe = datos[2].toDouble()
            val metrosCuadrados = datos[3].toInt()
            val valorContenido = datos[4].toDouble()
            val direccion = datos[5].toString()
            val anioConstruccion = datos[6].toInt()
            return SeguroHogar(numPoliza, dniTitular, importe, metrosCuadrados, valorContenido,direccion, anioConstruccion)
        }
    }

    constructor(dniTitular: String, //constructor primario que suma numPoliza
                importe: Double,
                metrosCuadrados: Int,
                valorContenido: Double,
                direccion: String,
                anioConstruccion: Int) : super(++numPolizaHogar, dniTitular, importe){
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
        return "$numPolizaHogar$$separador${obtenerDNI()}$separador${obtenerImporte()}$separador$metrosCuadrados$separador $valorContenido;$direccion$separador$anioConstruccion$separador${tipoSeguro()}"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val anioActual = LocalDate.now().year
        val antiguedad = (anioActual - anioConstruccion).toDouble()
        val interesAdicional = (antiguedad / CICLO_ANIOS_INCREMENTO).toInt() * PORCENTAJE_INCREMENTO_ANIOS
        val interesTotal = interes + interesAdicional

        return valorContenido * (1 + interesTotal)
    }

    override fun toString(): String {
        return "${tipoSeguro()}(numPoliza = $numPolizaHogar, dniTitular=${obtenerDNI()}, importe=$importe, metrosCuadrados=$metrosCuadrados, valorContenido=$valorContenido, direccion=$direccion, anioContrusccion=$anioConstruccion)"
    }
}