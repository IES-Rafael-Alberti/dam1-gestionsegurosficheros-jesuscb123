package prog2425.dam1.seguros

import prog2425.dam1.seguros.UI.Consola
import prog2425.dam1.seguros.app.ControlAcceso
import prog2425.dam1.seguros.app.GestorMenu
import prog2425.dam1.seguros.data.RepoSegurosMem

import prog2425.dam1.seguros.data.RepoUsuariosMem
import prog2425.dam1.seguros.service.GestorSeguros
import prog2425.dam1.seguros.service.GestorUsuarios
import prog2425.dam1.seguros.utils.Fichero
import prog2425.dam1.seguros.utils.Seguridad


fun pedirSimulacionFich(consola: Consola): Int{
    var salir = false
    var opcionUsuario = 0
    do{
        try{
          opcionUsuario = consola.pedirEntero("""
                    ¿Cómo desea trabajar?
                     1. Simulación
                     2. Ficheros
            """.trimIndent(), "Introduce un número disponible", "Introduce un número entero"){
                it in arrayOf(1,2)
            }
            salir = true

        }catch (e: IllegalArgumentException){
            consola.mostrarError(e.toString())
        }catch (e: Exception){
            consola.mostrarError(e.toString())
        }
    }while (!salir)
    return opcionUsuario
}

fun main(){
    val consola = Consola()
    val fichero = Fichero(consola)
    val seguridad = Seguridad()
    val repoUsuariosMem = RepoUsuariosMem()
    val repoSegurosMem = RepoSegurosMem()
    val gestorSeguros = GestorSeguros(repoSegurosMem)
    val gestorUsuarios = GestorUsuarios(repoUsuariosMem, seguridad)
    val controlAcceso = ControlAcceso("", gestorUsuarios, consola, fichero)








}