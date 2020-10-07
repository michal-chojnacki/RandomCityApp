package com.github.michalchojnacki.randomcityapp.data

import org.junit.Assert.*
import org.junit.Test

class ColorNameToHexMapperTest {

    private val tested = ColorNameToHexMapper()

    @Test
    fun `given Yellow colorName when map is called then result should be #FFFF00`() {
        // given
        val colorName = "Yellow"

        // when
        val result = tested.map(colorName)

        // then
        assertEquals("#FFFF00", result)
    }

    @Test
    fun `given Green colorName when map is called then result should be #00FF00`() {
        // given
        val colorName = "Green"

        // when
        val result = tested.map(colorName)

        // then
        assertEquals("#00FF00", result)
    }

    @Test
    fun `given Blue colorName when map is called then result should be #0000FF`() {
        // given
        val colorName = "Blue"

        // when
        val result = tested.map(colorName)

        // then
        assertEquals("#0000FF", result)
    }

    @Test
    fun `given Red colorName when map is called then result should be #FF0000`() {
        // given
        val colorName = "Red"

        // when
        val result = tested.map(colorName)

        // then
        assertEquals("#FF0000", result)
    }

    @Test
    fun `given Black colorName when map is called then result should be #000000`() {
        // given
        val colorName = "Black"

        // when
        val result = tested.map(colorName)

        // then
        assertEquals("#000000", result)
    }

    @Test
    fun `given White colorName when map is called then result should be #FFFFFF`() {
        // given
        val colorName = "White"

        // when
        val result = tested.map(colorName)

        // then
        assertEquals("#FFFFFF", result)
    }

    @Test
    fun `given Not defined colorName when map is called then result should be #00000000`() {
        // given
        val colorName = "Not defined"

        // when
        val result = tested.map(colorName)

        // then
        assertEquals("#00000000", result)
    }
}