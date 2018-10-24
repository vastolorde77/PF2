package com.example.mf.pf2.utils

import org.junit.Assert.assertArrayEquals
import org.junit.Test
import org.mockito.Mockito.mock

class ChartGeneratorTest {
    private var  chartGenerator = ChartGenerator()

    @Test
    fun shouldGenerateSimpleLabels() {
        val expected = arrayOf("Week 1","Week 2","Week 3","Week 4","Week 5")
        val actual = chartGenerator.generateLabels(5,"Week")
        assertArrayEquals(expected,actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldFailWhenSizeIsTooSmall(){
        chartGenerator.generateLabels(0,"Week")
    }

}