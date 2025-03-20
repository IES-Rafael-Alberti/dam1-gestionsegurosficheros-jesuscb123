package dominio

class SeguroHogar(numPoliza: Int,
                  dniTitular: String,
                  importe: Double,
                  val metrosCuadrados: Int,
                  val valorContenido: Double,
                  val direccion: String) : Seguro(numPoliza, dniTitular, importe) {



    override fun serializar(): String {
        return super.serializar() + ";$metrosCuadrados;$valorContenido;$direccion;${tipoSeguro()}"
    }
}