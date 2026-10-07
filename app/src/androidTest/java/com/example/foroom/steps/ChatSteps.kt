package com.example.foroom.steps

import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {
    private val chatsPage = ChatsPage()
    private val createChatPage = CreateChatPage()

    fun verifyHomeScreenDisplayed() {
        chatsPage.checkHomeScreenIsDisplayed()
    }

    fun createChat(chatName: String): ChatSteps {
        chatsPage.navigateToCreateChat()
        createChatPage.verifyPageLoaded()
            .enterChatName(chatName)
            .selectChatImage()
            .clickCreateChat()
        return this
    }

    fun verifyCreatedChatDetailsAndClose(chatName: String): ChatSteps {
        createChatPage.verifyCreatedChatScreenDisplayed(chatName)
            .clickClose()
        return this
    }

    fun searchAndVerifyChatInList(chatName: String): ChatSteps {
        chatsPage.searchChat(chatName)
        chatsPage.verifyChatExists(chatName)
        return this
    }
}
