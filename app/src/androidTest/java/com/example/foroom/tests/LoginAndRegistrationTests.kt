package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {
    private val clearSession = object : ExternalResource() {
        override fun before() = clearUserData()
        override fun after() = clearUserData()

        private fun clearUserData() = runBlocking {
            GlobalContext.get().get<ForoomUserDataStore>().clearUserData()
        }
    }

    private val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(clearSession).around(activityRule)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun validUsernameAndInvalidPassword_showsPasswordError() {
        loginSteps.verifyLoginScreenIsDisplayed()

        loginSteps.login(EXISTING_USERNAME, WRONG_PASSWORD)

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
            username = "user_${System.currentTimeMillis()}",
            password = VALID_PASSWORD,
            avatarPosition = AVATAR_POSITION
        )

        registrationSteps.verifyHomeScreenIsDisplayed()
    }

    private companion object {
        const val EXISTING_USERNAME = "student"
        const val NON_EXISTING_USERNAME = "no_such_user_9f3ahjbh7c"
        const val WRONG_PASSWORD = "WrongPassword123!"
        const val VALID_PASSWORD = "Test1234!"
        const val AVATAR_POSITION = 1
    }
}
