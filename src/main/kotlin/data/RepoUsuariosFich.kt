package prog2425.dam1.seguros.data


import prog2425.dam1.seguros.model.Usuario
import prog2425.dam1.seguros.utils.IUtilFicheros

class RepoUsuariosFich(val rutaArchivo: String, val fich: IUtilFicheros) : RepoUsuariosMem(), ICargarUsuariosIniciales {

    override fun agregar(usuario: Usuario): Boolean {
        if (fich.agregarLinea(rutaArchivo, usuario.serializar())) {
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
        val usuario = buscar(nombreUsuario)
        if (usuario != null && fich.escribirArchivo(rutaArchivo, listaUsuarios.filter { it.nombre != nombreUsuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return fich.escribirArchivo(rutaArchivo,listaUsuarios)
    }

    fun actualizarFichero() {
        TODO()
    }


    override fun cargarUsuarios(): Boolean {
        val lineas = fich.leerArchivo(rutaArchivo)
        if(lineas.isNotEmpty()){
            listaUsuarios.clear()
            for (linea in lineas){
                val datos = linea.split(";")
                if (datos.size == 3){
                    listaUsuarios.add(Usuario.crearUsuario(datos))
                }
            }
        }
        return listaUsuarios.isNotEmpty()
    }
}
