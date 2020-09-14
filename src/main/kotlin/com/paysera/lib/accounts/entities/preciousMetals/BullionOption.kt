package com.paysera.lib.accounts.entities.preciousMetals

import org.joda.money.Money

data class BullionOption(
    val identifier: String,
    val type: String,
    val photos: List<String>,
    val weights: List<BullionOptionWeight>,
    val fineness: Double,
    val dimensions: String,
    val diameter: Double,
    val edge: Double,
    val maker: String,
    val countryOfOrigin: String,
    val preciousMetalType: String,
    val purchaseAmount: Money,
    val availableForPurchase: Boolean
) {

    enum class BullionOptionType(val value: String) {
        BAR("bar"),
        COIN("coin")
    }
}