package com.example.newsapp // Asegúrate de que este sea el nombre de tu paquete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
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

        // 3. Últimas noticias (Carrusel Horizontal)
        Text(
            text = "Ultimas noticias",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyRow es para hacer scroll hacia los lados
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(3) { // Le decimos que dibuje 3 tarjetas de ejemplo
                Box(
                    modifier = Modifier
                        .width(260.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(24.dp)) // Bordes redondeados de la tarjeta
                        .background(Color(0xFF5C5CFF)) // El color azul de la foto
                        .padding(20.dp)
                ) {
                    // Usamos un Column adentro para empujar el texto hacia abajo
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = "El presidente de EE.UU. no muestra signos de arrepentimiento...",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            lineHeight = 24.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "febrero 08 - 2024",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

// 4. Título de Alrededor del mundo
        Text(
            text = "Alrededor del mundo",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Preparamos los datos de las noticias con sus fotos de internet
        val noticiasMundo = listOf(
            Pair("El presidente de EE.UU. no muestra signos de...", "https://images.unsplash.com/photo-1508433957232-3107f5fd5995?q=80&w=886&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            Pair("Bañarse en la piscina del desierto de Cleopatra", "https://images.unsplash.com/photo-1705939924260-70e1c3ba4c63?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            Pair("Gigantes tecnológicos", "https://images.unsplash.com/photo-1620712943543-bcc4688e7485?q=80&w=400&auto=format&fit=crop"),
            Pair("El rover de Marte envía", "https://images.unsplash.com/photo-1614730321146-b6fa6a46bcb4?q=80&w=400&auto=format&fit=crop")
        )

        // Cuadrícula de 2 columnas
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Recorremos nuestra lista de noticias
            items(noticiasMundo.size) { index ->
                val noticia = noticiasMundo[index]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(24.dp))
                ) {
                    AsyncImage(
                        model = noticia.second, // La URL de la foto
                        contentDescription = "Foto de noticia",
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop, // Recorta la foto para que llene el espacio sin aplastarse
                        modifier = Modifier.fillMaxSize()
                    )

                    // 2. La caja de texto gris encima
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                            .background(Color(0xFFD9D9D9))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = noticia.first, // El texto de la noticia
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsAppPreview() {
    NewsAppTheme {
        NewsAppScreen()
    }
}