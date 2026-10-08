package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ChangePasswordPage {
    private fun inputChild(parentId: Int): Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(withId(parentId)))

    val passwordInput: Matcher<View> = inputChild(R.id.passwordInput)
    val repeatPasswordInput: Matcher<View> = inputChild(R.id.repeatPasswordInput)
    val actionButton: Matcher<View> = withId(DesignR.id.actionButton)
}
