package com.example.foroom.pages

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.core.view.children
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R as AppR
import com.example.design_system.R as DesignR
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.shared.model.Image
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class RegistrationPage {

    private fun inputChild(parentId: Int, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(withId(parentId)))

    private val usernameInput = inputChild(AppR.id.userNameInput, DesignR.id.inputEditText)
    private val passwordInput = inputChild(AppR.id.passwordInput, DesignR.id.inputEditText)
    private val repeatPasswordInput = inputChild(AppR.id.repeatPasswordInput, DesignR.id.inputEditText)
    private val avatarList = withId(AppR.id.listView)
    private val signUpButton = withId(AppR.id.signUpButton)

    // Stable home-screen elements, shown once registration has succeeded.
    private val homeContainer = withId(AppR.id.homeContainer)
    private val navBar = withId(AppR.id.navBar)

    fun enterUsername(username: String) {
        onView(usernameInput).input(username)
    }

    fun enterPassword(password: String) {
        onView(passwordInput).input(password)
    }

    fun enterRepeatPassword(password: String) {
        onView(repeatPasswordInput).input(password)
    }


    fun selectAvatar(position: Int) {
        onView(avatarList).waitUntilVisible(WAIT_SEC)
        waitUntil(avatarsLoaded())
        onView(avatarItemAt(position)).tap(WAIT_SEC)
    }

    fun clickSignUp() {
        onView(signUpButton).tap(WAIT_SEC)
    }

    fun checkIsDisplayed() {
        onView(signUpButton).waitUntilVisible(WAIT_SEC).check(matches(isDisplayed()))
        onView(avatarList).check(matches(isDisplayed()))
        onView(repeatPasswordInput).check(matches(isDisplayed()))
    }

    fun checkHomeScreenIsDisplayed() {
        onView(navBar).waitUntilVisible(HOME_WAIT_SEC).check(matches(isDisplayed()))
        onView(homeContainer).check(matches(isDisplayed()))
    }

    private fun waitUntil(matcher: Matcher<View>) {
        val deadline = System.currentTimeMillis() + WAIT_SEC * 1000
        while (true) {
            try {
                onView(avatarList).check(matches(matcher))
                return
            } catch (e: AssertionError) {
                if (System.currentTimeMillis() > deadline) throw e
                Thread.sleep(POLL_MS)
            }
        }
    }

    private fun avatarsLoaded(): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar list with loaded avatars")
            }

            override fun matchesSafely(list: ImageChooserListView) =
                list.isChoosingEnabled &&
                    list.images.isNotEmpty() &&
                    list.images.none { it.id == Image.BLANK_IMAGE_ID }
        }


    private fun avatarItemAt(position: Int): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar item at position $position")
            }

            override fun matchesSafely(item: ImageChooserItemView): Boolean {
                val list = item.parent?.parent as? ImageChooserListView ?: return false
                val items = list.children.flatMap { (it as ViewGroup).children }
                return items.filterIsInstance<ImageChooserItemView>().toList().indexOf(item) == position
            }
        }

    private companion object {
        const val WAIT_SEC = 10L
        const val HOME_WAIT_SEC = 15L
        const val POLL_MS = 100L
    }
}
