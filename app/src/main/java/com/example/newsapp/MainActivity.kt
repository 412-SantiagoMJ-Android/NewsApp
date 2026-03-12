package com.example.newsapp // Asegúrate de que este sea el nombre de tu paquete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Pasamos el innerPadding para respetar la barra superior del cel
                    NewsAppScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NewsAppScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 16.dp)
    ) {

        // 1. Buscador (TopBar)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Buscar") },
            leadingIcon = {
                Icon(Icons.Default.Home, contentDescription = "Buscar")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Tabs (Noticias, Eventos, Clima)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tab seleccionada (Noticias)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Noticias",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                // La rayita azul debajo de Noticias
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(3.dp)
                        .background(Color(0xFF5C5CFF))
                )
            }

            // Tabs deshabilitadas
            Text(
                text = "Eventos",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.LightGray
            )
            Text(
                text = "Clima",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsAppPreview() {
    NewsAppTheme {
        NewsAppScreen()
    }
}