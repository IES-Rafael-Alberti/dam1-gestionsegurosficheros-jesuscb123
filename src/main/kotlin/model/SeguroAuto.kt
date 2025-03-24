package model

class SeguroAuto : Seguro{
    var descripcion: String
    var combustible: String
    var tipoAuto: TipoAuto
    var cobertura: Cobertura
    var asistenciaCarretera: Boolean
    var numPartes: Int

    companion object{
        var numPolizaAuto = 400000
        fun crearSeguro(datos: List<String>): Seguro{
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


    override fun serializar(separador: ): String {
        return "$numPolizaAuto;${obtenerDNI()};${obtenerImporte()};$descripcion;$combustible;$tipoAuto;$cobertura;$asistenciaCarretera;$numPartes;${tipoSeguro()}"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val interesFinal = interes + (numPartes * 2)
        return obtenerImporte() + (obtenerImporte() * interesFinal)
    }

}