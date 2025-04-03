package prog2425.dam1.seguros.UI

import jdk.internal.org.jline.reader.EndOfFileException
import jdk.internal.org.jline.reader.LineReaderBuilder
import jdk.internal.org.jline.reader.UserInterruptException
import jdk.internal.org.jline.terminal.TerminalBuilder

class Consola : IEntradaSalida {
    override fun mostrar(msj: String, salto: Boolean, pausa: Boolean) {
        print(msj)
        if (salto) println()
        if (pausa) pausar()
    }

    override fun mostrarError(msj: String, pausa: Boolean) {
        if (msj.startsWith("ERROR -")) println() else println(msj)
        if (pausa) pausar()
    }

    override fun pedirInfo(msj: String): String {
        if (msj.isNotEmpty()){
            println(msj.trim())
        }
        return readln().trim()
    }

    override fun pedirInfo(msj: String, error: String, debeCumplir: (String) -> Boolean): String {
        val respuestaUsuario = pedirInfo(msj)
        require(debeCumplir(respuestaUsuario)) { error }
        return respuestaUsuario
    }

    override fun pedirDouble(
        prompt: String,
        error: String,
        errorConv: String,
        debeCumplir: (Double) -> Boolean
    ): Double {
        println(prompt)
        val respuestaUsuario = pedirInfo(prompt).replace(",",".").toDoubleOrNull()
        require(respuestaUsuario != null){ errorConv }
        require(debeCumplir(respuestaUsuario)){ error }
        return respuestaUsuario

    }

    override fun pedirEntero(prompt: String, error: String, errorConv: String, debeCumplir: (Int) -> Boolean): Int {
        println(prompt)
        val respuestaUsuario = pedirInfo(prompt).toIntOrNull()
        require(respuestaUsuario != null){errorConv}
        require(debeCumplir(respuestaUsuario)){error}
        return respuestaUsuario
    }

    override fun pedirInfoOculta(prompt: String): String {
        return try {
            val terminal = TerminalBuilder.builder()
                .dumb(true) // Para entornos no interactivos como IDEs
                .build()

            val reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build()

            reader.readLine(prompt, '*') // Oculta la contraseña con '*'
        } catch (e: UserInterruptException) {
            mostrarError("Entrada cancelada por el usuario (Ctrl + C).", pausa = false)
            ""
        } catch (e: EndOfFileException) {
            mostrarError("Se alcanzó el final del archivo (EOF ó Ctrl+D).", pausa = false)
            ""
        } catch (e: Exception) {
            mostrarError("Problema al leer la contraseña: ${e.message}", pausa = false)
            ""
        }
    }

    override fun pausar(msj: String) {
        println(msj)
    }

    override fun limpiarPantalla(numSaltos: Int) {
        if (System.console() != null) {
            mostrar("\u001b[H\u001b[2J", false)
            System.out.flush()
        } else {
            repeat(numSaltos) {
                mostrar("")
            }
        }
    }

    override fun preguntar(mensaje: String): Boolean {
        val siONo = arrayOf("s","n")
        var respuestaFinal = false
        var respuestaCorrecta = false
        do {
            var respuestaUsuario = readln().trim()
            if (respuestaUsuario in siONo){
                when (respuestaUsuario){
                    "s" -> respuestaFinal = true
                    else -> respuestaFinal = false
                }
            }else{
                mostrarError("ERROR - Introduce s o n.")
                respuestaCorrecta = false
            }
        }while(!respuestaCorrecta)

        return respuestaFinal
    }


}