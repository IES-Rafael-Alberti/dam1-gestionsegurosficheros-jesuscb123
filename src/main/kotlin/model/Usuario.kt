package prog2425.dam1.seguros.model

class Usuario(val nombre: String, var clave: String, val perfil: Perfil) : IExportable {

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
}