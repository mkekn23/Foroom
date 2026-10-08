package com.example.foroom.Helper

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.test.espresso.matcher.BoundedMatcher
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.shared.model.Image
import org.hamcrest.Description
import org.hamcrest.Matcher

fun imageChooserLoaded(): Matcher<View> =
    object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image chooser with loaded images")
        }

        override fun matchesSafely(list: ImageChooserListView) =
            list.isChoosingEnabled &&
                list.images.isNotEmpty() &&
                list.images.none { it.id == Image.BLANK_IMAGE_ID }
    }

fun imageChooserItemAt(position: Int): Matcher<View> =
    object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image chooser item at position $position")
        }

        override fun matchesSafely(item: ImageChooserItemView): Boolean {
            val list = item.parent?.parent as? ImageChooserListView ?: return false
            val items = list.children.flatMap { (it as ViewGroup).children }
            return items.filterIsInstance<ImageChooserItemView>().toList().indexOf(item) == position
        }
    }
