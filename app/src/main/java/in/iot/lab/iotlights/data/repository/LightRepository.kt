package `in`.iot.lab.iotlights.data.repository

import `in`.iot.lab.iotlights.data.data_source.remote.LightApiInstance
import `in`.iot.lab.iotlights.data.model.LightPostData
import `in`.iot.lab.iotlights.util.LightApiState

/**
 * This is the Light Repository Class which is responsible for fetching the data and return it to the
 * viewModel
 *
 * @property postLightData This function posts The light status which we want the lab to have
 * @property getLightData This function gets the Current Light Data in the Database
 */
class LightRepository {

    // This function posts The light status which we want the lab to have
    suspend fun postLightData(postData: LightPostData): LightApiState {

        // Posting the New Data to the Database
        val response = LightApiInstance.lightApi.postLightRequest(
            lightPostData = postData
        )

        // Returning the Light Api State according to the Request Status
        return if (response.isSuccessful)
            LightApiState.Success(response.body()!!)
        else
            LightApiState.Failure(errorMessage = "Error Connecting to the Server")
    }

    // This function gets the Current Light Data in the Database
    suspend fun getLightData(): LightApiState {

        // Fetching the Data to the Database
        val response = LightApiInstance.lightApi.getLightRequest()

        // Returning the Light Api State according to the Request Status
        return if (response.isSuccessful)
            LightApiState.Success(response.body()!!)
        else
            LightApiState.Failure(errorMessage = "Error Connecting to the Server")
    }
}