package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import com.example.foroom.Helper.imageChooserLoaded
import com.example.foroom.Helper.input
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilMatches
import com.example.foroom.data.Constants.CHAT_IMAGE_POSITION
import com.example.foroom.data.Constants.HOME_WAIT_SEC
import com.example.foroom.data.Constants.MAX_SWIPES
import com.example.foroom.data.Constants.SWIPE_DURATION_MS
import com.example.foroom.data.Constants.SWIPE_END_PERCENT
import com.example.foroom.data.Constants.SWIPE_START_PERCENT
import com.example.foroom.data.Constants.WAIT_SEC
import com.example.foroom.pages.ChatPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage
import org.junit.Assert.assertFalse

class ChatSteps(
    private val chatsPage: ChatsPage = ChatsPage(),
    private val createChatPage: CreateChatPage = CreateChatPage(),
    private val chatPage: ChatPage = ChatPage()
) {

    fun verifyHomeScreenDisplayed(): ChatSteps {
        waitUntil(HOME_WAIT_SEC) {
            onView(chatsPage.navBar).check(matches(isDisplayed()))
            onView(chatsPage.homeNavigationChats).check(matches(isDisplayed()))
            onView(chatsPage.homeNavigationCreateChat).check(matches(isDisplayed()))
            onView(chatsPage.homeNavigationProfile).check(matches(isDisplayed()))
        }
        return this
    }

    fun createChat(chatName: String): ChatSteps {
        onView(chatsPage.homeNavigationCreateChat).tap(WAIT_SEC)
        waitUntil {
            onView(createChatPage.chatNameInput).check(matches(isDisplayed()))
            onView(createChatPage.chatImageChooser).check(matches(isDisplayed()))
            onView(createChatPage.createChatButton).check(matches(isDisplayed()))
        }
        onView(createChatPage.chatNameInput).input(chatName)
        selectChatImage()
        onView(createChatPage.createChatButton).tap(WAIT_SEC)
        return this
    }

    private fun selectChatImage() {
        onView(createChatPage.chatImageChooser).waitUntilMatches(imageChooserLoaded())
        onView(createChatPage.chatImageItem(CHAT_IMAGE_POSITION)).tap(WAIT_SEC)
    }

    fun verifyCreatedChatDetailsAndClose(chatName: String): ChatSteps {
        verifyConversationIsOpen(chatName)
        closeChat()
        return this
    }

    fun searchAndVerifyChatInList(chatName: String): ChatSteps {
        searchChat(chatName)
        waitUntil {
            onView(chatsPage.chatTitle(chatName)).check(matches(isDisplayed()))
        }
        return this
    }

    fun openChatAndVerify(chatName: String): ChatSteps {
        searchChat(chatName)
        onView(chatsPage.openChatButton(chatName)).tap(WAIT_SEC)
        verifyConversationIsOpen(chatName)
        return this
    }

    private fun searchChat(chatName: String) {
        onView(chatsPage.searchChatInput).input(chatName)
    }

    fun verifyConversationIsOpen(chatName: String): ChatSteps {
        waitUntil {
            onView(chatPage.messageInput).check(matches(isDisplayed()))
            onView(chatPage.chatTitle(chatName)).check(matches(isDisplayed()))
        }
        return this
    }

    fun sendMessageAndVerify(message: String): ChatSteps {
        sendMessage(message)
        verifyMessageDisplayed(message)
        return this
    }

    private fun sendMessage(message: String) {
        onView(chatPage.messageEditText).input(message)
        onView(chatPage.sendButton).waitUntilMatches(isEnabled())
        onView(chatPage.sendButton).tap(WAIT_SEC)
    }

    fun closeAndReopenAndVerifyMessage(chatName: String, message: String): ChatSteps {
        closeChat()
        openChatAndVerify(chatName)
        verifyMessageDisplayed(message)
        return this
    }

    fun sendMessages(prefix: String, count: Int, runId: Long): ChatSteps {
        var last = ""
        for (number in 1..count) {
            last = "$prefix $number $runId"
            sendMessage(last)
        }
        verifyMessageDisplayed(last)
        return this
    }

    fun verifyMessageIsNotInVisibleArea(message: String): ChatSteps {
        assertFalse("'$message' should be older than the initially visible messages", isMessageDisplayed(message))
        return this
    }

    fun swipeToMessage(message: String, maxSwipes: Int = MAX_SWIPES): ChatSteps {
        var swipes = 0
        while (!isMessageDisplayed(message) && swipes < maxSwipes) {
            swipeToOlderMessages()
            swipes++
        }
        verifyMessageDisplayed(message)
        return this
    }

    fun verifyMessageFromSender(message: String, sender: String): ChatSteps {
        waitUntil {
            onView(chatPage.messageFrom(message, sender)).check(matches(isDisplayed()))
        }
        return this
    }

    fun closeChat(): ChatSteps {
        onView(chatPage.closeButton).tap(WAIT_SEC)
        verifyHomeScreenDisplayed()
        return this
    }

    private fun verifyMessageDisplayed(message: String) {
        waitUntil {
            onView(chatPage.message(message)).check(matches(isDisplayed()))
        }
    }

    private fun isMessageDisplayed(message: String): Boolean =
        try {
            onView(chatPage.message(message)).check(matches(isDisplayed()))
            true
        } catch (e: NoMatchingViewException) {
            false
        } catch (e: AssertionError) {
            false
        }

    private fun swipeToOlderMessages() {
        var top = 0
        var height = 0
        onView(chatPage.messagesList).check { view, noViewFoundException ->
            if (view == null) throw noViewFoundException
            val location = IntArray(2)
            view.getLocationOnScreen(location)
            top = location[1]
            height = view.height
        }
        swiper(
            start = top + height * SWIPE_START_PERCENT / 100,
            end = top + height * SWIPE_END_PERCENT / 100,
            delay = SWIPE_DURATION_MS
        )
    }
}
