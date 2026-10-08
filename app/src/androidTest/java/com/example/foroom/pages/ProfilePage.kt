package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ProfilePage {
    val changePasswordItem: Matcher<View> = withId(R.id.changePasswordItem)
    val changeLanguageItem: Matcher<View> = withId(R.id.changeLanguageItem)
    val signOutItem: Matcher<View> = withId(R.id.signOutItem)

    fun changeLanguageLabel(label: String): Matcher<View> =
        allOf(withText(label), isDescendantOfA(changeLanguageItem))

    fun signOutLabel(label: String): Matcher<View> =
        allOf(withText(label), isDescendantOfA(signOutItem))
}
