package prog2425.dam1.seguros.utils

import java.time.format.DateTimeFormatter

object Fecha : IUtilFecha {
    override fun formatearFecha(fecha: String): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }

    override fun validarFecha(fecha: String): Boolean {
        val regex = """\d{2}/\d{2}/\d{4}"""
        return fecha.matches(regex.toRegex())
    }
}