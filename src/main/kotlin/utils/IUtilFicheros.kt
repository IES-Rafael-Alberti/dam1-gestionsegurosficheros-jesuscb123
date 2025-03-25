package prog2425.dam1.seguros.utils

import prog2425.dam1.seguros.model.IExportable
import prog2425.dam1.seguros.model.Seguro

interface IUtilFicheros {
    fun leerArchivo(ruta: String): List<String>
    fun leerSeguros(ruta: String, mapaSeguros: Map<String, (List<String>) -> Seguro>): List<Seguro>
    fun agregarLinea(ruta: String, linea: String): Boolean
    fun <T: IExportable> escribirArchivo(ruta: String, elementos: List<T>): Boolean
    fun existeFichero(ruta: String): Boolean
    fun existeDirectorio(ruta: String): Boolean
}