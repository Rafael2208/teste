package com.msf.githubissues

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.msf.githubissues.util.EspressoIdlingResource
import com.msf.githubissues.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val issueNumber = "Issue #2410"
private const val issueCreatedAt = "13/06/19 13:39"
private const val issueTitle = "KT-31127: register only generated Java sources to AGP"

@RunWith(AndroidJUnit4::class)
class ListIssueFragmentTest {

    @Rule
    @JvmField
    val listIssueFragmentTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private lateinit var mIdlingResource: IdlingResource

    @Before
    fun registerIdleResource(){
        mIdlingResource = EspressoIdlingResource.getIdlingResource()
        IdlingRegistry.getInstance().register(mIdlingResource)
    }

    @Test
    fun testListShowingAndClickOnItem(){
        onView(withId(R.id.progress_loading)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
        val recyclerView = withId(R.id.recyclerViewIssues)
        onView(recyclerView).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(recyclerView).perform(actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(issueNumber)),click()))

        onView(withId(R.id.txt_label_title)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.txt_title)).check(matches(withText(issueTitle)))
        onView(withId(R.id.txt_data)).check(matches(withText(issueCreatedAt)))
        onView(withId(R.id.txt_link_ext)).check(matches(withText("Visualizar")))
    }


}