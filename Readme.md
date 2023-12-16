

# Juego Simon Dice

Este es un juego de Simon Dice implementado en Kotlin utilizando el kit de herramientas de interfaz de usuario Android Compose.

## Lógica del juego

La lógica del juego se implementa en el archivo `VM.kt`. Aquí hay un fragmento de código que muestra cómo se genera la secuencia de colores:

```kotlin
fun aumentarSecuencia() {
    Datos.status = Status.SECUENCIA
    Datos.secuencia.add(generaNumero(4))
    mostrarSecuencia()
}
```

La entrada del usuario se maneja en la función `aumentarSecuenciaUsuario()`. Aquí hay un fragmento de código que muestra cómo se maneja la entrada del usuario:

```kotlin
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
```

## Interfaz de usuario

La interfaz de usuario se implementa en el archivo `UI.kt` utilizando Android Compose. Aquí hay un fragmento de código que muestra cómo se crean los botones de colores:

```kotlin
Button(
    onClick = {
        if (Datos.status == Status.ESPERANDO){
            Log.d("ButtonBlue", "Estoy en Onclick")
            miViewModel.cambiaColorBoton(Colores.AZUL)
            miViewModel.aumentarSecuenciaUsuario(Colores.AZUL.ordinal)
        }
    },
    shape= RectangleShape,
    colors = buttonColors(Datos.colorBlue),
    modifier = Modifier
        .padding(20.dp)
        .size(150.dp)
        .border(3.dp, Color.Black),
)
{
}
```

## Estado del juego

El estado del juego se almacena en el archivo `Datos.kt`. Aquí hay un fragmento de código que muestra cómo se almacena el estado del juego:

```kotlin
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
```

## Ejecución del juego

Para ejecutar el juego, abre el proyecto en Android Studio y ejecuta el archivo `MainActivity.kt`.
```

Este README proporciona una descripción general de cómo funciona el código y cómo se estructura el proyecto, con fragmentos de código para ilustrar las partes clave del juego.