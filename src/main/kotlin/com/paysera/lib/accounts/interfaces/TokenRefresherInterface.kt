package com.paysera.lib.accounts.interfaces

import io.reactivex.Single

interface TokenRefresherInterface {
    fun refreshToken(): Single<Any>
    fun isRefreshing(): Boolean
}