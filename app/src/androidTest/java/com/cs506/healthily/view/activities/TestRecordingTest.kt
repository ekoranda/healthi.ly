package com.cs506.healthily.view.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.cs506.healthily.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TestRecordingTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION"
        )

    @Test
    fun testRecordingTest() {
        val chip = onView(
            allOf(
                withText("Gyms"),
                childAtPosition(
                    allOf(
                        withId(R.id.placesGroup),
                        childAtPosition(
                            withId(R.id.placesList),
                            0
                        )
                    ),
                    0
                )
            )
        )
        chip.perform(scrollTo(), click())

        val appCompatImageView = onView(
            allOf(
                withId(R.id.imgSaveLocation),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val chip2 = onView(
            allOf(
                withId(R.id.btnChipWalking), withText("Walking"),
                childAtPosition(
                    allOf(
                        withId(R.id.travelMode),
                        childAtPosition(
                            withClassName(`is`("android.widget.HorizontalScrollView")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        chip2.perform(scrollTo(), click())

        pressBack()

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.stepsFragment), withContentDescription("Step Count"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigatin_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
