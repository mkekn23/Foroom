package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matchers.allOf

class ProfilePage {
    private val changePasswordItem = withId(R.id.changePasswordItem)
    private val changeLanguageItem = withId(R.id.changeLanguageItem)
    private val signOutItem = withId(R.id.signOutItem)

    fun verifyPageLoaded(): ProfilePage {
        onView(changePasswordItem).waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        return this
    }

    fun clickChangePassword() {
        onView(changePasswordItem).tap(WAIT_SEC)
    }

    fun clickChangeLanguage() {
        onView(changeLanguageItem).tap(WAIT_SEC)
    }

    fun clickSignOut() {
        onView(signOutItem).tap(WAIT_SEC)
    }

    // The list items are localized, so the label is awaited (the activity is recreated on a language change).
    fun verifyChangeLanguageLabel(label: String): ProfilePage {
        onView(allOf(withText(label), isDescendantOfA(changeLanguageItem)))
            .waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        return this
    }

    fun verifySignOutLabel(label: String): ProfilePage {
        onView(allOf(withText(label), isDescendantOfA(signOutItem)))
            .waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        return this
    }

    private companion object {
        const val WAIT_SEC = 10L
    }
}
