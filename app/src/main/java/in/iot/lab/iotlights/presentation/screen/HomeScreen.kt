package `in`.iot.lab.iotlights.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import `in`.iot.lab.iotlights.R
import `in`.iot.lab.iotlights.presentation.components.TextButtonUI
import `in`.iot.lab.iotlights.presentation.stateholder.LightViewModel
import `in`.iot.lab.iotlights.util.LightApiState

@Composable
fun HomeScreen() {

    // View Model Variable
    val myViewModel: LightViewModel = viewModel()

    //
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = Modifier.clickable {
            myViewModel.getLightData()
        }
    )


    when (myViewModel.lightApiState) {
        is LightApiState.Initialized -> {}
        is LightApiState.Loading -> {
            LoadingScreen()
        }
        is LightApiState.Success -> {

            val request =
                (myViewModel.lightApiState as LightApiState.Success).lightResponseData.data.request

            SuccessScreen(request = request)
        }
        else -> {
            FailureScreen(R.string.failed_to_load_data_try_again) {
                myViewModel.getLightData()
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

// This is shown to show the Success Cards in the UI
@Composable
fun SuccessScreen(request: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = request.toString()
        )
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