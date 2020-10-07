package com.github.michalchojnacki.randomcityapp.data

import javax.inject.Inject

internal class ColorNameToHexMapper @Inject constructor() {

    fun map(colorName: String): String = when (colorName) {
        "Yellow" -> "#FFFF00"
        "Green" -> "#00FF00"
        "Blue" -> "#0000FF"
        "Red" -> "#FF0000"
        "Black" -> "#000000"
        "White" -> "#FFFFFF"
        else -> "#00000000"
    }
}