package `in`.iot.lab.iotlights

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import `in`.iot.lab.iotlights.core.theme.IoTLightsTheme
import `in`.iot.lab.iotlights.presentation.screens.LandingScreen
import `in`.iot.lab.iotlights.presentation.stateholder.LightViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IoTLightsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // Light View Model variable
                    val myViewModel: LightViewModel = viewModel()

                    Box(
                        modifier =
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LandingScreen(myViewModel = myViewModel)
                    }
                }
            }
        }
    }
}