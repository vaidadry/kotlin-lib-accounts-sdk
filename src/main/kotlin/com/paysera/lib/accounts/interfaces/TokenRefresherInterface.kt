package com.paysera.lib.accounts.interfaces

import io.reactivex.Observable

interface TokenRefresherInterface {
    fun refreshToken(): Observable<Any>
    fun isRefreshing(): Boolean
}