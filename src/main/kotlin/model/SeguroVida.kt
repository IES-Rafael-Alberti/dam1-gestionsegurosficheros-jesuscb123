package prog2425.dam1.seguros.model

import java.time.LocalDate

class SeguroVida : Seguro {
    private var fechaNac: LocalDate
    private var nivelRiesgo: NivelRiesgo
    private var indemnizacion: Double

    companion object{
        var numPolizasVida = 800000
        fun crearSeguro(datos: List<String>): SeguroVida{
            val dniTitular = datos[0]
            val importe = datos[1].toDouble()
            val fechaNac = LocalDate.parse(datos[2])
            val nivelRiesgo = NivelRiesgo.getRiesgo(datos[3])
            val indemnizacion = datos[4].toDouble()
            return SeguroVida(dniTitular, importe, fechaNac, nivelRiesgo, indemnizacion)
        }
    }

    constructor(dniTitular: String,
                importe: Double,
                fechaNac: LocalDate,
                nivelRiesgo: NivelRiesgo,
                indemnizacion: Double) : super(
                    numPolizasVida++, dniTitular, importe){
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
        return "$numPolizasVida$separador${obtenerDNI()}$separador$importe$separador${tipoSeguro()}"
    }

    override fun toString(): String {
        return "SeguroVida(numPoliza=$numPolizasVida, dniTitular=${obtenerDNI()}, importe=${obtenerImporte()}, fechaNac=$fechaNac, nivelRiesgo=$nivelRiesgo, indemnización=$indemnizacion)"
    }
}

