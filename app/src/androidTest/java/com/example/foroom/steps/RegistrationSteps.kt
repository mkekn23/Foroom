package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.imageChooserLoaded
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilMatches
import com.example.foroom.data.Constants.HOME_WAIT_SEC
import com.example.foroom.data.Constants.WAIT_SEC
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps(
    private val page: RegistrationPage = RegistrationPage(),
    private val homePage: ChatsPage = ChatsPage()
) {

    fun verifyRegistrationScreenIsDisplayed(): RegistrationSteps {
        waitUntil {
            onView(page.usernameInput).check(matches(isDisplayed()))
            onView(page.passwordInput).check(matches(isDisplayed()))
            onView(page.repeatPasswordInput).check(matches(isDisplayed()))
            onView(page.avatarList).check(matches(isDisplayed()))
            onView(page.signUpButton).check(matches(isDisplayed()))
        }
        return this
    }

    fun register(username: String, password: String, avatarPosition: Int): RegistrationSteps {
        onView(page.usernameInput).input(username)
        onView(page.passwordInput).input(password)
        onView(page.repeatPasswordInput).input(password)
        selectAvatar(avatarPosition)
        onView(page.signUpButton).tap(WAIT_SEC)
        return this
    }

    private fun selectAvatar(position: Int) {
        onView(page.avatarList).waitUntilMatches(imageChooserLoaded())
        onView(page.avatarItem(position)).tap(WAIT_SEC)
    }

    fun verifyHomeScreenIsDisplayed(): RegistrationSteps {
        waitUntil(HOME_WAIT_SEC) {
            onView(homePage.navBar).check(matches(isDisplayed()))
            onView(homePage.homeContainer).check(matches(isDisplayed()))
        }
        return this
    }
}
