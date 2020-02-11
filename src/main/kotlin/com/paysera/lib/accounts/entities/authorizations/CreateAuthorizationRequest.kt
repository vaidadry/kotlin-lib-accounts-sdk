package com.paysera.lib.accounts.entities.authorizations

data class CreateAuthorizationRequest(
    var accountNumber: String,
    var userIds: List<Int>,
    var readPermission: Boolean,
    var writePermission: Boolean,
    var signPermission: SignPermission?
)