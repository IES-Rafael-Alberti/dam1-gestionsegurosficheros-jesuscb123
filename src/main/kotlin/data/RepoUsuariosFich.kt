package prog2425.dam1.seguros.data


import prog2425.dam1.seguros.model.Usuario

class RepoUsuariosFich : RepoUsuariosMem(), ICargarUsuariosIniciales {


    override fun agregar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, listaUsuarios.filter { it != usuario })) {
            return super.agregar(usuario)
        }
        return false
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, listaUsuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        if (fich.escribirArchivo(rutaArchivo, listaUsuarios.filter { it.nombre != nombreUsuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    fun actualizarFichero() {
        TODO()
    }


    override fun cargarUsuarios(): Boolean {
        TODO()
    }

}
