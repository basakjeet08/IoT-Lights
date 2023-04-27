package `in`.iot.lab.iotlights.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.iot.lab.iotlights.R
import `in`.iot.lab.iotlights.presentation.components.ImageAndTextUI
import `in`.iot.lab.iotlights.presentation.components.TextButtonUI
import `in`.iot.lab.iotlights.presentation.stateholder.LightViewModel
import `in`.iot.lab.iotlights.util.LightApiState

@Composable
fun LandingScreen(
    myViewModel: LightViewModel
) {

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
                ImageAndTextUI(
                    image = R.drawable.bulb_not_glowing,
                    textToShow = R.string.tap_on_bulb_to_turn_on_the_light
                ) {
                    myViewModel.getLightData()
                }
            else
                ImageAndTextUI(
                    image = R.drawable.bulb_glowing,
                    textToShow = R.string.tap_on_bulb_to_turn_off_the_light
                ) {
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