package com.example.foroom.steps

import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {
    private val chatsPage = ChatsPage()
    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()

    fun navigateToProfileSection(): ProfileSteps {
        chatsPage.navigateToProfile()
        profilePage.verifyPageLoaded()
        return this
    }

    fun changePassword(newPassword: String): ProfileSteps {
        profilePage.clickChangePassword()
        changePasswordPage.verifyPageLoaded()
            .enterNewPassword(newPassword)
            .enterRepeatPassword(newPassword)
            .clickSubmit()
        return this
    }

    fun changeLanguageToGeorgian(): ProfileSteps {
        profilePage.clickChangeLanguage()
        changeLanguagePage.verifyPageLoaded().selectGeorgian()
        return this
    }

    fun changeLanguageToEnglish(): ProfileSteps {
        profilePage.clickChangeLanguage()
        changeLanguagePage.verifyPageLoaded().selectEnglish()
        return this
    }

    fun verifyProfileIsGeorgian(): ProfileSteps {
        profilePage.verifyChangeLanguageLabel(GEORGIAN_CHANGE_LANGUAGE)
            .verifySignOutLabel(GEORGIAN_SIGN_OUT)
        return this
    }

    fun verifyProfileIsEnglish(): ProfileSteps {
        profilePage.verifyChangeLanguageLabel(ENGLISH_CHANGE_LANGUAGE)
            .verifySignOutLabel(ENGLISH_SIGN_OUT)
        return this
    }

    private companion object {
        const val GEORGIAN_CHANGE_LANGUAGE = "ენის შეცვლა"
        const val GEORGIAN_SIGN_OUT = "გამოსვლა"
        const val ENGLISH_CHANGE_LANGUAGE = "Change Language"
        const val ENGLISH_SIGN_OUT = "Sign Out"
    }
}
