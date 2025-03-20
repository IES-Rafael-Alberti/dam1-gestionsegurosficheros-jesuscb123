package dominio

class SeguroHogar(dniTitular: String,
                  importe: Double,
                  val metrosCuadrados: Int,
                  val valorContenido: Double,
                  val direccion: String) : Seguro(dniTitular, importe) {

    override var numPoliza: Int = generarNumPoliza(tipoSeguro())

    companion object{
              var contadorPoliza = 100000
          }

    override fun serializar(): String {
        return "$numPoliza;$dniTitutar;${obtenerImporte()};$metrosCuadrados;$valorContenido;$direccion;${tipoSeguro()}"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return obtenerImporte() + (obtenerImporte() * interes)
    }

}