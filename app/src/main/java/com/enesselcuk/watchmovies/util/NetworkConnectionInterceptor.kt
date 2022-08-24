package com.enesselcuk.watchmovies.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import kotlin.jvm.Throws

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            throw NoConnectionException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectionException : IOException() {
        override val message: String?
            get() = super.message ?: "No Internet Connection"
    }
}