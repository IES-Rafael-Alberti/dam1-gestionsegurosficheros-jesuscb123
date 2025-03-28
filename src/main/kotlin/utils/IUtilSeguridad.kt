package prog2425.dam1.seguros.utils

interface IUtilSeguridad {
    fun encriptarClave(clave: String, nivelSeguridad: Int = 12): String
    fun verificarClave(claveIngresada: String, hashAlmacenado: String): Boolean
}