package prog2425.dam1.seguros.data

import prog2425.dam1.seguros.model.*
import prog2425.dam1.seguros.utils.IUtilFicheros

class RepoSegurosFich(val rutaArchivo: String, val fich: IUtilFicheros) : RepoSegurosMem(), ICargarSegurosIniciales {
    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        val lineas = fich.leerArchivo(rutaArchivo)
        listaSeguros.clear()

        for (linea in lineas) {
            val datos = linea.split(";").toMutableList()
            val tipoSeguro = datos.removeAt(-1)
            val parametros = datos
            val crearSeguro = mapa[tipoSeguro]

            if (crearSeguro != null) {
                listaSeguros.add(crearSeguro(parametros))
            } else {
                listaSeguros.clear()
                return false
            }
        }

        actualizarContadores(listaSeguros)
        return true
    }

    override fun agregar(seguro: Seguro): Boolean {
        if (fich.agregarLinea(rutaArchivo, seguro.serializar())) {
            return super.agregar(seguro)
        }
        return false
    }

    override fun eliminar(seguro: Seguro): Boolean {
        if (fich.escribirArchivo(rutaArchivo, listaSeguros.filter { it != seguro })) {
            return super.eliminar(seguro)
        }
        return false
    }

    override fun eliminar(numPoliza: Int): Boolean {
        val usuario = buscar(numPoliza)
        if (usuario != null && fich.escribirArchivo(rutaArchivo, listaSeguros.filter { it.numPoliza != numPoliza })) {
            return super.eliminar(usuario)
        }
        return false
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