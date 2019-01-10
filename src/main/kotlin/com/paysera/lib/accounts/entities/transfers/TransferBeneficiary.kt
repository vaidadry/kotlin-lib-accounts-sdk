package com.paysera.lib.accounts.entities.transfers

class TransferBeneficiary {
    var type: String? = null
    var name: String? = null
    var bankAccount: TransferBeneficiaryBankAccount? = null
    var webmoneyAccount: TransferBeneficiaryWebmoneyAccount? = null
    var payzaAccount: TransferBeneficiaryPayzaAccount? = null
    var taxAccount: TransferBeneficiaryTaxAccount? = null
    var payseraAccount: TransferBeneficiaryPayseraAccount? = null

    val isBankAccount = bankAccount != null
    val isWebmoneyAccount = webmoneyAccount != null
    val isPayzaAccount = payzaAccount != null
    val isTaxAccount = taxAccount != null
    val isPayseraAccount = payseraAccount != null
}


class TransferBeneficiaryBankAccount {
    var iban: String? = null
    var bic: String? = null
    var bankCode: String? = null
    var bankTitle: String? = null
    var sortCode: String? = null
    var accountNumber: String? = null
    var countryCode: String? = null
}

class TransferBeneficiaryBankAccountBankAddress {
    var countryCode: String? = null
}

class TransferBeneficiaryPayseraAccount {
    var phone: String? = null
    var email: String? = null
    var accountNumber: String? = null
    var userId: Int? = null
        private set

    fun setUserId(userId: Int) {
        this.userId = userId
    }
}

class TransferBeneficiaryPayzaAccount {
    var email: String? = null
}

class TransferBeneficiaryTaxAccount {
    var identifier: String? = null
}

class TransferBeneficiaryWebmoneyAccount {
    var purse: String? = null
}
