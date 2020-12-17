package com.justclean.laundry

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.codingwithmitch.espressouitestexamples.util.EspressoIdlingResourceRule
import com.justclean.zuhaib.laundry.R
import com.justclean.zuhaib.laundry.adapter.CommentViewHolder
import com.justclean.zuhaib.laundry.utils.CommentListTest
import com.justclean.zuhaib.laundry.views.CommentListActivity

import junit.framework.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.runners.MethodSorters

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class PostCommentListActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CommentListActivity::class.java)

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()
    val LIST_ITEM_IN_TEST = 0


    val MostViewArticle = CommentListTest.resultList[LIST_ITEM_IN_TEST]
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.justclean.zuhaib.laundry", appContext.packageName)
    }


    @Test
    fun mostViewArtilceListAppLaunch() {
        Espresso.onView(withId(R.id.commentList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test fun listpostCommentList() {

        onView(withId(R.id.commentList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CommentViewHolder>(
                    LIST_ITEM_IN_TEST,
                    ViewActions.click()
                )
            )

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.titleTxtView)).check(matches(withText(MostViewArticle.title)))

        Espresso.pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.commentList)).check(matches(isDisplayed()))


    }


    @Test fun listfavCommentList() {

        onView(withId(R.id.favRc))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition<CommentViewHolder>(
                                LIST_ITEM_IN_TEST,
                                ViewActions.click()
                        )
                )

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.titleTxtView)).check(matches(withText(MostViewArticle.title)))

        Espresso.pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.favRc)).check(matches(isDisplayed()))


    }



}