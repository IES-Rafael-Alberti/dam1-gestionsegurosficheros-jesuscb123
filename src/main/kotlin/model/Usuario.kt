package prog2425.dam1.seguros.model

class Usuario(val nombre: String, clave: String, val perfil: Perfil) : IExportable {
    var clave = clave
        private set

    companion object{
        fun crearUsuario(datos: List<String>): Usuario{
            val nombre = datos[0]
            val clave = datos[1]
            val perfilDefinitivo = Perfil.getPerfil(datos[2])
            return Usuario(nombre,clave,perfilDefinitivo)
        }
    }


   fun cambiarClave(nuevaClaveEncriptada: String){
       clave = nuevaClaveEncriptada
   }

    override fun serializar(separador: String): String {
        return "$nombre$separador$clave$separador$perfil"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Usuario) return true
        return this.nombre == other.nombre
    }

    override fun toString(): String {
        return "Usuario (nombre: $nombre, clave = $clave, perfil = $perfil)"
    }
}