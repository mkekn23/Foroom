package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntil
import com.example.foroom.data.Constants.WAIT_SEC
import com.example.foroom.pages.LoginPage
import org.hamcrest.Matchers.not

class LoginSteps(
    private val page: LoginPage = LoginPage()
) {

    fun verifyLoginScreenIsDisplayed(): LoginSteps {
        waitUntil {
            onView(page.usernameInput).check(matches(isDisplayed()))
            onView(page.passwordInput).check(matches(isDisplayed()))
            onView(page.logInButton).check(matches(isDisplayed()))
            onView(page.signUpButton).check(matches(isDisplayed()))
        }
        return this
    }

    fun login(username: String, password: String): LoginSteps {
        onView(page.usernameInput).input(username)
        onView(page.passwordInput).input(password)
        onView(page.logInButton).tap(WAIT_SEC)
        return this
    }

    fun openRegistration(): LoginSteps {
        onView(page.signUpButton).tap(WAIT_SEC)
        return this
    }

    fun verifyUsernameError(): LoginSteps {
        waitUntil {
            onView(page.usernameError).check(matches(isDisplayed()))
            onView(page.usernameError).check(matches(not(withText(""))))
        }
        return this
    }

    fun verifyPasswordError(): LoginSteps {
        waitUntil {
            onView(page.passwordError).check(matches(isDisplayed()))
            onView(page.passwordError).check(matches(not(withText(""))))
        }
        return this
    }
}
