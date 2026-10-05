package com.example.foroom.steps

import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {

    private val registrationPage = RegistrationPage()

    fun verifyRegistrationScreenIsDisplayed() {
        registrationPage.checkIsDisplayed()
    }

    fun register(username: String, password: String, avatarPosition: Int) {
        registrationPage.enterUsername(username)
        registrationPage.enterPassword(password)
        registrationPage.enterRepeatPassword(password)
        registrationPage.selectAvatar(avatarPosition)
        registrationPage.clickSignUp()
    }

    fun verifyHomeScreenIsDisplayed() {
        registrationPage.checkHomeScreenIsDisplayed()
    }
}
