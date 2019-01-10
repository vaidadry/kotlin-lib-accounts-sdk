package com.paysera.lib.accounts.entities.transfers

class TransferNotification {
    companion object {
        val TYPE_DONE = "done"
    }

    var type: String? = null
    var email: String? = null
    var locale: String? = null

    constructor() {}

    constructor(type: String, email: String, locale: String) {
        this.type = type
        this.email = email
        this.locale = locale
    }
}
