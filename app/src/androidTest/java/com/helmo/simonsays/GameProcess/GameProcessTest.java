package com.helmo.simonsays.GameProcess;

import static androidx.test.espresso.Espresso.onIdle;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.CountDownTimer;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.helmo.simonsays.MainActivity;
import com.helmo.simonsays.R;
import com.helmo.simonsays.utils.RecyclerViewItemCountAssertion;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameProcessTest {
    public static final String DIFFICULTY = "DIFFICILE";
    public static final String PSEUDO = "Pseudo";
    public static final int POINT = 0;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @BeforeClass
    public static void beforeClass() {
        ApplicationProvider.getApplicationContext().deleteDatabase("score_database");
    }

    @Test
    public void testClickGameHardWaitThreeSecondAndTestResult() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.hardButton)).perform(click());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.pseudo_input)).perform(typeText("Pseudo"),closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.scoreButton)).perform(click());
        onView(withId(R.id.classement)).check(new RecyclerViewItemCountAssertion(1));
        onView(withId(R.id.difficulty_score_item)).check(matches(withText(DIFFICULTY+" | "+POINT)));
        onView(withId(R.id.pseudo_item)).check(matches(withText(PSEUDO)));
    }
    @Test
    public void testClickGameHardWaitThreeSecondAndTestResultOnEmptyPseudo() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.hardButton)).perform(click());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        onView(withId(R.id.pseudo_input)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.pseudo_input)).perform(typeText("Pseudo"),closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.scoreButton)).perform(click());
        onView(withId(R.id.classement)).check(new RecyclerViewItemCountAssertion(2));
    }
}
