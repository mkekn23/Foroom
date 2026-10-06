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
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ChatsPage {
    private val navBar = withId(R.id.navBar)
    private val homeNavigationChats = withId(R.id.homeNavigationChats)
    private val homeNavigationProfile = withId(R.id.homeNavigationProfile)
    private val homeNavigationCreateChat = withId(R.id.homeNavigationCreateChat)
    private val chatsRecyclerView = withId(R.id.chatsRecyclerView)

    // The same child ids are reused inside every Input component, so the field is scoped to its parent.
    private val searchChatInput: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(withId(R.id.searchChatInput)))

    // Login runs asynchronously, so the home screen is awaited.
    fun checkHomeScreenIsDisplayed(): ChatsPage {
        onView(navBar).waitUntilVisible(HOME_WAIT_SEC).check(matches(isDisplayed()))
        onView(homeNavigationChats).check(matches(isDisplayed()))
        onView(homeNavigationCreateChat).check(matches(isDisplayed()))
        onView(homeNavigationProfile).check(matches(isDisplayed()))
        return this
    }

    fun navigateToProfile() {
        onView(homeNavigationProfile).tap(WAIT_SEC)
    }

    fun navigateToCreateChat() {
        onView(homeNavigationCreateChat).tap(WAIT_SEC)
    }

    fun navigateToChats() {
        onView(homeNavigationChats).tap(WAIT_SEC)
    }

    fun searchChat(name: String): ChatsPage {
        onView(searchChatInput).input(name)
        return this
    }

    fun verifyChatExists(chatName: String) {
        onView(
            allOf(
                withId(DesignR.id.chatTitleTextView),
                withText(chatName),
                isDescendantOfA(chatsRecyclerView)
            )
        ).waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
    }

    private companion object {
        const val WAIT_SEC = 10L
        const val HOME_WAIT_SEC = 15L
    }
}
