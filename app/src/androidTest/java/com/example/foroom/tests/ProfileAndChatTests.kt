package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

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
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Test
    fun changePasswordTest() {
        loginSteps.verifyLoginScreenIsDisplayed()
        loginSteps.login(EXISTING_USERNAME, CURRENT_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()

        profileSteps.navigateToProfileSection()
        profileSteps.changePassword(NEW_PASSWORD)

        loginSteps.verifyLoginScreenIsDisplayed()
        loginSteps.login(EXISTING_USERNAME, NEW_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()
        //revert
        profileSteps.navigateToProfileSection()
        profileSteps.changePassword(CURRENT_PASSWORD)
        loginSteps.verifyLoginScreenIsDisplayed()
    }

    @Test
    fun changeLanguageTest() {
        loginSteps.verifyLoginScreenIsDisplayed()
        loginSteps.login(EXISTING_USERNAME, CURRENT_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()
        profileSteps.navigateToProfileSection()

        profileSteps.changeLanguageToGeorgian()
        profileSteps.verifyProfileIsGeorgian()

        profileSteps.changeLanguageToEnglish()
        profileSteps.verifyProfileIsEnglish()

        profileSteps.changeLanguageToGeorgian()
        profileSteps.verifyProfileIsGeorgian()
    }

    @Test
    fun createChatAndFindItInChatListTest() {
        loginSteps.verifyLoginScreenIsDisplayed()
        loginSteps.login(EXISTING_USERNAME, CURRENT_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()

        val chatName = CHAT_NAME + System.currentTimeMillis()

        chatSteps.createChat(chatName)
        chatSteps.verifyCreatedChatDetailsAndClose(chatName)
        chatSteps.searchAndVerifyChatInList(chatName)
    }

    private companion object {
        const val EXISTING_USERNAME = "mariam"
        const val CURRENT_PASSWORD = "password1234"
        const val NEW_PASSWORD = "newpassword1234!"
        const val CHAT_NAME = "MariamQeqnadze"
    }
}
