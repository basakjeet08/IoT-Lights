package `in`.iot.lab.iotlights.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.iot.lab.iotlights.R
import `in`.iot.lab.iotlights.core.theme.IoTLightsTheme

// Preview Function For Both Light and Dark Mode of the App
@Preview("Light")
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DefaultPreview() {
    IoTLightsTheme {
        ImageAndTextUI(
            R.drawable.bulb_glowing,
            R.string.tap_on_bulb_to_turn_off_the_light
        ) {}
    }
}

@Composable
fun ImageAndTextUI(
    image: Int,
    textToShow: Int,
    getLightData: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Image of the Bulb
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .clickable {
                    getLightData()
                }
        )

        // Text
        Text(
            text = stringResource(id = textToShow),
            letterSpacing = 1.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}