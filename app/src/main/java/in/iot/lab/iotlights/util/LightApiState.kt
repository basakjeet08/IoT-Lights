package `in`.iot.lab.iotlights.util

import `in`.iot.lab.iotlights.data.model.LightResponseData

/**
 * This Light Status api state holds all the Api State of the Light Api Calls
 *
 * @property Initialized This is the initialized state when the state is initialized at first
 * @property Loading This is the state when the Data is actually getting fetched from the Internet
 * @property Success This is the state which contains the data when the data is fetched from the
 * server successfully
 * @property Failure This is the state which contains the error Message when the data isn't fetched
 * from the Internet and we get Some Exceptions
 */

sealed class LightApiState {
    object Initialized : LightApiState()
    object Loading : LightApiState()
    class Success(val lightResponseData: LightResponseData) : LightApiState()
    class Failure(val errorMessage: String) : LightApiState()
}