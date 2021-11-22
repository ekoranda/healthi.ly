package com.cs506.healthily.view.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.cs506.healthily.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class StepCountFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SignInActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACTIVITY_RECOGNITION"
        )

    @Test
    fun stepCountFragmentTest() {
//        val materialButton = onView(
//            allOf(
//                withId(R.id.Signin), withText("Google Sign In"),
//                childAtPosition(
//                    allOf(
//                        withId(R.id.linearLayout),
//                        childAtPosition(
//                            withId(android.R.id.content),
//                            0
//                        )
//                    ),
//                    0
//                ),
//                isDisplayed()
//            )
//        )
//        materialButton.perform(click())

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

        val textView = onView(
            allOf(
                withId(R.id.progress_text),
                withParent(
                    allOf(
                        withId(R.id.progress_layout),
                        withParent(withId(R.id.main_activity_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
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
