package `in`.iot.lab.iotlights.presentation.stateholder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.iot.lab.iotlights.data.model.LightPostData
import `in`.iot.lab.iotlights.data.repository.LightRepository
import `in`.iot.lab.iotlights.util.LightApiState
import kotlinx.coroutines.launch

/**
 * This is the LightViewModel class which handles the state of the UI and also is responsible for
 * the easy flow of data and state events between UI and repository Layer
 *
 * @property myRepository This is the Repository variable of the Class
 * @property lightApiState This is the Api Call State variable which holds the state and data
 *
 * @property postLightData This function asks the repository to post the Light Status to the Database
 * @property getLightData This function asks the repository to get the Light Status from the Database
 */
class LightViewModel : ViewModel() {

    // This is the Repository variable of the Class
    private val myRepository = LightRepository()

    var lightGetApiState: LightApiState by mutableStateOf(
        LightApiState.Initialized
    )
        private set

    init{
        getLightDataOnly()
    }

    // This function asks the repository to post the Light Status to the Database
    private fun postLightData(postData: LightPostData) {

        // Posting the Data to the Database
        viewModelScope.launch {

            lightGetApiState = try {

                // posting to the Server
                myRepository.postLightData(postData = postData)
            } catch (_: Exception) {
                LightApiState.Failure("Network Not Available")
            }
        }
    }

    // This function asks the repository to get the Light Status from the Database
    fun getLightData() {

        // Setting the current state of the Api State as Loading
        lightGetApiState = LightApiState.Loading

        // Fetching the Data from the Database
        viewModelScope.launch {

            try {

                // Fetching the data from the Repository
                val response = myRepository.getLightData()

                // Checking if the Light Api State is a success or a Failure
                if (response is LightApiState.Success) {

                    // Current Request/ Lab Lights status
                    val request = response.lightResponseData.data.request

                    // Posting the New data we want in the Database
                    postLightData(
                        LightPostData(
                            request = if (request == 1) 0 else 1
                        )
                    )
                } else {
                    lightGetApiState = LightApiState.Failure("Failed to fetch Data from the Server")
                }

            } catch (_: java.lang.Exception) {
                LightApiState.Failure("Network Not Available")
            }
        }
    }

    // This function asks the repository to get the Light Status from the Database
    fun getLightDataOnly() {

        // Setting the current state of the Api State as Loading
        lightGetApiState = LightApiState.Loading

        // Fetching the Data from the Database
        viewModelScope.launch {

            lightGetApiState = try {

                // Fetching the data from the Repository
                myRepository.getLightData()
            } catch (_: java.lang.Exception) {
                LightApiState.Failure("Network Not Available")
            }
        }
    }
}