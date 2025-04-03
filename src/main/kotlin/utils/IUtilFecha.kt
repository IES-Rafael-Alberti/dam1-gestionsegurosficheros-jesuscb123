package prog2425.dam1.seguros.utils

import java.time.format.DateTimeFormatter

interface IUtilFecha {
    fun formatearFecha(fecha: String): DateTimeFormatter
    fun validarFecha(fecha: String): Boolean
}