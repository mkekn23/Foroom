package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.example.foroom.data.Constants.POLL_MS
import com.example.foroom.data.Constants.WAIT_SEC
import org.hamcrest.Matcher

fun waitUntil(timeoutSec: Long = WAIT_SEC, block: () -> Unit) {
    val deadline = System.currentTimeMillis() + timeoutSec * 1000
    while (true) {
        try {
            block()
            return
        } catch (e: Exception) {
            if (System.currentTimeMillis() > deadline) throw e
        } catch (e: AssertionError) {
            if (System.currentTimeMillis() > deadline) throw e
        }
        Thread.sleep(POLL_MS)
    }
}

fun ViewInteraction.waitUntilMatches(matcher: Matcher<View>, timeoutSec: Long = WAIT_SEC): ViewInteraction {
    waitUntil(timeoutSec) { check(matches(matcher)) }
    return this
}
