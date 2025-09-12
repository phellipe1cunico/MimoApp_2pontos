package com.example.mimoapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun Cabecalho1(){


    Card(
        modifier = Modifier.fillMaxWidth().height(50.dp),


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
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }


            Spacer(modifier = Modifier.width(10.dp))




            Row(
                modifier = Modifier.width(80.dp).padding(8.dp).height(80.dp),
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
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }
        }
    }
}


@Preview
@Composable
fun Linguagem() {
    Card(
        modifier = Modifier.fillMaxWidth().height(50.dp),


        ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(50.dp).padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){


                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    modifier = Modifier.size(30.dp)


                )


                Spacer(modifier = Modifier.width(130.dp))


                Text(
                    "Python",
                    style = MaterialTheme.typography.bodyLarge,


                    )


            }
        }
    }
}


@Preview
@Composable
fun Rodape(){


    Card(
        modifier = Modifier.fillMaxWidth().height(50.dp)
    ){
        Column (
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Button(
                onClick = {


                }
            ) {


            }
        }


    }
}

