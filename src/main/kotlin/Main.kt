package prog2425.dam1.seguros

import prog2425.dam1.seguros.UI.Consola
import prog2425.dam1.seguros.app.CargadorInicial
import prog2425.dam1.seguros.app.ControlAcceso
import prog2425.dam1.seguros.app.GestorMenu
import prog2425.dam1.seguros.data.*

import prog2425.dam1.seguros.service.GestorSeguros
import prog2425.dam1.seguros.service.GestorUsuarios
import prog2425.dam1.seguros.utils.Fichero
import prog2425.dam1.seguros.utils.Seguridad




fun main(){

        // Crear dos variables con las rutas de los archivos de texto donde se almacenan los usuarios y seguros.
        // Estos ficheros se usarán solo si el programa se ejecuta en modo de almacenamiento persistente.
        val ficheroUsarios = "./res/usuarios.txt"
        val ficheroSeguros = "./res/seguros.txt"

        // Instanciamos los componentes base del sistema: la interfaz de usuario, el gestor de ficheros y el módulo de seguridad.
        // Estos objetos serán inyectados en los diferentes servicios y utilidades a lo largo del programa.
        val ui = Consola()
        val gestorFichero = Fichero(ui)
        val seguridad = Seguridad()

        // Limpiamos la pantalla antes de comenzar, para que la interfaz esté despejada al usuario.
        ui.limpiarPantalla()
        // Preguntamos al usuario si desea iniciar en modo simulación.
        // En modo simulación los datos no se guardarán en archivos, solo estarán en memoria durante la ejecución.
        val respuestaUsuario = ui.preguntar("¿Desea iniciar en modo simulación? (s,n) >")

        // Declaramos los repositorios de usuarios y seguros.
        // Se asignarán más abajo dependiendo del modo elegido por el usuario.

        val repoSeguros: IRepoSeguros
        val repoUsuarios: IRepoUsuarios

        // Si se ha elegido modo simulación, se usan repositorios en memoria.
        // Si se ha elegido almacenamiento persistente, se instancian los repositorios que usan ficheros.
        // Además, creamos una instancia del cargador inicial de información y lanzamos la carga desde los ficheros.

        when(respuestaUsuario){
            true ->{
                repoSeguros = RepoSegurosMem()
                repoUsuarios = RepoUsuariosMem()
            }
            else -> {
                repoSeguros = RepoSegurosFich(ficheroSeguros,gestorFichero)
                repoUsuarios = RepoUsuariosFich(ficheroUsarios, gestorFichero)
                val cargadorInicial = CargadorInicial(ui, repoUsuarios, repoSeguros)
                cargadorInicial.cargarUsuarios()
                cargadorInicial.cargarSeguros()
            }

        }


        // Se crean los servicios de lógica de negocio, inyectando los repositorios y el componente de seguridad.
        val gestorUsuarios = GestorUsuarios(repoUsuarios, seguridad)
        val gestorSeguros = GestorSeguros(repoSeguros)

        // Se inicia el proceso de autenticación. Se comprueba si hay usuarios en el sistema y se pide login.
        // Si no hay usuarios, se permite crear un usuario ADMIN inicial.
        val controlAcceso = ControlAcceso(ficheroUsarios, gestorUsuarios, ui, gestorFichero)
        val (nombre, perfil) = controlAcceso.autenticar()

        // Si el login fue exitoso (no es null), se inicia el menú correspondiente al perfil del usuario autenticado.
        // Se lanza el menú principal, **iniciarMenu(0)**, pasándole toda la información necesaria.

        if (nombre != null && perfil != null) {
            val gestorMenu = GestorMenu(nombre, perfil.toString().lowercase(), ui, gestorUsuarios, gestorSeguros)
            gestorMenu.iniciarMenu()
        }


    }










