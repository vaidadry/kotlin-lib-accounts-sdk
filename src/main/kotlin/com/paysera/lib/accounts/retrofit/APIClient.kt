package com.paysera.lib.accounts.retrofit

import com.paysera.lib.accounts.entities.Balance
import com.paysera.lib.accounts.entities.IbanInformation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient {

    @GET("account/rest/v1/accounts/{accountNumber}/full-balance")
    fun getFullBalances(@Path("accountNumber") accountNumber: String): Observable<List<Balance>>

    @GET("transfer/rest/v1/swift/{iban}")
    fun getIbanInformation(@Path("iban") iban: String): Observable<IbanInformation>
}