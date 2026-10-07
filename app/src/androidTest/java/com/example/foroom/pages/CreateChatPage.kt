package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import com.example.foroom.Helper.imageChooserItemAt
import com.example.foroom.Helper.imageChooserLoaded
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilMatches
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class CreateChatPage {

    private val chatNameInput: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(withId(R.id.chatNameInput)))
    private val chatImageChooser = withId(R.id.chatImageChooser)
    private val createChatButton = withId(R.id.createChatButton)
    private val closeButton = withId(R.id.closeButton)

    // Elements of the chat screen that opens once the chat has been created.
    private val messageInput = withId(R.id.messageInput)

    fun enterChatName(name: String): CreateChatPage {
        onView(chatNameInput).input(name)
        return this
    }

    fun selectChatImage(position: Int = DEFAULT_IMAGE_POSITION): CreateChatPage {
        onView(chatImageChooser).waitUntilVisible(WAIT_SEC).waitUntilMatches(imageChooserLoaded(), WAIT_SEC)
        onView(imageChooserItemAt(position)).tap(WAIT_SEC)
        return this
    }

    fun clickCreateChat() {
        onView(createChatButton).tap(WAIT_SEC)
    }

    fun clickClose() {
        onView(closeButton).tap(WAIT_SEC)
    }

    fun verifyPageLoaded(): CreateChatPage {
        onView(createChatButton).waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        return this
    }


    fun verifyCreatedChatScreenDisplayed(chatName: String): CreateChatPage {
        onView(messageInput).waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        onView(
            allOf(
                withId(DesignR.id.chatNameTextView),
                isDescendantOfA(withId(R.id.chatHeaderView)),
                withText(chatName)
            )
        ).check(matches(isDisplayed()))
        return this
    }

    private companion object {
        const val WAIT_SEC = 10L
        const val DEFAULT_IMAGE_POSITION = 1
    }
}
