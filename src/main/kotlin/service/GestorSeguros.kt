package prog2425.dam1.seguros.service

import prog2425.dam1.seguros.data.IRepoSeguros
import prog2425.dam1.seguros.model.*
import java.time.LocalDate

class GestorSeguros(val repoSeguros: IRepoSeguros) : IServSeguros {
    override fun contratarSeguroHogar(
        dniTitular: String,
        importe: Double,
        metrosCuadrados: Int,
        valorContenido: Double,
        direccion: String,
        anioConstruccion: Int
    ): Boolean {
        val seguroHogarCreado = SeguroHogar(dniTitular,
            importe,
            metrosCuadrados,
            valorContenido,
            direccion,
            anioConstruccion)
           if (repoSeguros.agregar(seguroHogarCreado)) return true else return false
    }

    override fun contratarSeguroAuto(
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: TipoAuto,
        cobertura: Cobertura,
        asistenciaCarretera: Boolean,
        numPartes: Int
    ): Boolean {
        val seguroAutoCreado = SeguroAuto(dniTitular,
            importe,
            descripcion,
            combustible,
            tipoAuto,
            cobertura,
            asistenciaCarretera,
            numPartes)
        return if (repoSeguros.agregar(seguroAutoCreado)) true else false
    }

    override fun contratarSeguroVida(
        dniTitular: String,
        importe: Double,
        fechaNacimiento: LocalDate,
        nivelRiesgo: NivelRiesgo,
        indemnizacion: Double
    ): Boolean {
        val seguroVidaCreado = SeguroVida(dniTitular, importe, fechaNacimiento, nivelRiesgo, indemnizacion)
        return if (repoSeguros.agregar(seguroVidaCreado)) true else false
    }

    override fun eliminarSeguro(numPoliza: Int): Boolean {
        if (repoSeguros.eliminar(numPoliza)) return true else return false
    }

    override fun consultarTodos(): List<Seguro> {
        return repoSeguros.obtenerTodos()
    }

    override fun consultarPorTipo(tipoSeguro: String): List<Seguro> {
        return consultarTodos().filter { it.tipoSeguro() == tipoSeguro }

    }

}