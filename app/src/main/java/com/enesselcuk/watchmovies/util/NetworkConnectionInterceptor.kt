package com.enesselcuk.watchmovies.util

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import kotlin.jvm.Throws

class NetworkConnectionInterceptor(
    private val context: Context,

    ) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            throw NoConnectionException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectionException : IOException() {
        override val message: String
            get() = super.message ?: "No Internet Connection"
    }
}
//
//class NetworkConnectionInterceptor(
//    private val context: Context,
//) : Interceptor {
//
//    var tokenAccess: String? = "9b7b05d4b49cf93725c0221d6c96a5a322beba9b";
//
////    fun token(token: String) {
////        this.tokenAccess = token;
////    }
//
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        if (!NetworkUtils.isNetworkAvailable(context)) {
//            throw NoConnectionException()
//        }
//var tokenAccess: String? = "9b7b05d4b49cf93725c0221d6c96a5a322beba9b";
//var request = chain.request()
//
//if (!tokenAccess.isNullOrEmpty()) {
//    request = request.newBuilder()
//        .get()
//        .header("content-type", "application/json;charset=utf-8")
//        .header("authorization", "Bearer 4b6b6e20d1eda9c9e69298fb92d01318")
//        .build()
//}
//
//
//        Log.i("authorization", "$tokenAccess")
//
//        return chain.proceed(request)
//    }
//
//    inner class NoConnectionException : IOException() {
//        override val message: String
//            get() = super.message ?: "No Internet Connection"
//    }
//}


//val tokenResponse = service.tokenPost()
//            val token = tokenResponse.body()?.accessToken
//            val response = service.getUniversity(token = "Bearer $token")
//            if (response.code() == 401) {
//                val newRequest = service.tokenPost()
//                val newToken = newRequest.body()?.accessToken
//                val refreshResponse = service.getUniversity(token = "Bearer $newToken")
//                if (refreshResponse.isSuccessful) {
//                    response.body()?.let {
//                        emit(Resource.Success(it))
//                    }
//                }
//            }