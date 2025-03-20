package dominio

class SeguroAuto(
                 dniTitular: String,
                 importe: Double,
                 val descripcion: String,
                 val combustible: String,
                 val tipoAuto: TipoAuto,
                 val tipoCobertura: String,
                 val asistenciaCarrera: Boolean,
                 val numPartes: Int) : Seguro(dniTitular,importe){
         val numPoliza = generarNumPoliza()


         companion object{
             var contadorPoliza = 400000
         }


    override fun serializar(): String {
        return "$numPoliza;$dniTitutar;${obtenerImporte()};$descripcion;$combustible;$tipoAuto;$tipoCobertura;$asistenciaCarrera;$numPartes;${tipoSeguro()}"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val interesFinal = interes + (numPartes * 2)
        return obtenerImporte() + (obtenerImporte() * interesFinal)
    }


    override fun generarNumPoliza() {
        ++contadorPoliza
    }
}