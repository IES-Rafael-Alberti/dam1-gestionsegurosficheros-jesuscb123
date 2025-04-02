package prog2425.dam1.seguros

import prog2425.dam1.seguros.UI.Consola

fun validarNombre(nombre: String): Boolean{
   return (nombre.isNotEmpty() && nombre.length >= 5)

}
fun main(){
    val consola = Consola()
    val info = consola.pedirInfo("Introduce un nombre", "Error, no has introducido el nombre", ::validarNombre )

    val info2 = consola.pedirInfo("Introduce un nombre", "Error, no has introducido el nombre") {
       it.isNotEmpty() && it.length >= 5
    }



}