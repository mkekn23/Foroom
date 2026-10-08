package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import com.example.design_system.components.chat.ForoomChatCardView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ChatsPage {
    val navBar: Matcher<View> = withId(R.id.navBar)
    val homeContainer: Matcher<View> = withId(R.id.homeContainer)
    val homeNavigationChats: Matcher<View> = withId(R.id.homeNavigationChats)
    val homeNavigationProfile: Matcher<View> = withId(R.id.homeNavigationProfile)
    val homeNavigationCreateChat: Matcher<View> = withId(R.id.homeNavigationCreateChat)
    val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)

    val searchChatInput: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(withId(R.id.searchChatInput)))

    fun chatTitle(chatName: String): Matcher<View> =
        allOf(withId(DesignR.id.chatTitleTextView), withText(chatName), isDescendantOfA(chatsRecyclerView))

    fun openChatButton(chatName: String): Matcher<View> =
        allOf(
            withId(DesignR.id.sendMessageButton),
            isDescendantOfA(
                allOf(
                    isAssignableFrom(ForoomChatCardView::class.java),
                    hasDescendant(allOf(withId(DesignR.id.chatTitleTextView), withText(chatName)))
                )
            )
        )
}
