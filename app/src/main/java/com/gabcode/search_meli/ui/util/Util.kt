package com.gabcode.search_meli.ui.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun formatDecimal(value: Double): String {
    val symbols = DecimalFormatSymbols(Locale("es", "AR"))
    symbols.decimalSeparator = ','
    symbols.groupingSeparator = '.'

    val decimalFormat = DecimalFormat("#,###.##", symbols)
    decimalFormat.roundingMode = RoundingMode.CEILING

    return decimalFormat.format(value)
}