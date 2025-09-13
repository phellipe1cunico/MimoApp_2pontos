package com.example.mimoapp


import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mimoapp.ui.theme.MimoAppTheme


class TelaPrincipal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


        }
    }
}

@Preview
@Composable
fun TelaInicial(){

    Scaffold {
            innerPadding ->

        Surface(
            color = Color(255,250,250),
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface (
                    color = Color(15,82,186))
                {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(15.dp)
                    ){
                        Cabecalho1()
                        Spacer(modifier = Modifier.height(10.dp))
                        Linguagem()
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                Titulo()
                Spacer(modifier = Modifier.height(40.dp))
                Exercicios()
                Spacer(modifier = Modifier.weight(1f))
                Rodape()
            }
        }
    }
}


//@Preview
@Composable
fun Cabecalho1(){

    Card(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(24, 104, 238, 255)
        )


        ){
        Row (
            modifier = Modifier.fillMaxWidth().height(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {


            Row(
                modifier = Modifier.width(80.dp).padding(8.dp).height(80.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){


                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Vidas",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Red
                )


                Spacer(modifier = Modifier.width(5.dp))


                Text(
                    "5",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }


            Spacer(modifier = Modifier.width(10.dp))




            Row(
                modifier = Modifier.padding(8.dp).height(80.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){


                Icon(
                    painter = painterResource(id = R.drawable.ic_bitcoin),
                    contentDescription = "Moedas",
                    modifier = Modifier.size(30.dp),
                    tint = Color(211,175,55),


                    )


                Spacer(modifier = Modifier.width(5.dp))


                Text(
                    "800",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }
    }
}

//@Preview
@Composable
fun Linguagem() {
    Card(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(24, 104, 238, 255)
        )


        ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(50.dp).padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){


                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )


                Spacer(modifier = Modifier.width(130.dp))


                Text(
                    "Kotlin",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White


                    )


            }
        }
    }
}

@Composable
fun Titulo(){
    Card(
        modifier = Modifier.width(350.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(15,82,186)
        )
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ){

            Text(
                "Noções Básicas",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

        }
    }
}

//@Preview
@Composable
fun Exercicios(){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color(15,82,186)
        )
    ){
        Row(
            modifier = Modifier.padding(horizontal = 20.dp).height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){

            Text(
                "Funções",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(15,82,186),
                    contentColor = Color.White,
                )

            ) {

                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "Acessar Exercicio",
                    modifier = Modifier.size(40.dp)
                )

            }


        }


    }

}


//Preview
@Composable
fun Rodape(){

    Surface(
        color = Color(15,82,186)
    ){

        Row (
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BotaoChapeu()
            Spacer(modifier = Modifier.weight(4f))
            BotaoNotas()
            Spacer(modifier = Modifier.weight(4f))
            BotaoMedalha()
            Spacer(modifier = Modifier.weight(4f))
            BotaoPerfil()

        }

    }
}

//@Preview
@Composable
fun BotaoChapeu(){
    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(15,82,186),
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.graduate),
            contentDescription = "Chapéu",
            modifier = Modifier.size(40.dp)
        )

    }
}

//@Preview
@Composable
fun BotaoMedalha(){
    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(15,82,186),
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.medalha),
            contentDescription = "Medalha",
            modifier = Modifier.size(40.dp)
        )

    }
}

//@Preview
@Composable
fun BotaoPerfil(){
    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(15,82,186),
            contentColor = Color.White
        )
    ) {
        Icon(
            Icons.Default.Person,
            contentDescription = "Perfil",
            modifier = Modifier.size(40.dp)
        )

    }
}

//@Preview
@Composable
fun BotaoNotas(){
    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(15,82,186),
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_notas),
            contentDescription = "Perfil",
            modifier = Modifier.size(40.dp)
        )

    }
}

