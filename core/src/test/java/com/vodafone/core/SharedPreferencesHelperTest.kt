package com.vodafone.core

import android.content.Context
import android.content.SharedPreferences
import com.vodafone.core.utils.SharedPreferencesHelper
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class SharedPreferencesHelperTest {
    @Test
    fun `saveSearchedCity and getSearchedCity work correctly`() {
        val context = mockk<Context>()
        val sharedPreferences = mockk<SharedPreferences>()
        val editor = mockk<SharedPreferences.Editor>()

        every { context.getSharedPreferences(any(), any()) } returns sharedPreferences
        every { sharedPreferences.edit() } returns editor
        every { editor.putString(any(), any()) } returns editor
        every { editor.apply() } returns Unit
        every { sharedPreferences.getString("cityName", null) } returns "London"

        val helper = SharedPreferencesHelper(context)
        helper.saveSearchedCity("London")

        val city = helper.getSearchedCity()
        assertEquals("London", city)
    }
}