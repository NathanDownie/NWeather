package com.ndcoding.nweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ndcoding.nweather.ui.theme.NWeatherTheme
import com.ndcoding.nweather.ui.theme.Strings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherApp()
                }
            }
        }
    }
}

@Preview(name = "1", device = "spec:width=360dp,height=747dp", showSystemUi = true,
    showBackground = true, backgroundColor = 0xFFFFFFFF
)
@Composable
fun WeatherApp() {
    Column {
        WeatherAppHeader()
        CurrentLocation()
        CurrentWeather()
        AdditionalInformation()
    }
}

@Composable
fun WeatherAppHeader() {
    val primaryVariant = MaterialTheme.colorScheme.primaryContainer
    val transition = updateTransition(targetState = false, label = "")
    val scale by transition.animateFloat(label = "") { state ->
        if (state) 0.8f else 1f
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryVariant)
            .padding(0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = Strings.nweather, color = MaterialTheme.colorScheme.onPrimary)
        Row {
            IconButton(onClick = { /* TODO: Open settings */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = Strings.settings,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.scale(scale)
                )
            }
            IconButton(onClick = { /* TODO: Refresh weather data */ }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = Strings.refresh,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.scale(scale)
                )
            }
        }
    }
}

@Composable
fun CurrentLocation() {
    Text(
        text = Strings.current_location,
        modifier = Modifier.padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun CurrentWeather() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = Strings.sunny, fontSize = 24.sp, color = MaterialTheme.colorScheme.onBackground)
        Icon(
            painter = painterResource(id = R.drawable.ic_sunny),
            contentDescription = Strings.sunny,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "23°",
            fontSize = 64.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Composable
fun AdditionalInformation() {
    val primaryVariant = MaterialTheme.colorScheme.primaryContainer
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryVariant)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AdditionalInformationItem(
            label = Strings.humidity,
            modifier = Modifier.weight(1f)
        )
        AdditionalInformationItem(
            label = Strings.wind_speed,
            modifier = Modifier.weight(1f)
        )
        AdditionalInformationItem(
            label = Strings.pressure,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun AdditionalInformationItem(label: String, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


