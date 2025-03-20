package dominio

class SeguroAuto(numPoliza: Int,
                 dniTitular: String,
                 importe: Double,
                 val descripcion: String,
                 val combustible: String,
                 val tipoAuto: TipoAuto,
                 val tipoCobertura: String,
                 val asistenciaCarrera: Boolean,
                 val numPartes: Int) : Seguro(numPoliza,dniTitular,importe){

    override fun serializar(): String {
        return super.serializar() + ";$descripcion;$combustible;$tipoAuto;$tipoCobertura;$asistenciaCarrera;$numPartes;${tipoSeguro()}"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {

    }
}