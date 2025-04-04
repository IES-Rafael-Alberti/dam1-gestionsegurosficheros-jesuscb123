package prog2425.dam1.seguros.service

import prog2425.dam1.seguros.model.Cobertura
import prog2425.dam1.seguros.model.NivelRiesgo
import prog2425.dam1.seguros.model.Seguro
import prog2425.dam1.seguros.model.TipoAuto
import java.time.LocalDate

interface IServSeguros {
    fun contratarSeguroHogar(
    dniTitular: String,
    importe: Double,
    metrosCuadrados: Int,
    valorContenido: Double,
    direccion: String,
    anioConstruccion: Int
    ): Boolean

    fun contratarSeguroAuto(
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: TipoAuto,
        cobertura: Cobertura,
        asistenciaCarretera: Boolean,
        numPartes: Int
    ): Boolean

    fun contratarSeguroVida(
        dniTitular: String,
        importe: Double,
        fechaNacimiento: LocalDate,
        nivelRiesgo: NivelRiesgo,
        indemnizacion: Double
    ): Boolean

    fun eliminarSeguro(numPoliza: Int): Boolean

    fun consultarTodos(): List<Seguro>

    fun consultarPorTipo(tipoSeguro: String): List<Seguro>
}