package com.example.data.network.safe

import com.example.commen.ApiWrapper
import com.example.commen.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException

abstract class SafeApi {
   // private val mutex = Mutex()
    private var flag: Boolean = false

    suspend fun <T> safeApi(call: suspend () -> Response<T>): ApiWrapper<T> {

/*        return if (!mutex.isLocked) mutex.withLock { apiTry { call.invoke() } }
        else   null*/
        return withContext(Dispatchers.IO) { apiTry { call.invoke() } }

    }


    private fun <T> handleResponse(response: Response<T>): ApiWrapper<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return ApiWrapper.Success(
                    data = it,
                    headers = response.headers(),
                    code = response.code()
                )
            }
        }
        return ApiWrapper.ApiError(
            error = Gson().fromJson(response.errorBody()?.string() , ErrorResponse::class.java),
            headers = response.headers()
        )
    }

    private suspend fun <T> apiTry(call: suspend () -> Response<T>): ApiWrapper<T> {
        return try {
            handleResponse(call.invoke())
        } catch (e: NoInternetException) {
            ApiWrapper.NetworkError(message = "${e.message}")
        } catch (e: UnknownHostException){
            ApiWrapper.NetworkError(message = "${e.message}")
        } catch (e: SocketException){
            ApiWrapper.NetworkError(message = "${e.message}")
        } catch (t: Throwable) {
            ApiWrapper.UnknownError(message = "${t.message}//${t.cause}")
/*            if (!flag) {
                flag = true
                delay(3000)
                apiTry(call)
            } else {
                flag = false
                ApiWrapper.UnknownError(message = "${t.message}//${t.cause}")
            }*/
        }
    }
}