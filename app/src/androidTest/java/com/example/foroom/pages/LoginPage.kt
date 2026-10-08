package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class LoginPage {
    private fun inputChild(parentId: Int, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(withId(parentId)))

    val usernameInput: Matcher<View> = inputChild(R.id.userNameInput, DesignR.id.inputEditText)
    val passwordInput: Matcher<View> = inputChild(R.id.passwordInput, DesignR.id.inputEditText)
    val usernameError: Matcher<View> = inputChild(R.id.userNameInput, DesignR.id.descriptionTextView)
    val passwordError: Matcher<View> = inputChild(R.id.passwordInput, DesignR.id.descriptionTextView)
    val logInButton: Matcher<View> = withId(R.id.logInButton)
    val signUpButton: Matcher<View> = withId(R.id.signUpButton)
}
