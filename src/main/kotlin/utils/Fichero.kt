package prog2425.dam1.seguros.utils

import prog2425.dam1.seguros.model.IExportable
import prog2425.dam1.seguros.model.Seguro
import java.io.File

class Fichero : IUtilFicheros {
    override fun leerArchivo(ruta: String): List<String> {
        return File(ruta).readLines()
    }

    override fun leerSeguros(ruta: String, mapaSeguros: Map<String, (List<String>) -> Seguro>): List<Seguro> {
        TODO("Not yet implemented")
    }

    override fun agregarLinea(ruta: String, linea: String): Boolean {
        if (linea != null) {
            File(ruta).appendText(linea)
            return true
        }else{
            return false
        }
    }

    override fun <T : IExportable> escribirArchivo(ruta: String, elementos: List<T>): Boolean {
       if (elementos.isNotEmpty()){
           File(ruta).writeText(elementos.toString())
           return true
       }else{
           return false
       }
    }

    override fun existeFichero(ruta: String): Boolean {
        if (File(ruta).exists()){
            return true
        }else{
            return false
        }
    }

    override fun existeDirectorio(ruta: String): Boolean {
        val directorio = File(ruta)
        if (directorio.exists() && directorio.isDirectory){
            return true
        }else{
            return false
        }
    }

}