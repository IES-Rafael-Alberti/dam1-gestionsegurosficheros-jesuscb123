package prog2425.dam1.seguros.utils

import prog2425.dam1.seguros.model.IExportable
import prog2425.dam1.seguros.model.Seguro

class Fichero : IUtilFicheros {
    override fun leerArchivo(ruta: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun leerSeguros(ruta: String, mapaSeguros: Map<String, (List<String>) -> Seguro>): List<Seguro> {
        TODO("Not yet implemented")
    }

    override fun agregarLinea(ruta: String, linea: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : IExportable> escribirArchivo(ruta: String, elementos: List<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun existeFichero(ruta: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun existeDirectorio(ruta: String): Boolean {
        TODO("Not yet implemented")
    }

}