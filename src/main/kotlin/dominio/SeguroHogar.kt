package dominio

class SeguroHogar(dniTitular: String,
                  importe: Double,
                  val metrosCuadrados: Int,
                  val valorContenido: Double,
                  val direccion: String) : Seguro(dniTitular, importe) {

    var numPoliza = generarNumPoliza()

          companion object{
              var contadorPoliza = 100000
          }


    override fun serializar(): String {
        return "$numPoliza;$dniTitutar;${obtenerImporte()};$valorContenido;$direccion"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return obtenerImporte() + (obtenerImporte() * interes)
    }

    override fun generarNumPoliza() {
        ++contadorPoliza
    }

}