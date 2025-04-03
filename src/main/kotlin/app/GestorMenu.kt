package prog2425.dam1.seguros.app

import prog2425.dam1.seguros.UI.IEntradaSalida
import prog2425.dam1.seguros.model.Perfil
import prog2425.dam1.seguros.service.IServSeguros

import prog2425.dam1.seguros.service.IServUsuarios

/**
 * Clase encargada de gestionar el flujo de menús y opciones de la aplicación,
 * mostrando las acciones disponibles según el perfil del usuario autenticado.
 *
 * @property nombreUsuario Nombre del usuario que ha iniciado sesión.
 * @property perfilUsuario Perfil del usuario: admin, gestion o consulta.
 * @property ui Interfaz de usuario.
 * @property gestorUsuarios Servicio de operaciones sobre usuarios.
 * @property gestorSeguros Servicio de operaciones sobre seguros.
 */
class GestorMenu(val nombreUsuario: String,
                 val perfilUsuario: String,
                 val ui: IEntradaSalida,
                 val gestorUsuarios: IServUsuarios,
                 val gestorSeguros: IServSeguros
){

    /**
     * Inicia un menú según el índice correspondiente al perfil actual.
     *
     * @param indice Índice del menú que se desea mostrar (0 = principal).
     */
    fun iniciarMenu(indice: Int = 0) {
        val (opciones, acciones) = ConfiguracionesApp.obtenerMenuYAcciones(perfilUsuario, indice)
        ejecutarMenu(opciones, acciones)
    }

    /**
     * Formatea el menú en forma numerada.
     */
    private fun formatearMenu(opciones: List<String>): String {
        var cadena = ""
        opciones.forEachIndexed { index, opcion ->
            cadena += "${index + 1}. $opcion\n"
        }
        return cadena
    }

    /**
     * Muestra el menú limpiando pantalla y mostrando las opciones numeradas.
     */
    private fun mostrarMenu(opciones: List<String>) {
        ui.limpiarPantalla()
        ui.mostrar(formatearMenu(opciones), salto = false)
    }

    /**
     * Ejecuta el menú interactivo.
     *
     * @param opciones Lista de opciones que se mostrarán al usuario.
     * @param ejecutar Mapa de funciones por número de opción.
     */
    private fun ejecutarMenu(opciones: List<String>, ejecutar: Map<Int, (GestorMenu) -> Boolean>) {
        do {
            mostrarMenu(opciones)
            val opcion = ui.pedirInfo("Elige opción > ").toIntOrNull()
            if (opcion != null && opcion in 1..opciones.size) {
                // Buscar en el mapa las acciones a ejecutar en la opción de menú seleccionada
                val accion = ejecutar[opcion]
                // Si la accion ejecutada del menú retorna true, debe salir del menú
                if (accion != null && accion(this)) return
            }
            else {
                ui.mostrarError("Opción no válida!")
            }
        } while (true)
    }

    /** Crea un nuevo usuario solicitando los datos necesarios al usuario */
    fun nuevoUsuario() {
        var usuarioCorrecto = false
        var nombreUsuario: String
        var clave: String
        do{
            try{
                nombreUsuario = ui.pedirInfo("Introduce un nombre de usuario")
                clave = ui.pedirInfo("Introduce una clave", "La clave debe tener mínimo 5 caracteres."){
                    it.length >= 5
                }
                if (gestorUsuarios.agregarUsuario(nombreUsuario,clave, Perfil.ADMIN)){
                    usuarioCorrecto = true
                }
            }catch(e:Exception){
                ui.mostrarError("$e")
            }
        }while (!usuarioCorrecto)
    }

    /** Elimina un usuario si existe */
    fun eliminarUsuario() {
       var usuarioEliminado = false
        do{
            try{
                ui.mostrar("----USUARIOS----")
                ui.mostrarLista(gestorUsuarios.consultarTodos())
                var nombre = ui.pedirInfo("Introduce el nombre de usuario que quieres eliminar o salir.", "ERROR - El nombre no puede estar vacío."){
                    it != ""
                }
                require (gestorUsuarios.buscarUsuario(nombre) != null)
                require (gestorUsuarios.buscarUsuario(nombre) != null) {"El usuario no se encuentra en la base de datos."}
                gestorUsuarios.eliminarUsuario(nombre)
                usuarioEliminado = true
            }catch (e: IllegalArgumentException) {
                ui.mostrarError(e.toString())
            }
        }while (!usuarioEliminado)
    }

    /** Cambia la contraseña del usuario actual */
    fun cambiarClaveUsuario() {
        TODO("Implementar este método")
    }

    /**
     * Mostrar la lista de usuarios (Todos o filstrados por un perfil)
     */
    fun consultarUsuarios() {
        TODO("Implementar este método")
    }

    /**
     * Solicita al usuario un DNI y verifica que tenga el formato correcto: 8 dígitos seguidos de una letra.
     *
     * @return El DNI introducido en mayúsculas.
     */
    private fun pedirDni() {
        TODO("Implementar este método")
    }

    /**
     * Solicita al usuario un importe positivo, usado para los seguros.
     *
     * @return El valor introducido como `Double` si es válido.
     */
    private fun pedirImporte() {
        TODO("Implementar este método")
    }

    /** Crea un nuevo seguro de hogar solicitando los datos al usuario */
    fun contratarSeguroHogar() {
        TODO("Implementar este método")
    }

    /** Crea un nuevo seguro de auto solicitando los datos al usuario */
    fun contratarSeguroAuto() {
        TODO("Implementar este método")
    }

    /** Crea un nuevo seguro de vida solicitando los datos al usuario */
    fun contratarSeguroVida() {
        TODO("Implementar este método")
    }

    /** Elimina un seguro si existe por su número de póliza */
    fun eliminarSeguro() {
        TODO("Implementar este método")
    }

    /** Muestra todos los seguros existentes */
    fun consultarSeguros() {
        TODO("Implementar este método")
    }

    /** Muestra todos los seguros de tipo hogar */
    fun consultarSegurosHogar() {
        TODO("Implementar este método")
    }

    /** Muestra todos los seguros de tipo auto */
    fun consultarSegurosAuto() {
        TODO("Implementar este método")
    }

    /** Muestra todos los seguros de tipo vida */
    fun consultarSegurosVida() {
        TODO("Implementar este método")
    }


}