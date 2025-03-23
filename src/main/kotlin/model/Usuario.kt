package model

class Usuario(val nombre: String, val clave: String, val perfil: Perfil) : IExportable {

    companion object{
        fun crearUsuario(datos: List<String>): Usuario{
            val nombre = datos[0]
            val clave = datos[1]
            val perfilDefinitivo = Perfil.getPerfil(datos[2])
            return Usuario(nombre,clave,perfilDefinitivo)
        }
    }

    // fun verificarClave(valor)

   // fun cambiarClave(nuevaClaveEncriptada: String)

    override fun serializar(): String {
        return "$nombre;$clave;$perfil"
    }
}