package model

class SeguroVida(dniTitular: String,
                 importe: Double,
                 val fechaNac: String,
                 val nivelRiesgo: NivelRiesgo,
                 val indemnizacion: Double) : Seguro(dniTitular, importe) {

    override fun calcularImporteAnioSiguiente(interes: Double): Double {

    }



    override fun serializar(): String {
        return ""
    }
}

