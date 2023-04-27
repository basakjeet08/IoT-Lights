package `in`.iot.lab.iotlights

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import `in`.iot.lab.iotlights.core.theme.IoTLightsTheme
import `in`.iot.lab.iotlights.presentation.components.TextButtonUI
import `in`.iot.lab.iotlights.presentation.stateholder.LightViewModel
import `in`.iot.lab.iotlights.util.LightApiState

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

                    // Stating the State accordingly
                    when (myViewModel.lightGetApiState) {
                        is LightApiState.Initialized -> {
                            myViewModel.getLightDataOnly()
                        }
                        is LightApiState.Loading -> {
                            LoadingScreen()
                        }
                        is LightApiState.Success -> {

                            val request =
                                (myViewModel.lightGetApiState as LightApiState.Success)
                                    .lightResponseData.data.request

                            // Checking if the Light is on or Off
                            if (request == 0)
                                ImageUI(image = R.drawable.bulb_not_glowing) {
                                    myViewModel.getLightData()
                                }
                            else
                                ImageUI(image = R.drawable.bulb_glowing) {
                                    myViewModel.getLightData()
                                }
                        }
                        else -> {
                            FailureScreen(R.string.failed_to_load_data_try_again) {
                                myViewModel.getLightDataOnly()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageUI(
    image: Int,
    getLightData: () -> Unit
) {
    //
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier.clickable {
            getLightData()
        }
    )

}

// This function is shown to show the Loading Screen in the UI
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

// This function shows a UI when the api call fails
@Composable
fun FailureScreen(
    textToShow: Int = R.string.failed_to_load_data_try_again,
    tryAgain: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(26.dp),
        contentAlignment = Alignment.Center
    ) {
        TextButtonUI(textToShow = textToShow) {
            tryAgain()
        }
    }
}