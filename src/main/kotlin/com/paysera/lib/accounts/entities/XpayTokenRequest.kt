package com.paysera.lib.accounts.entities

import com.google.gson.annotations.SerializedName

data class XpayTokenRequest(
	@SerializedName("app_id")
	val appId: String,
	@SerializedName("card_id")
	val cardId: Int,
	@SerializedName("device_id")
	val deviceId: String,
	@SerializedName("token_requestor_code")
	val tokenRequestorCode: String,
	@SerializedName("wallet_account_id")
	val walletAccountId: String
)
