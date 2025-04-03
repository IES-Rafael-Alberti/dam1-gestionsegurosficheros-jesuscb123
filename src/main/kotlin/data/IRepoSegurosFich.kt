package prog2425.dam1.seguros.data

import prog2425.dam1.seguros.model.Seguro
import prog2425.dam1.seguros.model.SeguroAuto
import prog2425.dam1.seguros.model.SeguroHogar
import prog2425.dam1.seguros.model.SeguroVida

class IRepoSegurosFich : RepoSegurosMem(), ICargarSegurosIniciales {
    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {

    }

    private fun actualizarContadores(seguros: List<Seguro>) {
        // Actualizar los contadores de polizas del companion object según el tipo de seguro
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.numPolizaHogar = maxHogar
        if (maxAuto != null) SeguroAuto.numPolizaAuto = maxAuto
        if (maxVida != null) SeguroVida.numPolizaVida = maxVida
    }

}