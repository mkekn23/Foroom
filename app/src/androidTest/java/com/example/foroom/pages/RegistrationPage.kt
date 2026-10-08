package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import com.example.foroom.Helper.imageChooserItemAt
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class RegistrationPage {
    private fun inputChild(parentId: Int): Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(withId(parentId)))

    val usernameInput: Matcher<View> = inputChild(R.id.userNameInput)
    val passwordInput: Matcher<View> = inputChild(R.id.passwordInput)
    val repeatPasswordInput: Matcher<View> = inputChild(R.id.repeatPasswordInput)
    val avatarList: Matcher<View> = withId(R.id.listView)
    val signUpButton: Matcher<View> = withId(R.id.signUpButton)

    fun avatarItem(position: Int): Matcher<View> = imageChooserItemAt(position)
}
