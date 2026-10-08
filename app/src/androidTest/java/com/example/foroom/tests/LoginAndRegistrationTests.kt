package com.example.foroom.tests

import com.example.foroom.data.Constants.AVATAR_POSITION
import com.example.foroom.data.Constants.DEMO_USERNAME
import com.example.foroom.data.Constants.NON_EXISTING_USERNAME
import com.example.foroom.data.Constants.REGISTRATION_PASSWORD
import com.example.foroom.data.Constants.REGISTRATION_USERNAME_PREFIX
import com.example.foroom.data.Constants.WRONG_PASSWORD
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Test

class LoginAndRegistrationTests : BaseTest() {
    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun validUsernameAndInvalidPassword_showsPasswordError() {
        loginSteps.verifyLoginScreenIsDisplayed()

        loginSteps.login(DEMO_USERNAME, WRONG_PASSWORD)

        loginSteps.verifyPasswordError()
    }

    @Test
    fun invalidUsernameAndInvalidPassword_showsUsernameAndPasswordErrors() {
        loginSteps.verifyLoginScreenIsDisplayed()

        loginSteps.login(NON_EXISTING_USERNAME, WRONG_PASSWORD)

        loginSteps.verifyUsernameError()
        loginSteps.verifyPasswordError()
    }

    @Test
    fun successfulRegistration_opensHomeScreen() {
        loginSteps.verifyLoginScreenIsDisplayed()

        loginSteps.openRegistration()
        registrationSteps.verifyRegistrationScreenIsDisplayed()

        registrationSteps.register(
            username = REGISTRATION_USERNAME_PREFIX + System.currentTimeMillis(),
            password = REGISTRATION_PASSWORD,
            avatarPosition = AVATAR_POSITION
        )

        registrationSteps.verifyHomeScreenIsDisplayed()
    }
}
