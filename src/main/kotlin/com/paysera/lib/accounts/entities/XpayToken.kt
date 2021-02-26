package com.paysera.lib.accounts.entities

import com.google.gson.annotations.SerializedName

data class XpayToken(
	@SerializedName("encrypted_data")
	val encryptedData: String
)
