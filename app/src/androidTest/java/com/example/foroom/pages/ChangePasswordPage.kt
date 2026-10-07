package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ChangePasswordPage {

    private fun inputChild(parentId: Int): Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(withId(parentId)))

    private val passwordInput = inputChild(R.id.passwordInput)
    private val repeatPasswordInput = inputChild(R.id.repeatPasswordInput)
    private val actionButton = withId(DesignR.id.actionButton)

    fun enterNewPassword(password: String): ChangePasswordPage {
        onView(passwordInput).input(password)
        return this
    }

    fun enterRepeatPassword(password: String): ChangePasswordPage {
        onView(repeatPasswordInput).input(password)
        return this
    }

    fun clickSubmit() {
        onView(actionButton).tap(WAIT_SEC)
    }

    fun verifyPageLoaded(): ChangePasswordPage {
        onView(actionButton).waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        return this
    }

    private companion object {
        const val WAIT_SEC = 10L
    }
}
