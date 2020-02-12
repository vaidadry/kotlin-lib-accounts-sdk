package com.paysera.lib.accounts.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.Balance
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.lang.reflect.Type

class BalanceDeserializer : JsonDeserializer<List<Balance>> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Balance> {
        val balances = arrayListOf<Balance>()
        json.asJsonObject.getAsJsonArray("balance").forEach {
            val jsonObject = it.asJsonObject
            val currency = CurrencyUnit.of(jsonObject.get("currency").asString)
            val atDisposalAmount = jsonObject.get("at_disposal").asJsonObject.get("amount").asBigDecimal
            val reservedAmount = jsonObject.get("reserved").asJsonObject.get("amount").asBigDecimal
            balances.add(
                Balance(
                    atDisposal = Money.of(currency, atDisposalAmount),
                    reserved = Money.of(currency, reservedAmount)
                )
            )
        }
        return balances
    }
}