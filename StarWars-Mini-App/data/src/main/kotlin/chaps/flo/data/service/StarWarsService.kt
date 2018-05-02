package chaps.flo.data.service

import chaps.flo.data.BuildConfig
import chaps.flo.data.model.api.people.PeopleAPI
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsService {

    @GET("people/")
    fun getPeople(@Query("page") page: Int?): Single<PeopleAPI>

    companion object {
        fun createService(): Single<StarWarsService> {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

            return Single.create<StarWarsService> {
                val retrofit = Retrofit.Builder()
                        .client(client)
                        .baseUrl(BuildConfig.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                it.onSuccess(retrofit.create(StarWarsService::class.java))
            }
        }
    }
}