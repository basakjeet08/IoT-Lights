package `in`.iot.lab.iotlights.data.data_source.remote

import `in`.iot.lab.iotlights.core.util.Constants
import `in`.iot.lab.iotlights.data.model.LightPostData
import `in`.iot.lab.iotlights.data.model.LightResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

/**
 * This is the retrofit Api Interface which is implemented by the retrofit Class itself
 *
 * @property postLightRequest This function posts the Light Response to the Database
 * @property getLightRequest This function gets the current Light Status from the Database
 */
interface LightApiInterface {

    // This function posts the Light Response to the Database
    @PATCH(Constants.LIGHTS_ENDPOINT)
    suspend fun postLightRequest(@Body lightPostData: LightPostData): Response<LightResponseData>

    // This function gets the current Light Status from the Database
    @GET(Constants.LIGHTS_ENDPOINT)
    suspend fun getLightRequest(): Response<LightResponseData>

}