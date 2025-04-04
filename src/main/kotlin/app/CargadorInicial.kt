package prog2425.dam1.seguros.app

import prog2425.dam1.seguros.UI.IEntradaSalida
import prog2425.dam1.seguros.data.ICargarSegurosIniciales
import prog2425.dam1.seguros.data.ICargarUsuariosIniciales
import java.io.IOException

/**
 * Clase encargada de cargar los datos iniciales de usuarios y seguros desde ficheros,
 * necesarios para el funcionamiento del sistema en modo persistente.
 *
 * @param ui Interfaz de entrada/salida para mostrar mensajes de error.
 * @param repoUsuarios Repositorio que permite cargar usuarios desde un fichero.
 * @param repoSeguros Repositorio que permite cargar seguros desde un fichero.
 */
class CargadorInicial(val consola: IEntradaSalida, val repoFichUsuarios: ICargarUsuariosIniciales, val repoFichSeguros: ICargarSegurosIniciales) {

    /**
     * Carga los usuarios desde el archivo configurado en el repositorio.
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    fun cargarUsuarios(){
        try {
            repoFichUsuarios.cargarUsuarios()
        }catch (e: NumberFormatException){
            consola.mostrarError("Error al convertir los datos.")
        }catch (e: Exception){
            consola.mostrarError("No se pueden cargar los usuarios.")
        }
    }


    fun cargarSeguros(){
        try {
            repoFichSeguros.cargarSeguros(ConfiguracionesApp.mapaCrearSeguros)
        }catch (e: Exception){}
    }





}