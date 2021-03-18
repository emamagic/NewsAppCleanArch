package com.example.data.network.safe

import android.content.Context
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import java.io.File
import java.util.concurrent.TimeUnit

class MyCache(val context: Context) {

    private val HEADER_CACHE_CONTROL = "Cache-Control"
    private val HEADER_PRAGMA = "Pragma"
    private val cacheSize = (5 * 1024 * 1024 ).toLong()

    private fun cache(): Cache {
        return Cache(File(context.cacheDir.path, "someIdentifier"), cacheSize)
    }

    /**
     * This interceptor will be called both if the network is available and if the network is not available
     * @return
     */
/*    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.e("TAG", "offline interceptor: called.")
            var request: Request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!hasNetwork()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }*/


    /**
     * This interceptor will be called ONLY if the network is available
     * @return
     */
    private fun netWorkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain?.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()

            response?.newBuilder()
                ?.removeHeader(HEADER_PRAGMA)
                ?.removeHeader(HEADER_CACHE_CONTROL)
                ?.header(HEADER_CACHE_CONTROL, cacheControl.toString())
                ?.build()
        }
    }



/*    private fun httpLoggingInterceptor(): HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e("TAG", "log: http log: $message")
            }
        })
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }*/

}