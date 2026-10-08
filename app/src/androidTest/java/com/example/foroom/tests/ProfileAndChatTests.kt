package com.example.foroom.tests

import com.example.foroom.data.Constants.CHAT_NAME_PREFIX
import com.example.foroom.data.Constants.NEW_PASSWORD
import com.example.foroom.data.Constants.USERA
import com.example.foroom.data.Constants.USERA_PASSWORD
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Test

class ProfileAndChatTests : BaseTest() {
    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Test
    fun changePasswordTest() {
        loginSteps.verifyLoginScreenIsDisplayed()
        loginSteps.login(USERA, USERA_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()

        profileSteps.navigateToProfileSection()
        profileSteps.changePassword(NEW_PASSWORD)

        loginSteps.verifyLoginScreenIsDisplayed()
        loginSteps.login(USERA, NEW_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()

        profileSteps.navigateToProfileSection()
        profileSteps.changePassword(USERA_PASSWORD)
        loginSteps.verifyLoginScreenIsDisplayed()
    }

    @Test
    fun changeLanguageTest() {
        loginSteps.verifyLoginScreenIsDisplayed()
        loginSteps.login(USERA, USERA_PASSWORD)
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
        loginSteps.login(USERA, USERA_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()

        val chatName = CHAT_NAME_PREFIX + System.currentTimeMillis()

        chatSteps.createChat(chatName)
        chatSteps.verifyCreatedChatDetailsAndClose(chatName)
        chatSteps.searchAndVerifyChatInList(chatName)
    }
}
