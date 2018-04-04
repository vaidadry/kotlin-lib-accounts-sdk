package com.paysera.lib.accounts.retrofit

import com.google.gson.GsonBuilder
import com.paysera.lib.accounts.deserializers.BalanceDeserializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.reflect.TypeToken
import com.paysera.lib.accounts.entities.AccountsApiCredentials
import com.paysera.lib.accounts.entities.Balance
import io.reactivex.schedulers.Schedulers

class RetrofitConfigurator(private val accountsApiCredentials: AccountsApiCredentials) {

        fun createRetrofit(baseUrl: String = "https://accounts.paysera.com/public/") = with(Retrofit.Builder()) {
            baseUrl(baseUrl)
            addConverterFactory(createGsonConverterFactory())
            addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            client(createOkHttpClient())
            build()
        }

        private fun createOkHttpClient() = with(OkHttpClient().newBuilder()) {
            addInterceptor { chain ->
                val originalRequest = chain.request()
                val builder = originalRequest.newBuilder().header("Authorization", "Bearer ${accountsApiCredentials.accessToken}")
                val modifiedRequest = builder.build()
                chain.proceed(modifiedRequest)
            }
            build()
        }

        private fun createGsonConverterFactory(): GsonConverterFactory {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
            val balancesType = TypeToken.getParameterized(List::class.java, Balance::class.java).type
            gsonBuilder.registerTypeAdapter(balancesType, BalanceDeserializer())
            return GsonConverterFactory.create(gsonBuilder.create())
        }
    }