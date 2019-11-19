package  com.kchernenko.spacex.injection.provider

import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kchernenko.spacex.SpaceXApp
import javax.inject.Inject
import okhttp3.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitServiceProvider @Inject constructor(private val baseUrl:String) {

    private lateinit var retrofitBuilder: Retrofit.Builder

    init {
        createServiceRetrofit()
    }

    fun <T> getServiceRetrofit(clazz: Class<T>): T {
        if (!::retrofitBuilder.isInitialized)
            createServiceRetrofit()
        return retrofitBuilder.build().create(clazz)
    }

    private fun createServiceRetrofit() {
        retrofitBuilder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(initHttpClient())
            .baseUrl(baseUrl)
    }

    private fun initHttpClient() = OkHttpClient
        .Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(initHeaderInterceptor())
      //  .addInterceptor(initChuckInterceptor())
        .build()

    private fun initHeaderInterceptor() = Interceptor {
        val original = it.request()

        val request = original.newBuilder()
            .method(original.method(), original.body())
            .build()
        it.proceed(request)
    }


    private fun initChuckInterceptor() = ChuckInterceptor(SpaceXApp.getInstance())
        .showNotification(true)
        .maxContentLength(7000)
        .retainDataFor(ChuckInterceptor.Period.ONE_DAY)

}








