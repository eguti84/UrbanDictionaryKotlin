package com.batch.urbandictionarykotlin

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.batch.urbandictionarykotlin.view.masterviewfragment.MasterViewFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MasterViewTest {

    @Test fun testEventFragment() {
        val scenario = launchFragmentInContainer<MasterViewFragment>()
        //testing editText
        onView(withId(R.id.search_bar))
            .perform(ViewActions.typeText("Test"))
        //testing searchButton
        onView(withId(R.id.search_button))
            .perform(click())
        //testing sortButton
        onView(withId(R.id.sort_button))
            .perform(click())
    }

}