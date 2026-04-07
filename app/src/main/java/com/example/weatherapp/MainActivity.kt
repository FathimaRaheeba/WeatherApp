package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppUI()
        }
    }
}

@Composable
fun WeatherAppUI() {

    // 🔥 STATE
    var city by remember { mutableStateOf("Kochi") }
    var temperature by remember { mutableStateOf("Loading...") }
    var condition by remember { mutableStateOf("Loading...") }
    var inputCity by remember { mutableStateOf("") } // for TextField

    // 🌐 API CALL
    LaunchedEffect(city) {
        try {
            val response = RetrofitInstance.api.getWeather(
                city = city,
                apiKey = "58ba5813c4aba773e34e0610a839861f"
            )

            temperature = "${response.main.temp}°C"
            condition = response.weather[0].description

        } catch (e: Exception) {
            temperature = "Error"
            condition = "Failed to load"
        }
    }

    // 🎨 UI
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF4FACFE),
            Color(0xFF00F2FE)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // 🔍 TextField
            OutlinedTextField(
                value = inputCity,
                onValueChange = { inputCity = it },
                label = { Text("Enter City") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 🔘 Search Button
            Button(
                onClick = {
                    if (inputCity.isNotEmpty()) {
                        city = inputCity
                    }
                }
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 🌍 City
            Text(
                text = city,
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🌡️ Temperature
            Text(
                text = temperature,
                fontSize = 64.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            // ☀️ Condition
            Text(
                text = condition,
                fontSize = 22.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 🧾 Card
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.2f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    WeatherInfoItem("Humidity", "70%")
                    WeatherInfoItem("Wind", "12 km/h")
                    WeatherInfoItem("Feels Like", "32°C")
                }
            }
        }
    }
}

@Composable
fun WeatherInfoItem(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 14.sp
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}