package com.example.foroom.steps

import com.example.foroom.pages.LoginPage

class LoginSteps {

    private val loginPage = LoginPage()

    fun verifyLoginScreenIsDisplayed() {
        loginPage.checkIsDisplayed()
    }

    fun login(username: String, password: String) {
        loginPage.enterUsername(username)
        loginPage.enterPassword(password)
        loginPage.clickLogIn()
    }

    fun openRegistration() {
        loginPage.clickSignUp()
    }

    fun verifyUsernameError() {
        loginPage.checkUsernameErrorDisplayed()
    }

    fun verifyPasswordError() {
        loginPage.checkPasswordErrorDisplayed()
    }
}
