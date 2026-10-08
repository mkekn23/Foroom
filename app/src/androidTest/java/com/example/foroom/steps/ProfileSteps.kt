package com.example.foroom.steps

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntil
import com.example.foroom.data.Constants.ENGLISH_CHANGE_LANGUAGE
import com.example.foroom.data.Constants.ENGLISH_SIGN_OUT
import com.example.foroom.data.Constants.GEORGIAN_CHANGE_LANGUAGE
import com.example.foroom.data.Constants.GEORGIAN_SIGN_OUT
import com.example.foroom.data.Constants.WAIT_SEC
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ProfilePage
import org.hamcrest.Matcher

class ProfileSteps(
    private val chatsPage: ChatsPage = ChatsPage(),
    private val profilePage: ProfilePage = ProfilePage(),
    private val changePasswordPage: ChangePasswordPage = ChangePasswordPage(),
    private val changeLanguagePage: ChangeLanguagePage = ChangeLanguagePage()
) {

    fun navigateToProfileSection(): ProfileSteps {
        onView(chatsPage.homeNavigationProfile).tap(WAIT_SEC)
        waitUntil {
            onView(profilePage.changePasswordItem).check(matches(isDisplayed()))
            onView(profilePage.changeLanguageItem).check(matches(isDisplayed()))
            onView(profilePage.signOutItem).check(matches(isDisplayed()))
        }
        return this
    }

    fun signOut(): ProfileSteps {
        navigateToProfileSection()
        onView(profilePage.signOutItem).tap(WAIT_SEC)
        return this
    }

    fun changePassword(newPassword: String): ProfileSteps {
        onView(profilePage.changePasswordItem).tap(WAIT_SEC)
        waitUntil {
            onView(changePasswordPage.passwordInput).check(matches(isDisplayed()))
            onView(changePasswordPage.repeatPasswordInput).check(matches(isDisplayed()))
            onView(changePasswordPage.actionButton).check(matches(isDisplayed()))
        }
        onView(changePasswordPage.passwordInput).input(newPassword)
        onView(changePasswordPage.repeatPasswordInput).input(newPassword)
        onView(changePasswordPage.actionButton).tap(WAIT_SEC)
        return this
    }

    fun changeLanguageToGeorgian(): ProfileSteps = chooseLanguage(changeLanguagePage.languageButtonGeo)

    fun changeLanguageToEnglish(): ProfileSteps = chooseLanguage(changeLanguagePage.languageButtonEng)

    private fun chooseLanguage(languageButton: Matcher<View>): ProfileSteps {
        onView(profilePage.changeLanguageItem).tap(WAIT_SEC)
        waitUntil {
            onView(changeLanguagePage.languageButtonGeo).check(matches(isDisplayed()))
            onView(changeLanguagePage.languageButtonEng).check(matches(isDisplayed()))
        }
        onView(languageButton).tap(WAIT_SEC)
        return this
    }

    fun verifyProfileIsGeorgian(): ProfileSteps = verifyLabels(GEORGIAN_CHANGE_LANGUAGE, GEORGIAN_SIGN_OUT)

    fun verifyProfileIsEnglish(): ProfileSteps = verifyLabels(ENGLISH_CHANGE_LANGUAGE, ENGLISH_SIGN_OUT)

    private fun verifyLabels(changeLanguageLabel: String, signOutLabel: String): ProfileSteps {
        waitUntil {
            onView(profilePage.changeLanguageLabel(changeLanguageLabel)).check(matches(isDisplayed()))
            onView(profilePage.signOutLabel(signOutLabel)).check(matches(isDisplayed()))
        }
        return this
    }
}
