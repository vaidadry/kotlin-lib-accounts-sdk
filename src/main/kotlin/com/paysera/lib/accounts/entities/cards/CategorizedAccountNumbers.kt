package com.paysera.lib.accounts.entities.cards

data class CategorizedAccountNumbers(
    val category: String,
    val items: List<String>
)