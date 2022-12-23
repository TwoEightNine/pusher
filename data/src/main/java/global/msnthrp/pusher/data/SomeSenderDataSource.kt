package global.msnthrp.pusher.data

import global.msnthrp.pusher.domain.entity.RemoteMessage
import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.messaging.SenderDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

class SomeSenderDataSource : SenderDataSource {

    private val retrofitService: RetrofitService =
        Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/fcm/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build())
            .build()
            .create(RetrofitService::class.java)

    override suspend fun sendMessage(user: User, message: RemoteMessage) {
        val key = "key=${BuildConfig.FCM_SERVER_KEY}"
        retrofitService.postRemoteMessage(
            key,
            OngoingMessage(to = user.token, data = message)
        )
    }

    interface RetrofitService {

        @POST("send")
        suspend fun postRemoteMessage(
            @Header("Authorization") key: String,
            @Body ongoingMessage: OngoingMessage
        ): Response<Any>
    }

    data class OngoingMessage(
        val to: String,
        val data: RemoteMessage
    )
}