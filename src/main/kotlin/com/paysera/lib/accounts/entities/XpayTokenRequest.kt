package com.paysera.lib.accounts.entities

data class XpayTokenRequest(
	val appId: String,
	val cardId: Int,
	val deviceId: String,
	val tokenRequestorCode: String,
	val walletAccountId: String
)
