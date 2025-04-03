package prog2425.dam1.seguros.app

import prog2425.dam1.seguros.UI.IEntradaSalida
import prog2425.dam1.seguros.model.Cobertura
import prog2425.dam1.seguros.model.NivelRiesgo
import prog2425.dam1.seguros.model.Perfil
import prog2425.dam1.seguros.model.TipoAuto
import prog2425.dam1.seguros.service.IServSeguros

import prog2425.dam1.seguros.service.IServUsuarios
import prog2425.dam1.seguros.utils.IUtilFecha
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
                 val gestorSeguros: IServSeguros,
                 val fecha: IUtilFecha
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
                require(gestorUsuarios.buscarUsuario(nombreUsuario) == null) {"El usuario ya existe."}
                clave = ui.pedirInfo("Introduce una clave", "La clave debe tener mínimo 5 caracteres."){
                    it.length >= 5
                }
                if (gestorUsuarios.agregarUsuario(nombreUsuario,clave, Perfil.ADMIN)){
                    usuarioCorrecto = true
                }
            }catch(e:IllegalArgumentException){
                ui.mostrarError(e.toString())
            }catch (e: Exception){
                ui.mostrarError(e.toString())
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
        var contraseniaCambiada = false
        do{
            try{
                val usuario = gestorUsuarios.buscarUsuario(nombreUsuario)
                if (usuario != null) {
                    val nuevaClave = ui.pedirInfo("Clave de nueva", "La contraseña debe tener mínimo 5 caracteres"){
                        it.length >= 5
                    }
                    gestorUsuarios.cambiarClave(usuario, nuevaClave)
                    contraseniaCambiada = true
                }else{
                    contraseniaCambiada = false
                }

            }catch(e:IllegalArgumentException){
                ui.mostrarError(e.toString())
            }catch (e: Exception){
                ui.mostrarError(e.toString())
            }
        }while (!contraseniaCambiada)
    }

    /**
     * Mostrar la lista de usuarios (Todos o filstrados por un perfil)
     */
    fun consultarUsuarios() {
        ui.mostrar("----USUARIOS----")
        ui.mostrar("--USUARIOS: ADMIN--")
        consultarUsuariosPerfil("admin")
        ui.mostrar("--USUARIOS: GESTIÓN--")
        consultarUsuariosPerfil("gestion")
        ui.mostrar("--USUARIOS: CONSULTA--")
        consultarUsuariosPerfil("consulta")
    }

    private fun consultarUsuariosPerfil(perfil: String){
        ui.mostrarLista(gestorUsuarios.consultarPorPerfil(Perfil.getPerfil(perfil)))
    }

    /**
     * Solicita al usuario un DNI y verifica que tenga el formato correcto: 8 dígitos seguidos de una letra.
     *
     * @return El DNI introducido en mayúsculas.
     */
    private fun pedirDni(): String {
        var dniCorrecto = false
        var dni: String = ""
        do{
            try {
                dni = ui.pedirInfo("Introduce un dni","DNI incorrecto, debe tener 8 dígitos y una letra."){
                    it.length >= 8 && it[7].isLetter()
                }
                dniCorrecto = true

            }catch(e:IllegalArgumentException){
                ui.mostrarError(e.toString())
            }catch (e: Exception){
                ui.mostrarError(e.toString())
            }

        }while (!dniCorrecto)
        return dni.uppercase()

    }

    /**
     * Solicita al usuario un importe positivo, usado para los seguros.
     *
     * @return El valor introducido como `Double` si es válido.
     */
    private fun pedirImporte(): Double {
        var importeCorrecto = false
        var importe = 0.0
        do {
            try {
                ui.limpiarPantalla()
                importe = ui.pedirDouble("Introduce el importe","Introduzca un importe positivo", "Introduce un número decimal"){
                    it > 0
                }
                importeCorrecto = true
            }catch(e:IllegalArgumentException){
                ui.mostrarError(e.toString())
            }catch (e: Exception){
                ui.mostrarError(e.toString())
            }
        }while (!importeCorrecto)
        return importe
    }

    /** Crea un nuevo seguro de hogar solicitando los datos al usuario */
    fun contratarSeguroHogar() {
        var seguroCorrecto = false
        do {
            try {
                var dni = pedirDni()
                var importe = pedirImporte()
                var metrosCuadrados = ui.pedirEntero("Introduce los metros cuadrados","El número de metros debe ser positivo","Debes introducir un número"){
                    it > 0
                }
                var valorContenido = ui.pedirDouble("Introduce el valor del contenido","El valor debe ser positivo", "Debes introducir un número válido"){
                    it > 0
                }
                var direccion = ui.pedirInfo("Introduce tu dirección")
                var anioConstruccion = ui.pedirEntero("Introduce el año de construcción","El año debe ser positivo y menor que el año actual", "debe ser un número"){
                    it > 0
                    it < LocalDate.now().year
                }
                gestorSeguros.contratarSeguroHogar(dni, importe, metrosCuadrados, valorContenido, direccion, anioConstruccion)
                seguroCorrecto = true
            }catch (e:IllegalArgumentException){
                ui.mostrarError(e.toString())
            }catch (e: Exception){
                ui.mostrarError(e.toString())
            }
        }while (!seguroCorrecto)
    }

    /** Crea un nuevo seguro de auto solicitando los datos al usuario */
    fun contratarSeguroAuto() {
        var seguroCorrecto = false
        do {
            try {
                ui.limpiarPantalla()
                var dni = pedirDni()
                var importe = pedirImporte()
                var descripcion = ui.pedirInfo("Introduce una descripción del auto")
                var combustible = ui.pedirInfo("Introduce el tipo de combustible")
                var tipoAuto = ui.pedirInfo("Introduce el tipo de auto", "El tipo de auto no existe"){
                    it.lowercase() in TipoAuto.entries.toString()
                }
                var cobertura = ui.pedirInfo("Introduce la cobertura", "el tipo de cobertura no existe"){
                    it.lowercase() in Cobertura.entries.toString()
                }
                var asistenciaEnCarretera = ui.pedirInfo("Introduce si quieres asistencia en carretera (s,n)","Debes introducir s o n"){
                    val siONo = arrayOf("s","n", "si", "no")
                    it in siONo
                }

                var numPartes = ui.pedirEntero("Introduce número de partes", "El número de partes debe ser positivo", "Introduce un número entero"){
                    it > 0
                }
                gestorSeguros.contratarSeguroAuto(dni, importe, descripcion, combustible, TipoAuto.getTipoAuto(tipoAuto), Cobertura.getCobertura(cobertura), devolverAsistenciaCarretera(asistenciaEnCarretera), numPartes)
                seguroCorrecto = true
            }catch (e:IllegalArgumentException){
                ui.mostrarError(e.toString())
            }catch (e: Exception){
                ui.mostrarError(e.toString())
            }
        }while (!seguroCorrecto)
    }

    /** Crea un nuevo seguro de vida solicitando los datos al usuario */
    fun contratarSeguroVida() {
        var seguroCorrecto = false
        do {
            try {
                ui.limpiarPantalla()
                var dni = pedirDni()
                var importe = pedirImporte()
                var fechaNacimiento = ui.pedirInfo("Introduce una fecha de nacimiento", "Introduce una fecha válida"){
                    fecha.validarFecha(it) && LocalDate.parse(it, fecha.formatearFecha(it)).year < LocalDate.now().year // La fecha debe estar escrita correctamente y el año de nacimiento debe ser menor al año actual.
                }
                var fechaNacimientoDate = LocalDate.parse(fechaNacimiento, fecha.formatearFecha(fechaNacimiento))
                var nivelRiesgo = ui.pedirInfo("Introdce el nivel de riesgo", "Introduce un nivel de riesgo válido"){
                    it in NivelRiesgo.entries.toString()
                }
                var indemnizacion = ui.pedirDouble("Introduce la indemnización","La indemnización debe ser positiva", "Introduce un número decimal"){
                    it > 0
                }
                gestorSeguros.contratarSeguroVida(dni, importe, fechaNacimientoDate, NivelRiesgo.getRiesgo(nivelRiesgo), indemnizacion)
                seguroCorrecto = true
            }catch (e:IllegalArgumentException){
                ui.mostrarError(e.toString())
            }catch (e: Exception){
                ui.mostrarError(e.toString())
            }
        }while (!seguroCorrecto)
    }

    /** Elimina un seguro si existe por su número de póliza */
    fun eliminarSeguro(){
        var seguroCorrecto = false
        do {
            try {
                ui.limpiarPantalla()
               val numPoliza = ui.pedirEntero("Introduce el número de póliza del seguro que quieres eliminar", "Introduce un número positivo", "El número de póliza debe ser número entero"){
                    it > 0
                }
                gestorSeguros.eliminarSeguro(numPoliza)
                seguroCorrecto = true

            }catch (e:IllegalArgumentException){
                ui.mostrarError(e.toString())
            }
        }while (!seguroCorrecto)
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


    private fun devolverAsistenciaCarretera(respuesta: String): Boolean{
        when (respuesta){
            "s" -> return true
            else -> return false
        }

    }

    private fun formatearFecha



}