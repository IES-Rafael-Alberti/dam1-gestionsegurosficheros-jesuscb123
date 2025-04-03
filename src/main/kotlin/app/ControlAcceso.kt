package prog2425.dam1.seguros.app

import prog2425.dam1.seguros.UI.IEntradaSalida
import prog2425.dam1.seguros.model.Perfil
import prog2425.dam1.seguros.model.Usuario
import prog2425.dam1.seguros.service.IServUsuarios
import prog2425.dam1.seguros.utils.IUtilFicheros

/**
 * Clase responsable del control de acceso de usuarios: alta inicial, inicio de sesión
 * y recuperación del perfil. Su objetivo es asegurar que al menos exista un usuario
 * en el sistema antes de acceder a la aplicación.
 *
 * Esta clase encapsula toda la lógica relacionada con la autenticación de usuarios,
 * separando así la responsabilidad del acceso del resto de la lógica de negocio.
 *
 * Utiliza inyección de dependencias (DIP) para recibir los servicios necesarios:
 * - La ruta del archivo de usuarios
 * - El gestor de usuarios para registrar o validar credenciales
 * - La interfaz de entrada/salida para interactuar con el usuario
 * - La utilidad de ficheros para comprobar la existencia y contenido del fichero
 *
 * @property rutaArchivo Ruta del archivo donde se encuentran los usuarios registrados.
 * @property gestorUsuarios Servicio encargado de la gestión de usuarios (login, alta...).
 * @property ui Interfaz para mostrar mensajes y recoger entradas del usuario.
 * @property ficheros Utilidad para operar con ficheros (leer, comprobar existencia...).
 */
class ControlAcceso(val rutaArchivo: String, val gestorUsuarios: IServUsuarios, val consola: IEntradaSalida, val ficheros: IUtilFicheros) {

    /**
     * Inicia el proceso de autenticación del sistema.
     *
     * Primero verifica si hay usuarios registrados. Si el archivo está vacío o no existe,
     * ofrece al usuario la posibilidad de crear un usuario ADMIN inicial.
     *
     * A continuación, solicita credenciales de acceso en un bucle hasta que sean válidas
     * o el usuario decida cancelar el proceso.
     *
     * @return Un par (nombreUsuario, perfil) si el acceso fue exitoso, o `null` si el usuario cancela el acceso.
     */
    fun autenticar() {

    }

    fun verificarFicheroUsuarios(){
        if (ficheros.existeFichero(rutaArchivo) || ficheros.leerArchivo(rutaArchivo).isEmpty()){
            consola.mostrar("El fichero está vacío, no hay usuarios existentes.")
            if (consola.preguntar("¿Desea crear un usuario nuevo ADMIN?")){
                crearUsuario()
            }else{

            }
        }
    }

    private fun crearUsuario(): Boolean{
        var usuarioCorrecto = false
        var nombreUsuario: String
        var clave: String
        do{
            try{
                nombreUsuario = consola.pedirInfo("Introduce un nombre de usuario")
                clave = consola.pedirInfo("Introduce una clave")
                if (gestorUsuarios.agregarUsuario(nombreUsuario,clave, Perfil.ADMIN)){
                    usuarioCorrecto = true
                }
            }catch(e:Exception){}
        }while (!usuarioCorrecto)
        return usuarioCorrecto
    }
}