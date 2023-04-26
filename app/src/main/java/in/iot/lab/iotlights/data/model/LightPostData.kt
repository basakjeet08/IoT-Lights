package `in`.iot.lab.iotlights.data.model

/**
 * This is the structure of the Light post Data which is used to post Light Response to the Server
 *
 * @param request This is the light status of the Lab which we want to push
 */
data class LightPostData(
    val request: Int
)