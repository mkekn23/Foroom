package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible

class ChangeLanguagePage {
    private val languageButtonGeo = withId(R.id.languageButtonGeo)
    private val languageButtonEng = withId(R.id.languageButtonEng)

    fun verifyPageLoaded(): ChangeLanguagePage {
        onView(languageButtonGeo).waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        onView(languageButtonEng).check(matches(isDisplayed()))
        return this
    }

    fun selectGeorgian() {
        onView(languageButtonGeo).tap(WAIT_SEC)
    }

    fun selectEnglish() {
        onView(languageButtonEng).tap(WAIT_SEC)
    }

    private companion object {
        const val WAIT_SEC = 10L
    }
}
