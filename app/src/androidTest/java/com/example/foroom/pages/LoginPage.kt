package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R as AppR
import com.example.design_system.R as DesignR
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class LoginPage {

    // The same child ids are reused inside every Input component, so each one is scoped to its parent.
    private fun inputChild(parentId: Int, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(withId(parentId)))

    private val usernameInput = inputChild(AppR.id.userNameInput, DesignR.id.inputEditText)
    private val passwordInput = inputChild(AppR.id.passwordInput, DesignR.id.inputEditText)
    private val usernameError = inputChild(AppR.id.userNameInput, DesignR.id.descriptionTextView)
    private val passwordError = inputChild(AppR.id.passwordInput, DesignR.id.descriptionTextView)
    private val logInButton = withId(AppR.id.logInButton)
    private val signUpButton = withId(AppR.id.signUpButton)

    fun enterUsername(username: String) {
        onView(usernameInput).input(username)
    }

    fun enterPassword(password: String) {
        onView(passwordInput).input(password)
    }

    fun clickLogIn() {
        onView(logInButton).tap(WAIT_SEC)
    }

    fun clickSignUp() {
        onView(signUpButton).tap(WAIT_SEC)
    }

    fun checkIsDisplayed() {
        onView(logInButton).waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        onView(signUpButton).check(matches(isDisplayed()))
        onView(usernameInput).check(matches(isDisplayed()))
        onView(passwordInput).check(matches(isDisplayed()))
    }

    // Login runs asynchronously, so the error text is awaited rather than asserted immediately.
    fun checkUsernameErrorDisplayed() {
        onView(usernameError).waitUntilVisible(WAIT_SEC).check(matches(not(withText(""))))
    }

    fun checkPasswordErrorDisplayed() {
        onView(passwordError).waitUntilVisible(WAIT_SEC).check(matches(not(withText(""))))
    }

    private companion object {
        const val WAIT_SEC = 10L
    }
}
