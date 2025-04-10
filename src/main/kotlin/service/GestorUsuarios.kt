package prog2425.dam1.seguros.service

import prog2425.dam1.seguros.data.IRepoUsuarios
import prog2425.dam1.seguros.model.Perfil
import prog2425.dam1.seguros.model.Usuario
import prog2425.dam1.seguros.utils.IUtilSeguridad

class GestorUsuarios(private val repoUsuarios: IRepoUsuarios, private val seguridad: IUtilSeguridad) : IServUsuarios {

    override fun iniciarSesion(nombre: String, clave: String): Perfil? {
       val usuarioExiste = buscarUsuario(nombre)
        if (usuarioExiste != null && seguridad.verificarClave(clave, usuarioExiste.clave)) return usuarioExiste.perfil else return null
    }

    override fun agregarUsuario(nombre: String, clave: String, perfil: Perfil): Boolean {
            val claveEncriptada = seguridad.encriptarClave(clave)
            if (repoUsuarios.agregar(Usuario(nombre,claveEncriptada,perfil))) return true else return false
    }

    override fun eliminarUsuario(nombre: String): Boolean {
        return repoUsuarios.eliminar(nombre)
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        val nuevaClaveEncriptada = seguridad.encriptarClave(nuevaClave)
        if (repoUsuarios.cambiarClave(usuario, nuevaClaveEncriptada)) return true else return false
    }

    override fun buscarUsuario(nombre: String): Usuario? {
        val usuarioExiste = repoUsuarios.buscar(nombre)
        if (usuarioExiste != null) return usuarioExiste else return null
    }

    override fun consultarTodos(): List<Usuario> {
        return repoUsuarios.obtenerTodos()
    }

    override fun consultarPorPerfil(perfil: Perfil): List<Usuario> {
        return repoUsuarios.obtener(perfil)
    }
}