package com.paysera.lib.accounts.entities.clientAllowances

import com.google.gson.annotations.SerializedName

data class PSClientAllowances(
    @SerializedName("covenantee_id")
    val userId: Int,
    val canOrderCard: Boolean,
    val canFillQuestionnaire: Boolean,
    val canCreateAccount: Boolean,
    @SerializedName("can_create_authorization")
    val accountAuthorizationAllowances: List<PSAccountAuthorizationAllowance>
)