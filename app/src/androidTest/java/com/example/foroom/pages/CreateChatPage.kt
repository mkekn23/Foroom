package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import com.example.foroom.Helper.imageChooserItemAt
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class CreateChatPage {
    val chatNameInput: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(withId(R.id.chatNameInput)))
    val chatImageChooser: Matcher<View> = withId(R.id.chatImageChooser)
    val createChatButton: Matcher<View> = withId(R.id.createChatButton)
    val closeButton: Matcher<View> = withId(R.id.closeButton)

    fun chatImageItem(position: Int): Matcher<View> = imageChooserItemAt(position)
}
