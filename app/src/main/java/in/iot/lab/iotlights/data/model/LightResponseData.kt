package `in`.iot.lab.iotlights.data.model

/**
 * This is the Light Response Data structure which is returned by the Server as Response
 *
 * @param data This contains the data related to the Light Entry which is also defined in the
 * [LightData] Class
 */
data class LightResponseData(
    val data: LightData
)