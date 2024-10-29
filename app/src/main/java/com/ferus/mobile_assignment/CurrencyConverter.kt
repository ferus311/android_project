package com.ferus.mobile_assignment

object CurrencyConverter {
    private val conversionRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "JPY" to 110.0,
        "GBP" to 0.75,
        "AUD" to 1.35,
        "CAD" to 1.25,
        "CHF" to 0.92,
        "CNY" to 6.45,
        "SEK" to 8.6,
        "NZD" to 1.4
    )

    fun convert(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val fromRate = conversionRates[fromCurrency] ?: return 0.0
        val toRate = conversionRates[toCurrency] ?: return 0.0
        return amount * (toRate / fromRate)
    }
}