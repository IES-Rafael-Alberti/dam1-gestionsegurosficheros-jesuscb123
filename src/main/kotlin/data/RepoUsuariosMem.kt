package prog2425.dam1.seguros.data


import prog2425.dam1.seguros.model.Perfil
import prog2425.dam1.seguros.model.Usuario

open class RepoUsuariosMem : IRepoUsuarios {
    val listaUsuarios = mutableListOf<Usuario>()

    override fun agregar(usuario: Usuario): Boolean {
        val usuarioExistente = buscar(usuario.nombre)
        if (usuarioExistente == null) {
            listaUsuarios.add(usuario)
            return true
        } else {
            return false
        }
    }

    override fun buscar(nombreUsuario: String): Usuario? {
       return listaUsuarios.find { it.nombre == nombreUsuario }
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (listaUsuarios.remove(usuario)){
            return true
        }else{
            return false
        }
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        val usuarioExistente = buscar(nombreUsuario)
        if (usuarioExistente != null){
            eliminar(usuarioExistente)
        }
        return false
    }

    override fun obtenerTodos(): List<Usuario> {
        return listaUsuarios
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return listaUsuarios.filter { it.perfil == perfil }
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        val usuarioExistente = buscar(usuario.nombre)
        if (usuarioExistente != null){
            usuario.cambiarClave(nuevaClave)
            return true
        }else{
            return false
        }
    }
}