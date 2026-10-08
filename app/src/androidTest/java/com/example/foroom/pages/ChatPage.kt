package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import com.example.design_system.components.message.ForoomMessageView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ChatPage {
    val messageInput: Matcher<View> = withId(R.id.messageInput)
    val messageEditText: Matcher<View> = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(messageInput))
    val sendButton: Matcher<View> = allOf(withId(R.id.sendMessageButton), isDescendantOfA(messageInput))
    val closeButton: Matcher<View> = withId(R.id.closeButton)
    val chatHeader: Matcher<View> = withId(R.id.chatHeaderView)
    val messagesList: Matcher<View> = withId(R.id.messagesRecyclerView)

    fun chatTitle(title: String): Matcher<View> =
        allOf(withId(DesignR.id.chatNameTextView), isDescendantOfA(chatHeader), withText(title))

    fun message(message: String): Matcher<View> =
        allOf(withId(DesignR.id.messageTextView), isDescendantOfA(messagesList), withText(message))

    fun messageFrom(message: String, sender: String): Matcher<View> =
        allOf(
            withId(DesignR.id.messageTextView),
            withText(message),
            isDescendantOfA(
                allOf(
                    isAssignableFrom(ForoomMessageView::class.java),
                    isDescendantOfA(messagesList),
                    hasDescendant(allOf(withId(DesignR.id.userNameTextView), withText(sender)))
                )
            )
        )
}
