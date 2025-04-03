package prog2425.dam1.seguros.data

import prog2425.dam1.seguros.model.Seguro
import prog2425.dam1.seguros.model.Usuario
import prog2425.dam1.seguros.utils.IUtilFicheros

class RepoSegurosFich(val fich: IUtilFicheros, val rutaArchivo: String, ) : RepoSegurosMem(), ICargarSegurosIniciales {
    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        TODO()
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
        val seguro = buscar(numPoliza)
        if (seguro != null && fich.escribirArchivo(rutaArchivo, listaSeguros.filter { it.numPoliza != numPoliza })) {
            return super.eliminar(seguro)
        }
        return false
    }


}