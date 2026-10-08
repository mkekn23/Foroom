package com.example.foroom.tests

import com.example.foroom.data.Constants.DRINK_MESSAGE
import com.example.foroom.data.Constants.FILLER_MESSAGE
import com.example.foroom.data.Constants.FILLER_MESSAGE_COUNT
import com.example.foroom.data.Constants.FULL_NAME_CHAT
import com.example.foroom.data.Constants.GREETING
import com.example.foroom.data.Constants.JOHNWEEK_CHAT
import com.example.foroom.data.Constants.QUESTION
import com.example.foroom.data.Constants.REPLY
import com.example.foroom.data.Constants.SHAREDCHAT_NAME
import com.example.foroom.data.Constants.USERA
import com.example.foroom.data.Constants.USERA_PASSWORD
import com.example.foroom.data.Constants.USERB
import com.example.foroom.data.Constants.USERB_PASSWORD
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Test

class UsingExistingChatTest : BaseTest() {
    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Test
    fun userASendsMessageInJohnWeekChat_messageIsShownAndKeptAfterReopen() {
        val message = DRINK_MESSAGE + " " + System.currentTimeMillis()

        loginSteps.verifyLoginScreenIsDisplayed()
            .login(USERA, USERA_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()
            .searchAndVerifyChatInList(JOHNWEEK_CHAT)
            .openChatAndVerify(JOHNWEEK_CHAT)
            .sendMessageAndVerify(message)
            .closeAndReopenAndVerifyMessage(JOHNWEEK_CHAT, message)
    }

    @Test
    fun userASendsQuestionInFullNameChat_questionIsShownInConversation() {
        val question = QUESTION + " " + System.currentTimeMillis()

        loginSteps.verifyLoginScreenIsDisplayed()
            .login(USERA, USERA_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()
            .searchAndVerifyChatInList(FULL_NAME_CHAT)
            .openChatAndVerify(FULL_NAME_CHAT)
            .sendMessageAndVerify(question)
    }

    @Test
    fun userBRepliesToUserAGreetingInSharedChat_userAThenSeesTheReply() {
        val runId = System.currentTimeMillis()
        val greeting = "$GREETING $runId"
        val reply = "$REPLY $runId"

        loginSteps.verifyLoginScreenIsDisplayed()
            .login(USERA, USERA_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()
            .openChatAndVerify(SHAREDCHAT_NAME)
            .sendMessageAndVerify(greeting)
            .sendMessages(FILLER_MESSAGE, FILLER_MESSAGE_COUNT, runId)
            .closeChat()

        profileSteps.signOut()
        loginSteps.verifyLoginScreenIsDisplayed()
            .login(USERB, USERB_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()
            .openChatAndVerify(SHAREDCHAT_NAME)
            .verifyMessageIsNotInVisibleArea(greeting)
            .swipeToMessage(greeting)
            .verifyMessageFromSender(greeting, USERA)
            .sendMessageAndVerify(reply)
            .closeChat()

        profileSteps.signOut()
        loginSteps.verifyLoginScreenIsDisplayed()
            .login(USERA, USERA_PASSWORD)
        chatSteps.verifyHomeScreenDisplayed()
            .openChatAndVerify(SHAREDCHAT_NAME)
            .verifyMessageFromSender(reply, USERB)
    }
}
