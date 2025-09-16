package com.example.mimoapp

import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mimoapp.ui.theme.MimoAppTheme

class Leaderboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MimoAppTheme {
                TelaLeaderboard()

            }
        }
    }
}

@Preview
@Composable
fun TelaLeaderboard() {
    Scaffold { innerPadding ->

        Surface(
            color = Color(255, 250, 250),
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    color = Color(15, 82, 186)
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Rodape()
            }
        }
    }
}

@Preview
@Composable
fun CabecalhoLiga(){


    Card(

    ) {
        Column {

            Surface(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                color = Color(24, 104, 238, 255)

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().height(50.dp).padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        "Leaderboard",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )

                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth().height(5.dp),
                color = Color.Black
            ) {}

            Surface(
                modifier = Modifier.fillMaxWidth().height(150.dp),
                color = Color(24, 104, 238, 255)

            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 15.dp)
                ) {

                    Column {

                        Card(
                            modifier = Modifier.width(80.dp).padding(5.dp),
                            shape = ShapeDefaults.ExtraLarge,
                        ) {
                            Column (
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.codigo),
                                    contentDescription = "Code",
                                    modifier = Modifier.size(50.dp),
                                    tint = Color(113,54,0)
                                )

                            }

                        }

                        Text(
                            "Liga de Madeira",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }


                    Spacer(modifier = Modifier.weight(1f))

                    Card(
                        modifier = Modifier.width(80.dp).padding(5.dp),
                        shape = ShapeDefaults.ExtraLarge,
                    ) {
                        Column (
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_lock),
                                contentDescription = "Code",
                                modifier = Modifier.size(50.dp),
                                tint = Color(74,74,74)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Card(
                        modifier = Modifier.width(80.dp).padding(5.dp),
                        shape = ShapeDefaults.ExtraLarge,
                    ) {
                        Column (
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_lock),
                                contentDescription = "Code",
                                modifier = Modifier.size(50.dp),
                                tint = Color(211,175,55)
                            )
                        }
                    }



                }

            }

        }
    }
}