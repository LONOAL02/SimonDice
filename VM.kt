package com.dam.simondice

import android.widget.Button
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.luminance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


fun Color.darken(factor: Float): Color {
    val a = alpha
    val r = red * factor
    val g = green * factor
    val b = blue * factor
    return Color(r, g, b, a)
}

/**
 * ViewModel del juego SimonDice.
 */

class VM {
    /**
     * Genero un numero aleatorio entre 0 y maximo -1
     * @param maximo Numero maximo generado
     * @return Numero aleatorio
     */
    fun generaNumero(maximo: Int): Int {
        return (Math.random() * maximo).toInt()
    }

    /**
     * Inicializo el juego
     */
    fun inicializaJuego() {
        Datos.speed = 100
        Datos.ronda = 0
        Datos.secuencia.clear()
        Datos.secuenciaUsuario.clear()
        Datos.record = 0
        Datos.status = Status.INICIO
        aumentarSecuencia()
    }

    /**
     * Genera secuencia de colores
     * muestra al usuario el color
     */
    fun aumentarSecuencia() {
        Datos.status = Status.SECUENCIA
        Datos.secuencia.add(generaNumero(4))
        mostrarSecuencia()
    }

    /**
     * Muestra la secuencia de colores al usuario
     */
    fun mostrarSecuencia() {
        GlobalScope.launch {
            for (i in Datos.secuencia.indices) {
                cambiaColorBoton(Colores.values()[Datos.secuencia[i]])
                kotlinx.coroutines.delay(Datos.speed.toLong() * 10)
            }
            Datos.status = Status.ESPERANDO
        }
    }


    /**
     * Comprueba si la secuencia del usuario es correcta
     * Finaliza el juego si no es correcta o aumenta la ronda si es correcta
     * @param correcta true si es correcta, false si no lo es
     */

    fun compruebaSecuenciaUsuario(correcta: Boolean) {
        GlobalScope.launch {
            if (correcta) {
                Datos.ronda++
                if (Datos.speed > 20) {
                    Datos.speed -= 10
                }
                if (Datos.ronda > Datos.record) {
                    Datos.record = Datos.ronda
                }
                Datos.secuenciaUsuario.clear()
                kotlinx.coroutines.delay(400)
                aumentarSecuencia()
            } else {
                finalizaJuego()
                Datos.status = Status.FINALIZADO
            }
        }
    }


    /**
     * Compruebo si la secuencia introducida por el usuario es correcta
     */
    fun compruebaSecuencia(){
        Datos.status = Status.COMPROBANDO
        var correcto = true
        for (i in Datos.secuencia.indices) {
            if (Datos.secuencia[i] != Datos.secuenciaUsuario[i]) {
                correcto = false
            }
        }

        compruebaSecuenciaUsuario(correcto)
    }


    /**
     * Aumentar la secuencia del usuario
     * @param color Color a añadir
     */

    fun aumentarSecuenciaUsuario(color: Int) {
        Datos.status = Status.ENTRADA
        Datos.secuenciaUsuario.add(color)
        if (Datos.secuenciaUsuario.size == Datos.secuencia.size) {
            compruebaSecuencia()
            Datos.status = Status.COMPROBANDO
        }else{
            Datos.status = Status.ESPERANDO
        }


    }

    /**
     * Aumento la ronda
     */
    fun aumentaRonda() {
        Datos.ronda++
    }

    /**
     * Aumento el record
     */

    fun aumentaRecord() {
        Datos.record++
    }

    /**
     * Termina el juego
     */

    fun finalizaJuego() {
        GlobalScope.launch {
            Datos.status = Status.FINALIZADO
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    MainActivity.context,
                    "Has perdido, tu record es: " + Datos.record,
                    Toast.LENGTH_LONG
                ).show()
            }
            Datos.text= "START"
        }
    }



    /**
     * Cambia el color del boton cuando se pulsa y al cabo de un tiempo vuelve a su color original
     */
    fun cambiaColorBoton(colores: Colores) {
        GlobalScope.launch {
            when (colores) {
                Colores.ROJO -> {
                    Datos.colorRed = Color.Red.darken(0.2f)
                    kotlinx.coroutines.delay(150)
                    Datos.colorRed = Color.Red
                }
                Colores.VERDE -> {
                    Datos.colorGreen = Color.Green.darken(0.2f)
                    kotlinx.coroutines.delay(150)
                    Datos.colorGreen = Color.Green
                }
                Colores.AZUL -> {
                    Datos.colorBlue = Color.Blue.darken(0.2f)
                    kotlinx.coroutines.delay(150)
                    Datos.colorBlue = Color.Blue
                }
                Colores.AMARILLO -> {
                    Datos.colorYellow = Color.Yellow.darken(0.2f)
                    kotlinx.coroutines.delay(150)
                    Datos.colorYellow = Color.Yellow
                }
            }
        }
    }


}
