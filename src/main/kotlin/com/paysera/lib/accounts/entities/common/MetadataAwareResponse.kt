package com.paysera.lib.accounts.entities.common

import com.google.gson.annotations.SerializedName

class MetadataAwareResponse <T> (
    @SerializedName("items", alternate = ["card_designs"])
    val items: List<T>,
    @SerializedName("_metadata")
    val metadata: Metadata
)