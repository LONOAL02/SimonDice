package com.dam.simondice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
* Clase que almacena los datos del juego.
* @property ronda Número de ronda actual.
* @property secuencia Secuencia de colores.
* @property secuenciaUsuario Secuencia de colores que ha introducido el usuario.
* @property record Record de puntuación.
 * @property satus Estado del juego.
 */


object Datos {
    var ronda = 0
    var secuencia = mutableListOf<Int>()
    var secuenciaUsuario = mutableListOf<Int>()
    var record = 0
    var status = Status.INICIO

    var text by mutableStateOf("START")
    var fontSize by mutableStateOf(24)
    var speed = 100
    var colorRed by mutableStateOf(Color.Red)
    var colorGreen by mutableStateOf(Color.Green)
    var colorBlue by mutableStateOf(Color.Blue)
    var colorYellow by mutableStateOf(Color.Yellow)


}

/**
 * Enum con los estados del juego.
 */

enum class Status {
    INICIO, SECUENCIA, ESPERANDO, ENTRADA, COMPROBANDO, FINALIZADO, CLICK
}
enum class Colores {
    ROJO, VERDE, AZUL, AMARILLO
}
