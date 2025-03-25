package prog2425.dam1.seguros.model

class SeguroAuto : Seguro{
    private val descripcion: String
    private val combustible: String
    private val tipoAuto: TipoAuto
    private val cobertura: Cobertura
    private val asistenciaCarretera: Boolean
    private val numPartes: Int

    companion object{
        var numPolizaAuto = 400000
        const val PORCENTAJE_INCREMENTO_PARTES = 2
        fun crearSeguro(datos: List<String>): SeguroAuto{
            val dniTitular = datos[0]
            val importe = datos[1].toDouble()
            val descripcion = datos[2]
            val combustible = datos[3]
            val tipoAuto = TipoAuto.getTipoAuto(datos[4])
            val cobertura = Cobertura.getCobertura(datos[5])
            val asistenciaCarretera = datos[6].toBoolean()
            val numPartes = datos[7].toInt()
            return SeguroAuto(dniTitular,importe,descripcion,combustible,tipoAuto,cobertura,asistenciaCarretera,numPartes)
        }

    }
    constructor(dniTitular: String,
                importe: Double,
                descripcion: String,
                combustible: String,
                tipoAuto: TipoAuto,
                cobertura: Cobertura,
                asistenciaCarretera: Boolean,
                numPartes: Int) : super(numPolizaAuto++, dniTitular, importe){
                    this.descripcion = descripcion
                    this.combustible = combustible
                    this.tipoAuto = tipoAuto
                    this.cobertura = cobertura
                    this.asistenciaCarretera = asistenciaCarretera
                    this.numPartes = numPartes
                }

    constructor(numPoliza: Int,
                dniTitular: String,
                importe: Double,
                descripcion: String,
                combustible: String,
                tipoAuto: TipoAuto,
                cobertura: Cobertura,
                asistenciaCarretera: Boolean,
                numPartes: Int) : super(numPoliza, dniTitular, importe){
                    this.descripcion = descripcion
                    this.combustible = combustible
                    this.tipoAuto = tipoAuto
                    this.cobertura = cobertura
                    this.asistenciaCarretera = asistenciaCarretera
                    this.numPartes = numPartes
                }


    override fun serializar(separador: String): String {
        return "$numPolizaAuto$separador${obtenerDNI()}$separador${obtenerImporte()}$separador$descripcion  $separador$combustible$separador$tipoAuto$separador$cobertura$separador$asistenciaCarretera$separador$numPartes$separador${tipoSeguro()}"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val interesFinal = interes + (numPartes * 2)
        return obtenerImporte() + (obtenerImporte() * interesFinal)
    }

    override fun toString(): String {
        return "SeguroAuto(numPoliza=$numPolizaAuto, dniTitular=${obtenerDNI()}, importe=${obtenerImporte()}, descripción=$descripcion, combustible=$combustible, tipoAuto=$tipoAuto, cobertura=$cobertura, asistenciaCarretera=$asistenciaCarretera, numPartes=$numPartes)"
    }
}