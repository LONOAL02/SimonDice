package com.dam.simondice

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dam.simondice.ui.theme.SimonDiceTheme


@Composable
fun IU(miViewModel: VM) {


    Column  (horizontalAlignment = Alignment.End, modifier = Modifier.padding(10.dp)) {

        Text(
            text = "RONDA",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(10.dp)
        )


        Box(modifier = Modifier
            .padding(end = 10.dp)
            .border(1.dp, Color.Black)
            .width(80.dp)) {
            Text(
                text = Datos.ronda.toString(),
                fontSize = Datos.fontSize.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Center)
            )
        }



    }

    Column (Modifier.padding(top = 180.dp), horizontalAlignment = Alignment.CenterHorizontally){
        Row {
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
            Button(
                onClick = {
                    if (Datos.status == Status.ESPERANDO) {
                        Log.d("ButtonGreen", "Estoy en Onclick")
                        miViewModel.cambiaColorBoton(Colores.VERDE)
                        miViewModel.aumentarSecuenciaUsuario(Colores.VERDE.ordinal)
                    }
                },
                shape= RectangleShape,
                colors = buttonColors(Datos.colorGreen),
                modifier = Modifier
                    .padding(20.dp)
                    .size(150.dp)
                    .border(3.dp, Color.Black),
            )
            {
            }
        }
        Row {
            Button(
                onClick = {
                    if (Datos.status == Status.ESPERANDO) {
                        Log.d("ButtonRed", "Estoy en Onclick")
                        miViewModel.cambiaColorBoton(Colores.ROJO)
                        miViewModel.aumentarSecuenciaUsuario(Colores.ROJO.ordinal)
                    }
                },
                shape= RectangleShape,
                colors = buttonColors(Datos.colorRed),
                modifier = Modifier
                    .padding(20.dp)
                    .size(150.dp)
                    .border(3.dp, Color.Black),
            )
            {
            }
            Button(
                onClick = {
                    if (Datos.status == Status.ESPERANDO) {
                        Log.d("ButtonYellow", "Estoy en Onclick")
                        miViewModel.cambiaColorBoton(Colores.AMARILLO)
                        miViewModel.aumentarSecuenciaUsuario(Colores.AMARILLO.ordinal)
                    }
                },
                shape= RectangleShape,
                colors = buttonColors(Datos.colorYellow),
                modifier = Modifier
                    .padding(20.dp)
                    .size(150.dp)
                    .border(3.dp, Color.Black),
            )
            {
            }
        }
        Row(Modifier.padding(top = 40.dp)) {
            Button(
                onClick = {
                    if (Datos.text.equals("START")){
                        Log.d("ButtonStart", "Juego iniciado")
                        miViewModel.inicializaJuego()
                        Datos.text="RESET"
                    }else{
                        Log.d("ButtonStart", "Juego terminado")
                        miViewModel.finalizaJuego()
                        Datos.text="START"

                        Datos.fontSize=24
                    }
                },
                shape = RectangleShape,
                colors = buttonColors(Color.White),
                modifier = Modifier
                    .width(180.dp)
                    .height(130.dp)
                    .padding(20.dp)
                    .border(1.dp, Color.Black),
            ) {
                Text(text = Datos.text
                    , fontSize = 24.sp
                    , color = Color.Black)
            }
            Button(
                onClick = {
                    if (Datos.text.equals("RESET")) {
                        Log.d("ButtonRound", "Ronda pasada")
                    }
                },
                shape = RectangleShape,
                colors = buttonColors(Color.Black),
                modifier = Modifier
                    .width(180.dp)
                    .height(130.dp)
                    .padding(20.dp)
                    .border(1.dp, Color.Black),
            ) {
                Icon(painter = painterResource(id = R.drawable.paper_plane), contentDescription = "Pasar_Ronda")
            }
        }
    }
}
@Preview
@Composable
fun IUPreview() {
    SimonDiceTheme {
        val viewModel = remember { VM() }
        IU (miViewModel = viewModel)
    }

}






















