package prog2425.dam1.seguros.model

import java.time.LocalDate

class SeguroVida : Seguro {
    private var fechaNac: LocalDate
    private var nivelRiesgo: NivelRiesgo
    private var indemnizacion: Double

    companion object{
        var numPolizaVida = 800000
        fun crearSeguro(datos: List<String>): SeguroVida{
            val numPoliza = datos[0].toInt()
            val dniTitular = datos[1]
            val importe = datos[2].toDouble()
            val fechaNac = LocalDate.parse(datos[3])
            val nivelRiesgo = NivelRiesgo.getRiesgo(datos[4])
            val indemnizacion = datos[5].toDouble()
            return SeguroVida(numPoliza,dniTitular, importe, fechaNac, nivelRiesgo, indemnizacion)
        }
    }

    constructor(dniTitular: String,
                importe: Double,
                fechaNac: LocalDate,
                nivelRiesgo: NivelRiesgo,
                indemnizacion: Double) : super(
                    numPolizaVida++, dniTitular, importe){
                    this.fechaNac = fechaNac
                    this.nivelRiesgo = nivelRiesgo
                    this.indemnizacion = indemnizacion
    }

    constructor(numPoliza: Int,
                dniTitular: String,
                importe: Double,
                fechaNac: LocalDate,
                nivelRiesgo: NivelRiesgo,
                indemnizacion: Double) : super(numPoliza, dniTitular, importe){
                    this.fechaNac = fechaNac
                    this.nivelRiesgo = nivelRiesgo
                    this.indemnizacion = indemnizacion
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val edad = LocalDate.now().year - fechaNac.year
        TODO()
    }

    override fun serializar(separador: String): String {
        return "$numPolizaVida$separador${obtenerDNI()}$separador$importe$separador${tipoSeguro()}"
    }

    override fun toString(): String {
        return "${tipoSeguro()}(numPoliza=$numPolizaVida, dniTitular=${obtenerDNI()}, importe=${obtenerImporte()}, fechaNac=$fechaNac, nivelRiesgo=$nivelRiesgo, indemnización=$indemnizacion)"
    }
}

